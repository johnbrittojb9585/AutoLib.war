$(document).ready(function() {
   $(function showBulkCounterMemberId(data){
    $("#searchBulkCounterUserId").autocomplete({
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
                url: "/AutoLib/Bulk_Counter/BulkCounterServlet?flag=clar",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	mcode : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.mcode);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.code, value: item.code };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});	  

$(function showBulkCounterAccessNo(data) {	  
	  $( "#searchBulkCounterAccessNo" ).autocomplete({
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
                url: "/AutoLib/Bulk_Counter/BulkCounterServlet?flag=clar",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	accno : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.accno);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.accessNo, value: item.accessNo };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
    
  });
  
