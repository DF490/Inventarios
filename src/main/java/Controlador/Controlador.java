package Controlador;

import Vista.Frame;
import Modelo.Modelo;
import Modelo.Producto;
import Modelo.conexion;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Controlador implements ActionListener {

    private Modelo modelo;
    private Frame menuFrame;
    private ArrayList<Producto> Listado;

    public Controlador(Frame menuFrame, Modelo modelo) {

        this.modelo = modelo;
        this.menuFrame = menuFrame;
        menuVisible();

    }

    public void menuVisible() {

        menuFrame.setVisible(true);
        menuFrame.BTSave.addActionListener(this);
        menuFrame.Eliminar.addActionListener(this);
        menuFrame.Consultar.addActionListener(this);
        menuFrame.Actualizar.addActionListener(this);
        menuFrame.TablaProductos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int itemSelect = menuFrame.TablaProductos.getSelectedRow();
                Producto dato = Listado.get(itemSelect);
                menuFrame.CTid.setText(String.valueOf(dato.getId()));
                menuFrame.CTnom.setText(dato.getNombre());
                menuFrame.CTcant.setText(String.valueOf(dato.getCantidad()));
                menuFrame.CBcon.setSelectedItem(dato.getCategoria());
                menuFrame.CTprecio.setText(String.valueOf(dato.getPrecio()));

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public void limpiar() {

//categoria Consultar        
        menuFrame.CTid.setText("");
        menuFrame.CTnom.setText("");
        menuFrame.CTcant.setText("");
        menuFrame.CTprecio.setText("");
        menuFrame.CBreg.setSelectedItem("");

//categoria Registrar
        menuFrame.RTnom.setText("");
        menuFrame.RTcant.setText("");
        menuFrame.RTprecio.setText("");
        menuFrame.CBcon.setSelectedItem("");

    }

    public void tabla() {

        Listado = modelo.Listado();

        String[] columnas = new String[]{"ID", "NOMBRE", "CANTIDAD", "CATEGORIA", "PRECIO"};
        Object[][] filas = new Object[][]{};

        DefaultTableModel tabla = new DefaultTableModel(filas, columnas) {

            @Override
            public boolean isCellEditable(int rowIndex, int columIndex) {

                return false;
            }
        };

        for (int i = 0; i < Listado.size(); i++) {
            Producto li = Listado.get(i);
            Object[] fila1 = new Object[]{li.getId(), li.getNombre(), li.getCantidad(), li.getCategoria(), li.getPrecio()};
            tabla.addRow(fila1);
        }
        menuFrame.TablaProductos.setModel(tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == menuFrame.BTSave) {

            String nombre = menuFrame.RTnom.getText();
            int cantidad = Integer.parseInt(menuFrame.RTcant.getText());
            String categoria = (String) menuFrame.CBreg.getSelectedItem();
            double precio = Double.parseDouble(menuFrame.RTprecio.getText());

            Producto saveInsert = new Producto(nombre, cantidad, categoria, precio);
            modelo.insertarP(saveInsert);
            JOptionPane.showMessageDialog(menuFrame, "Producto creado Correctamente!");
            limpiar();

        }

        if (e.getSource() == menuFrame.Consultar) {
            tabla();
            limpiar();
        }

        if (e.getSource() == menuFrame.Actualizar) {

            if (menuFrame.CTid.getText().isEmpty()) {
                JOptionPane.showMessageDialog(menuFrame, "No ha seleccionado ningún registro");
            } else {
                int alerta = JOptionPane.showConfirmDialog(menuFrame, "¿Desea modificar este registro?");
                if (alerta == 0) {

                    int itemSelect = menuFrame.TablaProductos.getSelectedRow();
                    Producto dato = Listado.get(itemSelect);
                    dato.setNombre(menuFrame.CTnom.getText());
                    dato.setCantidad(Integer.parseInt(menuFrame.CTcant.getText()));
                    dato.setCategoria((String) menuFrame.CBcon.getSelectedItem());
                    dato.setPrecio(Double.parseDouble(menuFrame.CTprecio.getText()));

                    modelo.ActualizarP(dato);
                    JOptionPane.showMessageDialog(menuFrame, "¡Datos Actualizados Correctamente!");
                    tabla();
                    limpiar();

                }
            }
        }

        if (e.getSource() == menuFrame.Eliminar) {

            int alerta = JOptionPane.showConfirmDialog(menuFrame, "¿Continuar con la eliminación del registro?");

            if (alerta == 0) {
                int itemSelect = menuFrame.TablaProductos.getSelectedRow();
                Producto dato = Listado.get(itemSelect);

                modelo.eliminarP(dato);
                JOptionPane.showMessageDialog(menuFrame, "¡Registro Eliminado!");
                tabla();
                limpiar();
            }
        }
    }

}
