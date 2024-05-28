package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexionBD {
    Connection con;
    String bd="diego_dj_producciones";
    String url ="jdbc:mysql://localhost:3306/" + bd + "?useUnicode=true&use" + 
            "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" 
            + "serverTimezone=UTC";
    String usuario = "root";
    String pwd ="";
    String driver = "com.mysql.cj.jdbc.Driver";
    
    public Connection ConectarBaseDatos(){
    try{
        Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, pwd);
            System.out.println("Conexión exitosa a la base de datos " + bd);
    }catch(ClassNotFoundException | SQLException e ) {
        System.out.println("No se pudo Conectar a la base de datos " + bd);
        Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE, null, e);
    }
      return con;  
    }// Fin del metodo Conectar Base de datos 
    
    public static void main(String[] args) {
        conexionBD conexion = new conexionBD();
        conexion.ConectarBaseDatos();
    }}


    

    