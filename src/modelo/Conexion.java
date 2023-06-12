/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author sebas
 */
public class Conexion {

    private Connection con;
    private String usuario = "root";
    private String password = "";
    private boolean conectado;

    public Connection getCon() {
        return con;
    }

    public boolean isConectado() {
        return conectado;

    }

    public Conexion() {
        this.con = null;
        this.conectado = false;
    }

    public void conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedParcial", usuario, password);
            this.conectado = true;
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            this.conectado = false;
            e.printStackTrace();
        }
    }

    public void desconectar() {
        if (this.conectado) {
            this.conectado = false;
            try {
                this.con.close();
            } catch (SQLException ex) {
                this.con = null;
            }
        }
    }
}
