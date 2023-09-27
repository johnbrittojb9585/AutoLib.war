<html>
<head>
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<title>Autolib Software Systems,Chennai</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache">
</head>
<body ><!--bgcolor="#C5C5C5"-->
<br>

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
if((a=="yearpub") || (a=="NoOfCopy") || (a=="BPrice") || (a=="BCost") || (a=="Accepted_Price") || (a=="Received_Date") ) 
{
query.condition1.options[0]= new Option('=                   ','=');
query.condition1.options[1]= new Option('LT                  ','<');
query.condition1.options[2]= new Option('GT                  ','>');
query.condition1.options[3]= new Option('LE                  ','<=');
query.condition1.options[4]= new Option('GE                  ','>=');
query.condition1.options[5]= new Option('Between        ','between');
query.condition1.options[6]= new Option('One of          ','in');
}
else
{
query.condition1.options[0]= new Option('Like             ','like');
query.condition1.options[1]= new Option('Starting         ','start');
query.condition1.options[2]= new Option('Ending           ','end');
query.condition1.options[3]= new Option('=             ','=');
query.condition1.options[4]= new Option('Word             ','word');

}
}
function selec2()
{
var a=(query.field2.options[query.field2.selectedIndex].value);
var l = document.query.operator2.length;
for(i=0;i<l;i++)
{
for(j=0;j<l;j++)
{
document.query.operator2.options[i]=null;
}
}
if((a=="yearpub") || (a=="NoOfCopy") || (a=="BPrice") || (a=="BCost") || (a=="Accepted_Price") || (a=="Received_Date")) 
{

query.operator2.options[0]= new Option('=                   ','=');
query.operator2.options[1]= new Option('LT                  ','<');
query.operator2.options[2]= new Option('GT                  ','>');
query.operator2.options[3]= new Option('LE                  ','<=');
query.operator2.options[4]= new Option('GE                  ','>=');
query.operator2.options[5]= new Option('Between        ','between');
query.operator2.options[6]= new Option('One of          ','in');
}
else
{
query.operator2.options[0]= new Option('Like                ','like');
query.operator2.options[1]= new Option('Starting         ','start');
query.operator2.options[2]= new Option('Ending           ','end');
query.operator2.options[3]= new Option('=             ','=');
query.operator2.options[4]= new Option('Word             ','word');
}
}
function selec3()
{
var a=(query.field3.options[query.field3.selectedIndex].value);
var l = document.query.operator3.length;
for(i=0;i<l;i++)
{
for(j=0;j<l;j++)
{
document.query.operator3.options[i]=null;
}
}
if((a=="yearpub") || (a=="NoOfCopy") || (a=="BPrice") || (a=="BCost") || (a=="Accepted_Price") || (a=="Received_Date") ) 
{

query.operator3.options[0]= new Option('=                   ','=');
query.operator3.options[1]= new Option('LT                  ','<');
query.operator3.options[2]= new Option('GT                  ','>');
query.operator3.options[3]= new Option('LE                  ','<=');
query.operator3.options[4]= new Option('GE                  ','>=');
query.operator3.options[5]= new Option('Between        ','between');
query.operator3.options[6]= new Option('One of          ','in');
}
else
{
query.operator3.options[0]= new Option('Like                ','like');
query.operator3.options[1]= new Option('Starting         ','start');
query.operator3.options[2]= new Option('Ending           ','end');
query.operator3.options[3]= new Option('=             ','=');
query.operator3.options[4]= new Option('Word             ','word');
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
if((a=="yearpub") || (a=="NoOfCopy") || (a=="BPrice") || (a=="BCost") || (a=="Accepted_Price") || (a=="Received_Date") ) 
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
query.operator4.options[1]= new Option('Starting         ','start');
query.operator4.options[2]= new Option('Ending           ','end');
query.operator4.options[3]= new Option('Like             ','like');
query.operator4.options[4]= new Option('Word             ','word');
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
if((a=="yearpub") || (a=="NoOfCopy") || (a=="BPrice") || (a=="BCost") || (a=="Accepted_Price") || (a=="Received_Date") ) 
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
if((a=="yearpub") || (a=="NoOfCopy") || (a=="BPrice") || (a=="BCost") || (a=="Accepted_Price") || (a=="Received_Date") ) 
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
if((a=="yearpub") || (a=="NoOfCopy") || (a=="BPrice") || (a=="BCost") || (a=="Accepted_Price") || (a=="Received_Date") ) 
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
if(t1() && t2() && t3())
{
//location.href="AdvSearch/advsearch.jsp";
alert("Atleast One Value Should Be Entered ");

return false;
}
 
else
{

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


<FORM NAME="query" ACTION="./AdvancedBrowse" METHOD="post" ONSUBMIT="return validate()">
<center>

<img border='0' src='Image/Account.gif' onclick='account()'> -->
<img border='0' src='/AutoLib/images/Search.gif' onclick='Simple()'>

</center>

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
    <TABLE >
      <TR>
        <TD >
        Select the Field</TD>
        <TD >
            <CENTER>
 Operator
                  </CENTER></TD>
        <TD >
	<CENTER>
        Type the String to Search
	</CENTER></TD>
        <TD >

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
        <TD >
        <SELECT NAME="LIST1" SIZE="1" style="font-family: verdana; font-size: 8pt">
            <OPTION SELECTED VALUE="Title">Title</OPTION>
            <OPTION VALUE="Author">Author</OPTION>
            <OPTION VALUE="publisher">Publisher</OPTION>
            <OPTION VALUE="Subject">Subject</OPTION>
            <OPTION VALUE="keywords">Keywords</OPTION>
            <OPTION VALUE="year">Publication Year</OPTION>
          </SELECT></TD>


        <TD >
        <SELECT NAME="condition1" SIZE="1" style="font-family: Verdana; font-size: 8pt">
            <OPTION VALUE="like" selected>Like</OPTION>
            <OPTION VALUE="=">=</OPTION>
            <OPTION VALUE="start">Starting With</OPTION>
            <OPTION VALUE="end">Ending With</OPTION>
          </SELECT></TD>
        <TD >
        <INPUT TYPE="text" NAME="text1" SIZE="40" ></TD>
        <TD >
        <SELECT SIZE="1" NAME="boolean1" style="font-size: 8pt; font-family: verdana">
            <OPTION VALUE="AND" SELECTED>AND</OPTION>
            <OPTION VALUE="OR">OR</OPTION>
            <OPTION VALUE="AND NOT">NOT</OPTION>
          </SELECT></TD>
      </TR>
      <TR ALIGN="center">
        <TD >
        <SELECT SIZE="1" NAME="LIST2"  style="font-family: Verdana; font-size: 8pt">
            <OPTION SELECTED VALUE="Title">Title</OPTION>
            <OPTION VALUE="Author">Author</OPTION>
            <OPTION VALUE="publisher">Publisher</OPTION>
            <OPTION VALUE="Subject">Subject</OPTION>
            <OPTION VALUE="keywords">Keywords</OPTION>
            <OPTION VALUE="year">Publication Year</OPTION>
          </SELECT></TD>
        <TD >
        <SELECT SIZE="1" NAME="condition2" style="font-family: Verdana; font-size: 8pt">
            <OPTION VALUE="like" selected>Like</OPTION>
            <OPTION VALUE="=">=</OPTION>
            <OPTION VALUE="start">Starting With</OPTION>
            <OPTION VALUE="end">Ending With</OPTION>
          </SELECT></TD>
        <TD >
        <INPUT TYPE="text" NAME="text2" SIZE="40" ></TD>
        <TD>
        <SELECT SIZE="1" NAME="boolean2" style="font-family: Verdana; font-size: 8pt">
            <OPTION VALUE="AND" SELECTED>AND</OPTION>
            <OPTION VALUE="OR">OR</OPTION>
            <OPTION VALUE="AND NOT">NOT</OPTION>
          </SELECT></TD>
      </TR>
      <TR>
        <TD >
        <SELECT SIZE="1" NAME="LIST3" style="font-family: Verdana; font-size: 8pt">
            <OPTION SELECTED VALUE="Title">Title</OPTION>
            <OPTION VALUE="Author">Author</OPTION>
            <OPTION VALUE="publisher">Publisher</OPTION>
            <OPTION VALUE="subject">Subject</OPTION>
            <OPTION VALUE="keywords">Keywords</OPTION>
            <OPTION VALUE="year">Publication Year</OPTION>
          </SELECT></TD>
        <TD >
        <SELECT SIZE="1" NAME="condition3" style="font-size: 8pt; font-family: Verdana">
            <OPTION VALUE="like" selected>Like</OPTION>
            <OPTION VALUE="=">=</OPTION>
            <OPTION VALUE="start">Starting With</OPTION>
            <OPTION VALUE="end">Ending With</OPTION>
          </SELECT></TD>
        <TD >
        <INPUT TYPE="text" NAME="text3" SIZE="40" ></TD>
        <TD></TD>
      </TR>

  <BR><BR>
<TR><td colspan="4"><center>
    	<input type="submit" value="search" name="B1" >
             <input type="reset" value="Clear" name="B2">
               <input type="hidden" name="hid" value="search" >
	       </center></td>
  	</TABLE>
    </CENTER>


    </form>
    </DIV>
  </CENTER>

<script language='javascript'>

function search()
{
location.href="index.asp"
}

function Simple()
{
location.href="/AutoLib/BrowseSimple/index.jsp"
}

function home()
{
location.href="/AutoLib/Browse/index.jsp";
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




