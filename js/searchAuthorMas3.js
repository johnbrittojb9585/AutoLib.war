function monkeyPatchAutocomplete() {

          // Don't really need to save the old fn, 
          // but I could chain if I wanted to
          var oldFn = $.ui.autocomplete.prototype._renderItem;

          $.ui.autocomplete.prototype._renderItem = function( ul, item) {
              var re = new RegExp("^" + this.term, "i") ;
              var t = item.label.replace(re,"<span style='font-weight:bold;color:Blue;'>" + this.term + "</span>");
              return $( "<li></li>" )
                  .data( "item.autocomplete", item )
                  .append( "<a>" + t + "</a>" )
                  .appendTo( ul );
          };
      }

$(function showAuthor(data) {
	monkeyPatchAutocomplete();
	var cache = {};
    $( "#searchAuthorName" ).autocomplete({
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
                url: "/AutoLib/Author/AuthorServlet?flag=",
                type: "GET",
                async : true,
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
                id: item.code, value: item.name ,desc: item.desc,email: item.email };
            }));
                	
			     },   
			}); 
        },
select: function (event, ui) {
	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchAuthorName").val(ui.item.value); // display the selected text
         $("#searchAuthorCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchAuthorDesc").val(ui.item.desc);
         $("#searchAuthorEmail").val(ui.item.email);		 
		 console.log($("#searchAuthorCode").val + " has faded!");
         return false;
     },
  
      });
  });
