$(document).ready(function(){
    
    /**
     * Cancel button
     * 
     * @element cancelButton class
     * @event click
     * @action Return to personnal dashboard
     */
    $('.cancelButton').bind('click', function(){
        window.location.replace("/");
    });
    
    $('.addCarButton').bind('click', function(){
        window.location.replace("/car/add");
    });
});