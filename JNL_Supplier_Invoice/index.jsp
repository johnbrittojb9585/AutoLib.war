<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" import="Lib.Auto.Currency.CurrencyBean" import="Lib.Auto.JNL_Order.JnlorderBean" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="viewObject" scope="request" class="Lib.Auto.JNL_Order.JnlorderBean"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>


<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("currency");
%>
				<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<script language="javascript" src="/AutoLib/datetimepicker_css.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg" >
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<!-- Style Sheet -->

<form method="post" name="ordinv" action=./SupInvoiceJNLServlet>
<br><br><br>

<h2 >Journal Supplier Invoice Processing</h2>

  <center>
<table align="center" class="contentTable" width="76%">
<td>
<table align="center" width="95%">
<tr><td> &nbsp; </td></tr>


<c:choose>
<c:when test="${JNLSearchSize gt 0}">  
<c:forEach items="${JNLSearch}" var="std"  begin="0" end="0">
    <tr>      
      <td Class="addedit">Inv. No</td>
      <td colspan="2"><input type="text" name="invno" size="17" maxlength=15 value="<c:out value="${std.quoteNo}"/>" >
            <input type="button" value="Find" Class="submitButton" onclick=FindValue("SupInvoice")></td>
      <td Class="addedit" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;Inv. Date
	<INPUT name=invdate size=8  onfocus=this.blur(); value="<c:out value="${std.quoteDate}"/>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.invdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
	
   <td Class="addedit"  colspan="3">OrderNo
      <input type="text" name="ordno" value="<c:out value="${std.ordNo}"/>" readonly=true size="17" maxlength=15>
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Sup")></td>
   </tr>           
    
<tr><td colspan=2>
 <a href="javascript:void();" onclick="javascript:checkAll('ordinv', true);">Check All</a> | 
         <a href="javascript:void();" onclick="javascript:checkAll('ordinv', false);">UnCheck All</a>
</td>
</tr>
</c:forEach> 

</c:when>
<c:otherwise>
<tr>      
      <td Class="addedit">Inv. No</td>
<!--      <td ><input type="text" name="invno" size="17" maxlength=15 onKeyUp="return author_code_val();"></td>-->
      <td><input type="text" name="invno" size="15" maxlength=15 onKeydown="Javascript: if (event.keyCode==13) SearchRecord();">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("SupInvoice")></td>
      <td Class="addedit">Inv. Date</td>
      <TD >
<%-- 	<INPUT name=invdate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=invdate size=8 id="datepicker" value="<%=dateString%>">
				
		 </td>	 

      <td Class="addedit">OrderNo</td>
      <td colspan=2><input type="text" name="ordno" readonly=true value="" size="15" maxlength=15>
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Sup")></td>
   </tr>
         
</c:otherwise>
</c:choose> 

<c:forEach items="${JNLSearch}" var="std" varStatus="loop">

<tr><td><br><br> </td></tr>
<tr><td><input type='checkbox' id="selectedJNL[]" name="selectedJNL[]" value="<c:out value="${std.jnlCode}"/>" /><c:out value="${std.jnlCode}"/></td>
<td colspan="3"><input type="hidden" name="jnlname[]" value="<c:out value="${std.journal}"/>"><c:out value="${std.journal}"/> </td>
<!--<td>&nbsp;</td><td>&nbsp;</td>-->
<!--<td Class="addedit">OrderNo</td><td><input type="hidden" name="ordno[]"  value="<c:out value="${std.ordNo}"/>"><c:out value="${std.ordNo}"/>-->
<!--<input type="hidden" name=orddate[] value="<c:out value="${std.ordDate}"/>"></td>-->
<!--<td Class="addedit">Supplier</td><td><input type="hidden" name="supcode[]" value="<c:out value="${std.supCode}"/>">-->
<!--<input type="hidden" name="sname[]" value="<c:out value="${std.supplier}"/>"><c:out value="${std.supplier}"/></td></tr>-->

