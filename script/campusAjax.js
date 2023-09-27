     ////added by selvas
function sendRequest(url) {
	var divId = getDivId(url);
	
    var http;
    var browser = navigator.appName;
    
    var exportIndex = url.indexOf("-e=");
    if(exportIndex != -1){
      window.open(url);
    } else {
    if(browser == "Microsoft Internet Explorer"){
        http = new ActiveXObject("Microsoft.XMLHTTP");
    }else{
        http = new XMLHttpRequest();
    }

    http.open('POST', url, false);    
    http.send(null); 

	if (http.readyState == 4 && (http.status==200 || window.location.href.indexOf("http")==-1)){
		document.getElementById(divId).innerHTML=http.responseText;
    } else {
    	alert('AJAX error');
    }
   }
}

function getDivId(url){
	var divId = 'displayTag';
	return divId;
}

var req;
     ////added by selva
 /*
  * Get the second options by calling a Struts action
  */
 function displayBoardingPoint(){

    var displayBoardingPointCat = document.getElementById('displayBoardingPointCat');
    //Nothing selected
    if(displayBoardingPointCat.selectedIndex==0) {
	    document.getElementById('boardingpoint').options.length = 0;
	    document.getElementById('boardingpoint').options[0] = new Option("Please select BoardingPoint!", "0");
	     	return;
    }
    var selectedOption = displayBoardingPointCat.options[displayBoardingPointCat.selectedIndex].value;
  
    //get the (form based) params to push up as part of the get request
    url="retrieveBoardingpoint.do?selectedOption="+selectedOption;
  
    //Do the Ajax call
    if (window.XMLHttpRequest){ // Non-IE browsers
      req = new XMLHttpRequest();
      //A call-back function is define so the browser knows which function to call after the server gives a reponse back
      req.onreadystatechange = populateBoardingpoint;
      try {
      	req.open("POST", url, true); //was post
      } catch (e) {
         alert("Cannot connect to server");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE      
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = populateBoardingpoint;
        req.open("POST", url, true);
        req.send();
          
      }
    }
  }
  function populateBoardingpoint(){
  	document.getElementById('boardingpoint').options.length = 0;
	 if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
      	
         var textToSplit = req.responseText;
         if(textToSplit == '803'){
			alert("No select option available on the server");
		}
          //Split the document
          var returnElements=textToSplit.split("||");
          //Process each of the elements 	
          for ( var i=0; i<returnElements.length; i++ ){
             var valueLabelPair = returnElements[i].split(";");
             document.getElementById('boardingpoint').options[i] = new Option(valueLabelPair[1], valueLabelPair[0]);
          }
          // use global variable to do select element if any
          if(boardingPointId != '') {
          	var opt = document.getElementById('boardingpoint');
			SelectOptionInList(opt,boardingPointId);
          }
        }
      } else {  
            //alert("Bad response by the server");
        }
    }
    
      function SelectOptionInList( lstSelectList, intID )
      {
            try
            {
                  var intIndex = 0;
                  // Loop through all the options
                  for( intIndex = 0; intIndex < lstSelectList.options.length; intIndex++ )
                  {
                        // Is this the ID we are looking for?
                        if( lstSelectList.options[intIndex].value == intID ) 
                        {
                              // Select it
                              lstSelectList.selectedIndex = intIndex;
                              // Yes, so stop searching
                              break;
                        }
                  }
            }
            catch( expError )
            {
                  alert( "ajaxData.js::SelectOptionInList( ).\n" +
                              "Error:" + expError.number + ", " + expError.description );
            }
      } 
      
      
      
      ////added by selva
      
      
 /*
  * Get the second options by calling a Struts action
  */
 function displayCourseName(){

    var displayDegreeCat = document.getElementById('displayDegreeCat');
    //Nothing selected
    if(displayDegreeCat.selectedIndex==0) {
	    document.getElementById('courseDisplay').options.length = 0;
	    document.getElementById('courseDisplay').options[0] = new Option("#Please select degree#", "0");
	     	return;
    }
    var selectedOption = displayDegreeCat.options[displayDegreeCat.selectedIndex].value;
  
    //get the (form based) params to push up as part of the get request
    url="retrieveCourseName.do?selectedOption="+selectedOption;
  
    //Do the Ajax call
    if (window.XMLHttpRequest){ // Non-IE browsers
      req = new XMLHttpRequest();
      //A call-back function is define so the browser knows which function to call after the server gives a reponse back
      req.onreadystatechange = populateCourse;
      try {
      	req.open("POST", url, true); //was post
      } catch (e) {
         alert("Cannot connect to server");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE      
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = populateCourse;
        req.open("POST", url, true);
        req.send();
          
      }
    }
  }
  function populateCourse(){
  	document.getElementById('courseDisplay').options.length = 0;
	 if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
      	
         var textToSplit = req.responseText;
         if(textToSplit == '803'){
			alert("No select option available on the server");
		}
          //Split the document
          var returnElements=textToSplit.split("||");
          //Process each of the elements 	
          for ( var i=0; i<returnElements.length; i++ ){
             var valueLabelPair = returnElements[i].split(";");
             document.getElementById('courseDisplay').options[i] = new Option(valueLabelPair[1], valueLabelPair[0]);
          }
          // use global variable to do select element if any
          if(courseDisplayId != '') {
          	var opt = document.getElementById('courseDisplay');
			SelectOptionInList(opt,courseDisplayId);
          }
        }
      } else {  
            //alert("Bad response by the server");
        }
    }
    
      function SelectOptionInList( lstSelectList, intID )
      {
            try
            {
                  var intIndex = 0;
                  // Loop through all the options
                  for( intIndex = 0; intIndex < lstSelectList.options.length; intIndex++ )
                  {
                        // Is this the ID we are looking for?
                        if( lstSelectList.options[intIndex].value == intID ) 
                        {
                              // Select it
                              lstSelectList.selectedIndex = intIndex;
                              // Yes, so stop searching
                              break;
                        }
                  }
            }
            catch( expError )
            {
                  alert( "ajaxData.js::SelectOptionInList( ).\n" +
                              "Error:" + expError.number + ", " + expError.description );
            }
      }   
      
     //added by ramesh
     //examtype loading
     
     function displayExamType(){

    var displayExamTypeCat = document.getElementById('displayExamtypeCat');
    //Nothing selected
    if(displayExamTypeCat.selectedIndex==0) {
	    document.getElementById('examtype').options.length = 0;
	    document.getElementById('examtype').options[0] = new Option("Please select Semester!", "0");
	     	return;
    }
    var selectedOption = displayExamTypeCat.options[displayExamTypeCat.selectedIndex].value;
  
    //get the (form based) params to push up as part of the get request
    url="retrieveExamType.do?selectedOption="+selectedOption;
   
  //alert(selectedOption);
    //Do the Ajax call
    if (window.XMLHttpRequest){ // Non-IE browsers
      req = new XMLHttpRequest();
      //A call-back function is define so the browser knows which function to call after the server gives a reponse back
      req.onreadystatechange = populateExamType;
      try {
      	req.open("POST", url, true); //was post
      } catch (e) {
         alert("Cannot connect to server");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE      
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = populateExamType;
        req.open("POST", url, true);
        req.send();
          
      }
    }
  }
  function populateExamType(){
  	document.getElementById('examtype').options.length = 0;
  	//alert(document.getElementById('examtype').options.length);
	 if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
      	
         var textToSplit = req.responseText;
         if(textToSplit == '803'){
			alert("No select option available on the server");
		}
          //Split the document
          var returnElements=textToSplit.split("||");
          //Process each of the elements 	
          for ( var i=0; i<returnElements.length; i++ ){
             var valueLabelPair = returnElements[i].split(";");
           //  alert(valueLabelPair);
             document.getElementById('examtype').options[i] = new Option(valueLabelPair[1], valueLabelPair[0]);
          }
          // use global variable to do select element if any
          if(examtypeId != '') {
          
          	var opt = document.getElementById('examtype');
			SelectOptionInList(opt,examtypeId);
          }
        }
      } else {  
            //alert("Bad response by the server");
        }
    }
    
      function SelectOptionInList( lstSelectList, intID )
      {
            try
            {
                  var intIndex = 0;
                  // Loop through all the options
                  for( intIndex = 0; intIndex < lstSelectList.options.length; intIndex++ )
                  {
                        // Is this the ID we are looking for?
                        if( lstSelectList.options[intIndex].value == intID ) 
                        {
                              // Select it
                              lstSelectList.selectedIndex = intIndex;
                              // Yes, so stop searching
                              break;
                        }
                  }
            }
            catch( expError )
            {
                  alert( "ajaxData.js::SelectOptionInList( ).\n" +
                              "Error:" + expError.number + ", " + expError.description );
            }
      }  
 
 
 //added by Boopathi.K
 //Course Loading
 //Apr, 24, 2009
  function displayCourse(){

    var displayCourseCat = document.getElementById('displayCourseCat');
    //Nothing selected
    if(displayCourseCat.selectedIndex==0) {
	    document.getElementById('Course').options.length = 0;
	    document.getElementById('Course').options[0] = new Option("Please select Course!", "0");
	     	return;
    }
    var selectedOption = displayCourseCat.options[displayCourseCat.selectedIndex].value;
  
    //get the (form based) params to push up as part of the get request
    url="retrieveCourseSyllabus.do?selectedOption="+selectedOption;
  
    //Do the Ajax call
    if (window.XMLHttpRequest){ // Non-IE browsers
      req = new XMLHttpRequest();
      //A call-back function is define so the browser knows which function to call after the server gives a reponse back
      req.onreadystatechange = populateCourseSyllabus;
      try {
      	req.open("POST", url, true); //was post
      } catch (e) {
         alert("Cannot connect to server");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE      
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = populateCourseSyllabus;
        req.open("POST", url, true);
        req.send();
          
      }
    }
  }
  function populateCourseSyllabus(){
  	document.getElementById('Course').options.length = 0;
	 if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
      	
         var textToSplit = req.responseText;
         if(textToSplit == '803'){
			alert("No select option available on the server");
		}
          //Split the document
          var returnElements=textToSplit.split("||");
          //Process each of the elements 	
          for ( var i=0; i<returnElements.length; i++ ){
             var valueLabelPair = returnElements[i].split(";");
             document.getElementById('Course').options[i] = new Option(valueLabelPair[1], valueLabelPair[0]);
          }
          // use global variable to do select element if any
          if(courseId != '') {
          	var opt = document.getElementById('Course');
			SelectOptionInList(opt,courseId);
          }
        }
      } else {  
            //alert("Bad response by the server");
        }
    }
    
      function SelectOptionInList( lstSelectList, intID )
      {
            try
            {
                  var intIndex = 0;
                  // Loop through all the options
                  for( intIndex = 0; intIndex < lstSelectList.options.length; intIndex++ )
                  {
                        // Is this the ID we are looking for?
                        if( lstSelectList.options[intIndex].value == intID ) 
                        {
                              // Select it
                              lstSelectList.selectedIndex = intIndex;
                              // Yes, so stop searching
                              break;
                        }
                  }
            }
            catch( expError )
            {
                  alert( "ajaxData.js::SelectOptionInList( ).\n" +
                              "Error:" + expError.number + ", " + expError.description );
            }
      } 
      
