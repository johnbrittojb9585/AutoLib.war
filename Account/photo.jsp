<jsp:useBean id="bean" scope="request" class="Lib.Auto.Account.AccountBean"/>

<%
if(bean.getPhoto1()!= null) {

	response.reset();
	response.setContentType("image/jpeg");
    response.addHeader("Cache-Control", "no-transform, max-age=0");  

try{
	response.getOutputStream().write(bean.getPhoto1());

	response.getOutputStream().flush();	
    response.getOutputStream().close();
    out.clear(); 
    bean.setPhoto1(null);	
   
}catch(Exception e){
	e.printStackTrace();
 	bean.setPhoto1(null);
 }
    return;
}
%>		
