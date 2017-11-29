/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.controller.resources;

import com.iesvdc.acceso.dao.AlumnoDAO;
import com.iesvdc.acceso.dao.AlumnoDAOImpl;
import com.iesvdc.acceso.dao.DAOException;
import com.iesvdc.acceso.pojo.Alumno;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juangu <jgutierrez at iesvirgendelcarmen.coms>
 */
@Path("/alumno")
public class AlumnoResource {
    
    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Alumno getAlumnoById(@PathParam("id") String id) {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        Alumno al;
        try {
            al = al_dao.findById(Integer.parseInt(id));
        } catch (DAOException ex) {
            al = new Alumno(-1,"Error","Error");
            System.out.println(ex.getLocalizedMessage());
        }
        return al;
    }
    
    @GET
    @Path("apellido/{apellido}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Alumno> getAlumnoByApellido(@PathParam("apellido") String apellido) {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        List<Alumno> list_al;
        try {
            list_al = al_dao.findByApellido(apellido);
        } catch (DAOException ex) {
            list_al = new ArrayList<>();
            System.out.println(ex.getLocalizedMessage());
        }
        return list_al;
    }
    
    @GET
    @Path("nombre/{nombre}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Alumno> getAlumnoByNombre(@PathParam("nombre") String nombre) {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        List<Alumno> list_al;
        try {
            list_al = al_dao.findByNombre(nombre);
        } catch (DAOException ex) {
            list_al = new ArrayList<>();
            System.out.println(ex.getLocalizedMessage());
        }
        return list_al;
    }
    
}
