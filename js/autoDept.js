$(function showData(data) {
    $( "input#searchDept" ).autocomplete({
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
                url: "/AutoLib/Department/DepartmentServlet?flag="+data,
                type: "POST",
                async : false,
                dataType: "json",
                data : {
					name : request.term
				 },
                success: function (data) {
                	if (typeof Array.prototype.forEach != 'function') {
					    Array.prototype.forEach = function(callback){
					      for (var i = 0; i < this.length; i++){
					        callback.apply(this, [this[i], i,this]);
					      }
					    };
					}

					var values = data;
					var newArray = new Array(values.length);
					var i = 0;
					 values.forEach(function (entry) {
	                    var newObject = {
	                        label: entry.name
	                    };
	                    newArray[i] = newObject;
	                    i++;
	                });

					  response(newArray);
			     }   
				  
            }); 
        },  
        minLength: 1
    });
  });

