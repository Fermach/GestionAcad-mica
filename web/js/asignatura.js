/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*jslint browser:true, devel:true, white:true, vars:true */
/*global $:false, intel:false */
// variables para el jslint

/**
 * Creamos el objeto asignatura y todos sus métodos.
 */
$.asignatura = {};
// Configuración del HOST y URL del servicio
$.asignatura.HOST = 'http://localhost:8080';
// $.alumno.URL = '/GA-JPA/webresources/com.iesvdc.acceso.entidades.alumno';
$.asignatura.URL = '/GestionAcademica/rest/asignatura';

$.asignatura.panel_alta = '#panel_al_asig';
$.asignatura.panel_list = '#panel_li_asig';
$.asignatura.panel_modi = '#panel_mo_asig';
$.asignatura.panel_borr = '#panel_bo_asig';
$.asignatura.panel_erro = '#panel_error';

/**
 Esta función hace la llamada REST al servidor y crea la tabla con todos los profesores.
 */
$.profesor.ProfesorReadREST = function () {
    // con esta función jQuery hacemos la petición GET que hace el findAll()
    $.controller.doGet(
            this.HOST + this.URL,
            function (json) {
                $($.asignatura.panel_list).empty();
                $($.asignatura.panel_list).append('<h4>Listado de Asignaturas</h4>');
                var table = $('<table />').addClass('responsive-table highlight');

                table.append($('<thead />').append($('<tr />').append('<th>Nombre</th>', '<th>ID</th>', '<th>Curso</th>', '<th>Ciclo</th>')));
                var tbody = $('<tbody />');
                for (var clave in json) {
                    tbody.append($('<tr />').append('<td>' + json[clave].nombre + '</td>','<td>' + json[clave].id + '</td>',
                            '<td>' + json[clave].curso + '</td>', '<td>' + json[clave].ciclo + '</td>'));
                }
                table.append(tbody);

                $($.asignatura.panel_list).append($('<div />').append(table));
                //$('tr:odd').css('background', '#CCCCCC');
            });
};

/**
 Crea un desplegable, un select, con todos los profesores del servicio para seleccionar el profesor a eliminar
 */
$.profesor.ProfesorDeleteREST = function (id) {
    // si pasamos el ID directamente llamamos al servicio DELETE
    // si no, pintamos el formulario de selección para borrar.
    if (id !== undefined) {
        id = $('#d_as_sel').val();
        // doDelete (target, id, fn_exito)
        $.controller.doDelete(
            $.asignatura.HOST + $.asignatura.URL,
            id,
            function () {
                // probamos que se ha actualizado cargando de nuevo la lista -no es necesario-
                $.asignatura.AsignaturaReadREST();
                // cargamos el panel listado
                $.controller.activate($.asignatura.panel_list);
            });
    } else {
        // doGet (target, fn_exito)
        $.controller.doGet(
            this.HOST + this.URL,
            function (json) {
                // pintamos el formulario para ver a quien modificar
                $('select').material_select('destroy');
                $($.asignatura.panel_borr).empty();
                var formulario = $('<div />');
                formulario.addClass('input-field');
                var div_select = $('<div />');
                div_select.addClass('form-group');
                var select = $('<select id="d_as_sel" />');
                select.addClass('form-group');
                for (var clave in json) {
                    select.append('<option value="' + json[clave].id + '">' + json[clave].nombre + ' ' + json[clave].curso + ' ' + json[clave].ciclo+ '</option>');
                }
                formulario.append(select);
                formulario.append('<div class="form-group"></div>').append('<div class="btn btn-danger" onclick="$.asignatura.AsignaturaDeleteREST(1)"> eliminar! </div>');
                $($.asignatura.panel_borr).append(formulario);
                $('select').material_select();
            }); 
    }

};

/**
 Función para la gestión de errores y mensajes al usuario
 */
$.asignatura.error = function (title, msg) {
    $($.asignatura.panel_erro).empty();
    $($.asignatura.panel_erro).append('<h4>' + title + '</h4>');
    $($.asignatura.panel_erro).append('<p>' + msg + '</p>');

    // cargamos el panel con id r_alumno.
    $.controller.activate($.asignatura.panel_erro);
};