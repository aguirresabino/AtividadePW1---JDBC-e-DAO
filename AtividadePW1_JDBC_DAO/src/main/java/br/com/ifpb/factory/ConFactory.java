/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author aguirre
 */
public class ConFactory {
    private final String host;
    Properties props;

    public ConFactory(){
        this.host = "jdbc:postgresql://127.0.0.1:5432/AtividadePW";
        props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "102495");
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(host, props);
    }
}
