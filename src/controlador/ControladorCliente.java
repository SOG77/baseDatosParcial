/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DaoCliente;
import dao.DaoProducto;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Producto;

/**
 *
 * @author sebas ospina
 */
public class ControladorCliente {

    DaoCliente daocliente;
    DaoProducto daoproducto;

    public ControladorCliente() {
        daocliente = new DaoCliente();
        daoproducto = new DaoProducto();

    }

    public boolean registrarCliente(Cliente cliente) throws SQLException {
        return daocliente.guardarCliente(cliente);
    }

    public Cliente buscarCliente(String documentoIdentidad) throws SQLException {
        return daocliente.BuscarCliente(documentoIdentidad);
    }

    public boolean editarCliente(Cliente cliente) throws SQLException {
        return daocliente.editarCliente(cliente);
    }

    public boolean eliminarCliente(String documentoIdentidad) throws SQLException {
        if (daocliente.BuscarCliente(documentoIdentidad) != null) {
            return daocliente.eliminarCliente(documentoIdentidad);
        }
        return false;
    }

    public boolean registrarProducto(Producto producto) throws SQLException {
        return daoproducto.guardarProducto(producto);
    }

    public Producto buscarProducto(String nombreProducto) throws SQLException {
        return daoproducto.BuscarProducto(nombreProducto);
    }

    public boolean editarProducto(Producto producto) throws SQLException {
        return daoproducto.editarProducto(producto);
    }

    public boolean eliminarProducto(String nombreProducto) throws SQLException {
        if (daoproducto.BuscarProducto(nombreProducto) != null) {
            return daoproducto.eliminarProducto(nombreProducto);
        }
        return false;
    }

    public static void update() {

    }

    public DefaultTableModel listarCliente() throws SQLException {
        DefaultTableModel tablemodel;
        String[] columnas = new String[]{"Nombre", "Apellido", "Documento", "Telefono", "Direccion", "Correo", "Genero"};
        tablemodel = new DefaultTableModel(new Object[][]{}, columnas);
        for (Cliente cliente : daocliente.ListarCliente()) {
            Object[] fila = new Object[]{cliente.getNombre(), cliente.getApellido(), cliente.getDocumentoIdentidad(),
                cliente.getTelefono(), cliente.getDireccion(), cliente.getCorreo(), cliente.getGenero()};
            tablemodel.addRow(fila);

        }

        return tablemodel;
    }

    public DefaultTableModel listarPacienteFiltrado(Cliente cliente) {
        DefaultTableModel tablemodel;
        String[] columnas = new String[]{"Nombre", "Apellido", "Docummento", "Telefono", "Direccion", "Correo", "Genero"};
        tablemodel = new DefaultTableModel(new Object[][]{}, columnas);
        Object[] fila = new Object[]{cliente.getNombre(), cliente.getApellido(), cliente.getDocumentoIdentidad(),
            cliente.getTelefono(), cliente.getDireccion(), cliente.getCorreo(), cliente.getGenero()};
        tablemodel.addRow(fila);
        return tablemodel;
    }

    public DefaultTableModel listarProducto() throws SQLException {
        DefaultTableModel tablemodel;
        String[] columnas = new String[]{"NombreProducto", "Precio", "Cantidad"};
        tablemodel = new DefaultTableModel(new Object[][]{}, columnas);
        for (Producto producto : daoproducto.ListarProducto()) {
            Object[] fila = new Object[]{producto.getNombreProducto(), producto.getPrecio(), producto.getCantidad()};
            tablemodel.addRow(fila);

        }

        return tablemodel;

    }

    public DefaultTableModel listarProductoFiltrado(Producto producto) {
        DefaultTableModel tablemodel;
        String[] columnas = new String[]{"nombreProdcuto", "precio", "cantidad"};
        tablemodel = new DefaultTableModel(new Object[][]{}, columnas);
        Object[] fila = new Object[]{producto.getNombreProducto(), producto.getPrecio(), producto.getCantidad()};
        tablemodel.addRow(fila);
        return tablemodel;
    }

}
