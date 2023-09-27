<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<html>
<head>
<meta charset="ISO-8859-1">

<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">

<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>

</head>
<body background="/AutoLib/soft.jpg">

<br><br><br><br>

<script language="javascript">
function selec1()
{
var a=(query.LIST1.options[query.LIST1.selectedIndex].value);

var l = document.query.condition1.length;
for(i=0;i<l;i++)
{
for(j=0;j<l;j++)
{
document.query.condition1.options[i]=null;
}
}

if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 
{
query.condition1.options[0]= new Option('=                   ','=');
query.condition1.options[1]= new Option('Less than                  ','<');
query.condition1.options[2]= new Option('Greater than                  ','>');
query.condition1.options[3]= new Option('Less than or Equal to                  ','<=');
query.condition1.options[4]= new Option('Greater than or Equal to','>=');

}
else
{
query.condition1.options[0]= new Option('Like             ','like');
query.condition1.options[1]= new Option('Starting with         ','start');
query.condition1.options[2]= new Option('Ending with          ','end');
query.condition1.options[3]= new Option('=             ','=');
// query.condition1.options[4]= new Option('Word             ','word');



}
}
function selec2()
{
var a=(query.LIST2.options[query.LIST2.selectedIndex].value);
var l = document.query.condition2.length;
for(i=0;i<l;i++)
{
for(j=0;j<l;j++)
{
document.query.condition2.options[i]=null;
}
}
if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 
{

	query.condition2.options[0]= new Option('=                   ','=');
	query.condition2.options[1]= new Option('Less than                  ','<');
	query.condition2.options[2]= new Option('Greater than                  ','>');
	query.condition2.options[3]= new Option('Less than or Equal to                  ','<=');
	query.condition2.options[4]= new Option('Greater than or Equal to','>=');
	
}
else
{
query.condition2.options[0]= new Option('Like                ','like');
query.condition2.options[1]= new Option('Starting with        ','start');
query.condition2.options[2]= new Option('Ending with          ','end');
query.condition2.options[3]= new Option('=             ','=');
// query.operator2.options[4]= new Option('Word             ','word');
}
}
function selec3()
{
var a=(query.LIST3.options[query.LIST3.selectedIndex].value);
var l = document.query.condition3.length;
for(i=0;i<l;i++)
{
for(j=0;j<l;j++)
{
document.query.condition3.options[i]=null;
}
}

if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") )  
{

	query.condition3.options[0]= new Option('=                   ','=');
	query.condition3.options[1]= new Option('Less than                  ','<');
	query.condition3.options[2]= new Option('Greater than                  ','>');
	query.condition3.options[3]= new Option('Less than or Equal to                  ','<=');
	query.condition3.options[4]= new Option('Greater than or Equal to','>=');
	
}
else
{
query.condition3.options[0]= new Option('Like                ','like');
query.condition3.options[1]= new Option('Starting         ','start');
query.condition3.options[2]= new Option('Ending           ','end');
query.condition3.options[3]= new Option('=             ','=');
// query.operator3.options[4]= new Option('Word             ','word');
}
}
function selec4()
{
var a=(query.field4.options[query.field4.selectedIndex].value);
var l = document.query.operator4.length;
for(i=0;i<l;i++)
{
for(j=0;j<l;j++)
{
document.query.operator4.options[i]=null;
}
}
if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 
{

query.operator4.options[0]= new Option('=                   ','=');
query.operator4.options[1]= new Option('LT                  ','<');
query.operator4.options[2]= new Option('GT                  ','>');
query.operator4.options[3]= new Option('LE                  ','<=');
query.operator4.options[4]= new Option('GE                  ','>=');
query.operator4.options[5]= new Option('Between        ','between');
query.operator4.options[6]= new Option('One of          ','in');
}
else
{
query.operator4.options[0]= new Option('=                ','=');
query.operator4.options[1]= new Option('Starting with         ','start');
query.operator4.options[2]= new Option('Ending with           ','end');
query.operator4.options[3]= new Option('Like             ','like');
// query.operator4.options[4]= new Option('Word             ','word');
}
}
function selec5()
{
var a=(query.field5.options[query.field5.selectedIndex].value);
var l = document.query.operator5.length;
for(i=0;i<l;i++)
{
for(j=0;j<l;j++)
{
document.query.operator5.options[i]=null;
}
}
if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") )  
{

query.operator5.options[0]= new Option('=                   ','=');
query.operator5.options[1]= new Option('LT                  ','<');
query.operator5.options[2]= new Option('GT                  ','>');
query.operator5.options[3]= new Option('LE                  ','<=');
query.operator5.options[4]= new Option('GE                  ','>=');
query.operator5.options[5]= new Option('Between        ','between');
query.operator5.options[6]= new Option('One of          ','in');
}
else
{
query.operator5.options[0]= new Option('=                ','=');
query.operator5.options[1]= new Option('Starting         ','start');
query.operator5.options[2]= new Option('Ending           ','end');
query.operator5.options[3]= new Option('Like             ','like');
query.operator5.options[4]= new Option('Word             ','word');
}
}
function selec6()
{
var a=(query.field6.options[query.field6.selectedIndex].value);
var l = document.query.operator6.length;
for(i=0;i<l;i++)
{
for(j=0;j<l;j++)
{
document.query.operator6.options[i]=null;
}
}
if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") )  
{

query.operator6.options[0]= new Option('=                   ','=');
query.operator6.options[1]= new Option('LT                  ','<');
query.operator6.options[2]= new Option('GT                  ','>');
query.operator6.options[3]= new Option('LE                  ','<=');
query.operator6.options[4]= new Option('GE                  ','>=');
query.operator6.options[5]= new Option('Between        ','between');
query.operator6.options[6]= new Option('One of          ','in');
}
else
{
query.operator6.options[0]= new Option('=                ','=');
query.operator6.options[1]= new Option('Starting         ','start');
query.operator6.options[2]= new Option('Ending           ','end');
query.operator6.options[3]= new Option('Like             ','like');
query.operator6.options[4]= new Option('Word             ','word');
}
}

