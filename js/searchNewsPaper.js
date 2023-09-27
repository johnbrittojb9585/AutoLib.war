$(document).ready(function() {
   $(function showNewsPaperName(data){
    $("#searchNewsPaperName").autocomplete({
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
                url: "/AutoLib/Newsclliping/NewscllipingServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	name : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.name);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.nname };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});	  
$(function showNewsPaperType(data) {	  
	  $( "#searchNewsPaperType" ).autocomplete({
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
                url: "/AutoLib/Newsclliping/NewscllipingServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	type : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.ntype);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.ntype };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
  $(function showNewsPaperSubject(data) {	  
	  $( "#searchNewsPaperSubject" ).autocomplete({
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
                url: "/AutoLib/Newsclliping/NewscllipingServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	subject : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.subject);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.nsubject };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });

  
  });
  
