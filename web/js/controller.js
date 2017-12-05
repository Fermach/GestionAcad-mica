/**
 * Este Objeto es usado para controlar el panel activo en cada momento
 * y es llamado desde los otros objetos.
 */

$.controller = {};

$.controller.active_panel = "";

$.controller.activate = function (panel_name) {
    $($.controller.active_panel).hide();
    $(panel_name).show();
    $.controller.active_panel = panel_name;
};

$.controller.init = function () {
    $('[id^="menu_"]').each(function () {
        var $this = $(this);
        var menu_id = $this.attr('id');
        var panel_id = menu_id.replace('menu_', 'panel_');

        $("#" + menu_id).click(function () {
            $.controller.activate("#" + panel_id);
        });
        console.log("id_menu::" + menu_id + "  id_panel" + panel_id);
    });
    $(".panel").hide();
    $("#panel_main").show();
    $.controller.active_panel = "#panel_main";
};




