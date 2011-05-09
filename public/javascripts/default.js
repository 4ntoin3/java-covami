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
});