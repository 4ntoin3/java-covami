$(document).ready(function(){
    
    /**
     * Cancel button
     * 
     * @element cancelButton class
     * @event click
     * @action Return to personnal dashboard
     */
    $('.cancelButton').bind('click', function(){
        window.location.replace("/"+(document.location.href).split("/")[3]);
    });
    
    $('.addButton').bind('click', function(){
//        window.location.replace("/car/add");
        window.location.replace("/"+(document.location.href).split("/")[3]+"/add");
    });
});