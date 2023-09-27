$(document).ready(function() {
   $(function showEBookAccessNo(data){
    $("#searchEBookAccessNo").autocomplete({
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
                url: "/AutoLib/EBook/EBookServlet?flag="+data,
                type: "GET",
                async : true,
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

$(function showEBookCallNo(data){
    $("#searchEBookCallNo").autocomplete({
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
                url: "/AutoLib/EBook/EBookServlet?flag="+data,
                type: "GET",
                async : true,
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
                url: "/AutoLib/EBook/EBookServlet?flag="+data,
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
                id: item.accessNo, value: item.title };
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
                url: "/AutoLib/EBook/EBookServlet?flag="+data,
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
                id: item.code, value: item.name };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
  $(function showEBookPublisher(data){
    $("#searchEBookPublisher").autocomplete({
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
                url: "/AutoLib/EBook/EBookServlet?flag="+data,
                type: "GET",
                async : true,
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
                id: item.sp_code, value: item.sp_name };
            }));
                	
			     },   
				  
            }); 
        },
        select: function (event, ui) {
        	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
                 $("#searchEBookPublisher").val(ui.item.value); // display the selected text
                 $("#searchpub").val(ui.item.id); // save selected id to hidden input
        		 
        		// console.log($("#searchDeptCode").val + " has faded!");
                 return false;
             },
      });
});
  

  $(function showEBookSupplier(data){
    $("#searchEBookSupplier").autocomplete({
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
                url: "/AutoLib/EBook/EBookServlet?flag="+data,
                type: "GET",
                async : true,
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
                id: item.sp_code, value: item.sp_name };
            }));
                	
			     },   
				  
            }); 
        },
        select: function (event, ui) {
        	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
                 $("#searchEBookSupplier").val(ui.item.value); // display the selected text
                 $("#searchSup").val(ui.item.id); // save selected id to hidden input
        		 
        		// console.log($("#searchDeptCode").val + " has faded!");
                 return false;
             },
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
                url: "/AutoLib/EBook/EBookServlet?flag="+data,
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
                id: item.code, value: item.name };
            }));
                	
			     },   
				  
            }); 
        },

select: function (event, ui) {
	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchEBookSubject").val(ui.item.value); // display the selected text
         $("#searchSub").val(ui.item.id); // save selected id to hidden input
		 
		 console.log($("#searchDeptCode").val + " has faded!");
         return false;
     },
		
      });	  
	  
  });
  
  $(function showEBookDept(data){
    $("#searchEBookDept").autocomplete({
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
                url: "/AutoLib/EBook/EBookServlet?flag="+data,
                type: "GET",
                async : true,
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
                id: item.code, value: item.name };
            }));
                	
			     },   
				  
            }); 
        }, 
    select: function (event, ui) {
    	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
             $("#searchEBookDept").val(ui.item.value); // display the selected text
             $("#searchDept").val(ui.item.id); // save selected id to hidden input
   ;		 
    	//	 console.log($("#searchAuthorCode").val + " has faded!");
             return false;
         },
    
    
    
      });
});

  
  });
  
