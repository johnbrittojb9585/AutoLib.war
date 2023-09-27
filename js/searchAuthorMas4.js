    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }

$(function showAuthor(data) {
    $( "#searchAuthorName" )
	      // don't navigate away from the field on tab when selecting an item
      .on( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).autocomplete( "instance" ).menu.active ) {
          event.preventDefault();
        }
      })
	.autocomplete({
    	width: 100,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: false,
        highlight: true,
            	
      source: function( request, response ) {    				
            $.ajax({
                url: "/AutoLib/Author/AuthorServlet?flag=",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	name : extractLast(request.term)
				 },
				 
				 //source: function( request, response ) {
                // response( $.ui.autocomplete.filter(
                  //   item, extractLast( data ) ) );
             //},
				 
                success: function (data) {
					
					//response(data);
					//response ($.map(data, function (item)
					//response ( $.ui.autocomplete.filter($.map(extractLast(data), function (item))
					//response ( $.ui.autocomplete.filter($.map(item,data)
					response ( $.ui.autocomplete.filter(item,(extractLast(data) ))
            //{
            //console.log (item.name);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.code, value: item.name ,desc: item.desc,email: item.email };
            }));
                	
			     },   
			}
			}); 
        },
		
		 focus: function() {
                 return false;
             },

					 
select: function (event, ui) {
	     		  
	alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchAuthorName").val(ui.item.value); // display the selected text
         $("#searchAuthorCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchAuthorDesc").val(ui.item.desc);
         $("#searchAuthorEmail").val(ui.item.email);		 
		 console.log($("#searchAuthorCode").val + " has faded!");
         return false;
		 
		 var terms = split( this.value );
          // remove the current input
          terms.pop();
          // add the selected item
          terms.push( ui.item.value );
          // add placeholder to get the comma-and-space at the end
          terms.push( "" );
          this.value = terms.join( ", " );
          return false;
     },
	 
	  
      });
  });
