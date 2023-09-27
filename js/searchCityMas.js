$(function showCity(data) {
	var cache = {};
    $( "#searchCity" ).autocomplete({
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
                url: "/AutoLib/City/CityServlet?flag=",
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
                id: item.code, value: item.name,state: item.state,country: item.country,pincode: item.pincode,desc: item.desc };
            }));
                	
			     },   
			}); 
        },
select: function (event, ui) {
	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchCity").val(ui.item.value); // display the selected text
         $("#searchCityCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchCityState").val(ui.item.state);
         $("#searchCityCountry").val(ui.item.country);
         $("#searchCityPincode").val(ui.item.pincode);	
         $("#searchCityRemarks").val(ui.item.desc);		 
		 console.log($("#searchCityCode").val + " has faded!");
         return false;
     },
  
      });
  });
