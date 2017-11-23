/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('[id^="menu_"]').each(function () {
    var id_menu = $(this).attr('id');
    var id_panel = id_menu.attr('id').replace('menu_', 'panel_');
    $("#"+id_panel).click( function(){
                $(".panel").hide();
                $("#"+id_panel).show();
    });
    console.log("id_menu::"+id_menu+"  id_panel"+id_panel);
});