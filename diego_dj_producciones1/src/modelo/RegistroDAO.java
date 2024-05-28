/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.conexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JOTACE
 */
public class RegistroDAO {

    conexionBD conexion = new conexionBD(); // instancia conexionBD
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        String sql = "select * from Registro";
        List<Registro> lista = new ArrayList<>();
        try {
            con = conexion.ConectarBaseDatos();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Registro registro = new Registro();
                registro.setCodigo(rs.getInt(1));
                registro.setNombre(rs.getString(2));
                registro.setApellido(rs.getString(3));
                registro.setTelefono(rs.getString(4));
                registro.setCorreo(rs.getString(5));
                registro.setTipo_evento(rs.getString(6));
                registro.setCantidad_invitados(rs.getInt(7));
                lista.add(registro);
            }

        } catch (SQLException e) {
            System.out.println("Error listar:" + e);
        }
        return lista;
    } // fin del metodo listar

    // Metodo agregar
public void agregar(Registro registro) {
    String sql = "insert into registro (Nombre, Apellido, Telefono, Correo, Tipo_Evento, Cantidad_Invitados) values (?, ?, ?, ?, ?, ?)";
    try {
        con = conexion.ConectarBaseDatos();
        ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, registro.getNombre());
        ps.setString(2, registro.getApellido());
        ps.setString(3, registro.getTelefono());
        ps.setString(4, registro.getCorreo());
        ps.setString(5, registro.getTipo_evento());
        ps.setInt(6, registro.getCantidad_invitados());
        ps.executeUpdate();

        // Obtener la clave generada (el nuevo valor autoincremental)
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            int nuevoCodigo = generatedKeys.getInt(1);
            System.out.println("Nuevo codigo auto incremental" + nuevoCodigo);
            registro.setCodigo(nuevoCodigo); // Actualizar el objeto Registro con el nuevo código
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "El registro ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println("Error en agregar: " + e);
    } finally {
        cerrarRecursos();
    }
} // fin del metodo agregar


    // Metodo Actualizar 
    public void actualizar(Registro registro) {
        String sql = "update registro set nombre=?, apellido=?, telefono=?, correo=?, tipo_evento=?, cantidad_invitados=? where codigo=?";
        try {
            con = conexion.ConectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.setString(1, registro.getNombre());
            ps.setString(2, registro.getApellido());
            ps.setString(3, registro.getTelefono());
            ps.setString(4, registro.getCorreo());
            ps.setString(5, registro.getTipo_evento());
            ps.setInt(6, registro.getCantidad_invitados());
            ps.setInt(7, registro.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error ActualizarDAO: " + e);
        }
    } // fin metodo actualizar 

    // Metodo borrar 
    public void borrar(int id) {
        String SQL = "delete from registro where codigo=?";
        try {
            con = conexion.ConectarBaseDatos();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error borrarDao:" + e);
        }finally {
            cerrarRecursos(); //Asegurate de cerrar la conexion y los recursos 
        }
        
    }// fin del metodo borrar 

// Agrega este método para cerrar la conexión y recursos
private void cerrarRecursos() {
    try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (SQLException e) {
        System.out.println("Error al cerrar recursos: " + e);
    }
  }
}
