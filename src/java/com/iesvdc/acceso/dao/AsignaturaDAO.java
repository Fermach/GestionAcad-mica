/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.dao;

import com.iesvdc.acceso.pojo.AluAsi;
import com.iesvdc.acceso.pojo.ProAsi;
import com.iesvdc.acceso.pojo.Alumno;
import com.iesvdc.acceso.pojo.Asignatura;
import com.iesvdc.acceso.pojo.Profesor;
import java.util.List;

/**
 *
 * @author profesor
 */
public interface AsignaturaDAO {
    public void create(Asignatura as) throws DAOException;
    public void update(Integer old_id, Asignatura new_as) throws DAOException;
    public void update(Asignatura old_as, Asignatura new_as) throws DAOException;
    public void delete(Integer id) throws DAOException;
    public void delete(Asignatura as) throws DAOException;
    public List<Asignatura> findAll() throws DAOException;
    public List<Asignatura> findByName(String name) throws DAOException;
    public Asignatura findById(Integer id) throws DAOException;
    public List<Asignatura> findByCurso(Integer curso) throws DAOException;
    public List<Asignatura> findByCiclo(String ciclo) throws DAOException;
    
}
