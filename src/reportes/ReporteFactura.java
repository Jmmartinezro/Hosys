/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reportes;

import control.AdministrarReserva;
import control.BusquedaDeHabitaciones;
import control.IOHuespedes;
import utilidades.NumeroALetra;
import dao.RecepcionistaDao;
import entidad.Habitacion;
import entidad.Reserva;
import entidad.Servicio;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
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
 * @author Martinez Romero
 */
public class ReporteFactura {
    private Object[] objetosFactura;
    private long fileName;
    private BusquedaDeHabitaciones control = new BusquedaDeHabitaciones();

    public ReporteFactura(Habitacion habitacion){
        objetosFactura = new Object[11];

        extraerDatos(habitacion);
    }

    public void GenerarFactura(){
        generarXML();
        agregarDatos();
        generarPDF();
    }

    private void extraerDatos(Habitacion hab){
        String[] huesped = new String[7];
        Reserva reservaActiva = new AdministrarReserva().ConsultarReserva(hab.getCodigoReservaActiva(), true);
        fileName = reservaActiva.getHuesped().getDNI();
        objetosFactura[0] = new RecepcionistaDao().recepcionistaActivo().getUsuario();
        objetosFactura[1] = 1000000 + (reservaActiva.getHuesped().getFactura().getIdFactura());
        objetosFactura[4] = reservaActiva.getHuesped().getFactura().getValorNeto();
        objetosFactura[5] = reservaActiva.getHuesped().getFactura().getCostoPorEstadia() * (0.1);
        objetosFactura[6] = reservaActiva.getHuesped().getFactura().getSeguroHotelero() * (0.16);
        objetosFactura[7] = reservaActiva.getHuesped().getFactura().getValorTotal();
        objetosFactura[8] = objetosFactura[7];
        objetosFactura[9] = 0;
        objetosFactura[10] = new NumeroALetra().Convertir(objetosFactura[7].toString(), true);

        ArrayList<String[]> lista = new ArrayList<String[]>();

        SimpleDateFormat fn = new SimpleDateFormat("dd'/'MM'/'yyyy");
        SimpleDateFormat fl = new SimpleDateFormat("dd MMM", new Locale("ES"));
        SimpleDateFormat fd = new SimpleDateFormat("EEEEE", new Locale("ES"));

        Date d = new GregorianCalendar().getTime();

        String[] detalles = new String[6];

        // <editor-fold defaultstate="collapsed" desc="Detalles Estadia">
        detalles[0] = fn.format(d);
        detalles[1] = "Habitacion " + hab.getIdHabitacion();
        detalles[2] = fd.format(d).toUpperCase() + " " + fl.format(d);
        detalles[3] = Long.toString(new IOHuespedes().diasEstadia(reservaActiva));
        detalles[4] = "50000";
        detalles[5] = Long.toString(reservaActiva.getHuesped().getFactura().getCostoPorEstadia());

        lista.add(detalles);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Detalles Reserva">
        detalles = new String[6];
        d = reservaActiva.getFechaInicial();
        detalles[0] = fn.format(d);
        detalles[1] = "Reserva";
        detalles[2] = fd.format(d).toUpperCase() + " " + fl.format(d);
        detalles[3] = Integer.toString(reservaActiva.getDiasReserva());
        detalles[4] = "16200";
        detalles[5] = Long.toString(reservaActiva.getHuesped().getFactura().getCostoPorReserva());

        lista.add(detalles);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Detalles Plan">
        detalles = new String[6];
        d = new GregorianCalendar().getTime();
        detalles[0] = fn.format(d);
        detalles[1] = "Plan " + reservaActiva.getHuesped().getPlan();
        detalles[2] = fd.format(d).toUpperCase() + " " + fl.format(d);
        detalles[3] = "N/A";
        detalles[4] = Integer.toString((reservaActiva.getHuesped().getPlan().equals("Europeo")) ? 0 : 100000);
        detalles[5] = Long.toString(reservaActiva.getHuesped().getFactura().getCostoPorPlan());

        lista.add(detalles);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Detalles Seguro Hotelero">
        detalles = new String[6];
        detalles[0] = fn.format(d);
        detalles[1] = "Seguro Hotelero";
        detalles[2] = fd.format(d).toUpperCase() + " " + fl.format(d);
        detalles[3] = "1";
        detalles[4] = "3.4%";
        detalles[5] = Long.toString(reservaActiva.getHuesped().getFactura().getSeguroHotelero());

        lista.add(detalles);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Detalles Servicios">
        if(reservaActiva.getHuesped().getFactura().getCostoPorPlan() == 0){
            for (Servicio u : reservaActiva.getHuesped().getFactura().getServicio()) {
                String[] ser = new String[6];

                SimpleDateFormat format = new SimpleDateFormat("dd'/'MM'/'yyyy");
                ser[0] = format.format(u.getFecha());
                ser[1] = u.getNombre();
                //TODO: Preguntar que debe ir en el detalle
                ser[2] = " ";
                ser[3] = Integer.toString(u.getCantidad());
                ser[4] = Integer.toString(u.getCosto());
                ser[5] = Long.toString(u.getCostoTotal());

                lista.add(ser);
            }
        }
        // </editor-fold>

        objetosFactura[3] = lista;

        // <editor-fold defaultstate="collapsed" desc="Detalles Huesped">
        huesped[0] = reservaActiva.getHuesped().getNombre();
        huesped[1] = reservaActiva.getHuesped().getApellido();
        huesped[2] = Long.toString(reservaActiva.getHuesped().getDNI());
        huesped[3] = Integer.toString(hab.getIdHabitacion());
        huesped[4] = reservaActiva.getHuesped().getTarjetaCredito();
        huesped[5] = reservaActiva.getHuesped().getTipoTarjetaCredito();

        objetosFactura[2] = huesped;
        // </editor-fold>

    }

