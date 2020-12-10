/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Inventario;
import edu.petlovers.entity.MarcaProductos;
import edu.petlovers.entity.Productos;
import edu.petlovers.entity.Proveedores;
import edu.petlovers.entity.TipoProductos;
import edu.petlovers.local.InventarioFacadeLocal;
import edu.petlovers.local.MarcaProductosFacadeLocal;
import edu.petlovers.local.ProductosFacadeLocal;
import edu.petlovers.local.ProveedoresFacadeLocal;
import edu.petlovers.local.TipoProductosFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;

/**
 *
 * @author wsbachiller
 */
@ManagedBean
@Named(value = "productosRequest")
@RequestScoped
public class ProductosRequest implements Serializable {

    @EJB
    ProductosFacadeLocal productosFacadeLocal;
    @EJB
    ProveedoresFacadeLocal proveedoresFacadeLocal;
    @EJB
    TipoProductosFacadeLocal tipoProductosFacadeLocal;
    @EJB
    InventarioFacadeLocal inventarioFacadeLocal;
    @EJB
    MarcaProductosFacadeLocal marcaProductosFacadeLocal;

//    @Inject
//    UsuarioSession usuarioSession;

    private Productos objProductos = new Productos();

    private Integer idToUpdate;
     private int idProducto;
    ArrayList<Productos> listaProductos = new ArrayList();

    private Part archivoExcel;

    /**
     * Creates a new instance of ProductosRequest
     */
    public ProductosRequest() {
    }

    @PostConstruct
    public void postProductos() {
        listaProductos.addAll(productosFacadeLocal.findAll());
        objProductos.setNitProveedor(new Proveedores());
        objProductos.setIdTipoProducto(new TipoProductos(1));
        objProductos.setIdInventario(new Inventario(1));
        objProductos.setIdMarcaProducto(new MarcaProductos(1));
    }