function selec7()
{
var a=(query.field7.options[query.field7.selectedIndex].value);
var l = document.query.operator7.length;
for(i=0;i<l;i++)
{
for(j=0;j<l;j++)
{
document.query.operator7.options[i]=null;
}
}
if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 
{

query.operator7.options[0]= new Option('=                   ','=');
query.operator7.options[1]= new Option('LT                  ','<');
query.operator7.options[2]= new Option('GT                  ','>');
query.operator7.options[3]= new Option('LE                  ','<=');
query.operator7.options[4]= new Option('GE                  ','>=');
query.operator7.options[5]= new Option('Between        ','between');
query.operator7.options[6]= new Option('One of          ','in');
}
else
{
query.operator7.options[0]= new Option('=                ','=');
query.operator7.options[1]= new Option('Starting         ','start');
query.operator7.options[2]= new Option('Ending           ','end');
query.operator7.options[3]= new Option('Like             ','like');
query.operator7.options[4]= new Option('Word             ','word');
}
}







 function order11()
    { 
  if( (document.query.order1.value!="NO") && (document.query.order2.value!="NO") )
    {
     if( (document.query.order1.value==document.query.order2.value) && (document.query.order1.value!=document.query.order3.value) ) 
           {
             alert("Cant select more then one order by value same option 1 and 2"); document.query.order1[0].selected=true; 
           }
     } 
    if( (document.query.order1.value!="NO") && (document.query.order3.value!="NO") )
       {
         if( (document.query.order1.value==document.query.order3.value) && (document.query.order1.value!=document.query.order2.value) )  
              {
                alert("Cant select more then one order by value same option 1 and 3");  document.query.order1[0].selected=true; 
               }  
        } 
      
    if( (document.query.order1.value!="NO") && (document.query.order3.value!="NO") ) 
      {  
        if( (document.query.order1.value==document.query.order3.value) && (document.query.order1.value==document.query.order2.value)) 
            {
              alert("Cant select more then one order by value same option 1 and 2 and 3");  
              }
      } 
  } 
              
              
  function order22()
   {
     if( (document.query.order1.value!="NO") && (document.query.order2.value!="NO") )
      {
        if( (document.query.order1.value==document.query.order2.value) && (document.query.order1.value!=document.query.order3.value) ) 
            {
              alert("Cant select more then one order by value same option 1 and 2");  document.query.order2[0].selected=true;
            }
      } 
       if( (document.query.order3.value!="NO") && (document.query.order2.value!="NO") ) 
         {
          if( (document.query.order2.value==document.query.order3.value) && (document.query.order2.value!=document.query.order1.value) ) 
            {
             alert("Cant select more then one order by value same option 2 and 3");document.query.order3[0].selected=true; 
            }
          } 
           if( (document.query.order3.value!="NO") && (document.query.order2.value!="NO") ) 
              {
               if( (document.query.order2.value==document.query.order3.value) && (document.query.order2.value==document.query.order1.value) ) 
                {
                  alert("Cant select more then one order by value same option 1 and 2 and 3"); document.query.order2[0].selected=true; 
                }
               }
   } 

  function order33() 
       {
         if( (document.query.order1.value!="NO") && (document.query.order3.value!="NO")) 
           {
             if( (document.query.order1.value==document.query.order3.value) && (document.query.order2.value!=document.query.order3.value) ) 
                {
                  alert("Cant select more then one order by value same option 1 and 3"); document.query.order3[0].selected=true;}} 
                  if( (document.query.order2.value!="NO") && (document.query.order3.value!="NO")) 
                    {
                      if( (document.query.order2.value==document.query.order3.value) && (document.query.order2.value!=document.query.order1.value) )
                        {
                          alert("Cant select more then one order by value same option 2 and 3"); document.query.order3[0].selected=true;
                        }
                     }
                       if( (document.query.order2.value!="NO") && (document.query.order3.value!="NO")) 
                       {
                         if( (document.query.order2.value==document.query.order3.value) &&  (document.query.order2.value==document.query.order1.value) )  
                           { 
                             alert("Cant select more then one order by value same option 1 and 2 and 3");   document.query.order3[0].selected=true;document.query.order2[0].selected=true;
                            }
                        }
        }
        
        
