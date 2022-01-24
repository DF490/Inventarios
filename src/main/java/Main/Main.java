package Main;

import Vista.Frame;
import Modelo.conexion;
import Controlador.Controlador;
import Modelo.Modelo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        Producto arroz = new Producto("arroz" , 2 , "granos" , 2500);
//        Producto te = new Producto("Te de fresa" , 1 , "jugos" , 3500);
//        
//        System.out.println(arroz.toString());
//        System.out.println(te.toString());
//          Producto p = new Producto();
//            p.setId(0);
//            p.setCantidad(1);        
//            p.setCategoria("granos");
//            p.setPrecio(2500);
//            p.setNombre("arroz");
//            System.out.println(p.toString());
//Creación de producto
/*
        conexion url = new conexion();
        String sql = "Select * From productos";
        ResultSet consulta = url.consultarBase(sql);
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
         */
//Eliminar producto
/*
        conexion url = new conexion();
        String query = "DELETE FROM productos where id = 5";
        boolean eliminar = url.borrar(query);
        
        if (eliminar == true){
            
            System.out.println("Registro Eliminado!");
        }
        else {
            System.out.println("No se ha podido completar la operación.");
        }
         */

//Insertar Producto
/*
        conexion url = new conexion();
        String query = "INSERT INTo productos (nombre,cantidad,categoria,precio) VALUES (\"Jugos\", 300 , \"VIVERES\", 1500);";
        boolean insertar = url.insertar(query);

        if (insertar == true) {

            System.out.println("Producto Registrado!");
        } else {
            System.out.println("No se ha podido completar la operación. Verifique e intente de nuevo.");
        }
*/
//Actualizar Producto
/*        
        int id = 7;
        conexion url = new conexion();
        String query = String.format("UPDATE productos set nombre =? ,cantidad = ?, categoria = ? ,precio = ? where id =" + id + ";");
                
        boolean actualiza = url.actualizar(query);
        
        if (actualiza == true) {

            System.out.println("Producto Actualizado!");
        } else {
            System.out.println("No se ha podido completar la operación. Verifique e intente de nuevo.");
        }
*/

//Ejecutar interface

        Frame menu = new Frame();
        Modelo modelo =  new Modelo();
        Controlador control = new Controlador(menu , modelo);

    }

}
