$(document).ready(function() {
   $(function showFineId(data){
    $("#searchFineId").autocomplete({
    	width: 500,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: true,
        highlight: false,
            	
    	source: function (request, response) {
            $.ajax({
                url: "/AutoLib/Fine/FineServlet",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	FCODE : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.FCODE);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.fcode, gcode: item.gcode, fperiod: item.fperiod, famount: item.famount, type: item.type };
            }));
                	
			     },   
				  
            }); 
        } ,

select: function (event, ui) {
	alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.gcode) : "Nothing selected, input was " + this.value);
         $("#searchFineGroup").val(ui.item.gcode); // display the selected text
         $("#searchFinePeriod").val(ui.item.fperiod); // save selected id to hidden input
		 $("#searchFineAmount").val(ui.item.famount);
         $("#searchFineType").val(ui.item.type);		 
		 console.log($("#searchFineId").val + " has faded!");
         return false;
     },

		
      });
});	  
$(function showFineGroup(data) {	  
	  $( "#searchFineGroup" ).autocomplete({
    	width: 500,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: true,
        highlight: false,
            	
    	source: function (request, response) {
            $.ajax({
                url: "/AutoLib/Fine/FineServlet",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	GROUPNAME : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.GROUPNAME);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.name };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
    
  });
  
