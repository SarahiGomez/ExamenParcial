
package negocio;

import javax.swing.table.DefaultTableModel;
import datos.impl.PropiedadesDAOImpl;
import dominio.Propiedades;
import java.util.ArrayList;
import java.util.List;

public class PropiedadesControl {
    private final PropiedadesDAOImpl DATOS;
    private Propiedades obj;
    private DefaultTableModel modeloTabla;

     public PropiedadesControl(){
        DATOS=new PropiedadesDAOImpl();
        obj=new Propiedades();
    }
    
     public DefaultTableModel listar(String texto){
        List<Propiedades> lista=new ArrayList();
        lista.addAll(DATOS.listar(texto));
        String[] titulos = {"ID", "Nombre", "Direccion", "Caracteristicas", 
        "Estado", "Precio de Alquiler"};
        String[] registro = new String[2];
        this.modeloTabla = new DefaultTableModel(null, titulos);
        for (Propiedades item: lista){
            registro[0]=Integer.toString(item.getIdPropiedades());
            registro[1]=item.getNombre();
            registro[2]=item.getDireccion();
            registro[3]=item.getCaracteristicas();
            registro[4]=item.getEstado();
            registro[5]=Double.toString((double) item.getPrecioalquiler());
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }
     
    public String insertar(Propiedades obj){
        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el ingreso de datos.";
        }
    }
    public String actualizar(Propiedades obj){
        if (DATOS.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la actualización de datos.";
        }
    }
    public String eliminar(int id){
        if (DATOS.eliminar(id)) {
            return "OK";
        } else {
            return "Error la eliminación de datos.";
        }
    }
}