$(function showQBSubjectCode(data) {
	var cache = {};
    $("#searchQBSubjectCode").autocomplete({
    	width: 100,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: false,
        highlight: true,
            	
    	source: function (request, response) {
			var term          = request.term.toLowerCase(),
        element       = this.element,
        cache         = this.element.data('autocompleteCache') || {},
        foundInCache  = false;

    $.each(cache, function(key, data){
      if (term.indexOf(key) === 0 && data.length > 0) {
        response(data);
        foundInCache = true;
        return;
      }
    });

      if (foundInCache) return;

			
            $.ajax({
                url: "/AutoLib/QBSubject/QBSubjectServlet?flag=",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	qbsubcode : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.qbsubcode);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.qbcode, value: item.qbsubcode ,qbsubname: item.qbsubname,qbsubdesc: item.qbsubdesc };
            }));
                	
			     },   
			}); 
        },
select: function (event, ui) {
	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchQBSubjectCode").val(ui.item.value); // display the selected text
         $("#searchQBSubCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchQBSubjectName").val(ui.item.qbsubname);
         $("#searchQBSubjectDesc").val(ui.item.qbsubdesc);		 
		 console.log($("#searchQBSubCode").val + " has faded!");
         return false;
     },
  
      });
  });
