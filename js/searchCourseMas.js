$(function showCourse(data) {
	var cache = {};
    $( "#searchCourseName" ).autocomplete({
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
                url: "/AutoLib/Course/CourseServlet?flag=",
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
                //id: item.code, value: item.name,desc: item.desc,email: item.email };
				id: item.code, value: item.name, major: item.major, period: item.period, type: item.type, desc: item.desc };
            }));
                	
			     },   
			}); 
        },
select: function (event, ui) {
	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchCourseName").val(ui.item.value); // display the selected text
         $("#searchCourseCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchCourseMajor").val(ui.item.major);
		 $("#searchCoursePeriod").val(ui.item.period);
		 $("#searchCourseType").val(ui.item.type);
		 $("#searchCourseDesc").val(ui.item.desc);
         		 
		 console.log($("#searchCourseCode").val + " has faded!");
         return false;
     },
  
      });
  });
