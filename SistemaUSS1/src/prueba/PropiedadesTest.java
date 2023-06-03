package prueba;
import datos.impl.PropiedadesDAOImpl;
import dominio.Propiedades;

public class PropiedadesTest {
    public static void main(String[] args){
        insertarPropiedades("Los Olivos", "Chiclao 5001", "medida 100x100", "en alquiler", 500.00);
        
    }
    
    private static void insertarPropiedades(String nombre, String direccion, String caracteristicas,
                                                String estado, Double precioAlquiler){
        Propiedades obj=new Propiedades();
        PropiedadesDAOImpl datos=new PropiedadesDAOImpl();
        obj.setNombre(nombre);
        obj.setDireccion(direccion);
        obj.setCaracteristicas(caracteristicas);
        obj.setEstado(estado);
        obj.setPrecioalquiler(precioAlquiler);
        boolean resp;
        resp=datos.insertar(obj);
        System.out.println("Insertado:" +resp);
    }
    
    
    
}