<td colspan="4"><input type="hidden" name="ordno[]"  value="<c:out value="${std.ordNo}"/>"><c:out value="${std.ordNo}"/>
<input type="hidden" name=orddate[] value="<c:out value="${std.ordDate}"/>">
<input type="hidden" name="supcode[]" value="<c:out value="${std.supCode}"/>">-
<input type="hidden" name="sname[]" value="<c:out value="${std.supplier}"/>"><c:out value="${std.supplier}"/></td></tr>



    <tr>   
    <td Class="addedit"><input type="hidden" name="jnlno[]" value="<c:out value="${std.jnlCode}"/>">
    <input type="hidden" name="frequency[]" value="<c:out value="${std.frequency}"/>">Subs. From</td>
    <TD>      
	<INPUT name="subsfrom<c:out value="${std.jnlCode}"/>" size=8  onfocus=this.blur(); value="<c:out value="${std.subsfrmdate}"/>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.subsfrom<c:out value="${std.jnlCode}"/>, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
				
		 </td><td Class="addedit">Subs. To</td>
      <TD >
	<INPUT name="substo<c:out value="${std.jnlCode}"/>" size=8  onload="return datechange()" onfocus=this.blur(); value="<c:out value="${std.substodate}"/>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.substo<c:out value="${std.jnlCode}"/>, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px' onblur='return datechange()' >")
						}
		       </SCRIPT></TD>

    <td Class="addedit">No.of.Issues</td>
      <td ><input type="text" name="noofissue[]" size="12" onblur="return datechange()"  maxlength=5 value="<c:out value="${std.noofissue}"/>" onKeyUp="return author_code_val1();"></td>
      <td Class="addedit">&nbsp;&nbsp;&nbsp;Vol. No</td>
      <td ><input type="text" name="volno[]" value="<c:out value="${std.volumeNo}"/>" size="10" maxlength=5 onclick="return datechange()"></td>      
    </tr>
<tr>
<td Class="addedit">
  B-Cost
   <td >
   <input type="text" name="bcost[]" size="13" value="<c:out value="${std.bcost}"/>" maxlength=8  onKeyUp="find_amount()" > <!--onKeyUp="return author_code_val1();"-->
	<td Class="addedit">Currency
	<td>
	<SELECT SIZE="1" NAME="currency[]"   	onchange="find_amount()" >
    <OPTION  VALUE="1">Rupees&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </OPTION>
    
     <% 
			                           Iterator it=sc.iterator();
			                           
                                        while(it.hasNext()){
                                        	                                        	
                                        	CurrencyBean view=(CurrencyBean) it.next();                                        	
				                     
				                        String curr=view.getCurrency();
				                        String ind=view.getIndian_value();
				                        %>
				                       
				                     <%
				                       out.println("<option  value="+ind+">" +curr+"</option>");
                                       }
				                        %>
			                            

	 </SELECT>
	 <input type="hidden" name="currencylist[]" value="<c:out value="${std.bcurrency}"/>" >	  	 
    </td>
    <td Class="addedit">Bprice<td ><input type="text" name="bprice[]" size="12" value="<c:out value="${std.bprice}"/>" maxlength=12 onKeyUp="return author_code_val1();">
    </td> 
    <td Class="addedit">&nbsp;&nbsp;&nbsp;Discount<td >
    <input type="text" name="discount[]" size="10" value="<c:out value="${std.discount}"/>" maxlength=5 onKeyUp="return author_code_val1();">
    </td></tr>
<tr>    
    <td Class="addedit">Accepted&nbsp;Price
  <td ><input type="text"  name="acceptPrice[]" size="13" value="<c:out value="${std.netamount}"/>"  maxlength=9 onKeyUp="return author_code_val1();">
</td> </tr>

</c:forEach>

 <script language="javaScript" >

      var cboxes = document.getElementsByName('currencylist[]');
      var len = cboxes.length;	     
      for (var i=0; i<len; i++) 
      {          
         if(cboxes[i].value) {
                     //alert("hai IF:"+cboxes[i].value);
           document.getElementsByName('currency[]').item(i).value=cboxes[i].value;
         
         }else {
                     //alert("hai ELSE:"+cboxes[i].value);
            document.getElementsByName('currency[]').item(i).value=1;
         }
      } 
 </script>

</table>
<br><br>
<table align="center" width="90%">

<tr>
<td></td><td></td></tr>
<tr>
<td></td></tr>
<c:choose>

<c:when test="${JNLSearchSize gt 0}">  
<c:forEach items="${JNLSearch}" var="std"  begin="0" end="0">
<tr><td></td></tr>
    <tr>
     <td Class="addedit">Remarks</td>
      <td colspan="5"><input type="text" name="remarks" value="<c:out value="${std.remarks}"/>" size="75" maxlength=100></td>
    </tr>
    <tr>
      <td Class="addedit">Add1</td>
      <td  colspan="5"><input type="text" name="add1" value="<c:out value="${std.add1}"/>" size="75" maxlength=200></td>
    </tr>
    <tr>
      <td Class="addedit">Add2</td>
      <td  colspan="5" ><input type="text" name="add2" value="<c:out value="${std.add2}"/>" size="75" maxlength=200></td>
    </tr>  