function validate()
{
	{
		var content="";
	    var chkitem=0;	
		var cboxes = document.getElementsByName('rptValue[]');	 
	    var len = cboxes.length;	
	     
	    for(i=0;i<len;i++)  {
	    	if(cboxes[i].checked == true)
	        {
	    		chkitem++;
	            content=content+","+cboxes[i].value;
	    		//alert("From "+cboxes[i].value);
	        }
	    }
	    if(chkitem >= 1)
	 	{	   
		   /*  alert(content);  */
	       document.query.flag1.value=content;     
	 	}
	   // it can be empty
		}
		
		{
			var content1="";
		    var chkitem1=0;	
			var cboxes1 = document.getElementsByName('availValue[]');	 
		    var len1 = cboxes1.length;	
		     
		    for(i=0;i<len1;i++)  {
		    	if(cboxes1[i].checked == true)
		        {
		    		chkitem1++;
		            content1=content1+","+cboxes1[i].value;
		    		//alert("From "+cboxes[i].value);
		        }
		    }
		    if(chkitem1 >= 1)
		 	{	   
			    /* alert(content1);  */
		       document.query.flag2.value=content1;     
		 	}
		   // it can be empty
			}
if(t1() && t2() && t3())
{
alert("Please Enter the Value ");

return false;
}
 
else
{
if(((document.query.text1.value.length)+(document.query.text2.value.length)+(document.query.text3.value))<3 )
{
alert("Atleast three letters Should Be Entered ");
return false;

}
return true;

}      

}


function t1()
{
if(document.query.text1.value=="")
{
return true;
}
else
{
return false;
}
}

function t2()
{
if(document.query.text2.value=="")
{
return true;
}
else
{
return false;
}
}

function t3()
{
if(document.query.text3.value=="")
{
return true;
}
else
{
return false;
}
}









function User()
{

if(t1() && t2() && t3() && t4() && t5() && t6() && t7() )
{
alert("Atleast One Value Should Be Entered ");
return false;
}
 
else
{



query.method="post";
query.action ="Userdefinedreport.asp";
query.target ="_parent"; 
query.submit();
}
}

        

</script>


<FORM NAME="query" ACTION="./AdvancedServlet" METHOD="GET" ONSUBMIT="return validate()">
<br>
    <h2>Advanced Search</h2>

    <%
   if(request.getParameter("flags")!=null)
   {
   %>
<h3> -- Record Not Found --</h3>
   <%
   }
   %>
  <CENTER>
    <table align="center" class="contentTable" width="60%">
