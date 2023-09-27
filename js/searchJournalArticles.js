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
                url: "/AutoLib/Journal_ArticleSearch/JNLArticleSearchServlet?flag="+data,
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
                value: item.jname };
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
                url: "/AutoLib/Journal_ArticleSearch/JNLArticleSearchServlet?flag="+data,
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
                value: item.atitle };
            }));
                	
			     },   
				  
            }); 
        }  
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
                url: "/AutoLib/Journal_ArticleSearch/JNLArticleSearchServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	author : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.author);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.author };
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
                url: "/AutoLib/Journal_ArticleSearch/JNLArticleSearchServlet?flag="+data,
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
                value: item.asubject };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });

  $(function showAbstract(data) {	  
	  $( "#searchAbstract" ).autocomplete({
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
                url: "/AutoLib/Journal_ArticleSearch/JNLArticleSearchServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	abstract : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.abstract);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.nabstract };
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
                url: "/AutoLib/Journal_ArticleSearch/JNLArticleSearchServlet?flag="+data,
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
                value: item.akeywords };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });

  
  });
  
