/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Registro;
import modelo.RegistroDAO;
import vista.interfaz;

/**
 *
 * @author JOTACE
 */
public class ControladorRegistro implements ActionListener {

    // instancias
    Registro registro = new Registro();
    RegistroDAO registrodao = new RegistroDAO();
    interfaz vista = new interfaz();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    // variables globales
    private int codigo = 0;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String tipo_evento;
    private int cantidad_invitados;

    public ControladorRegistro(interfaz vista) {
        this.vista = vista;
        vista.setVisible(true);
        agregarEventos();
        listarTabla();
    }

    private void agregarEventos() {
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnActualizar().addActionListener(this);
        vista.getBtnBorrar().addActionListener(this);
        vista.getBtnLimpiar().addActionListener(this);
        vista.getTblTabla().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                llenarCampos(e);

            }
        });
    }

    private void listarTabla() {
        String[] titulos = new String[]{"codigo", "Nombre", "Apellido", "Telefono", "Correo", "Tipo_Evento", "Cantidad_Invitados"};
        modeloTabla = new DefaultTableModel(titulos, 0);
        
        // obten la lista actualizada de registro
        List<Registro> listaRegistro = registrodao.listar();
        
        //Agrega los registros al modelo de la tabla
        for (Registro registro : listaRegistro) {
            modeloTabla.addRow(new Object[]{registro.getCodigo(), registro.getNombre(), registro.getApellido(), registro.getTelefono(), registro.getCorreo(), registro.getTipo_evento(), registro.getCantidad_invitados()});
        }
        
        // Establecer el modelo de la tabla y ajusta el tamaño
        vista.getTblTabla().setModel(modeloTabla);
        vista.getTblTabla().setPreferredSize(new Dimension(350, modeloTabla.getRowCount() * 16));

    }

    private void llenarCampos(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        codigo = (int) vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 0);
        vista.getTxtNombre().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        vista.getTxtApellido().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        vista.getTxtTelefono().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 3).toString());
        vista.getTxtCorreo().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 4).toString());
        vista.getTxtTipo_Evento().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 5).toString());
        vista.getTxtCantidad_Invitados().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 6).toString());

    }
    //----------------------------------------validar formulario

    private boolean validarDatos() {
        if ("".equals(vista.getTxtNombre().getText()) || "".equals(vista.getTxtApellido().getText()) || "".equals(vista.getTxtTelefono().getText()) || "".equals(vista.getTxtCorreo().getText()) || "".equals(vista.getTxtTipo_Evento().getText()) || "".equals(vista.getTxtCantidad_Invitados().getText())) {
            JOptionPane.showMessageDialog(null, "los campos no pueden ser vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // metodo 3 en 1
    // 1. cargamos la variables globales 
    // 2. parciando los valores
    // 3. estamos validando que precio y que inventario sean numericos
    private boolean cargarDatos() {
        try {
            nombre = vista.getTxtNombre().getText();
            apellido = vista.getTxtApellido().getText();
            telefono = vista.getTxtTelefono().getText();
            correo = vista.getTxtCorreo().getText();
            tipo_evento = vista.getTxtTipo_Evento().getText();
            cantidad_invitados = Integer.parseInt(vista.getTxtCantidad_Invitados().getText());
            return true;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo cantidad de invitados de ser ", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al cargar Datos: " + e);
            return false;
        }
    }

    private void limpiarCampos() {
        vista.getTxtNombre().setText("");
        vista.getTxtApellido().setText("");
        vista.getTxtTelefono().setText("");
        vista.getTxtCorreo().setText("");
        vista.getTxtTipo_Evento().setText("");
        vista.getTxtCantidad_Invitados().setText("");
        codigo = 0;
        nombre = "";
        apellido = "";
        telefono = "";
        correo = "";
        tipo_evento = "";
        cantidad_invitados = 0;
    }

    //------------------------------------------------------------------------
    private void agregarRegistro() {
        try {
            if (validarDatos()) { // validarDatos () == true
                if (cargarDatos()) { // cargarDatos () == true
                    Registro registro = new Registro(nombre, apellido, telefono, correo, tipo_evento, cantidad_invitados);
                    registrodao.agregar(registro);
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                    limpiarCampos();
                }
            }
        } catch (HeadlessException e) {
            System.out.println("Error en AgregarC: " + e);
        } finally {
            listarTabla();
        }
    }

    private void actualizaRegistro() {
        try {
            if (validarDatos()) {
                if (cargarDatos()) {
                    Registro registro = new Registro(codigo, nombre, apellido, telefono, correo, tipo_evento, cantidad_invitados);
                    registrodao.actualizar(registro);
                    JOptionPane.showMessageDialog(null, "registro de actualizacion exitoso");
                    limpiarCampos();
                }
            }

        } catch (HeadlessException e) {
            System.out.println("Error actualizarC : " + e);
        } finally {
            listarTabla();
        }
    }

    private void borrarRegistro() {
        try {
            if (codigo != 0) {
                registrodao.borrar(codigo);
                JOptionPane.showMessageDialog(null, "registro borrado");
                limpiarCampos();
                
                // Despues de borrar, actualiza el modelo de la tabla
                
                listarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            System.out.println("Error borrarRegistroC:" + e);

        } finally {
            listarTabla();
        }

    }

    // Dar acciones a los botones
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnAgregar()) {
            agregarRegistro();
        }
        if (ae.getSource() == vista.getBtnActualizar()) {
            actualizaRegistro();
        }
        if (ae.getSource() == vista.getBtnBorrar()) {
            borrarRegistro();
        }

        if (ae.getSource() == vista.getBtnLimpiar()) {
            limpiarCampos();
        }
    }
}


