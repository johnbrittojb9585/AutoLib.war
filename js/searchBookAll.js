$(document).ready(function() {
   $(function showAccessNo(data){
    $("#searchBookCode").autocomplete({
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
                url: "/AutoLib/Book/BookServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	accessNo : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.accessNo);
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

$(function showCallNo(data){
    $("#searchCallNo").autocomplete({
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
                url: "/AutoLib/Book/BookServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	callNo : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.callNo);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.callNo, value: item.callNo };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});

$(function showTitle(data){
    $("#searchTitle").autocomplete({
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
                url: "/AutoLib/Book/BookServlet?flag="+data,
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
                id: item.accessNo, value: item.title };
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
                url: "/AutoLib/Book/BookServlet?flag="+data,
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
                id: item.accessNo, value: item.author };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
  $(function showPublisher(data){
    $("#searchPublisher").autocomplete({
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
                url: "/AutoLib/Book/BookServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	pubName : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.pubName);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.accessNo, value: item.pubName };
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
                url: "/AutoLib/Book/BookServlet?flag="+data,
                type: "GET",
                async : false,
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
                id: item.accessNo, value: item.subName };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
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
                url: "/AutoLib/Book/BookServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	deptName : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.deptName);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.accessNo, value: item.deptName };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});

$(function showSupplier(data){
    $("#searchSupplier").autocomplete({
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
                url: "/AutoLib/Book/BookServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	supName : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.supName);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.accessNo, value: item.supName };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});

$(function showBudget(data){
    $("#searchBudget").autocomplete({
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
                url: "/AutoLib/Book/BookServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	budName : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.budName);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.accessNo, value: item.budName };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});

$(function showKeywords(data){
    $("#searchKeywords").autocomplete({
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
                url: "/AutoLib/Book/BookServlet?flag="+data,
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
                id: item.accessNo, value: item.keywords };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});

  
  });
  
