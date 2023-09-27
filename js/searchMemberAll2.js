$(document).ready(function() {
   $(function showMemberCode(data){
    $("#searchMemberCode").autocomplete({
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
                url: "/AutoLib/Member/MemberServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	Code : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.Code);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.code,value: item.name,decode: item.decode,deptcode: item.deptcode };
			   }));
                	
			     },   
				  
            }); 
        } ,

select: function (event, ui) {
	alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchMemberName").val(ui.item.value); // display the selected text
         $("#searchMemberCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchMemberDesig").val(ui.item.decode);
         $("#searchMemberDept").val(ui.item.deptcode);		 
		 console.log($("#searchMemberCode").val + " has faded!");
         return false;
     },

		
      });
});

$(function showMemberName(data){
    $("#searchMemberName").autocomplete({
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
                url: "/AutoLib/Member/MemberServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	Name : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.Name);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.name,value: item.name };
            }));
                	
			     },   
				  
            }); 
        }  
      });
});

$(function showMemberDesig(data){
    $("#searchMemberDesig").autocomplete({
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
                url: "/AutoLib/Member/MemberServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	Desig : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.Desig);
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
	  
$(function showMemberDept(data) {	  
	  $( "#searchMemberDept" ).autocomplete({
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
                url: "/AutoLib/Member/MemberServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	Dname : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.Dname);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.name,value: item.name };
            }));
                	
			     },   
				  
            }); 
        }  
      });	  
	  
  });
  
  $(function showMemberGroup(data){
    $("#searchMemberGroup").autocomplete({
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
                url: "/AutoLib/Member/MemberServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	Gname : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.Gname);
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
  
  $(function showMemberCourse(data) {	  
	  $( "#searchMemberCourse" ).autocomplete({
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
                url: "/AutoLib/Member/MemberServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	Cname : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.Cname);
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
  
  $(function showMemberCity(data){
    $("#searchMemberCity").autocomplete({
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
                url: "/AutoLib/Member/MemberServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	City : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.City);
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
  
