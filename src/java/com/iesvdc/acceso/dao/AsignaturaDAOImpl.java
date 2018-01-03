/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.dao;

import com.iesvdc.acceso.pojo.Asignatura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fermach
 */
public class AsignaturaDAOImpl implements AsignaturaDAO {

    Conexion conex;

    private Connection obtenerConexion() throws DAOException {
        if (conex == null) {
            conex = new Conexion();
        }
        return conex.getConexion();
    }

    public AsignaturaDAOImpl() {
    }

    @Override
    public void create(Asignatura as) throws DAOException {
        try {
            if (as.getCiclo().length() > 3 && as.getCurso() > 1 && as.getNombre().length() > 3) {
                Connection con = obtenerConexion();
                PreparedStatement pstm = con.prepareStatement("INSERT INTO ASIGNATURA VALUES(NULL, ?,?,?)");
                pstm.setString(1, as.getNombre());
                pstm.setInt(2, as.getCurso());
                pstm.setString(3, as.getCiclo());
                pstm.execute();
                con.close();
            } else {
                throw new DAOException("Asignatura:Crear: El nombre es demasiado corto");
            }
        } catch (SQLException ex) {
            throw new DAOException("Asignatura:Crear: No puedo conectar a la BBDD");
        }
    }

  



    @Override
    public List<Asignatura> findAll() throws DAOException {
        Asignatura as;
        List<Asignatura> list_as = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM ASIGNATURA");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                as = new Asignatura(rs.getString("nombre"),
                        rs.getInt("id"), rs.getInt("curso"), rs.getString("ciclo"));
                list_as.add(as);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Asignatura:findAll(): No puedo conectar a la BBDD ");
        }
        return list_as;
    }

    @Override
    public List<Asignatura> findByName(String name) throws DAOException {
        Asignatura as;
        List<Asignatura> list_as = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM ASIGNATURA WHERE nombre=?");
            pstm.setString(1, name);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                as = new Asignatura(rs.getString("nombre"), rs.getInt("id"), rs.getInt("curso"), rs.getString("ciclo"));
                list_as.add(as);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Asignatura:findByNombre: No puedo conectar a la BBDD ");
        }
        return list_as;
    }

    @Override
    public Asignatura findById(Integer id) throws DAOException {
     Asignatura as;
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM ASIGNATURA WHERE id=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            as = new Asignatura(rs.getString("nombre"), rs.getInt("id"), rs.getInt("curso"), rs.getString("ciclo"));
            con.close();
        } catch (SQLException ex) {
            as = new Asignatura("error" ,-1, -1, "error");
            throw new DAOException("Asignatura:findById: No puedo conectar a la BBDD ");
        }
        return as;
    }

    @Override
    public List<Asignatura> findByCurso(Integer curso) throws DAOException {
        Asignatura as;
        List<Asignatura> list_as = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM ASIGNATURA WHERE curso=?");
            pstm.setInt(1, curso);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                as = new Asignatura(rs.getString("nombre"), rs.getInt("id"), rs.getInt("curso"), rs.getString("ciclo"));
                list_as.add(as);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Asignatura:findByCurso: No puedo conectar a la BBDD ");
        }
        return list_as;
    }

    @Override
    public List<Asignatura> findByCiclo(String ciclo) throws DAOException {
        Asignatura as;
        List<Asignatura> list_as = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM ASIGNATURA WHERE ciclo=?");
            pstm.setString(1, ciclo);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                as = new Asignatura(rs.getString("nombre"), rs.getInt("id"), rs.getInt("curso"), rs.getString("ciclo"));
                list_as.add(as);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Asignatura:findByCiclo: No puedo conectar a la BBDD ");
        }
        return list_as;
    }

    @Override
    public void update(Integer old_id, Asignatura new_as) throws DAOException {
         try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement(" UPDATE ASIGNATURA SET nombre=?, id=?, curso=?, ciclo=? WHERE id=?");
            pstm.setInt(5, old_id);
            pstm.setString(1, new_as.getNombre());
            pstm.setInt(2, new_as.getId());
            pstm.setInt(3, new_as.getCurso());
            pstm.setString(4, new_as.getCiclo());
            pstm.execute();
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Asignatura:Update: No puedo conectar a la BBDD");
        }
    }

    @Override
    public void update(Asignatura old_as, Asignatura new_as) throws DAOException {

      update(old_as.getId(),new_as);
    }

    @Override
    public void delete(Integer id) throws DAOException {
         try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("DELETE FROM ASIGNATURA WHERE id=?");
            pstm.setInt(1, id);
            pstm.execute();
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Asignatura:Delete: No puedo conectar a la BBDD");
        }  
    }

    @Override
    public void delete(Asignatura as) throws DAOException {
       delete(as.getId());
    }

}
