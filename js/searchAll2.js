$(document).ready(function() {
   $(function showTitle(data){
    $("#searchTitle").autocomplete({
    	width: 500,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: false,
        cacheLength: 1,
        scroll: true,
        highlight: false,
            	
    	source: function (request, response) {
            $.ajax({
                url: "/AutoLib/Simples/SimpleServlet?flag="+data,
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	Title : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.title);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.accno, value: item.title };
            }));
                	
			     },   
				  
            }); 
        },

//select: function (event, ui) {
	//     $("#searchTitle").css("color","green"); // display the selected text
    
      // },

		
      });
});	  
$(function showAuthor(data) {	  
	  $( "#searchAuthor" ).autocomplete({
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
                url: "/AutoLib/Simples/SimpleServlet?flag="+data,
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	Author : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.author);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.accno, value: item.author };
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
                url: "/AutoLib/Simples/SimpleServlet?flag="+data,
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
                id: item.accno, value: item.subject };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });

  
  });
  