    public void registrarProducto() {
        String mensajeSw = "";

        try {
            objProductos.setNitProveedor(proveedoresFacadeLocal.find(objProductos.getNitProveedor().getNitProveedor()));
            objProductos.setIdTipoProducto(tipoProductosFacadeLocal.find(objProductos.getIdTipoProducto().getIdTipoProducto()));
            objProductos.setIdInventario(inventarioFacadeLocal.find(1));
            objProductos.setIdMarcaProducto(marcaProductosFacadeLocal.find(1));

            productosFacadeLocal.create(objProductos);
            listaProductos.add(objProductos);
            mensajeSw = "swal('Producto creado' , 'con exito' , 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El producto' , 'no fue registrado' , 'error');";
        }
        objProductos = new Productos();
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void cargarFormulario(Productos objCargar) {
        this.objProductos = objCargar;
        this.idToUpdate = objCargar.getIdProducto();
    }

    public void removerProducto(Productos objProductos) {
        String mensaje = "";
        try {
            productosFacadeLocal.remove(objProductos);
            listaProductos.remove(objProductos);
            mensaje = "swal('Producto' , ' Eliminado ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('El producto' , ' No ha sido eliminado ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }

    public void actualizarProducto() {
        String mensaje = "";

        try {
//          objProductos.setIdProducto(this.idToUpdate);
            productosFacadeLocal.edit(objProductos);
            listaProductos.clear();
            listaProductos.addAll(productosFacadeLocal.findAll());
            mensaje = "swal('El producto' , ' se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('El producto' , ' No ha sido modificado ', 'error')";
        }
        objProductos = new Productos();
        PrimeFaces.current().executeScript(mensaje);
    }
    
     public void cargarProducto(Productos objProductos){
        this.objProductos = objProductos;
         this.idProducto = objProductos.getIdProducto();
    }

    public void descargaReporte(String nombreReporte, String nombreUsuario) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("RutaLogo", context.getRealPath("/assets/img/logo/fondo1.jpg"));
            parametro.put("UsuarioReporte", nombreUsuario);
            parametro.put("RutaImagenFondo", context.getRealPath("/assets/img/logo/logoPetlovers2.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/petlovers", "petloversuser", "1234");
            System.out.println("Catalogo : " + conec.getCatalog());

            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/petlovers/reports/" + nombreReporte + ".jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte Producto.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("edu.petlovers.controller.CitaView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("edu.petlovers.controller.CitaView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("edu.petlovers.controller.CitaView.descargaReporte() " + q.getMessage());
        }

    }

    public void insertarXLS(List cellDataList) {
        try {
            int filasContador = 0;
            for (int i = 0; i < cellDataList.size(); i++) {
                List cellTemp = (List) cellDataList.get(i);
                Productos newP = new Productos();
                for (int j = 0; j < cellTemp.size(); j++) {
                    XSSFCell hssfCell = (XSSFCell) cellTemp.get(j);
                    switch (filasContador) {
                        case 0:
                            Productos nueva = productosFacadeLocal.find((int) Math.floor(hssfCell.getNumericCellValue()));
                            newP.setIdTipoProducto(nueva.getIdTipoProducto());
                            productosFacadeLocal.create(newP);
                            filasContador = 0;
                            break;
                        case 1:
                            newP.setNombreProducto(hssfCell.toString());
                            filasContador++;
                            break;
                        case 2:
                            newP.setDescripcion(hssfCell.toString());
                            filasContador++;
                            break;
                        case 3:
                            newP.setTamanoProducto(hssfCell.toString());
                            filasContador++;
                            break;
                        case 4:
                            newP.setColorProducto(hssfCell.toString());
                            filasContador++;
                            break;
                        case 5:
                            newP.setSaborProducto(hssfCell.toString());
                            filasContador++;
                            break;
                        case 6:
                            newP.setPrecioProducto(hssfCell.getColumnIndex());
                            filasContador++;
                            break;
                        case 7:
                            newP.setCodigoBarrasProducto(hssfCell.getColumnIndex());
                            filasContador++;
                            break;
                        case 8:
                            Productos p = productosFacadeLocal.find((int) Math.floor(hssfCell.getNumericCellValue()));
                            newP.setNitProveedor(p.getNitProveedor());
                            productosFacadeLocal.create(newP);
                            filasContador = 0;
                            break;
                        case 9:
                            Productos r = productosFacadeLocal.find((int) Math.floor(hssfCell.getNumericCellValue()));
                            newP.setIdInventario(r.getIdInventario());
                            productosFacadeLocal.create(newP);
                            filasContador = 0;
                            break;
                        case 10:
                            Productos o = productosFacadeLocal.find((int) Math.floor(hssfCell.getNumericCellValue()));
                            newP.setIdMarcaProducto(o.getIdMarcaProducto());
                            productosFacadeLocal.create(newP);
                            filasContador = 0;
                            break;                        
                    }

                }
            }

        } catch (Exception e) {
        }
    }

    public void subeExcel() throws IOException {
        String mensajeSw = "";
        if (archivoExcel != null) {
            if (archivoExcel.getSize() < 4000000) {
                if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(archivoExcel.getContentType())) {
                    InputStream input = archivoExcel.getInputStream();
                    List cellData = new ArrayList();
                    try {
                        XSSFWorkbook workBook = new XSSFWorkbook(input);
                        XSSFSheet hssfSheet = workBook.getSheetAt(0);
                        Iterator rowIterator = hssfSheet.rowIterator();
                        rowIterator.next();

                        while (rowIterator.hasNext()) {
                            XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                            Iterator iterator = hssfRow.cellIterator();
                            List cellTemp = new ArrayList();
                            while (iterator.hasNext()) {
                                XSSFCell hssfCell = (XSSFCell) iterator.next();
                                cellTemp.add(hssfCell);
                            }
                            cellData.add(cellTemp);
                        }
                        insertarXLS(cellData);
                    } catch (Exception e) {
                        PrimeFaces.current().executeScript("swal('Problemas ingresando el archivo' , 'error');");
                    }
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    context.redirect("index.xhtml");
                } else {
                    mensajeSw = "swal('El archivo' , ' no es una XLSX ', 'error')";
                }
            } else {
                mensajeSw = "swal('El archivo' , ' es muy grande  ', 'error')";
            }
        } else {
            mensajeSw = "swal('No puede cargar ' , ' EL  archivo  ', 'error')";
        }

        PrimeFaces.current().executeScript(mensajeSw);
    }

    public Productos getObjProductos() {
        return objProductos;
    }

    public void setObjProductos(Productos objProductos) {
        this.objProductos = objProductos;
    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Part getArchivoExcel() {
        return archivoExcel;
    }

    public void setArchivoExcel(Part archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

}
