
$(document).ready(
        function () {

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
            
            $('[id^="menu_"]').each(function () {
                var $this = $(this);
                var menu_id = $this.attr('id');
                var panel_id = menu_id.replace('menu_', 'panel_');
                
                $("#" + menu_id).click(function () {
                    $(".panel").hide();
                    $("#" + panel_id).show();
                });
                console.log("id_menu::" + menu_id + "  id_panel" + panel_id);
            });

            $(".panel").hide();
            $("#panel_main").show();

        });