    private void generarXML(){
        try{
            File archivo2 = new File("Facturas");
            if(!archivo2.isDirectory()){
                archivo2.mkdir();
            }
            File archivo = new File("Facturas\\Factura_"+fileName+".xml");
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

    @SuppressWarnings("unchecked")
    private void agregarDatos(){
        String[] nodos = {"Documento", //0
                            "Factura", //1
                            "Huesped", //2
                            "Detalles", //3
                            "Subtotal", //4
                            "IVA10", //5
                            "IVA16", //6
                            "Total", //7
                            "Abonos", //8
                            "Saldo", //9
                            "TotalEnLetras", //10
                            "Nombre", //11
                            "Apellido", //12
                            "Identificacion", //13
                            "Habitacion", //14
                            "Tarjeta", //15
                            "Numero", //16
                            "Tipo", //17
                            "Producto", //18
                            "Fecha", //19
                            "Concepto", //20
                            "Detalles", //21
                            "Cantidad", //22
                            "VlrUnidad", //23
                            "VlrTotal"}; //24

        try {
            File f = new File("Facturas\\Factura_"+fileName+".xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement(nodos[0]);
            root.setAttribute("user", objetosFactura[0].toString());

            Element factura = document.createElement(nodos[1]);
            factura.setAttribute("id", objetosFactura[1].toString());

            for(int i = 2; i < 11; i++){
                Element child = document.createElement(nodos[i]);
                if(i == 2){
                    String[] datos = (String[]) objetosFactura[i];
                    for(int j = 11; j < 16; j++){
                        Element child2 = document.createElement(nodos[j]);
                        if(j == 15){
                            for(int h = 16; h < 18; h++){
                                Element child3 = document.createElement(nodos[h]);
                                child3.setTextContent(datos[h - 12]);
                                if(h==16){
                                    child2.appendChild(child3);
                                }
                                else{
                                    child2.insertBefore(child3, child2.getLastChild().getNextSibling());
                                }
                            }
                        }
                        else{
                            child2.setTextContent(datos[j - 11]);
                        }
                        if(j == 11){
                            child.appendChild(child2);
                        }
                        else{
                            child.insertBefore(child2, child.getLastChild().getNextSibling());
                        }
                    }
                    factura.appendChild(child);
                }
                else if(i == 3){
                    ArrayList<String[]> lis = (ArrayList<String[]>) objetosFactura[3];
                    int r = 1;
                    int id = 0;
                    for(String[] u: lis){
                        Element child2 = document.createElement(nodos[18]);
                        child2.setAttribute("id", Integer.toString(id));
                        if(r==1){
                            child2.setAttribute("iva", "10%");
                        }
                        else if(r==4){
                            child2.setAttribute("iva", "16%");
                        }
                        else{
                            child2.setAttribute("iva", " ");
                        }
                        
                        for(int j = 19; j < 25; j++){
                            Element child3 = document.createElement(nodos[j]);
                            child3.setTextContent(u[j-19]);
                            if(j == 19){
                                child2.appendChild(child3);
                            }
                            else{
                                child2.insertBefore(child3, child2.getLastChild().getNextSibling());
                            }
                        }
                        child.appendChild(child2);
                        r++;
                        id++;
                    }
                    factura.insertBefore(child, factura.getLastChild().getNextSibling());
                }
                else{
                    child.setTextContent(objetosFactura[i].toString());
                    factura.insertBefore(child, factura.getLastChild().getNextSibling());
                }
            }

            root.appendChild(factura);
            document.appendChild(root);

            TransformerFactory t = TransformerFactory.newInstance();
            Transformer tr = t.newTransformer();
            FileOutputStream out = new FileOutputStream(f);
            Result res = new StreamResult(out);
            Source sou = new DOMSource(document);
            tr.setOutputProperty(OutputKeys.STANDALONE, "yes");
            tr.transform(sou, res);
        }
        catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        }
        catch (TransformerException ex) {
            System.out.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void generarPDF(){
        File f = new File("DisenioFactura\\Factura.jasper");
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(f);
            JasperPrint print =
            JasperFillManager.fillReport(report, null, new JRXmlDataSource(new File("Facturas\\Factura_"+fileName+".xml"), "/Documento/Factura/Detalles/Producto"));
            JRExporter export = new JRPdfExporter();
            export.setParameter(JRExporterParameter.JASPER_PRINT, print);
            File archivo = new File("Facturas\\Factura_"+fileName+".pdf");
            export.setParameter(JRExporterParameter.OUTPUT_FILE, archivo);
            export.exportReport();
            System.out.println("Factura: " + archivo.getAbsoluteFile());
            Desktop.getDesktop().open(archivo);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