</c:forEach>
</c:when>
<c:otherwise>
<tr><td></td></tr>
    <tr>
     <td Class="addedit">Remarks</td>
      <td colspan="5"><input type="text" name="remarks" size="75" maxlength=100></td>
    </tr>
    <tr>
      <td Class="addedit">Add1</td>
      <td  colspan="5"><input type="text" name="add1" size="75" maxlength=200></td>
    </tr>
    <tr>
      <td Class="addedit">Add2</td>
      <td  colspan="5" ><input type="text" name="add2" size="75" maxlength=200></td>
    </tr>
</c:otherwise>
</c:choose>    
 
    <tr>
      <td  colspan="6" >
        <p align="center">
		<input type="button" value="New" Class="submitButton" name="new" onclick="test()"> <!--onclick="NewRecord()"-->
		<input type="button" value="Save" Class="submitButton" name="save" onclick="SaveRecord()">
		<input type="button" value="Delete" Class="submitButton" name="delete" onclick="DeleteRecord()">
		<input type="button" value="Search" Class="submitButton" name="search" onclick="SearchRecord()">
		<input type="reset"  Class="submitButton" value="clear" onclick="NewRecord()">
		<input type="hidden"  name="flag" value="null">
		<input type=hidden name="flag1" >
		<input type="hidden"  name="csubsdate" >
		<input type="hidden"  name="cstodate" >
		</td>
    </tr>    
  </table>
 </center></td></table></center>
</form>
</body>
</html>

<%

session.setAttribute("JNLSearch",null);
session.setAttribute("JNLSearchSize",null);

%>

<%

String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newInvoice")){
 %>
 <script language="JavaScript">
document.ordinv.slno.value="<%=viewObject.getImax()%>";
document.ordinv.ordno.focus();
</script>
<%
  }
			if(valid.equals("UpdateCheck")){%>
            <script language="JavaScript">
             //checkAll('ordinv', true);
            
			 msg=confirm("Record Already Exists. Do you want to Update !");
					if(msg){
					
					var cboxes = document.getElementsByName('selectedJNL[]');
                    var len = cboxes.length;
  
                    var content="";
                    var chkitem=0;
    
                    for (var i=0; i<len; i++) {
                    cboxes[i].checked = true;
                     if(cboxes[i].checked == true)
                     {  
                       chkitem++;
                       content=content+","+cboxes[i].value;       
                     }      
                    }
    
                    if(chkitem >= 1)
	                {
			           alert('You are selected - '+chkitem);
			           alert(content);
                       document.ordinv.flag1.value=content;
	                   document.ordinv.flag.value="update";
					   document.ordinv.submit();
	                }
	                else if(chkitem == 0)
	                {
			           alert('Please select atleast one Journal from Order to save !'+len);
			           //return false;
	                }   
						
						//document.ordinv.flag.value="update";
						//document.ordinv.submit();
						}
						else
						{
						alert("Operation Cancelled..!");						
						//document.ordinv.flag.value="new";  
						//document.ordinv.submit();
						}			
		   	</script><%
			}

			if(valid.equals("SaveSuccess")){%>
            		<script language="JavaScript">
			alert("Record Saved Successfully!");			
		   	</script><%
			}
			
			if(valid.equals("UpdateSuccess")){%>
    		<script language="JavaScript">
	          alert("Record Updated Successfully!");
	        </script><%
	}
			

			if(valid.equals("DeletedSuccess")){%>
    		<script language="JavaScript">
	          alert("Record Deleted Successfully!");
	        </script>        
	        <%
	}
			if(valid.equals("DeleteFail")){%>
    		<script language="JavaScript">
	          alert("Record Not Found!");
	        </script>        
	        <%
	}		
  }
  }
%>
<script>

function checkAll(formname, checktoggle)
{
     var checkboxes = new Array();
      checkboxes = document[formname].getElementsByTagName('input');

      for (var i = 0; i < checkboxes.length; i++) {
          if (checkboxes[i].type === 'checkbox') {
               checkboxes[i].checked = checktoggle;
          }
      }
}


