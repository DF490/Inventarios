package Modelo;

import Main.Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Modelo extends conexion {

    public Producto consulta(int id) {

        conexion url = new conexion();
        String query = "Select * From productos where id =" + id + ";";
        ResultSet consulta = url.consultarBase(query);

        Producto p = new Producto();

        try {
            ArrayList<Producto> obj = new ArrayList<Producto>();

            while (consulta.next()) {
                p.setId(consulta.getInt("id"));
                p.setNombre(consulta.getString("nombre"));
                p.setCantidad(consulta.getInt("cantidad"));
                p.setCategoria(consulta.getString("categoria"));
                p.setPrecio(consulta.getDouble("precio"));

            }
        } catch (SQLException ex) {

            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }

        return p;
    }

    public ArrayList<Producto> Listado() {

        conexion url = new conexion();
        String query = "Select * From productos";
        ResultSet consulta = url.consultarBase(query);
        ArrayList<Producto> productList = new ArrayList<Producto>();

        try {

            while (consulta.next()) {
                Producto p = new Producto();
                p.setId(consulta.getInt("id"));
                p.setNombre(consulta.getString("nombre"));
                p.setCantidad(consulta.getInt("cantidad"));
                p.setCategoria(consulta.getString("categoria"));
                p.setPrecio(consulta.getDouble("precio"));
                productList.add(p);
            }

            System.out.println(productList);

        } catch (SQLException ex) {

            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }

        return productList;

    }

    public void eliminarP(Producto item) {

        conexion url = new conexion();
        String query = "DELETE from productos where id = ?";

        try {
            PreparedStatement st = url.getConnection().prepareStatement(query);
            st.setInt(1, item.getId());
            st.execute();

        } catch (SQLException e) {

            System.out.println("No se pudo eliminar el registro!" + e.getMessage());
        }

    }

    public void insertarP(Producto item) {

        conexion url = new conexion();
        String query = "INSERT Into productos (nombre,cantidad,categoria,precio) VALUES ( ?, ?, ?, ?);";
        
        try {
            PreparedStatement st = url.getConnection().prepareStatement(query);
            st.setString(1, item.getNombre());
            st.setInt(2, item.getCantidad());
            st.setString(3, item.getCategoria());
            st.setDouble(4, item.getPrecio());
            st.execute();
            
        } catch (SQLException e) {

            System.out.println("No se pudo eliminar el registro!" + e.getMessage());
        }

    }

    public void ActualizarP(Producto item) {

        conexion url = new conexion();
        String query = "UPDATE productos set nombre = ? , cantidad = ? , categoria = ?, precio = ? WHERE id = ?";

        try {
            PreparedStatement st = url.getConnection().prepareStatement(query);
            st.setString(1, item.getNombre());
            st.setInt(2, item.getCantidad());
            st.setString(3, item.getCategoria());
            st.setDouble(4, item.getPrecio());
            st.setInt(5, item.getId());
            st.execute();

        } catch (SQLException e) {

            System.out.println("No se pudo actualizar el registro!" + e.getMessage());
        }

    }

}
