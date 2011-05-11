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
    
    /**
     * Add button
     * 
     * @element addButton class
     * @event click
     * @action Return to controller index
     */
    $('.addButton').bind('click', function(){
        window.location.replace("/"+(document.location.href).split("/")[3]+"/add");
    });
    
    /**
     * Search button
     * 
     * @element searchButton class
     * @event click
     * @action Return to controller search
     */
    $('.searchButton').bind('click', function(){
        window.location.replace("/"+(document.location.href).split("/")[3]+"/search");
    });
    
    /**
     * Timepicker Field
     * 
     * @element timePickerField class
     * @event Init timepicker plugin
     * @action Enable field for timepicker
     */
    $('.timePickerField').timepicker({
        timeOnlyTitle: 'Heure de départ',
        timeText: 'Départ a',
        hourText: 'Heure',
        minuteText: 'Minute',
        currentText: 'Heure actuelle',
        closeText: 'Valider'
    });
    
    /**
     * Datepicker Field
     * 
     * @element datePickerField class
     * @event Init datepicker plugin
     * @action Enable field to datepicker
     */
    $('.datePickerField').datepicker({
        minDate: 0
    });
    
    /**
     * Focus on search field of Covami
     * 
     * @element searchField input name
     * @event Focus on
     * @action Erase placeholder if nesserary
     */
    $('input[name=searchField]').bind("focus", function(){
        var that = $(this);
                
        if (that.val() == msg.search.invite) {
            that.val("");
        }
    });

    /**
     * Blur of search field of Covami 
     * 
     * @element searchField input name
     * @event Blur out
     * @action Replace text if nesserary
     */
    $('input[name=searchField]').bind("blur", function(){
        var that = $(this);
                
        if (that.val() == "") {
            that.val(msg.search.invite);
        }
    });
});