/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Conexion;

/**
 *
 * @author sebas
 */
public class DaoCliente {

    Conexion conect = null;

    public DaoCliente() {
        conect = new Conexion();

    }

    public boolean guardarCliente(Cliente cliente) throws SQLException {
        boolean condicion = false;
        conect.conectar();
        try (Connection con = conect.getCon()) {
            PreparedStatement pstm = con.prepareStatement("INSERT INTO cliente(cedula,nombre,apellido,telefono,direccion,correo,genero) VALUES (?,?,?,?,?,?,?)");
            pstm.setDouble(1, Double.parseDouble(cliente.getDocumentoIdentidad()));
            pstm.setString(2, cliente.getNombre());
            pstm.setString(3, cliente.getApellido());
            pstm.setInt(4, cliente.getTelefono());
            pstm.setString(5, cliente.getDireccion());
            pstm.setString(6, cliente.getCorreo());
            pstm.setString(7, cliente.getGenero());
            int rspt = pstm.executeUpdate();
            condicion = rspt > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            condicion = false;
        }

        return condicion;

    }

    public Cliente BuscarCliente(String documentoIdentidad) throws SQLException {
        conect.conectar();
        Cliente aux = new Cliente();
        try (Connection con = conect.getCon()) {
            PreparedStatement pstm = con.prepareStatement("SELECT cedula,nombre,apellido,telefono,direccion,correo,genero FROM cliente WHERE cedula=?");
            pstm.setDouble(1, Double.parseDouble(documentoIdentidad));
            ResultSet rspt = pstm.executeQuery();
            if (rspt.next()) {
                aux.setDocumentoIdentidad(Integer.toString((int) rspt.getDouble("cedula")));
                aux.setNombre(rspt.getString("nombre"));
                aux.setApellido(rspt.getString("apellido"));
                aux.setTelefono(rspt.getInt("telefono"));
                aux.setDireccion(rspt.getString("direccion"));
                aux.setCorreo(rspt.getString("correo"));
                aux.setGenero(rspt.getString("genero"));
                return aux;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            aux = null;
        }
        return null;

    }

    public boolean editarCliente(Cliente cliente) throws SQLException {
        boolean condicion = false;
        conect.conectar();
        try (Connection con = conect.getCon()) {
            PreparedStatement pstm = con.prepareStatement("UPDATE cliente SET nombre=?, apellido=?, telefono=?, direccion=?, correo=?, genero=? WHERE cedula=?");
            pstm.setString(1, cliente.getNombre());
            pstm.setString(2, cliente.getApellido());
            pstm.setInt(3, cliente.getTelefono());
            pstm.setString(4, cliente.getDireccion());
            pstm.setString(5, cliente.getCorreo());
            pstm.setString(6, cliente.getGenero());
            pstm.setDouble(7, Double.parseDouble(cliente.getDocumentoIdentidad()));
            int rspt = pstm.executeUpdate();
            condicion = rspt > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            condicion = false;
        }

        return condicion;
    }

    public boolean eliminarCliente(String documentoIdentidad) throws SQLException {
        boolean condicion = false;
        conect.conectar();
        try (Connection con = conect.getCon()) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM cliente WHERE cliente.cedula=?");
            pstm.setDouble(1, Double.parseDouble(documentoIdentidad));
            int rspt = pstm.executeUpdate();
            condicion = rspt > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            condicion = false;
        }
        return condicion;

    }

    public ArrayList<Cliente> ListarCliente() throws SQLException {
        conect.conectar();
        try (Connection con = conect.getCon()) {
            PreparedStatement pstm = con.prepareStatement("SELECT cedula,nombre,apellido,telefono,direccion,correo,genero FROM cliente");
            ResultSet rspt = pstm.executeQuery();
            ArrayList<Cliente> cliente = new ArrayList<>();
            boolean condicion = true;
            while (condicion == true) {
                if (rspt.next()) {
                    Cliente aux = new Cliente();
                    aux.setDocumentoIdentidad(Integer.toString((int) rspt.getDouble("cedula")));
                    aux.setNombre(rspt.getString("nombre"));
                    aux.setApellido(rspt.getString("apellido"));
                    aux.setTelefono(rspt.getInt("telefono"));
                    aux.setDireccion(rspt.getString("direccion"));
                    aux.setCorreo(rspt.getString("correo"));
                    aux.setGenero(rspt.getString("genero"));
                    cliente.add(aux);
                } else {
                    condicion = false;
                }
            }
            return cliente;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