function test() {
    var cboxes = document.getElementsByName('selectedJNL[]');
    var len = cboxes.length;
    
  
   var content="";
   var res=[];
    for (var i=0; i<len; i++) {
    if(cboxes[i].checked)
    {
        //alert(i + (cboxes[i].checked?' checked ':' unchecked ') + cboxes[i].value);
       
      content=content+","+cboxes[i].value;      
      
      //alert(content);
      //content="-"+content; 
                                     
      //document.ord_find.flag1.value=cboxes[i].value;  
    } 
      
    }
    
    alert(content);
    document.ordinv.flag.value="selectV1";
    document.ordinv.flag1.value=content;
    document.ordinv.submit();
    
    
    
   /* window.opener.document.ordinv.flag.value="selectV";
    window.opener.document.ordinv.flag1.value=content;
    window.opener.document.ordinv.csubsdate.value=document.ord_find.csubsdate.value;
    window.opener.document.ordinv.cstodate.value=document.ord_find.cstodate.value;        
    window.opener.document.ordinv.submit();
    
        window.close();*/
   
}

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function NewRecord()
{
	document.ordinv.method="get";
	//document.ordinv.flag.value="new";
	document.ordinv.action="index.jsp";
	document.ordinv.submit();

}

function SearchRecord()
{
if (document.ordinv.invno.value=="")
{
  alert("Invalid Invoice Number..!");		
}else  {
            document.ordinv.method="get";
            document.ordinv.flag.value="search";
            document.ordinv.submit();
}

}

function author_code_val() {
if((isNaN(document.ordinv.invno.value))||(document.ordinv.invno.value == ' ')) {
document.ordinv.invno.select();
document.ordinv.invno.value="";
    
  }
}


function author_code_val1() {
     var cboxes = document.getElementsByName('noofissue[]');
     var len = cboxes.length;	 
     
	 var cboxes1 = document.getElementsByName('bcost[]');
	 var cboxes2 = document.getElementsByName('bprice[]');
	 var cboxes3 = document.getElementsByName('discount[]');
	 var cboxes4 = document.getElementsByName('acceptPrice[]');	 
	 
	
for (var i=0; i<len; i++) 
    {       
      

if((isNaN(cboxes[i].value))||(cboxes[i].value == ' ')) {
cboxes[i].select();
cboxes[i].value="";
} 
if((isNaN(cboxes1[i].value))||(cboxes1[i].value == ' ')) {
cboxes1[i].select();
cboxes1[i].value="";
}
if((isNaN(cboxes2[i].value))||(cboxes2[i].value == ' ')) {
cboxes2[i].select();
cboxes2[i].value="";
}
if((isNaN(cboxes3[i].value))||(cboxes3[i].value == ' ')) {
cboxes3[i].select();
cboxes3[i].value="";
}  
if((isNaN(cboxes4[i].value))||(cboxes4[i].value == ' ')) {
cboxes4[i].select();
cboxes4[i].value="";
}
//document.Book.acceptPrice.value=document.Book.bprice.value-((document.Book.discount.value/100)* document.Book.bprice.value);
cboxes4[i].value=cboxes2[i].value-((cboxes3[i].value/100)* cboxes2[i].value);

}
}



function find_amount(){

	 var cboxes = document.getElementsByName('bprice[]');
     var len = cboxes.length;	 
     
	 var cboxes1 = document.getElementsByName('bcost[]');
	 var cboxes2 = document.getElementsByName('currency[]');
	 var cboxes3 = document.getElementsByName('acceptPrice[]');
	 var cboxes4 = document.getElementsByName('discount[]');
	 
	 
	
for (var i=0; i<len; i++) 
{       
cboxes[i].value=(cboxes2[i].value*cboxes1[i].value);
cboxes3[i].value=cboxes[i].value-((cboxes4[i].value/100)* cboxes[i].value);
      
}
author_code_val1();
//document.Book.bprice.value=document.Book.currency.value * document.Book.bcost.value
//document.Book.acceptPrice.value=document.Book.bprice.value

}





