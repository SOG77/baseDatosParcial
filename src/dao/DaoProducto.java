/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.Producto;

/**
 *
 * @author sebas
 */
public class DaoProducto {

    Conexion conect = null;

    public DaoProducto() {
        conect = new Conexion();

    }

    public boolean guardarProducto(Producto producto) throws SQLException {
        boolean condicion = false;
        conect.conectar();
        try (Connection con = conect.getCon()) {
            PreparedStatement pstm = con.prepareStatement("INSERT INTO productos(nombreProducto,precio,cantidad) VALUES (?,?,?)");
            pstm.setString(1, producto.getNombreProducto());
            pstm.setInt(2, producto.getPrecio());
            pstm.setInt(3, producto.getCantidad());
            int rspt = pstm.executeUpdate();
            condicion = rspt > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            condicion = false;
        }

        return condicion;

    }

    public Producto BuscarProducto(String nombreProducto) throws SQLException {
        conect.conectar();
        Producto aux = new Producto();
        try (Connection con = conect.getCon()) {
            PreparedStatement pstm = con.prepareStatement("SELECT nombreProducto,precio,cantidad FROM productos WHERE nombreProducto=?");
            pstm.setString(1, nombreProducto);
            ResultSet rspt = pstm.executeQuery();
            if (rspt.next()) {
                aux.setNombreProducto(rspt.getString("nombreProducto"));
                aux.setPrecio(rspt.getInt("precio"));
                aux.setCantidad(rspt.getInt("cantidad"));
                return aux;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            aux = null;
        }
        return null;

    }

    public boolean editarProducto(Producto producto) throws SQLException {
        boolean condicion = false;
        conect.conectar();
        try (Connection con = conect.getCon()) {
            PreparedStatement pstm = con.prepareStatement("UPDATE productos SET nombreProducto=?, precio=?, cantidad=? WHERE nombreProducto=?");
            pstm.setString(1, producto.getNombreProducto());
            pstm.setInt(2, producto.getPrecio());
            pstm.setInt(3, producto.getCantidad());
            pstm.setString(4, producto.getNombreProducto());
            int rspt = pstm.executeUpdate();
            condicion = rspt > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            condicion = false;
        }

        return condicion;
    }

    public boolean eliminarProducto(String nombreProducto) throws SQLException {
        boolean condicion = false;
        conect.conectar();
        try (Connection con = conect.getCon()) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM productos WHERE productos.nombreProducto=?");
            pstm.setString(1, nombreProducto);
            int rspt = pstm.executeUpdate();
            condicion = rspt > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            condicion = false;
        }
        return condicion;

    }

    public ArrayList<Producto> ListarProducto() throws SQLException {
        conect.conectar();
        try (Connection con = conect.getCon()) {
            PreparedStatement pstm = con.prepareStatement("SELECT nombreProducto,precio,cantidad FROM productos");
            ResultSet rspt = pstm.executeQuery();
            ArrayList<Producto> producto = new ArrayList<>();
            boolean condicion = true;
            while (condicion == true) {
                if (rspt.next()) {
                    Producto aux = new Producto();
                    aux.setNombreProducto(rspt.getString("nombreProducto"));
                    aux.setPrecio(rspt.getInt("precio"));
                    aux.setCantidad(rspt.getInt("cantidad"));
                    producto.add(aux);
                } else {
                    condicion = false;
                }
            }
            return producto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
