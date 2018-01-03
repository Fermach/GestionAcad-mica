/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.dao;

import com.iesvdc.acceso.pojo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  Fermach
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    Conexion conex;

    public UsuarioDAOImpl(){}
     
    private Connection obtenerConexion() throws DAOException {
        if (conex == null) {
            conex = new Conexion();
        }
        return conex.getConexion();
    }
    @Override
    public void create(Usuario us) throws DAOException {
          try {
            if (us.getUsername().length() > 3 && us.getPassword().length()> 3 && (us.getTipo()== 1 || us.getTipo()== -1 || us.getTipo()== 0)) {
                Connection con = obtenerConexion();
                PreparedStatement pstm = con.prepareStatement("INSERT INTO USUARIO VALUES(?,?,?)");
                pstm.setString(1, us.getUsername());
                pstm.setInt(3, us.getTipo());
                pstm.setString(2, us.getPassword());
                pstm.execute();
                con.close();
            } else {
                throw new DAOException("Usuario:Crear: campos introducidos erroneos");
            }
        } catch (SQLException ex) {
            throw new DAOException("Usuario:Crear: No puedo conectar a la BBDD");
        }   
    }

    @Override
    public void update(Usuario old_us, Usuario new_us) throws DAOException {
        update(old_us.getUsername(), new_us);
              
    }

    @Override
    public void update(String old_name, Usuario new_us) throws DAOException {
       try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement(" UPDATE USUARIO SET USERNAME=?, PASSWORD=?, TIPO=? WHERE USERNAME=?");
            pstm.setString(5, old_name);
            pstm.setString(1, new_us.getUsername());
            pstm.setString(2, new_us.getPassword());
            pstm.setInt(3, new_us.getTipo());
            pstm.execute();
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:Update: No puedo conectar a la BBDD");
        }

    }

    @Override
    public void delete(String username) throws DAOException {
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("DELETE FROM USUARIO WHERE USERNAME=?");
            pstm.setString(1, username);
            pstm.execute();
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:Delete: No puedo conectar a la BBDD");
        }   
    }

    @Override
    public void delete(Usuario us) throws DAOException {
        delete(us.getUsername());
    }

    @Override
    public List<Usuario> findByUsername(String username) throws DAOException {
        Usuario us;
        List<Usuario> list_us= new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM USUARIO WHERE USERNAME=?");
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                us = new Usuario(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("TIPO"));
                list_us.add(us);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:findByUsername: No puedo conectar a la BBDD ");
        }
        return list_us;
    }

    @Override
    public List<Usuario> findByPassword(String password) throws DAOException {
     Usuario us;
        List<Usuario> list_us= new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM USUARIO WHERE PASSWORD=?");
            pstm.setString(1, password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                us = new Usuario(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("TIPO"));
                list_us.add(us);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:findByPassword: No puedo conectar a la BBDD ");
        }
        return list_us;

    }

    @Override
    public List<Usuario> findAll() throws DAOException {
        Usuario us;
        List<Usuario> list_us = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM USUARIO");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                us = new Usuario(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("TIPO"));
                list_us.add(us);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:findAll: No puedo conectar a la BBDD ");
        }
        return list_us;
    }

    @Override
    public List<Usuario> findByUsernamePassword(String username, String password) throws DAOException {
        Usuario us;
        List<Usuario> list_us = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement(
                    "SELECT * FROM USUARIO WHERE USERNAME=? AND PASSWORD=?");
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                us = new Usuario(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("TIPO"));
                list_us.add(us);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:findByUsernamePassword: No puedo conectar a la BBDD ");
        }
        return list_us;
    }
    
}