function datechange()  
{
	 var cboxes1 = document.getElementsByName('jnlno[]');	 
     var len = cboxes1.length;	
     
     var cboxes = document.getElementsByName('noofissue[]');
           
     var sptdate,myDay,myMonth,myYear,combineDatestr,dt,dt1,diff;  
      
     
     for(i=0;i<len;i++)  {
     
     	 var cboxes2 = document.getElementsByName('subsfrom'+cboxes1[i].value);
         var cboxes3 = document.getElementsByName('substo'+cboxes1[i].value);
            //alert("Hai "+cboxes3[i].value);
            //cboxes[i].value=(cboxes3[0].value-cboxes2[0].value);                        
          //  cboxes[i].value=((new Date(cboxes3[i].value)) - (new Date(cboxes2[i].value))/ 24 / 3600000);
            // alert("hai"+cboxes3[0].value);

     sptdate = String(cboxes3[0].value).split("-");
     myDay = sptdate[0];    
     myMonth = sptdate[1];
     myYear = sptdate[2];
     combineDatestr = myYear + "/" + myMonth + "/" + myDay;  

     dt = new Date(combineDatestr);    
     //alert("SubsTo "+dt+"And Date:"+combineDatestr);    
   
     sptdate = String(cboxes2[0].value).split("-");
     myDay = sptdate[0];    
     myMonth = sptdate[1];
     myYear = sptdate[2];
     combineDatestr = myYear + "/" + myMonth + "/" + myDay;      
       
     dt1 = new Date(combineDatestr);  
     //alert("From "+dt+"And Date:"+combineDatestr); 
   
        
     diff=((dt-dt1)/ 24 / 3600000);
     //alert("difference:"+diff);  
    
   // cboxes[i].value=diff;  
    
           
       
    var frequency = document.getElementsByName('frequency[]');
    var noiss=0;
    

    
   // alert("hai-"+frequency[i].value);
    
    if(frequency[i].value=='DAILY')
    {
        //alert("Inside Daily");
        noiss =Math.round(diff);	
    
    }else if(frequency[i].value=='WEEKLY TWO')
    {
        //alert("Inside WEEKLY TWO");
        noiss =Math.round(diff/3.5);
    
    }else if(frequency[i].value=='WEEKLY')
    {
        //alert("Inside WEEKLY");
        noiss =Math.round(diff/7);        
    
    }else if(frequency[i].value=='FORTNIGHTLY')
    {
        //alert("Inside FORTNIGHTLY");
        noiss =Math.round(diff/14);        
    
    }else if(frequency[i].value=='MONTHLY')
    {
        //alert("Inside MONTHLY");
        noiss =Math.round(diff/30);        
    
    }else if(frequency[i].value=='BIMONTHLY')
    {
        //alert("Inside BIMONTHLY");
        noiss =Math.round(diff/60);  
    
    }else if(frequency[i].value=='QUARTERLY')
    {
        //alert("Inside QUARTERLY");
        noiss =Math.round(diff/90);  
    
    }else if(frequency[i].value=='HALF YEARLY')
    {
        //alert("Inside HALF YEARLY");
        noiss =Math.round(diff/180);  
    
    }else if(frequency[i].value=='ANNUAL')
    {
        //alert("Inside ANNUAL");
        noiss =Math.round(diff/365);  
    
    }else if(frequency[i].value=='OTHERS')
    {
        //alert("Inside OTHERS");
        //noiss =Math.round(diff);  
    
    }
    
    cboxes[i].value=noiss;  
      
    }
       
       

}



