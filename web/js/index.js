
$(document).ready(
        function () {
            $(".panel").hide();
            $("#main").show();
            
            $('.button-collapse').sideNav({
                menuWidth: 300, // Default is 300
                edge: 'left', // Choose the horizontal origin
                closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
                draggable: true // Choose whether you can drag to open on touch screens
              }
            );
            $('.button-collapse').sideNav('show');
            // $(".button-collapse").sideNav();
            $('.collapsible').collapsible();
            
            
        });
        
        console.log($('[id*=menu_]'));

$("[id*=menu_]").each(function () {
    var $id_menu = $(this).attr('id');
    var id_panel = $id_menu.attr('id').replace('menu_', 'panel_');
    $("#"+id_panel).click( function(){
        $(".panel").hide();
        $("#"+id_panel).show();
    });
    console.log("id_menu::"+ $(this) + "  id_panel"+id_panel);
});
        
        
        
        
        
