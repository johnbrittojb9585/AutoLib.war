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
                url: "/AutoLib//Journal_Artical/JournalArtServlet",
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
                value: item.code,name: item.name };
            }));
                	
			     },   
				  
            }); 
        } ,
select: function (event, ui) {
	//alert(ui.item ? ("You picked '" + ui.item.value + "' with the name of " + ui.item.name) : "Nothing selected, input was " + this.value);
         $("#searchJournalName").val(ui.item.name); // display the selected text
         $("#searchJournalCode").val(ui.item.value); // save selected id to hidden input
		 		 
		 console.log($("#searchJournalCode").val + " has faded!");
         return false;
     },
		
      });
});	  
$(function showJournalName(data) {	  
	  $( "#searchJournalName" ).autocomplete({
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
                url: "/AutoLib/Journal_Artical/JournalArtServlet",
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
                value: item.name };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
  $(function showJournalAtlNo(data) {	  
	  $( "#searchJournalAtlNo" ).autocomplete({
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
                url: "/AutoLib/Journal_Artical/JournalArtServlet",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	atlno : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.atlno);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.atlno };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });

  $(function showJournalIssueAccessNo(data) {	  
	  $( "#searchJournalIssueAccessNo" ).autocomplete({
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
                url: "/AutoLib/Journal_Artical/JournalArtServlet",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	issueaccessno : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.issueaccessno);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.iss_acc_no };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });

  $(function showJournalAtlSubject(data) {	  
	  $( "#searchJournalAtlSubject" ).autocomplete({
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
                url: "/AutoLib/Journal_Artical/JournalArtServlet",
                type: "GET",
                async : true,
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
                value: item.subject };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
  });
  
