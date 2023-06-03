
package datos.impl;
import dominio.Propiedades;
import datos.PropiedadesDAO;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.Conexion;
import java.util.ArrayList;

public class PropiedadesDAOImpl implements PropiedadesDAO<Propiedades>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public PropiedadesDAOImpl(){
        CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Propiedades> listar(String texto) {
        List<Propiedades> registros=new ArrayList();
        try {            
           // ps = CON.conectar().prepareStatement("SELECT id, nombre, direccion, caracteristicas, estado, precioAlquiler, created_at, updated_at FROM propiedad WHERE  nombre like ?");
            
            ps = CON.conectar().prepareStatement("SELECT id, nombre, direccion, caracteristicas, estado, precioAlquiler FROM propiedad where nombre like ?");
        ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add(new Propiedades(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5), rs.getDouble(6)));
            }
            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            ps=null;
            rs=null;
        }
        return registros;
    }

    @Override
    public boolean insertar(Propiedades obj) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("INSERT INTO propiedad( nombre, direccion, caracteristicas, estado, precioAlquiler) VALUES (?,?,?,?,?)");
            ps.setString(1, obj.getNombre());
             ps.setString(2, obj.getDireccion());
              ps.setString(3, obj.getCaracteristicas());
               ps.setString(4, obj.getEstado());
                ps.setDouble(5, obj.getPrecioalquiler());
            if (ps.executeUpdate() > 0) {
                resp = true;
          }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Propiedades obj) {
        resp = false;
    try {
        ps = CON.conectar().prepareStatement("UPDATE propiedad SET nombre=?, direccion=?, caracteristicas=?, estado=?, precioAlquiler=? WHERE id=?");
        ps.setString(1, obj.getNombre());
        ps.setString(2, obj.getDireccion());
        ps.setString(3, obj.getCaracteristicas());
        ps.setString(4, obj.getEstado());
        ps.setDouble(5, obj.getPrecioalquiler());
        ps.setInt(6, obj.getIdPropiedades()); // Assuming the ID of the property is stored in the 'id' attribute
        
        if (ps.executeUpdate() > 0) {
            resp = true;
        }
        ps.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } finally {
        ps = null;
        CON.desconectar();
    }
    return resp;
        
}

@Override

public boolean eliminar(int id) {
    resp = false;
    try {
        ps = CON.conectar().prepareStatement("DELETE FROM propiedad WHERE id=?");
        ps.setInt(1, id);
        
        if (ps.executeUpdate() > 0) {
            resp = true;
        }
        ps.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } finally {
        ps = null;
        CON.desconectar();
    }
    return resp;
}
    
}
