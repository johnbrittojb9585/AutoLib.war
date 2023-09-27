$(function showPubSup(data) {
	var cache = {};
    $( "#searchPubSupName").autocomplete({
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
                url: "/AutoLib/PubSup/PubSupServlet?flag="+data,
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
                id: item.sp_code, value: item.sp_name, short_desc: item.short_desc,address1: item.address1, address2: item.address2, city: item.city, state: item.state, country: item.country, pincode: item.pincode, phone: item.phone, fax: item.fax, emailid: item.emailid, urlid: item.urlid, sp_type: item.sp_type };
			    //id: item.sp_code, value: item.sp_name };
            }));
                	
			     },   
			}); 
        },
select: function (event, ui) {
	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchPubSupName").val(ui.item.value); // display the selected text
         $("#searchPubSupCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchPubSupDesc").val(ui.item.short_desc);
		 $("#searchPubSupAddress1").val(ui.item.address1);
         $("#searchPubSupAddress2").val(ui.item.address2);		 
		 $("#searchPubSupCity").val(ui.item.city);
		 $("#searchPubSupState").val(ui.item.state);
		 $("#searchPubSupCountry").val(ui.item.country);
		 $("#searchPubSupPincode").val(ui.item.pincode);
		 $("#searchPubSupPhone").val(ui.item.phone);
		 $("#searchPubSupFax").val(ui.item.fax);
		 $("#searchPubSupEmail").val(ui.item.emailid);
		 $("#searchPubSupUrl").val(ui.item.urlid);
		 $("#searchPubSupType").val(ui.item.sp_type);
		 console.log($("#searchPubSupCode").val + " has faded!");
         return false;
     },
  
      });
  });