function SaveRecord()
	{
	
	if (document.ordinv.invno.value=="")
    {
       alert("Please Enter Invoice No !");
          return false;     
    }
    
    if (document.ordinv.ordno.value=="")
    {
       alert("Please choose Order No !");
          return false;     
    }
   /* if (document.ordinv.sname.value=="")
    {
        alert("Please Choose Supplier !");
          return false;            
    }  
    if (document.ordinv.jnlname.value=="")
    {
       alert("Please Choose Journal !");
          return false;     
    }*/
    
    
    
	 var cboxes = document.getElementsByName('volno[]');
     var len = cboxes.length;	 
	 var cboxes1 = document.getElementsByName('noofissue[]');
	 var cboxes2 = document.getElementsByName('bcost[]');
	 var cboxes3 = document.getElementsByName('bprice[]');
	 var cboxes4 = document.getElementsByName('discount[]');
	 var cboxes5 = document.getElementsByName('acceptPrice[]');	 
	 
	 
	for (var i=0; i<len; i++) 
    {       
       
       
        if (cboxes[i].value=="")
        {
        alert("Please fillup atleast one textbox");
        cboxes[i].focus();
        return false;            
        }
        
        if (cboxes1[i].value=="")
        {
        alert("Please fillup atleast one textbox");
        cboxes1[i].focus();
        return false;            
        }        
        
        if (cboxes2[i].value=="")
        {
        alert("Please fillup atleast one textbox");
        cboxes2[i].focus();
        return false;            
        }
        
        if (cboxes3[i].value > 0)
        {
        if (cboxes2[i].value==0)
        {
        alert("Please must fillup Bcost");
        cboxes2[i].focus();
        return false; 
        }           
        }
        
        if (cboxes3[i].value=="")
        {
        alert("Please fillup atleast one textbox");
        cboxes3[i].focus();
        return false;            
        }
        
        if (cboxes4[i].value=="")
        {
        alert("Please fillup atleast one textbox");
        cboxes4[i].focus();
        return false;            
        }
        
        if (cboxes5[i].value=="")
        {
        alert("Please fillup atleast one textbox");
        cboxes5[i].focus();
        return false;            
        }
        
        
     //alert("hai"+cboxes[i].value);
    // alert("hai"+document.ordinv.volno[i].value);
      
    }                     
	 
	 var sptdate,myDay,myMonth,myYear,combineDatestr,dt,dt1,diff;  
	 var cboxesjnl = document.getElementsByName('jnlno[]');	 
     
     for(i=0;i<len;i++)  {
     
     	 var cboxessubsf = document.getElementsByName('subsfrom'+cboxesjnl[i].value);
         var cboxessubto = document.getElementsByName('substo'+cboxesjnl[i].value);
         
         
     sptdate = String(cboxessubto[0].value).split("-");
     myDay = sptdate[0];    
     myMonth = sptdate[1];
     myYear = sptdate[2];
     combineDatestr = myYear + "/" + myMonth + "/" + myDay;  

     dt = new Date(combineDatestr);    
      
     sptdate = String(cboxessubsf[0].value).split("-");
     myDay = sptdate[0];    
     myMonth = sptdate[1];
     myYear = sptdate[2];
     combineDatestr = myYear + "/" + myMonth + "/" + myDay;      
       
     dt1 = new Date(combineDatestr);  
         
    // alert("Form:"+dt1+" To:"+dt);
     
         if(dt < dt1)
         {
            alert("Subscription from date > subscription to date !");
            return false;
         }
         
	 }
	 
	 
  
  
    var cboxes = document.getElementsByName('selectedJNL[]');
    var len = cboxes.length;
  
    var content="";
    var chkitem=0;
    
    for (var i=0; i<len; i++) {
    if(cboxes[i].checked == true)
    {  
      chkitem++;
      content=content+","+cboxes[i].value;       
    }      
    }
    
    if(chkitem >= 1)
	{
			alert('You are selected - '+chkitem);
			alert(content);
            document.ordinv.flag1.value=content;
	        document.ordinv.flag.value="Save";
	        document.ordinv.submit();
	}
	else if(chkitem == 0)
	{
			alert('Please select atleast one Journal from Order to save !');
			return false;
	}     
}	 
	 

function DeleteRecord(){
document.ordinv.method="get";

if (document.ordinv.invno.value=="")
{
  alert("Invalid Invoice Number..!");	
  return false;	
}


    var cboxes = document.getElementsByName('selectedJNL[]');
    var len = cboxes.length;
  
    var content="";
    var chkitem=0;
    
    for (var i=0; i<len; i++) {
    if(cboxes[i].checked == true)
    {  
      chkitem++;
      content=content+","+cboxes[i].value;       
    }      
    }
    
    
    if(chkitem == 0)
	{
			alert('Please select All Journal to Delete !');
			return false;
	}
	else if(chkitem == len)
	{
			alert('You are selected - '+chkitem);
			alert(content);
            
	                msg=confirm("Are You Sure To Delete");
					if(msg){
						document.ordinv.flag.value="delete";
						document.ordinv.flag1.value=content;
						document.ordinv.submit();
						}
						else
						{
						alert("Operation Cancelled..!");						
						document.ordinv.flag.value="new";  
						//document.ordinv.submit();
						}
	}
	else if(chkitem < len)
	{
			alert('Please select All Journal to Delete !');
			return false;
	}

}




function FindValue(val)
{
winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}


function ordamt_val() {
if((isNaN(document.ordinv.invamount.value))||(document.ordinv.invamount.value == ' ')) {
document.ordinv.invamount.select();
document.ordinv.invamount.value="";
return false;
   }
}



</script>

