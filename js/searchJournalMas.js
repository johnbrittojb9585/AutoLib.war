$(document).ready(function() {
   $(function showJournalCode(data){
    $("#searchJournalCode").autocomplete({
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
                url: "/AutoLib/Journal/JournalServlet?flag="+data,
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	jcode : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.jcode);
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
                url: "/AutoLib/Journal/JournalServlet?flag="+data,
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	jname : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.jname);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.code, value: item.name };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});

$(function showJournalPub(data){
    $("#searchJournalPub").autocomplete({
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
                url: "/AutoLib/Journal/JournalServlet?flag="+data,
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	pname : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.pname);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.sp_code, value: item.sp_name };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});
	  
$(function showJournalDept(data) {	  
	  $( "#searchJournalDept" ).autocomplete({
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
                url: "/AutoLib/Journal/JournalServlet?flag="+data,
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	dname : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.dname);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.code, value: item.name };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
  $(function showJournalSubject(data){
    $("#searchJournalSubject").autocomplete({
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
                url: "/AutoLib/Journal/JournalServlet?flag="+data,
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	sname : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.sname);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.code, value: item.name };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});
  
  });
  
