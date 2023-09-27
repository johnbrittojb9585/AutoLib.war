$(function showCurrency(data) {
	var cache = {};
    $( "#searchCurrency" ).autocomplete({
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
                url: "/AutoLib/Currency/CurrencyServlet?flag=",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	Currency : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.Currency);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.code, value: item.currency,indian_value: item.indian_value,remarks: item.remarks };
            }));
                	
			     },   
			}); 
        },
select: function (event, ui) {
	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchCurrency").val(ui.item.value); // display the selected text
         $("#searchCurrencyCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchCurrencyVal").val(ui.item.indian_value);
         $("#searchCurrencyRemarks").val(ui.item.remarks);		 
		 console.log($("#searchCurrencyCode").val + " has faded!");
         return false;
     },
  
      });
  });
