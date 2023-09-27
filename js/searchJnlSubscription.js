$(document).ready(function() {
   $(function showJournalName(data){
    $("#searchJournalName").autocomplete({
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
                url: "/AutoLib/journalSubscription/JournalSubscriptionServlet?flag="+data,
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	journalName : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.journalName);
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
$(function showSupplier(data) {	  
	  $( "#searchSupplier" ).autocomplete({
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
                url: "/AutoLib/journalSubscription/JournalSubscriptionServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	Supplier : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.Supplier);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.sp_name };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
    
  });
  
