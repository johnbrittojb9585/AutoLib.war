$(function searchJournal(data) {
    $( "#journalSearch" ).autocomplete({
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
                url: "/AutoLib/JournalBrowse/JournalSearch?flag="+data,
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
            console.log (item.jnlName);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.jnlCode,value: item.jnlName };
            }));
                	
			     },   
				  
            }); 
        }  
      });
  });
