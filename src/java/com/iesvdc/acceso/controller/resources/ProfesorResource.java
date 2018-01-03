/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.controller.resources;

import com.iesvdc.acceso.dao.AlumnoDAO;
import com.iesvdc.acceso.dao.AlumnoDAOImpl;
import com.iesvdc.acceso.dao.DAOException;
import com.iesvdc.acceso.dao.ProfesorDAO;
import com.iesvdc.acceso.dao.ProfesorDAOImp;
import com.iesvdc.acceso.pojo.Alumno;
import com.iesvdc.acceso.pojo.Profesor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Fermach
 */
@Path("/profesor")
public class ProfesorResource {

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Profesor> getProfesores() {
        ProfesorDAO pr_dao = new ProfesorDAOImp();
        List<Profesor> list_pr;
        try {
            list_pr = pr_dao.findAll();
        } catch (DAOException ex) {
            list_pr = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_pr;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Profesor getProfesorById(@PathParam("id") String id) {
        ProfesorDAO pr_dao = new ProfesorDAOImp();
        Profesor pr;
        try {
            pr = pr_dao.findById(Integer.parseInt(id));
        } catch (DAOException ex) {
            pr = new Profesor(-1, "Error", "Error");
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return pr;
    }

    @GET
    @Path("apellido/{apellido}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Profesor> getProfesorByApellido(@PathParam("apellido") String apellido) {
        ProfesorDAO pr_dao = new ProfesorDAOImp();
        List<Profesor> list_pr;
        try {
            list_pr = pr_dao.findByApellido(apellido);
        } catch (DAOException ex) {
            list_pr = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_pr;
    }

    @GET
    @Path("nombre/{nombre}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Profesor> getProfesorByNombre(@PathParam("nombre") String nombre) {
        ProfesorDAO pr_dao = new ProfesorDAOImp();
        List<Profesor> list_pr;
        try {
            list_pr = pr_dao.findByNombre(nombre);
        } catch (DAOException ex) {
            list_pr = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_pr;
    }

    @GET
    @Path("nombre/{nombre}/apellido/{apellido}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Profesor> getProfesorByNombreApellido(
            @PathParam("nombre") String nombre,
            @PathParam("apellido") String apellido) {
        ProfesorDAO pr_dao = new ProfesorDAOImp();
        List<Profesor> list_pr;
        try {
            list_pr = pr_dao.findByNombreApellido(nombre, apellido);
        } catch (DAOException ex) {
            list_pr = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_pr;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateProfesor(@PathParam("id") Integer id, Profesor pr) {
        ProfesorDAO pr_dao = new ProfesorDAOImp();
        try {
            pr_dao.update(id, pr);
        } catch (DAOException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }

    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public void createProfesor(Profesor pr) {
        ProfesorDAO pr_dao = new ProfesorDAOImp();
        try {
            pr_dao.create(pr);
        } catch (DAOException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
        // return Response.status(200).entity(al).build();
    }
    
    @DELETE
    @Path("{id}")
    public void deleteProfesor(@PathParam("id") Integer id) {
        ProfesorDAO pr_dao = new ProfesorDAOImp();
        try {
            pr_dao.delete(id);
        } catch (DAOException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }
}
