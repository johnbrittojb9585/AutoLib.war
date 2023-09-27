$(function showDesig(data) {
	    $( "#searchDesigName" ).autocomplete({
    	width: 100,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: false,
        highlight: true,
            	
    	source: function (request, response) {
					
            $.ajax({
                url: "/AutoLib/Desig/DesignationServlet?flag="+data,
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
                //id: item.code, value: item.name,desc: item.desc,email: item.email };
				code: item.code, name: item.name, desc: item.desc };
            }));
                	
			     },   
			}); 
        },
select: function (event, ui) {
	alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchDesigName").val(ui.item.value); // display the selected text
         $("#searchDesigCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchDesigDesc").val(ui.item.desc);
         	 
		 console.log($("#searchDesigCode").val + " has faded!");
         return false;
     },
  
      });
  });
