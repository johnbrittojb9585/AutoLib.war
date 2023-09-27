$(document).ready(function() {
   $(function showName(data){
    $("#searchName").autocomplete({
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
                url: "/AutoLib/NewsClipSearch/NewsClipSearchServlet?flag="+data,
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
$(function showType(data) {	  
	  $( "#searchType" ).autocomplete({
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
                url: "/AutoLib/NewsClipSearch/NewsClipSearchServlet?flag="+data,
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
            console.log (item.type);
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
  
  $(function showTitle(data) {	  
	  $( "#searchTitle" ).autocomplete({
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
                url: "/AutoLib/NewsClipSearch/NewsClipSearchServlet?flag="+data,
                type: "GET",
                async : false,
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
                value: item.ntitle };
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
                url: "/AutoLib/NewsClipSearch/NewsClipSearchServlet?flag="+data,
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

  $(function showKeywords(data) {	  
	  $( "#searchKeywords" ).autocomplete({
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
                url: "/AutoLib/NewsClipSearch/NewsClipSearchServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	keywords : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.keywords);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.nkey };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });

  
  });
  
