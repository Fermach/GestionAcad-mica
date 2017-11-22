/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  jdbc:<DRIVER>://<HOST>:<PORT>/<DATABASE_NAME>
 *      y usuario y contraseña
 * @author profesor
 */
public class Conexion {
    private Properties props;
    private Integer driver;
    private String host;
    private String puerto;
    private String base_datos;
    private String usuario;
    private String password;
    
    public static final int ORACLE=1;
    public static final int MYSQL=2;
    public static final int DERBY=3;
    public static final int POSTGRE=4;
    
    Conexion() throws DAOException {
        try (InputStream in =new FileInputStream(new File("db.prop"))  ) {
            props.load(in);
            in.close();
        } catch (IOException ex) {
            throw new DAOException("Conexion: No puedo cargar el archivo de configuracion");
            // driver=-1;
        }
        loadProperties();
    }
    
    Connection getConexion() throws DAOException{
       Connection con = null;
       String jdbcUrl = null; 
       switch(driver){
            case ORACLE:
                jdbcUrl = "jdbc:oracle:thin:@" + this.host + ":" + 
                            this.puerto  + ":" + this.base_datos;
                break;
            case MYSQL:
                jdbcUrl = "jdbc:mysql://" + this.host + ":" + 
                            this.puerto  + ":" + this.base_datos;
                break;
            default:
                throw new DAOException("Conexion: El driver seleccionado no está disponible");
        }
       
        try {
            con = DriverManager.getConnection(jdbcUrl, usuario, password);
        } catch (SQLException ex) {
            throw new DAOException("Conexion: No se puede conectar a la BBDD");
        }
        
        return con;
    }
    
    /* 
    Conexion(int driver) throws DAOException {
        
        switch(driver){
            case ORACLE:
                
                break;
            case MYSQL:
                
                break;
            default:
                throw new DAOException("Conexion: El driver seleccionado no está disponible");
        }
        
    }*/
    
    private void loadProperties(){
        this.base_datos = props.getProperty("base_datos");
        this.driver     = Integer.parseInt(props.getProperty("driver"));
        this.host       = props.getProperty("host");
        this.password   = props.getProperty("password");
        this.usuario    = props.getProperty("usuario");
        this.puerto     = props.getProperty("puerto");          
    }
}


















