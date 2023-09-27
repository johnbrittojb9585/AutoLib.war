<%@ page language="java" errorPage="/Error/ErrorPage.jsp"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Author.AuthorBean"  type="Lib.Auto.Author.AuthorBean">

</jsp:useBean>



<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Author
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">


</head>
<body>

<form name="Account" method="post" action=./AccountServlet>
<br><br><br><br><br><br><br><br><br>
 <P ALIGN="left"><BR>
 <h2>  </h2>
 <center>
<table>
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</tr>
<tr><td Class="addedit">User&nbsp;ID<td><input type=text name="uid" size="30"  maxlength="20">

    
<tr><td Class="addedit">Old Password<td><input type=password name="pwd" size="30" maxlength="50">
   
<tr><td Class="addedit">New Password<td><input type=password name="newpwd" size="30" maxlength="50">

<tr><td Class="addedit">Confirm Password<td><input type=password name="cfmpwd" size="30" maxlength="50">
   
<tr><td colspan=3 align=center >
<CENTER>





<input type=button name=Save Class="submitButton" value=Change onclick=SaveRec()>
<input type=reset name=Clear Class="submitButton"  Value=Clear >

</CENTER>
<input type=hidden name=flag >
</table>
</CENTER>
</form>
</body>
</html>

<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
String uchek=request.getParameter("check");
if(uchek!=null){
if(uchek.equals("usernotfound")){
	 %>
	<script language="JavaScript">
	alert("Invalid UserId/Password!!!");
	document.Account.uid.focus();
	</script><%

	}
}

%>


<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newAuthor")){
 %>
<script language="JavaScript">
document.Author.code.value="<%=beanobject.getCode()%>";
document.Author.name.focus();
</script><%

}
if(valid.equals("searchAuthor")){
 // out.print("hai");
 %>
  <script language="JavaScript">


document.Author.code.value="<%=beanobject.getCode()%>";
document.Author.name.value="<%=beanobject.getName()%>";
document.Author.desc.value="<%=beanobject.getDesc()%>";
document.Author.email.value="<%=beanobject.getEmail()%>";
</script>
<%
}
if(valid.equals("FailAuthor")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Author.flag.value="new";
			document.Author.submit();
		   	</script><%
			}
	if(valid.equals("UpdateAuthor")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Author.flag.value="new";
			document.Author.submit();
		   	</script><%
			}		
if(valid.equals("CodeCompareAuthor")){
	
//String  Autcode=(String)request.getAttribute("Autname");
%>
            <script language="JavaScript">
         
				document.Author.code.value="<%=beanobject.getCode()%>";
                document.Author.name.value="<%=beanobject.getName()%>";
                document.Author.desc.value="<%=beanobject.getDesc()%>";
                document.Author.email.value="<%=beanobject.getEmail()%>";
                
			msg=confirm("Author name Already Exists in code:"+"<%=beanobject.getCode()%>"+",Do You Want update other than name");
			
			if(msg){
			 document.Author.flag.value="update";
		   	document.Author.submit();	
			
			}

			</script><%
			}
if(valid.equals("SaveAuthor")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Author.flag.value="new";
			 document.Author.submit();
		     </script>		
			<%
			}
if(valid.equals("ReferredAuthor")){
%>            <script>
				
             alert("You can't delete since the Author has been referred in other masters");
			 document.Author.flag.value="new";
			 document.Author.submit();
			</script>	
			<%
			}	
			
			if(valid.equals("DeleteAuthor")){
			
%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Author.flag.value="new";
			document.Author.submit();
		   </script>		
			<%
			}		
			
			
			if(valid.equals("deleteCheck")){
			
%>       
            <script language="JavaScript">
			    document.Author.code.value="<%=beanobject.getCode()%>";
                document.Author.name.value="<%=beanobject.getName()%>";
                document.Author.desc.value="<%=beanobject.getDesc()%>";
                document.Author.email.value="<%=beanobject.getEmail()%>";
			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Author.flag.value="Confirmdete";
		   	document.Author.submit();	
			
			}
			</script>		
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">
			    document.Author.code.value="<%=beanobject.getCode()%>";
                document.Author.name.value="<%=beanobject.getName()%>";
                document.Author.desc.value="<%=beanobject.getDesc()%>";
                document.Author.email.value="<%=beanobject.getEmail()%>";
			    alert("Record Not Present!!!");
				</script>		
			<%
			}				
		
			