<td>
<table align="center" width="90%">
      <TR>
        <TD Class="addedit">
        Select the Field</TD>
        <TD Class="addedit">
            <CENTER>
 Operator
                  </CENTER></TD>
        <TD Class="addedit">
	<CENTER>
        Type the String to Search
	</CENTER></TD>
        <TD Class="addedit">

          Logical
           </TD>
      </TR>
      <TR >
        <TD ></TD>
        <TD ></TD>
        <TD ></TD>
        <TD ></TD>
      </TR>
      <TR >
       <div class="search-container">
	      <div class="ui-widget">
        <TD >
        <SELECT NAME="LIST1" id="LIST1" onChange="selec1()" SIZE="1" style="font-family: verdana; font-size: 8pt">
            <OPTION SELECTED VALUE="Title">Title</OPTION>
            <OPTION VALUE="Access_no">Access no</OPTION>
            <OPTION VALUE="call_no">call no</OPTION>
            <OPTION VALUE="Author_name">Author</OPTION>
            <OPTION VALUE="edition">Edition</OPTION>
            <OPTION VALUE="location">Location</OPTION>
            <OPTION VALUE="availability">Availability</OPTION>
            <OPTION VALUE="document">document</OPTION> 
            <OPTION VALUE="year_pub">Publication Year</OPTION>
            <OPTION VALUE="sp_name">Publisher</OPTION>
            <OPTION VALUE="supplier">Supplier</OPTION>
            <OPTION VALUE="isbn">ISBN</OPTION>
            <OPTION VALUE="Sub_name">Subject</OPTION>
            <OPTION VALUE="Dept_Name">Department</OPTION>
            <OPTION VALUE="media">Media</OPTION>
            <OPTION VALUE="binding">Binding</OPTION>
            <OPTION VALUE="received_date">Received date</OPTION>
            <OPTION VALUE="invoice_No">Invoice no</OPTION>
            <OPTION VALUE="invoice_date">Invoice date</OPTION>
            <OPTION VALUE="Bprice">BPrice</OPTION>
            <OPTION VALUE="purchase">Gift_Purchase</OPTION>
            <OPTION VALUE="add_field1">Add field1</OPTION>
            <OPTION VALUE="add_field2">Add field2</OPTION>
            <OPTION VALUE="add_field3">Add field3</OPTION>
            <OPTION VALUE="keywords">Keywords</OPTION>
            
            
          </SELECT></TD>
</div>
</div>

         <TD >
        <SELECT NAME="condition1" SIZE="1" style="font-family: Verdana; font-size: 8pt">
           
            <OPTION VALUE="like" selected>Like</OPTION>
            <OPTION VALUE="=">=</OPTION>
            <OPTION VALUE="start">Starting With</OPTION>
            <OPTION VALUE="end">Ending With</OPTION>
            
          </SELECT></TD> 
    
 <div class="search-container">
	<div class="ui-widget">
 
        <TD >
        <INPUT TYPE="text" NAME="text1" SIZE="45" id="search" class="search">
        </TD>
        
       </div>
   </div>        
        <TD >
        <SELECT SIZE="1" NAME="boolean1" style="font-size: 8pt; font-family: verdana">
            <OPTION VALUE="AND" SELECTED>AND</OPTION>
            <OPTION VALUE="OR">OR</OPTION>
            <OPTION VALUE="AND NOT">NOT</OPTION>
          </SELECT></TD>
      </TR>
      <TR ALIGN="center">
      
 <div class="search-container">
	<div class="ui-widget">     
        <TD >
        <SELECT SIZE="1" NAME="LIST2" id="LIST2"  onChange="selec2()" style="font-family: Verdana; font-size: 8pt">
            <OPTION SELECTED VALUE="Title">Title</OPTION>
            <OPTION VALUE="Access_no">Access no</OPTION>
            <OPTION VALUE="call_no">call no</OPTION>
            <OPTION VALUE="Author_name">Author</OPTION>
            <OPTION VALUE="edition">Edition</OPTION>
            <OPTION VALUE="location">Location</OPTION>
            <OPTION VALUE="availability">Availability</OPTION>
            <OPTION VALUE="document">document</OPTION> 
            <OPTION VALUE="year_pub">Publication Year</OPTION>
            <OPTION VALUE="sp_name">Publisher</OPTION>
            <OPTION VALUE="Supplier">Supplier</OPTION>
            <OPTION VALUE="isbn">ISBN</OPTION>
            <OPTION VALUE="Sub_name">Subject</OPTION>
            <OPTION VALUE="Dept_Name">Department</OPTION>
            <OPTION VALUE="media">Media</OPTION>
            <OPTION VALUE="binding">Binding</OPTION>
            <OPTION VALUE="received_date">Received date</OPTION>
            <OPTION VALUE="invoice_No">Invoice no</OPTION>
            <OPTION VALUE="invoice_date">Invoice date</OPTION>
            <OPTION VALUE="Language">Language</OPTION>
            <OPTION VALUE="Bprice">BPrice</OPTION>
            <OPTION VALUE="purchase">Gift_Purchase</OPTION>
            <OPTION VALUE="add_field1">Add field1</OPTION>
            <OPTION VALUE="add_field2">Add field2</OPTION>
            <OPTION VALUE="add_field3">Add field3</OPTION>
            <OPTION VALUE="keywords">Keywords</OPTION>
          </SELECT></TD>
        
    </div>
 </div>
       <TD>          
        <SELECT SIZE="1" NAME="condition2" style="font-family: Verdana; font-size: 8pt">
            <OPTION VALUE="like" selected>Like</OPTION>
            <OPTION VALUE="=">=</OPTION>
            <OPTION VALUE="start">Starting With</OPTION>
            <OPTION VALUE="end">Ending With</OPTION>
          </SELECT></TD>
          
