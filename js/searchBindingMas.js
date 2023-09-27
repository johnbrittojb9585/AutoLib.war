$(function showBinding(data) {
	var cache = {};
    $( "#searchBinding" ).autocomplete({
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
                url: "/AutoLib/Binding/BindingServlet?flag=",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	sp_name : request.term
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.sp_name);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.code, value: item.name,desc: item.desc,add1: item.add1,add2: item.add2,city: item.city,state: item.state,country: item.country,pin: item.pin,phone: item.phone,fax: item.fax,email: item.email,url: item.url };
            }));
                	
			     },   
			}); 
        },
select: function (event, ui) {
	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchBinding").val(ui.item.value); // display the selected text
         $("#searchBindingCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchBindingDesc").val(ui.item.desc);
         $("#searchBindingAddress1").val(ui.item.add1);
         $("#searchBindingAddress2").val(ui.item.add2);	
         $("#searchBindingCity").val(ui.item.city);	
         $("#searchBindingState").val(ui.item.state);
         $("#searchBindingCountry").val(ui.item.country);
         $("#searchBindingPincode").val(ui.item.pin);	
         $("#searchBindingPhone").val(ui.item.phone);	
		 $("#searchBindingFax").val(ui.item.fax);
         $("#searchBindingEmail").val(ui.item.email);
         $("#searchBindingUrl").val(ui.item.url);		 
		 console.log($("#searchBindingCode").val + " has faded!");
         return false;
     },
  
      });
  });