//added by Boopathi.K
 //Subject Loading
 //Apr, 27, 2009
  function displaySubject(){

    var Course = document.getElementById('Course');
    //Nothing selected
    if(Course.selectedIndex==0) {
	    document.getElementById('Subject').options.length = 0;
	    document.getElementById('Subject').options[0] = new Option("Please select Subject!", "0");
	     	return;
    }
    
    var selectedOption = Course.options[Course.selectedIndex].value;
    //get the (form based) params to push up as part of the get request
    url="retrieveSubjectSyllabus.do?selectedOption="+selectedOption;
    //Do the Ajax call
    if (window.XMLHttpRequest){ // Non-IE browsers
      req = new XMLHttpRequest();
      //A call-back function is define so the browser knows which function to call after the server gives a reponse back
      req.onreadystatechange = populateSubjectSyllabus;
      try {
      	req.open("POST", url, true); //was post
      } catch (e) {
         alert("Cannot connect to server");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE      
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = populateSubjectSyllabus;
        req.open("POST", url, true);
        req.send();
          
      }
    }
  }
  function populateSubjectSyllabus(){
  	document.getElementById('Subject').options.length = 0;
	 if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
      	
         var textToSplit = req.responseText;
         if(textToSplit == '803'){
			alert("No select option available on the server");
		}
          //Split the document
          var returnElements=textToSplit.split("||");
          //Process each of the elements 	
          for ( var i=0; i<returnElements.length; i++ ){
             var valueLabelPair = returnElements[i].split(";");
             document.getElementById('Subject').options[i] = new Option(valueLabelPair[1], valueLabelPair[0]);
          }
          // use global variable to do select element if any
          if(subjectId != '') {
          	var opt = document.getElementById('Subject');
			SelectOptionInList(opt,subjectId);
          }
        }
      } else {  
            //alert("Bad response by the server");
        }
    }
    
      function SelectOptionInList( lstSelectList, intID )
      {
            try
            {
                  var intIndex = 0;
                  // Loop through all the options
                  for( intIndex = 0; intIndex < lstSelectList.options.length; intIndex++ )
                  {
                        // Is this the ID we are looking for?
                        if( lstSelectList.options[intIndex].value == intID ) 
                        {
                              // Select it
                              lstSelectList.selectedIndex = intIndex;
                              // Yes, so stop searching
                              break;
                        }
                  }
            }
            catch( expError )
            {
                  alert( "ajaxData.js::SelectOptionInList( ).\n" +
                              "Error:" + expError.number + ", " + expError.description );
            }
      }      
      
