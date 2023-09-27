$(document).ready(function() {
   $(function showDept(data){
    $("#searchDept").autocomplete({
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
                url: "/AutoLib/DBReport/DBReportServlet",
                type: "GET",
                async : false,
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
$(function showSubject(data) {	  
	  $( "#searchSubject" ).autocomplete({
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
                url: "/AutoLib/DBReport/DBReportServlet",
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
  
  $(function showPublisher(data) {	  
	  $( "#searchPublisher" ).autocomplete({
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
                url: "/AutoLib/DBReport/DBReportServlet",
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
                url: "/AutoLib/DBReport/DBReportServlet",
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
  
