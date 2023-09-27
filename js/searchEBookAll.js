$(document).ready(function() {
   $(function showEBookTitle(data){
    $("#searchEBookTitle").autocomplete({
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
                url: "/AutoLib/EBookSearch/EBookSearchServlet?",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	title : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.title);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.title };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});	  
$(function showEBookAuthor(data) {	  
	  $( "#searchEBookAuthor" ).autocomplete({
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
                url: "/AutoLib/EBookSearch/EBookSearchServlet",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	authorName : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.authorName);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.authorName };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
  $(function showEBookSubject(data) {	  
	  $( "#searchEBookSubject" ).autocomplete({
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
                url: "/AutoLib/EBookSearch/EBookSearchServlet",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	subName : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.subName);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.subName };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });

  
  });
  
