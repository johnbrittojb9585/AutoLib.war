<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>

<body background="/AutoLib/soft.jpg">
<form method="POST" action="./BulkUpdateServlet" name=Bupdate>
<br>
<a href="<%=request.getContextPath() %>/QueryBuilder/" ><font color="red">Back</font></a>
<h3><font color="Red" >Search&nbsp;Result </font></h3>

<c:if test="${searchResult ne null }">	
<c:forEach items="${searchResult}" var="std1"  begin="0" end="0">	
	<c:set var="name2" value="${std1.listColumn}" />
	<c:set var="count" value="0" />	
		
<table align="center" border="1" width="95%" cellspacing="0" cellpadding="3">

<tr id="tablehead">		

        


<c:forEach var="item" items="${name2}" varStatus="loop">
			
		<c:if test="${empty item}">              
              <td align="center" width="4%"> S.No </td>	
        </c:if>		
		<c:if test="${item eq 'access_no' }">
		      <td align="center" width="8%"> Access No </td>              
        </c:if>
        <c:if test="${item eq 'author_name' }">
		      <td align="center"> Author Name </td>              
        </c:if>
        <c:if test="${item eq 'title' }">
		      <td align="center"> Title </td>              
        </c:if>
        <c:if test="${item eq 'call_no' }">
		      <td align="center"> Call No </td>              
        </c:if>
        <c:if test="${item eq 'dept_name' }">
		      <td align="center"> Department </td>              
        </c:if>
        <c:if test="${item eq 'sub_name' }">
		      <td align="center"> Subject </td>              
        </c:if>
        <c:if test="${item eq 'sp_name' }">
		      <td align="center"> Publisher </td>              
        </c:if>
        <c:if test="${item eq 'availability' }">
		      <td align="center"> Status </td>              
        </c:if>
        <c:if test="${item eq 'isbn' }">
		      <td align="center"> ISBN </td>              
        </c:if>
        <c:if test="${item eq 'year_pub' }">
		      <td align="center"> Year </td>              
        </c:if>
        <c:if test="${item eq 'bprice' }">
		      <td align="center"> Price </td>              
        </c:if>
        <c:if test="${item eq 'received_date' }">
		      <td align="center"> Rec.Date </td>              
        </c:if>
        <c:if test="${item eq 'edition' }">
		      <td align="center"> Edition </td>              
        </c:if>
        <c:if test="${item eq 'location' }">
		      <td align="center"> Location </td>              
        </c:if>
        <c:if test="${item eq 'keywords' }">
		      <td align="center"> Keywords </td>              
        </c:if>
        <c:if test="${item eq 'language' }">
		      <td align="center"> Language </td>              
        </c:if>  
         <c:if test="${item eq 'supplier' }">
		      <td align="center"> Supplier </td>              
        </c:if>  
         <c:if test="${item eq 'invoice_no' }">
		      <td align="center"> Invoice No </td>              
        </c:if>  
         <c:if test="${item eq 'invoice_date' }">
		      <td align="center"> Inv.Date </td>              
        </c:if>  
        <c:if test="${item eq 'pages' }">
		      <td align="center"> Pages </td>              
        </c:if> 
        <c:if test="${item eq 'purchase' }">
		      <td align="center"> Purchase Mode </td>              
        </c:if> 
        <c:if test="${item eq 'document' }">
		      <td align="center"> Document </td>              
        </c:if> 
        <c:if test="${item eq 'volno' }">
		      <td align="center"> Volume Number </td>              
        </c:if> 
        <c:if test="${item eq 'net_price' }">
		      <td align="center"> Acc.Price </td>              
        </c:if>
        
              

</c:forEach>     
</tr>
    

<c:forEach items="${searchResult}" var="std" varStatus="loop" >		
<tr>
<c:set var="count" value="${count + 1}" />   