<div class="search-container">
	<div class="ui-widget">
        <TD >
        <INPUT TYPE="text" NAME="text2" SIZE="45" id="search2" class="search" ></TD>
<!--         <INPUT TYPE="text" NAME="text2" SIZE="45"> -->
        <TD>
         </div>
   </div>       
        
        <SELECT SIZE="1" NAME="boolean2" style="font-family: Verdana; font-size: 8pt">
            <OPTION VALUE="AND" SELECTED>AND</OPTION>
            <OPTION VALUE="OR">OR</OPTION>
            <OPTION VALUE="AND NOT">NOT</OPTION>
          </SELECT></TD>
      </TR>
      <TR>
<div class="search-container">
	<div class="ui-widget">
      
        <TD >
        <SELECT SIZE="1" NAME="LIST3" id="LIST3"  onChange="selec3()" style="font-family: Verdana; font-size: 8pt">
           <OPTION SELECTED VALUE="Title">Title</OPTION>
            <OPTION VALUE="Access_no">Access no</OPTION>
            <OPTION VALUE="call_no">call no</OPTION>
            <OPTION VALUE="Author_name">Author</OPTION>
            <OPTION VALUE="edition">Edition</OPTION>
            <OPTION VALUE="location">Location</OPTION>
            <OPTION VALUE="availability">Availability</OPTION>
            <OPTION VALUE="document">document</OPTION> 
            <OPTION VALUE="year_pub">Publication Year</OPTION>
            <OPTION VALUE="sp_name">Publisher</OPTION>
            <OPTION VALUE="Supplier">Supplier</OPTION>
            <OPTION VALUE="isbn">ISBN</OPTION>
            <OPTION VALUE="Sub_name">Subject</OPTION>
            <OPTION VALUE="Dept_name">Department</OPTION>
            <OPTION VALUE="media">Media</OPTION>
            <OPTION VALUE="binding">Binding</OPTION>
            <OPTION VALUE="received_date">Received date</OPTION>
            <OPTION VALUE="invoice_No">Invoice no</OPTION>
            <OPTION VALUE="invoice_date">Invoice date</OPTION>
            <OPTION VALUE="Language">Language</OPTION>
            <OPTION VALUE="Bprice">BPrice</OPTION>
            <OPTION VALUE="purchase">Gift_Purchase</OPTION>
            <OPTION VALUE="add_field1">Add field1</OPTION>
            <OPTION VALUE="add_field2">Add field2</OPTION>
            <OPTION VALUE="add_field3">Add field3</OPTION>
            <OPTION VALUE="keywords">Keywords</OPTION>
          </SELECT></TD>
     </div>
</div>          
        <TD >
        <SELECT SIZE="1" NAME="condition3" style="font-size: 8pt; font-family: Verdana">
            <OPTION VALUE="like" selected>Like</OPTION>
            <OPTION VALUE="=">=</OPTION>
            <OPTION VALUE="start">Starting With</OPTION>
            <OPTION VALUE="end">Ending With</OPTION>
          </SELECT></TD>
          
<div class="search-container">
	<div class="ui-widget">
          
        <TD >
        <INPUT TYPE="text" NAME="text3" SIZE="45" id="search3" class="search"></TD>
   </div>