//added by Boopathi.K
 //Exam Subject Loading
 //May, 07, 2009
  function displayExamSubject(){
    var displaySemesterCat = document.getElementById('displaySemesterCat');
    //Nothing selected
    if(displaySemesterCat.selectedIndex==0) {
	    document.getElementById('displaySubjectCat').options.length = 0;
	    document.getElementById('displaySubjectCat').options[0] = new Option("Please select Subject!", "0");
	     	return;
    }
    var selectedOption = displaySemesterCat.options[displaySemesterCat.selectedIndex].value+','+document.forms[0].courseId.value+','+document.forms[0].batchId.value;

    //get the (form based) params to push up as part of the get request
    url="retrieveExamSubject.do?selectedOption="+selectedOption;
    //Do the Ajax call
    
   
    
    if (window.XMLHttpRequest){ // Non-IE browsers
      req = new XMLHttpRequest();
      //A call-back function is define so the browser knows which function to call after the server gives a reponse back
      req.onreadystatechange = populateExamSubject;
      try {
      	req.open("POST", url, true); //was post
      } catch (e) {
         alert("Cannot connect to server");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE     
       
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = populateExamSubject;
        req.open("POST", url, true);
        req.send();
          
      }
    }
  }
  function populateExamSubject(){
  	document.getElementById('displaySubjectCat').options.length = 0;
	 if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
      	
         var textToSplit = req.responseText;
         if(textToSplit == '803'){
			alert("No select option available on the server");
		}
          //Split the document
          var returnElements=textToSplit.split("||");
          //Process each of the elements 	
          for ( var i=0; i<returnElements.length; i++ ){
             var valueLabelPair = returnElements[i].split(";");
             document.getElementById('displaySubjectCat').options[i] = new Option(valueLabelPair[1], valueLabelPair[0]);
          }
          // use global variable to do select element if any
          if(subjectId != '') {
          	var opt = document.getElementById('displaySubjectCat');
			SelectOptionInList(opt,subjectId);
          }
        }
      } else {  
            //alert("Bad response by the server");
        }
    }
    
      function SelectOptionInList( lstSelectList, intID )
      {
            try
            {
                  var intIndex = 0;
                  // Loop through all the options
                  for( intIndex = 0; intIndex < lstSelectList.options.length; intIndex++ )
                  {
                        // Is this the ID we are looking for?
                        if( lstSelectList.options[intIndex].value == intID ) 
                        {
                              // Select it
                              lstSelectList.selectedIndex = intIndex;
                              // Yes, so stop searching
                              break;
                        }
                  }
            }
            catch( expError )
            {
                  alert( "ajaxData.js::SelectOptionInList( ).\n" +
                              "Error:" + expError.number + ", " + expError.description );
            }
      }       
      
      
 //added by Ramesh
 //Exam Schedule Loading
 //May, 07, 2009
  function displayExamSchedule(){
 
    var displayExamScheduleCat = document.getElementById('displayExamScheduleCat');
    alert("hiexam");
     alert(displayExamScheduleCat);
    //Nothing selected
    if(displayExamScheduleCat.selectedIndex==0) {
	    document.getElementById('displaySubscheduleCat').options.length = 0;
	    document.getElementById('displaySubscheduleCat').options[0] = new Option("Please select Subject!", "0");
	     	return;
    }
    var selectedOption = displayExamScheduleCat.options[displayExamScheduleCat.selectedIndex].value+','+document.forms[0].courseId.value+','+document.forms[0].batchId.value+','+document.forms[0].semester.value;
	alert("param"+selectedOption);
    //get the (form based) params to push up as part of the get request
    url="retrieveExamSchedule.do?selectedOption="+selectedOption;
    //Do the Ajax call
    
   
    
    if (window.XMLHttpRequest){ // Non-IE browsers
      req = new XMLHttpRequest();
      //A call-back function is define so the browser knows which function to call after the server gives a reponse back
      req.onreadystatechange = populateExamSchedule;
      try {
      	req.open("POST", url, true); //was post
      } catch (e) {
         alert("Cannot connect to server");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE     
       
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = populateExamSchedule;
        req.open("POST", url, true);
        req.send();
          
      }
    }
  }
  function populateExamSchedule(){
  	document.getElementById('displaySubscheduleCat').options.length = 0;
	 if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
      	
         var textToSplit = req.responseText;
         if(textToSplit == '803'){
			alert("No select option available on the server");
		}
          //Split the document
          var returnElements=textToSplit.split("||");
          //Process each of the elements 	
          for ( var i=0; i<returnElements.length; i++ ){
             var valueLabelPair = returnElements[i].split(";");
             document.getElementById('displaySubscheduleCat').options[i] = new Option(valueLabelPair[1], valueLabelPair[0]);
          }
          // use global variable to do select element if any
          if(subjectId != '') {
          	var opt = document.getElementById('displaySubscheduleCat');
			SelectOptionInList(opt,subjectId);
          }
        }
      } else {  
            //alert("Bad response by the server");
        }
    }
    
      function SelectOptionInList( lstSelectList, intID )
      {
            try
            {
                  var intIndex = 0;
                  // Loop through all the options
                  for( intIndex = 0; intIndex < lstSelectList.options.length; intIndex++ )
                  {
                        // Is this the ID we are looking for?
                        if( lstSelectList.options[intIndex].value == intID ) 
                        {
                              // Select it
                              lstSelectList.selectedIndex = intIndex;
                              // Yes, so stop searching
                              break;
                        }
                  }
            }
            catch( expError )
            {
                  alert( "ajaxData.js::SelectOptionInList( ).\n" +
                              "Error:" + expError.number + ", " + expError.description );
            }
      }            
      
      
      