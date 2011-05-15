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
    
    /**
     * Click on delete item button
     * 
     * @element delete_conformation link
     * @event Click
     * @action Window confirmation for deleting
     */
    $('.delete_confirmation').bind("click", function(){
        if (confirm(msg.confirmation.remove_item))
            return true;
        
        return false;
    });
    
    /**
     * Focus on search new friend
     * 
     * @element searchFriendField input name
     * @event Focus on
     * @action Erase placeholder if nesserary
     */
    $('#searchFriendField').bind("focus", function(){
        var that = $(this);     
        if (that.val() == msg.friend.findPlaceholder) {
            that.val("");
        }
    });
    
    /**
     * Blur of filter of friend of Covami 
     * 
     * @element searchFriendField input name
     * @event Blur out
     * @action Replace text if nesserary
     */
    $('#searchFriendField').bind("blur", function(){
        var that = $(this);
                
        if (that.val() == "") {
            that.val(msg.friend.findPlaceholder);
        }
    });
    
    /**
     * Masque les amis non correspondant à la recherche
     * 
     * @element searchFriendField
     * @event   onKeyUp
     * @action  Hide
     */
    $('#searchFriendField').bind("keyup", function(event){
        var that = $(this);
        
        $('.friend').show();
        $('.friend').each(function(i,e){
            if($(e).find('h4').text().toLowerCase().indexOf(that.val().toLowerCase()) == -1){
                $(e).hide();
            }
        });
    });
    
    $('.citySelector').bind("change", function(){
        var that = $(this);
        
        if (that.attr('id') == "fieldWaystartCity") {
            $('#fieldWayfinishCity option').removeAttr("disabled");
            $('#fieldWayfinishCity option[value="' + that.val() + '"]').attr('disabled', 'disabled');
        } else {
            $('#fieldWaystartCity option').removeAttr("disabled");
            $('#fieldWaystartCity option[value="' + that.val() + '"]').attr('disabled', 'disabled');
        }
        
        $('#fieldWayfinishCity option[value=-1], #fieldWaystartCity option[value=-1]').attr("disabled", "disabled");
    });
    
});