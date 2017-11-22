/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.dao;

import java.util.Properties;

/**
 * jdbc:DRIVER://HOST./BaseDatos
 * @author Juangu <jgutierrez at iesvirgendelcarmen.coms>
 */
public class ConexionFactory {
    Properties props;
    
    String host;
    String puerto;
    String driver;
    String basedatos;
    String usuario;
    String password;

    public ConexionFactory() {
    }

    public ConexionFactory(String driver) {
        this.driver = driver;
    }
    
    
    
}