</div>           
        <TD></TD>
      </TR>
      <!--    <tr align="center">
      <td width="658" colspan="4" bgcolor="#008080" height="21">&nbsp; </td>
    </tr> -->




  <BR><BR>
<TR><td colspan="4"><center>
    	<input type="submit" Class="submitButton" value="search" name="B1" >
             <input type="reset"  Class="submitButton" value="Clear" name="B2">
               <input type="hidden" name="hid" value="search" >
               <input type="hidden" name="flag">
<!--                <input type="hidden" name="flag1"> -->
<input type="hidden" name="flag1">  <input type="hidden" name="flag2">
	       </center></td>
  	</TABLE>
    </CENTER></td></table></CENTER>

<br>
		
		<center>
<table align="center" class="contentTable" width="60%">
<td>
<table align="center" width="95%">

 <tr>
      <td Class="addedit" style="color: blue">Year Wise Range:</td></tr>
	 <tr> <td><input type="text" name="year" onkeypress="return isNumber(event)"></td>
	 <td><b>(ie: (1999-2001) You get inbetween of given value; / (-2000) You get in and before of given value; / (2000-) You get in and after of given value.)</b>
	 
	 
	 
     <td  Class="addedit" style="color: blue">SORT BY:</td>
     <td>
      <select name="sort" size="1" style="width: 155px">
  	  <!-- <option selected value="ALL">ALL</option> -->
  	  <option selected disabled>SUBJECT:</option>
  	  <option value="subasc">Subject(A-Z)</option>
      <option value="subdesc">Subject(Z-A)</option>
      <option selected disabled></option>
      
      <option selected disabled>DEPARTMENT:</option>
  	  <option value="deptasc">Department(A-Z)</option>
      <option value="deptdesc">Department(Z-A)</option>
      <option selected disabled></option>
       
      <option selected disabled>AUTHOR:</option>
      <option value="authorasc">Author(A-Z)</option>            
      <option value="authordesc">Author(Z-A)</option>
      <option selected disabled></option>
      
      <option selected disabled>TITLE:</option>
      <option value="titleasc">Title(A-Z)</option>
      <option value="titledesc">Title(Z-A)</option>
      <option selected disabled></option>
      
      <option selected disabled>DATE:</option>              
      <option value="dateasc">Date(Newest-Oldest)</option>
      <option value="datedesc">Date(Oldest-Newest)</option>
      <option selected disabled>ALL</option>  
	  </select></td>
	  <td  Class="addedit" style="color: blue">Contents</td>
	 
	  <td>
	   <select name="content" size="1" style="width: 105px">
	   <option value="ALL">ALL</option>
	  <option value="conavail">Available</option>
      <option value="connotavail">Not-Available</option>
       </select>
	  </td>
	 </tr>
</table>
</center>
<br>   
    
    


<!-- ===========Strats of Inner Table================= -->
<table align="center" class="contentTable" width="80%">
<td>
<table align="center" width="80%">
      <tr>
        <td Class="addedit"  style="color: blue"><center><u>DOCUMENT TYPE:</u></center></td>
        <center>
      
     <tr> 
     <td><input type=checkbox name="rptValue[]" id="book" value="book"><b>
     <img src="<%=request.getContextPath()%>/iconImages/Book.png" width="20" height="25" border="0"><label for="book">Book</label></b></td>
     <td><input type=checkbox name="rptValue[]" id="bookbank" value="bookbank"><b>
     <img src="<%=request.getContextPath()%>/iconImages/LibraryDue.png" width="20" height="25" border="0"><label for="bookbank">BookBank</label></b></td>
     <td><input type=checkbox name="rptValue[]" id="nonbook" value="nonbook"><b>
     <img src="<%=request.getContextPath()%>/images/cd.gif" width="20" height="25" border="0"><label for="nonbook">Non-Book</label></b></td>
     <td><input type=checkbox name="rptValue[]" id="report" value="report"><b>
     <img src="<%=request.getContextPath()%>/iconImages/im.jpeg" width="20" height="25" border="0"><label for="report">Report</label></b></td></tr>
     
     <tr>
     <td><input type=checkbox name="rptValue[]" id="thesis" value="thesis"><b>
     <img src="<%=request.getContextPath()%>/iconImages/renewBook.png" width="20" height="25" border="0"><label for="thesis">Thesis</label></b></td>
     <td><input type=checkbox name="rptValue[]" id="backvolume" value="backvolume"><b>
     <img src="<%=request.getContextPath()%>/iconImages/returnBook.png" width="20" height="25" border="0"><label for="backvolume">BackVolume</label></b></td>
     <td><input type=checkbox name="rptValue[]" id="standard" value="standard"><b>
     <img src="<%=request.getContextPath()%>/iconImages/LibraryCollection.png" width="20" height="25" border="0"><label for="standard">Standard</label></b></td>
     <td><input type=checkbox name="rptValue[]" id="proceeding" value="proceeding"><b>
     <img src="<%=request.getContextPath()%>/iconImages/catalogingBook.png" width="20" height="25" border="0"><label for="proceeding">Proceeding</label></b></td>
     </tr>
    
  </center>
  </table>
