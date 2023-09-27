$(document).ready(function() {
   $(function showStatDept(data){
    $("#searchStatDept").autocomplete({
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
                url: "/AutoLib/Statistics/StatisticsServlet",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	txtdepartment : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.txtdepartment);
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
$(function showStatSubject(data) {	  
	  $( "#searchStatSubject" ).autocomplete({
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
                url: "/AutoLib/Statistics/StatisticsServlet",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	txtsubject : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.txtsubject);
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
  
  $(function showStatPublisher(data) {	  
	  $( "#searchStatPublisher" ).autocomplete({
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
                url: "/AutoLib/Statistics/StatisticsServlet",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	txtpublisher : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.txtpublisher);
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

  $(function showStatSupplier(data) {	  
	  $( "#searchStatSupplier" ).autocomplete({
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
                url: "/AutoLib/Statistics/StatisticsServlet",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	txtsupplier : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.txtsupplier);
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
  