<c:forEach var="item" items="${name2}" varStatus="loop">

        <c:if test="${empty item}">
            <td align="left" id="tablebody" width="4%"> <c:out value="${count}"/> </td>
        </c:if>
            
         <c:if test="${item eq 'access_no' }">
		      <td align="left" id="tablebody" width="8%"> <c:out value="${std.accessNo}"/> </td>              
        </c:if>
        <c:if test="${item eq 'author_name' }">
		      <td align="left" id="tablebody" > <c:out value="${std.authorName}"/> </td>              
        </c:if>
        <c:if test="${item eq 'title' }">
		      <td align="left" id="tablebody"> <c:out value="${std.title}"/> </td>              
        </c:if>
        <c:if test="${item eq 'call_no' }">
		      <td align="left" id="tablebody"> <c:out value="${std.callNo}"/> </td>              
        </c:if>
        <c:if test="${item eq 'dept_name' }">
		      <td align="left" id="tablebody"> <c:out value="${std.department}"/> </td>              
        </c:if>
        <c:if test="${item eq 'sub_name' }">
		      <td align="left" id="tablebody"> <c:out value="${std.subject}"/> </td>              
        </c:if>
        <c:if test="${item eq 'sp_name' }">
		      <td align="left" id="tablebody"> <c:out value="${std.publisher}"/> </td>              
        </c:if>
        <c:if test="${item eq 'availability' }">
		      <td align="left" id="tablebody"> <c:out value="${std.availability}"/> </td>              
        </c:if>
        <c:if test="${item eq 'isbn' }">
		      <td align="left" id="tablebody"> <c:out value="${std.isbn}"/> </td>              
        </c:if>
        <c:if test="${item eq 'year_pub' }">
		      <td align="left" id="tablebody"> <c:out value="${std.year}"/> </td>              
        </c:if>
        <c:if test="${item eq 'bprice' }">
		      <td align="left" id="tablebody"> <c:out value="${std.price}"/> </td>              
        </c:if>
        <c:if test="${item eq 'received_date' }">
		      <td align="left" id="tablebody"> <c:out value="${std.receivedDate}"/> </td>              
        </c:if>
        <c:if test="${item eq 'edition' }">
		      <td align="left" id="tablebody"> <c:out value="${std.edition}"/> </td>              
        </c:if>
        <c:if test="${item eq 'location' }">
		      <td align="left" id="tablebody"> <c:out value="${std.location}"/> </td>              
        </c:if>
        <c:if test="${item eq 'keywords' }">
		      <td align="left" id="tablebody"> <c:out value="${std.keywords}"/> </td>              
        </c:if>
        <c:if test="${item eq 'language' }">
		      <td align="left" id="tablebody"> <c:out value="${std.language}"/> </td>              
        </c:if>	
         <c:if test="${item eq 'invoice_no' }">
		      <td align="left" id="tablebody"> <c:out value="${std.invoiceNo}"/> </td>              
        </c:if>	
         <c:if test="${item eq 'invoice_date' }">
		      <td align="left" id="tablebody"> <c:out value="${std.invoiceDate}"/> </td>              
        </c:if>	
         <c:if test="${item eq 'supplier' }">
		      <td align="left" id="tablebody"> <c:out value="${std.supplier}"/> </td>              
        </c:if>	
        <c:if test="${item eq 'pages' }">
		      <td align="left" id="tablebody"> <c:out value="${std.pages}"/> </td>              
        </c:if>	
         <c:if test="${item eq 'volno' }">
		      <td align="left" id="tablebody"> <c:out value="${std.volumeNo}"/> </td>              
        </c:if>	
         <c:if test="${item eq 'purchase' }">
		      <td align="left" id="tablebody"> <c:out value="${std.purchase}"/> </td>              
        </c:if>	
         <c:if test="${item eq 'document' }">
		      <td align="left" id="tablebody"> <c:out value="${std.document}"/> </td>              
        </c:if>	
         <c:if test="${item eq 'net_price' }">
		      <td align="left" id="tablebody"> <c:out value="${std.netPrice}"/> </td>              
        </c:if>	
        		
		
</c:forEach> 
</tr>   
</c:forEach>
    
</c:forEach>	
</c:if>
</form>

</body>
</html>

