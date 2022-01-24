package Modelo;

import java.sql.*;
import java.util.logging.*;

public class conexion {

    private String url = "";
    public Connection con = null;
    private Statement st = null;
    private ResultSet res = null;

    public conexion() {
        url = "jdbc:sqlite:reto5db.db";
        try {
            con = DriverManager.getConnection(url);
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("Conexión a Database exitosa! " + meta.getDriverName());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Connection getConnection() {
        return con;
    }

//close
    public void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
// result sql

    public ResultSet consultarBase(String sentencia) {
        try {
            st = con.createStatement();
            res = st.executeQuery(sentencia);
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        } catch (RuntimeException rex) {
            System.out.println(rex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return res;

    }
// Operaciones UPDATE, DELETE, INSERT
// Insert validando comunicacion
    public boolean insertar(String sentencia) {
        try {
            st = con.createStatement();
            st.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }
//Eliminacion
    public boolean borrar(String sentencia) {
        try {
            st = con.createStatement();
            st.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }
   
// Actualizar
    public boolean actualizar(String sentencia) {
        try {
            st = con.createStatement();
            st.executeUpdate(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }

    public boolean setAutoCommit(boolean parametro) {
        try {
            con.setAutoCommit(parametro);
        } catch (SQLException sqlex) {
            System.out.println("Error al configurar el autoCommit" + sqlex.getMessage());
            return false;
        }
        return true;
    }

    public void closeCon() {
        closeConnection(con);
    }

    public boolean commitBD() {
        try {
            con.commit();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer commit" + sqlex.getMessage());
            return false;
        }
    }

    public boolean rollbackBD() {
        try {
            con.rollback();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer rollback" + sqlex.getMessage());
            return false;
        }
    }
}