</center></td></table>
   <br>
   
<table align="center" class="contentTable" width="70%">
<td>
<table align="center" width="80%">
      <tr>
        <td Class="addedit"  style="color: blue"><center><u>AVAILABILITY:</u></center></td>
        <center>
      
     <tr> 
     <td><input type=checkbox name="availValue[]" id="yes" value="yes"><b><label for="yes">Yes</label></b></td>     
     <td><input type=checkbox name="availValue[]" id="issued" value="issued"><b><label for="issued">Issued</label></b></td>     
     <td><input type=checkbox name="availValue[]" id="reference" value="reference"><b><label for="reference">Reference</label></b></td>
     <td><input type=checkbox name="availValue[]" id="transfered" value="transfered"><b><label for="transfered">Transfered</label></b></td>    
     <td><input type=checkbox name="availValue[]" id="binding" value="binding"><b><label for="binding">Binding</label></b></td></tr>
     
     
     <tr>
     <td><input type=checkbox name="availValue[]" id="damaged" value="damaged"><b><label for="damaged">Damaged</label></b></td>
     <td><input type=checkbox name="availValue[]" id="lost" value="lost"><b><label for="lost">Lost</label></b></td>
     <td><input type=checkbox name="availValue[]" id="missing" value="missing"><b><label for="missing">Missing</label></b></td>
     <td><input type=checkbox name="availValue[]" id="withdrawn" value="withdrawn"><b><label for="withdrawn">Withdrawn</label></b></td>    
     <td><input type=checkbox name="availValue[]" id="display" value="display"><b><label for="display">Display</label></b></td>
     
     </tr>
  </center>
</table>
</center></td></table>
  <!-- ===========ENDS of Inner Table================= -->
</tr>
</table>
</center></td></table></center>
<br>

    </form>
    

<script language='javascript'>


    $( "#search" ).autocomplete({
    	width: 500,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: true,
        highlight: false,
    	source: function (request, response) {
    		var type = document.getElementById('LIST1').value;
            $.ajax({
                url: "/AutoLib/Advanced/AdvancedServlet",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	text1 : request.term ,
                	flag  : type
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.title);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.title };
            }));
                	
			     },   
            });
            
            	
        }  

    	
      });

    $( "#search2" ).autocomplete({
    	width: 500,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: true,
        highlight: false,
    	source: function (request, response) {
    		var type = document.getElementById('LIST2').value;
            $.ajax({
                url: "/AutoLib/Advanced/AdvancedServlet",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	text2 : request.term ,
                	flag  : type
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.title);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.title };
            }));
                	
			     },   
            });
            
            	
        }  

    	
      });

    $( "#search3" ).autocomplete({
    	width: 500,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: false,
        cacheLength: 1,
        scroll: true,
        highlight: false,
    	source: function (request, response) {
    		var type = document.getElementById('LIST3').value;
            $.ajax({
                url: "/AutoLib/Advanced/AdvancedServlet",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	text3 : request.term ,
                	flag  : type
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.title);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                 value: item.title };
            }));
                	
			     },   
            });
           	
        }  
	
      });

    
function search()
{
location.href="index.asp"
}

function Simple()
{
location.href="/AutoLib/Simple/index.jsp"
}
function adTitle(test)
{
	var test1 = document.query.LIST1.value;
// 	advSearchTitle(test,test1);
}
function home()
{
location.href="/AutoLib/Home.jsp";
}
function newarrival()
{
location.href="newarrivalhome.asp"
}

function account()
{
location.href="/portal/admin.html";}

function Logout()
{
location.href="/AutoLib/index.html";
}

</script>
</BODY>

</HTML>