if(valid.equals("UpdateCheck")){ 
%> 
                <script language="JavaScript">
  				document.Author.code.value="<%=beanobject.getCode()%>";
                document.Author.name.value="<%=beanobject.getName()%>";
                document.Author.desc.value="<%=beanobject.getDesc()%>";
                document.Author.email.value="<%=beanobject.getEmail()%>";
	
                 msg=confirm("Author has been referred in other masters,You Can't Update?");
                if(msg)
                   {
				    document.Author.flag.value="new";
		         	document.Author.submit();	
				    
		            }
		           
			    </script>	
			 	
			<%
			}			
			
if(valid.equals("Updatename")){ 
	
	
	%> 
				    
	                <script language="JavaScript">

	  				document.Author.code.value="<%=beanobject.getCode()%>";
	                document.Author.name.value="<%=beanobject.getName()%>";
	                document.Author.desc.value="<%=beanobject.getDesc()%>";
	                document.Author.email.value="<%=beanobject.getEmail()%>";
		
	                 msg=confirm("Record already Exist, Are You Sure To Update?");
	                if(msg)
	                   {
					    document.Author.flag.value="update";
			         	document.Author.submit();	
					    
			            }
			           
				    </script>	
				 	
				<%
				}					

}
}

%>
<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
<script language=javascript>

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function new_no(){
document.Author.method="get";
document.Author.flag.value="new";
document.Author.submit();
}

function SearchRec(){
document.Author.method="post";
var no=document.Author.code.value;
if(no==""){
				document.Author.code.focus();
				alert("Insufficent Data");
				document.Author.flag.value="new";
				document.Author.submit();
		  }else{
		       document.Author.flag.value="search";
			   document.Author.submit();
			  
		}
	
}


function SaveRec(){

            
			   if((document.Account.uid.value=="")||(document.Account.pwd.value=="")||(document.Account.newpwd.value=="")||(document.Account.cfmpwd.value=="")){
			    	
					alert("Insufficent Data");
					
			}		
	else if((document.Account.newpwd.value!= document.Account.cfmpwd.value))
	{
					alert("Not match Confirm Password!!! ");
	
	}
	else{
	                document.Account.flag.value="save";
		         	document.Account.submit();	
	}
	
}


function UpdateRec(){
             document.Author.method="post";
			   if(chk() ){
			       	document.Author.flag.value="update";
		         	document.Author.submit();	
					
			}		
	else
	{
	alert("Insufficent Data");
	}
	
}

function isWhitespace(str)
		{
	
			var re = /[\S]/g
			if (re.test(str)) return false;
			return true;
			
		}
		
function chk(){
var flag_s;
var i;
var sp=document.Author.name.value;
var d=sp.replace(" ");
alert(d);

if(d=="")
{

document.Author.name.focus();
				document.Author.flag.value="none";
				document.Author.name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Author.name.value.length;i++)
 	                      {
 	                       if(document.Author.name.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Author.name.focus();
		                   	document.Author.name.value="";
			                return false;
		                      }
		                   else if (document.Author.code.value==""){
		                 	document.Author.code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindAuthor()
{
winpopup=window.open("search.jsp","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function author_code_val() {
if((isNaN(document.Author.code.value))||(document.Author.code.value == ' ')) {
document.Author.code.select();
document.Author.code.value="";
    
  }
}
function ReportRec(){
			    	document.Author.flag.value="Report";
		         	document.Author.submit();	
					
			
	
}
function ClearRec(){ 
  
		 document.Author.code.value="";
         document.Author.name.value="";
         document.Author.desc.value="";
         document.Author.email.value="";
		 document.Author.flag.value="";
}
function DelRec(){
document.Author.method="post";
if (document.Author.code.value==""){
				document.Author.code.focus();
				alert("Insufficent Data");
				document.Author.flag.value="new";
				document.Author.submit();
				}
				else{
				  document.Author.flag.value="delete";
				  document.Author.submit();
				 }                         
				
}
			
function load()
{
document.Author.code.focus();
}


</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
