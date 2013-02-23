/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reportes;

import dao.HabitacionDao;
import entidad.Habitacion;
import entidad.Reserva;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author MIGUEL
 */
public class ReporteReservasPerdidas {

    private String fileName;
    private ArrayList<String[]> reservasPerdidas;

    public ReporteReservasPerdidas(){
        SimpleDateFormat f = new SimpleDateFormat("'_'dd'_'MMM'_'yyyy", new Locale("ES"));
        Calendar c = new GregorianCalendar();
        c.setTime(new GregorianCalendar().getTime());
        c.add(Calendar.DATE, -1);

        fileName = f.format(c.getTime());

        reservasPerdidas = new ArrayList<String[]>();

        extraerReservas();
    }

    public void GenerarReporte(){
        if (reservasPerdidas.size() != 1) {
            generarXML();
            agregarDatos();
            generarPDF();
        }
        else{
            JOptionPane.showMessageDialog(null, "No hay reservar perdidas a la fecha.", "Advertencia", JOptionPane.WARNING_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/m/remove.png")));
        }
    }

    private void extraerReservas(){
        //TODO Revisar
        Calendar fechaActual = new GregorianCalendar();
        fechaActual.setTime(new GregorianCalendar().getTime());
        Calendar fechaReserva = new GregorianCalendar();
        List<Habitacion> habitaciones= new HabitacionDao().obtenerHabitaciones();
        
        
        //for (Habitacion hab : Sistema.getInstance().getHabitacion()) {
        for (Habitacion hab : habitaciones) {
            for (Reserva r : hab.getReserva()) {
                fechaReserva.setTime(r.getFechaInicial());
                String[] datos = new String[6];

                long time = (fechaActual.getTimeInMillis() - fechaReserva.getTimeInMillis()) / (24 * 60 * 60 * 1000);

                if (time > 0 && hab.getCodigoReservaActiva() != r.getCodigoReserva()) {

                    SimpleDateFormat f = new SimpleDateFormat("dd'/'MM'/'yy");
                    datos[0] = f.format(r.getFechaInicial());
                    datos[1] = f.format(r.getFechaFinal());
                    datos[2] = Integer.toString(hab.getIdHabitacion());
                    datos[3] = r.getHuesped().getNombre() + " " + r.getHuesped().getApellido();
                    datos[4] = Long.toString(r.getHuesped().getNumeroContacto());
                    datos[5] = Long.toString(r.getCostoReserva());

                    reservasPerdidas.add(datos);
                }
            }
        }
        String[] datos = new String[6];
        for (int i = 0; i < 6; i++){
            datos[i] = " ";
        }

        datos[3] = "**FIN REPORTE**";

        reservasPerdidas.add(datos);
    }

    private void generarXML(){
        try{
            File archivo2 = new File("ReservasPerdidas");
            if(!archivo2.isDirectory()){
                archivo2.mkdir();
            }
            File archivo = new File("ReservasPerdidas\\Reporte"+fileName+".xml");
            if(!archivo.isFile()){
                archivo.createNewFile();
            }
        }catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        catch(java.io.IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void agregarDatos(){
        String[] nodos = {"Documento", //0
                            "Reserva", //1
                            "FechaInicial", //2
                            "FechaFinal", //3
                            "Habitacion", //4
                            "Huesped", //5
                            "Telefono", //6
                            "Recargo"}; //7

        try{
            File f = new File("ReservasPerdidas\\Reporte"+fileName+".xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement(nodos[0]);
            boolean flag = true;

            for(String[] s: reservasPerdidas){
                Element child = document.createElement(nodos[1]);

                for(int i = 0; i < s.length; i++){
                    Element child2 = document.createElement(nodos[i + 2]);

                    child2.setTextContent(s[i]);

                    if(i == 0){
                        child.appendChild(child2);
                    }
                    else{
                        child.insertBefore(child2, child.getLastChild().getNextSibling());
                    }
                }

                if(flag){
                    root.appendChild(child);
                    flag = false;
                }
                else{
                    root.insertBefore(child, root.getLastChild().getNextSibling());
                }
            }

            document.appendChild(root);

            TransformerFactory t = TransformerFactory.newInstance();
            Transformer tr = t.newTransformer();
            FileOutputStream out = new FileOutputStream(f);
            Result res = new StreamResult(out);
            Source sou = new DOMSource(document);
            tr.setOutputProperty(OutputKeys.STANDALONE, "yes");
            tr.transform(sou, res);
        }
        catch (TransformerException ex) {
            System.out.println(ex.getMessage());
        }
        catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void generarPDF(){
        try{
            File f = new File("DisenioFactura\\reportReservasPerdidas.jasper");

            JasperReport report = (JasperReport) JRLoader.loadObject(f);
            JasperPrint print =
            JasperFillManager.fillReport(report, null, new JRXmlDataSource(new File("ReservasPerdidas\\Reporte"+fileName+".xml"), "Documento/Reserva"));
            JRExporter export = new JRPdfExporter();
            export.setParameter(JRExporterParameter.JASPER_PRINT, print);
            File destino = new File("ReservasPerdidas\\Reporte"+fileName+".pdf");
            export.setParameter(JRExporterParameter.OUTPUT_FILE, destino);
            export.exportReport();
            System.out.println("Reservas Perdidas: " + destino.getAbsoluteFile());
            Desktop.getDesktop().open(destino);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
