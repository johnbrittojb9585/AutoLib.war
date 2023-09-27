package Lib.Auto.Member;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.Author;
import Lib.Auto.Book.bookbean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.City.CityBean;
import Lib.Auto.Photo.PhotoBean;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Member/MemberServlet")


public class Member extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Member.class);
	private static final long serialVersionUID = -8906987252709033668L;
	/**
	 * 
	 */
	
	 
	 
	private boolean isMultipart;
	   private String filePath="",tempPath="",indexPage=null;
	   private int maxFileSize = 800000 * 1024 ;   // File Size should be __KB
	   private int maxMemSize = 400 * 1024 ;     // Memory Size should be __KB
	   private File file ;
	   
	   private static final int IMG_WIDTH = 683;
	   private static final int IMG_HEIGHT = 1024;
	 
	int updateFlag;

	String flag = "",fileName="",Name="",Desig="",Year="",Gname="",Dname="",Birthdate,SSecurity="",validdate="",enroldate="",Cname=""
			,Add2="",Add1="",City="",Pin="",State="",Phone="",Email="",Sex="",Pro="",Rem="",Deposit="",flagphoto="",changeMemberId="",flagCheck="";



	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException {

performTask(request, response);

}

public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException {

performTask(request, response);

}

public void performTask(HttpServletRequest request,
	HttpServletResponse response) throws ServletException {


		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			
			
			String flagCheck= "";
			
			flagCheck = request.getParameter("flagCheck");
			
			System.out.println("flagCheck:::::::::::::::::"+request.getParameter("flagCheck"));
			
			
			
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			//For Auto Complete
			
            response.setContentType("application/json");
			
			try{
				String term = request.getParameter("Code");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<MemberBean> list = ss.getMemberAutoIdSearch(term);
			       for(MemberBean user: list){
					//System.out.println(user.getCode());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	            }	
		}catch(Exception e){
			//e.printStackTrace();
		}  		 


		try{
				String term = request.getParameter("Name");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<MemberBean> list = ss.getMemberAutoNameSearch(term);
			       for(MemberBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    		 

		try{
			String term = request.getParameter("Desig");
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
             {
			//System.out.println("Data from ajax call " + term);
						    
			   ArrayList<DesignationBean> list = ss.getMemberAutoDesigSearch(term);
		       for(DesignationBean user: list){
				//System.out.println(user.getName());
			}       

			String searchList = new Gson().toJson(list);
						
			response.getWriter().write(searchList);  
						
			//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
             }	
	}catch(Exception e){
		//e.printStackTrace();
	}    		 

		try{
			String term = request.getParameter("Dname");
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
             {
			//System.out.println("Data from ajax call " + term);
						    
			   ArrayList<DepartmentBean> list = ss.getMemberAutoDeptSearch(term);
		       for(DepartmentBean user: list){
		    	     
				//System.out.println(user.getName());
			}       

			String searchList = new Gson().toJson(list);
						
			response.getWriter().write(searchList);  
						
			//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
             }	
	}catch(Exception e){
		//e.printStackTrace();
	}    		 

		try{
			String term = request.getParameter("Gname");
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
             {
			//System.out.println("Data from ajax call " + term);
						    
			   ArrayList<GroupBean> list = ss.getMemberAutoGroupSearch(term);
		       for(GroupBean user: list){
				//System.out.println(user.getName());
			}       

			String searchList = new Gson().toJson(list);
						
			response.getWriter().write(searchList);  
						
			//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
             }	
	}catch(Exception e){
		//e.printStackTrace();
	}  
		
		try{
			String term = request.getParameter("Cname");
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
             {
			//System.out.println("Data from ajax call " + term);
						    
			   ArrayList<CourseBean> list = ss.getMemberAutoCourseSearch(term);
		       for(CourseBean user: list){
				//System.out.println(user.getName());
			}       

			String searchList = new Gson().toJson(list);
						
			response.getWriter().write(searchList);  
						
			//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
             }	
	}catch(Exception e){
		//e.printStackTrace();
	}   
		
		try{
			String term = request.getParameter("City");
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
             {
			//System.out.println("Data from ajax call " + term);
						    
			   ArrayList<CityBean> list = ss.getMemberAutoCitySearch(term);
		       for(CityBean user: list){
				//System.out.println(user.getName());
			}       

			String searchList = new Gson().toJson(list);
						
			response.getWriter().write(searchList);  
						
			//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
             }	
	}catch(Exception e){
		//e.printStackTrace();
	}    

			
			MemberBean memberBean=new MemberBean();
			
			
			/*if(flagCheck.equals("new") && (!flagCheck.equals(null)))
		      {
		    	  memberBean.setPhoto1(null);
		    	  indexPage ="/Member/index.jsp";
		    	  dispatch(request, response, indexPage);
		      }*/
			
			
//			 get Path from Property file 
			   Properties properties=new Properties();
		   	   properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
		   		
		   	   filePath = properties.getProperty("contents.upload.filepath");		
		   	   
		   	   File tempdir = new File(filePath+"/temp");
			   
		       if(tempdir.exists())
			   {
				   tempPath = tempdir + "/";
				   //log4jLogger.info(">>>>>>>>>>> Directory Exists >>>>>>>>>>>>"+tempdir+" Path:"+tempPath);
			   }else {
				   if(tempdir.mkdir()){
					   tempPath = tempdir + "/";
				       log4jLogger.info(">>>>>>>>>>> Directory Created  >>>>>>>>>>>>"+tempdir+" Path:"+tempPath);
				   }
			   }
		   	
			   
		      // Check that we have a file upload request
		      isMultipart = ServletFileUpload.isMultipartContent(request);
		      response.setContentType("text/html");
		      java.io.PrintWriter out;

				out = response.getWriter( );	
		      
		           
		      if( !isMultipart ){
		    	  
		         return;
		      }      
		            
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		      // Location to save data that is larger than maxMemSize.
		      factory.setRepository(new File(tempPath));

		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );

		       
		      // Parse the request to get file items.
		  
		      List fileItems = upload.parseRequest(request);
		      // Process the uploaded file items
		      Iterator i = fileItems.iterator();
		     
		      String member_code = "",flag = "";
		      byte[] fileInBytes = null;
		      
		      
		      while ( i.hasNext () ) 
		      {
		         FileItem fi = (FileItem)i.next();
		         if ( !fi.isFormField () )	
		         {
		            // Get the uploaded file parameters
		            String fieldName = fi.getFieldName();
		            fileName = fi.getName();
		                
		        	fileInBytes=fi.get(); 
		        	if(fileName!="")
		        	{
		        	memberBean.setPhoto1(fileInBytes);	
//		        	flagphoto="photo";		                       
		        	log4jLogger.info("start===========fieldName====="+fieldName);
		        	log4jLogger.info("start===========fileName====="+fileName);
		        	log4jLogger.info("start===========fileInBytes====="+fileInBytes);
		        	}
		         }else if(fi.isFormField())
		         {   
		        	 if("Code".equalsIgnoreCase(fi.getFieldName())){        		
		        		 member_code = fi.getString();
		        	 }
		        	 if("flag".equalsIgnoreCase(fi.getFieldName())){
		        		 flag = fi.getString();
		        	 }
		        	 if("Name".equalsIgnoreCase(fi.getFieldName())){
		        		 Name = fi.getString();
		        	 }
		        	 if("Desig".equalsIgnoreCase(fi.getFieldName())){        		
		        		 Desig = fi.getString();
		        	 }
		        	 if("Year".equalsIgnoreCase(fi.getFieldName())){
		        		 Year = fi.getString();
		        	 }
		        	 if("Gname".equalsIgnoreCase(fi.getFieldName())){
		        		 Gname = fi.getString();
		        	 }if("Dname".equalsIgnoreCase(fi.getFieldName())){        		
		        		 Dname = fi.getString();
		        	 }
		        	 if("Security".equalsIgnoreCase(fi.getFieldName())){
		        		 SSecurity = fi.getString();
		        	 }
		        	 if("Birthdate".equalsIgnoreCase(fi.getFieldName())){
		        		 Birthdate = fi.getString();
		        	 }if("Deposit".equalsIgnoreCase(fi.getFieldName())){        		
		        		 Deposit = fi.getString();
		        	 }
		        	 if("enroldate".equalsIgnoreCase(fi.getFieldName())){
		        		 enroldate = fi.getString();
		        	 }
		        	 if("validdate".equalsIgnoreCase(fi.getFieldName())){
		        		 validdate = fi.getString();
		        	 }if("Cname".equalsIgnoreCase(fi.getFieldName())){        		
		        		 Cname = fi.getString();
		        	 }
		        	 if("Add1".equalsIgnoreCase(fi.getFieldName())){
		        		 Add1 = fi.getString();
		        	 }
		        	 if("Add2".equalsIgnoreCase(fi.getFieldName())){
		        		 Add2 = fi.getString();
		        	 }if("City".equalsIgnoreCase(fi.getFieldName())){        		
		        		 City = fi.getString();
		        	 }
		        	 if("State".equalsIgnoreCase(fi.getFieldName())){
		        		 State = fi.getString();
		        	 }
		        	 if("Pin".equalsIgnoreCase(fi.getFieldName())){
		        		 Pin = fi.getString();
		        	 }
		        	 if("Email".equalsIgnoreCase(fi.getFieldName())){
		        		 Email = fi.getString();
		        	 }
		        	 if("Phone".equalsIgnoreCase(fi.getFieldName())){
		        		 Phone = fi.getString();
		        	 }if("Sex".equalsIgnoreCase(fi.getFieldName())){        		
		        		 Sex = fi.getString();
		        	 }
		        	 if("Pro".equalsIgnoreCase(fi.getFieldName())){
		        		 Pro = fi.getString();
		        	 }
		        	 if("Rem".equalsIgnoreCase(fi.getFieldName())){
		        		 Rem = fi.getString();
		        	 }
		        	 if("changeUserId".equalsIgnoreCase(fi.getFieldName())){
		        		 changeMemberId = fi.getString();
		        	 }
		        	 
		        	 if("flagCheck".equalsIgnoreCase(fi.getFieldName())){
		        		 flagCheck = fi.getString();
		        	 }
		        	 
		        	 
		        	 
		         }        
		      }    
		      
		      
		      
		      
		      
		      if(flag.equals("none"))
		      {
		    	  memberBean.setPhoto1(null);
		    	  indexPage ="/Member/index.jsp";
		    	  dispatch(request, response, indexPage);
		      }
			if(flag.equals("changeUserId")){
				
				log4jLogger.info("Old User Id value::::::::::::::::: "+member_code);
				log4jLogger.info("New User Id value::::::::::::::::: "+changeMemberId);
				int result = ss.getMemberIdChange(member_code,changeMemberId);
				
				if (result==1){
					indexPage ="/Member/index.jsp?check=failChangeMember";
				}else{
					indexPage ="/Member/index.jsp?check=SuccessChangeMember";	
				}
				memberBean.setPhoto1(null);
				dispatch(request, response, indexPage);
			}
			
			
			if (flag.equals("search")) {			
				log4jLogger.info("start===========search=====");
				MemberBean ob = ss.getMemberSearch(member_code);
				if (ob.getCode()!=null) {						
								
					request.setAttribute("ss", ob);				
					indexPage ="/Member/index.jsp?check=searchMember";
				} else {					
					request.setAttribute("ss", ob);
				indexPage ="/Member/index.jsp?check=FailMember";				
				}
				dispatch(request, response, indexPage);
			}

			if(flag.equals("save")){
				log4jLogger.info("start===========save=====");	
				
				 String Member_savebdate=Security.TextDate_member(Birthdate);
				 String Member_saveedate=Security.TextDate_member(enroldate);
				 String Member_saveexdate=Security.TextDate_member(validdate);
			
				  String currentDateString_bdate[]=Member_savebdate.split("-");
	 			  int cy=Integer.parseInt(currentDateString_bdate[0]); 
				  int cm=Integer.parseInt(currentDateString_bdate[1]); 
				  int cd=Integer.parseInt(currentDateString_bdate[2]); 
				  java.util.Calendar bir = new java.util.GregorianCalendar();
				  bir.set(cy,cm,cd);				  
				  String birth_date=currentDateString_bdate[0]+"-"+currentDateString_bdate[1]+"-"+currentDateString_bdate[2];
				  
				  String currentDateString_endate[]=Member_saveedate.split("-");					 
				  int cy1=Integer.parseInt(currentDateString_endate[0]); 
				  int cm1=Integer.parseInt(currentDateString_endate[1]); 
				  int cd1=Integer.parseInt(currentDateString_endate[2]); 
				  java.util.Calendar enr = new java.util.GregorianCalendar();
				  enr.set(cy1,cm1,cd1);				  
				  String enroll_date=currentDateString_endate[0]+"-"+currentDateString_endate[1]+"-"+currentDateString_endate[2];
				  
				  String currentDateString_exdate[]=Member_saveexdate.split("-");					 
				  int cy2=Integer.parseInt(currentDateString_exdate[0]); 
				  int cm2=Integer.parseInt(currentDateString_exdate[1]); 
				  int cd2=Integer.parseInt(currentDateString_exdate[2]); 
				  java.util.Calendar exp = new java.util.GregorianCalendar();
				  exp.set(cy2,cm2,cd2);							
				  String expiry_date=currentDateString_exdate[0]+"-"+currentDateString_exdate[1]+"-"+currentDateString_exdate[2];

				  memberBean=new MemberBean();
				  
				    memberBean.setCode(member_code);
					memberBean.setName(Name);
					memberBean.setBdate(birth_date);
					memberBean.setEdate(enroll_date);
					memberBean.setExdate(expiry_date);
					memberBean.setDamount(Deposit);
					memberBean.setSex(Sex);
					memberBean.setMadd1(Add1);
					memberBean.setMadd2(Add2);
					memberBean.setMcity(City);
					memberBean.setMstate(State);
					memberBean.setMpincode(Pin);
					memberBean.setMphone(Phone);
					memberBean.setMemail(Email);
					memberBean.setRemarks(Rem);
					memberBean.setProfile(Pro);
					//memberBean.setPhoto(request.getParameter("Photo"));
					memberBean.setSecurity(SSecurity);
					memberBean.setCyear(Year);
					memberBean.setDecode(Desig);
					memberBean.setDeptcode(Dname);
					memberBean.setCoursecode(Cname);
					memberBean.setGroupcode(Gname);
								  
				  
				  int member_check=ss.getMemberCheck(member_code);
				  
				 if((bir).after(enr)){ 
									
				request.setAttribute("ss", memberBean);				
				indexPage = "/Member/index.jsp?check=enroll";	
				 }
				 else if((enr).after(exp)){
					 request.setAttribute("ss", memberBean);				
						indexPage = "/Member/index.jsp?check=expiry";
				 }
		 
				 else if(member_check>0)
				 {
					 flagphoto = "";
					 request.setAttribute("ss", memberBean);				
						indexPage = "/Member/index.jsp?check=UpdateCheck";
					 
				 }
				 else
				 {
					   
					 	flagphoto="photo";		 
					    memberBean=new MemberBean();
						
					    
						memberBean.setCode(member_code);
						memberBean.setName(Name);
						memberBean.setBdate(birth_date);
						memberBean.setEdate(enroll_date);
						memberBean.setExdate(expiry_date);
						memberBean.setDamount(Deposit);
						memberBean.setSex(Sex);
						memberBean.setMadd1(Add1);
						memberBean.setMadd2(Add2);
						memberBean.setMcity(City);
						memberBean.setMstate(State);
						memberBean.setMpincode(Pin);
						memberBean.setMphone(Phone);
						memberBean.setMemail(Email);
						memberBean.setRemarks(Rem);
						memberBean.setProfile(Pro);
						//memberBean.setPhoto(request.getParameter("Photo"));
						memberBean.setSecurity(SSecurity);
						memberBean.setCyear(Year);
						memberBean.setDecode(Desig);
						memberBean.setDeptcode(Dname);
						memberBean.setCoursecode(Cname);
						memberBean.setGroupcode(Gname);
						
						int count=ss.getMemberSave(memberBean);
						if(memberBean.getPhoto1()!=null)
						{
						PhotoBean bean=new PhotoBean();
				        byte[] stutentImageInByte={0xa, 0x2, (byte) 0xff};
				      
				        BufferedImage studentImage = ImageIO.read(new ByteArrayInputStream(memberBean.getPhoto1()));
						int studentType = studentImage.getType() == 0? BufferedImage.TYPE_BYTE_BINARY : studentImage.getType();
						
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						BufferedImage resizeImageJpg  = resizeImage(studentImage, studentType);
						ImageIO.write(resizeImageJpg , "jpg",baos); 
						baos.flush();
						stutentImageInByte = baos.toByteArray();
						baos.close();	
						
						bean.setMemberid(member_code);
				        //bean.setPhoto(fileInBytes);       
						bean.setPhoto(stutentImageInByte);
						int count2=ss.getMemberPhotoSave(bean);
						memberBean.setPhoto1(null);
						memberBean.setPhoto(null);
						bean.setPhoto(null);
						
						}
						indexPage = "/Member/index.jsp?check=SaveMember";
				 }
				 
				 
				 
				 
				dispatch(request, response, indexPage);	
				
			}
			
			
			
			if(flag.equals("update"))
			{
				log4jLogger.info("start===========update=====");	
				flagphoto="photo";
				String Member_savebdate=Security.getdate(Birthdate);
				String Member_saveedate =Security.getdate(enroldate);
				String Member_saveexdate =Security.getdate(validdate);
				
	
				String currentDateString_bdate[]=Member_savebdate.split("-");
				 
				  
				  int cy=Integer.parseInt(currentDateString_bdate[0]); 
				  int cm=Integer.parseInt(currentDateString_bdate[1]); 
				  int cd=Integer.parseInt(currentDateString_bdate[2]); 
				  java.util.Calendar bir = new java.util.GregorianCalendar();
				  bir.set(cy,cm,cd);
				  
				  				  
				  String birth_date=currentDateString_bdate[2]+"-"+currentDateString_bdate[1]+"-"+currentDateString_bdate[0];
				  
				 
				  
				  String currentDateString_endate[]=Member_saveedate.split("-");
					 
				  int cy1=Integer.parseInt(currentDateString_endate[0]); 
				  int cm1=Integer.parseInt(currentDateString_endate[1]); 
				  int cd1=Integer.parseInt(currentDateString_endate[2]); 
				  java.util.Calendar enr = new java.util.GregorianCalendar();
				  enr.set(cy1,cm1,cd1);
				  
				  String enroll_date=currentDateString_endate[2]+"-"+currentDateString_endate[1]+"-"+currentDateString_endate[0];
				  
				  String currentDateString_exdate[]=Member_saveexdate.split("-");
					 
				  int cy2=Integer.parseInt(currentDateString_exdate[0]); 
				  int cm2=Integer.parseInt(currentDateString_exdate[1]); 
				  int cd2=Integer.parseInt(currentDateString_exdate[2]); 
				  java.util.Calendar exp = new java.util.GregorianCalendar();
				  exp.set(cy2,cm2,cd2);
							
				  String expiry_date=currentDateString_exdate[2]+"-"+currentDateString_exdate[1]+"-"+currentDateString_exdate[0];
				
				
				
				memberBean.setCode(member_code);
				memberBean.setName(Name);
				memberBean.setBdate(Security.TextDate_Insert(birth_date));
				memberBean.setEdate(Security.TextDate_Insert(enroll_date));
				memberBean.setExdate(Security.TextDate_Insert(expiry_date));
				memberBean.setDamount(Deposit);
				memberBean.setSex(Sex);
				memberBean.setMadd1(Add1);
				memberBean.setMadd2(Add2);
				memberBean.setMcity(City);
				memberBean.setMstate(State);
				memberBean.setMpincode(Pin);
				memberBean.setMphone(Phone);
				memberBean.setMemail(Email);
				memberBean.setRemarks(Rem);
				memberBean.setProfile(Pro);
				//memberBean.setPhoto(request.getParameter("Photo"));
				//memberBean.setPhoto(memberBean.getPhoto());  //By RK
				memberBean.setSecurity(SSecurity);
				
				memberBean.setCyear(Year);
				memberBean.setDecode(Desig);
				memberBean.setDeptcode(Dname);
				memberBean.setCoursecode(Cname);
				memberBean.setGroupcode(Gname);
				
				int count=ss.getMemberUpdate(memberBean);
				if(memberBean.getPhoto1()!=null)
				{
				PhotoBean bean=new PhotoBean();
		        byte[] stutentImageInByte={0xa, 0x2, (byte) 0xff};
		      
		        BufferedImage studentImage = ImageIO.read(new ByteArrayInputStream(memberBean.getPhoto1()));
				int studentType = studentImage.getType() == 0? BufferedImage.TYPE_BYTE_BINARY : studentImage.getType();
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				BufferedImage resizeImageJpg  = resizeImage(studentImage, studentType);
				ImageIO.write(resizeImageJpg , "jpg",baos); 
				baos.flush();
				stutentImageInByte = baos.toByteArray();
				baos.close();	
				
				bean.setMemberid(member_code);
		        //bean.setPhoto(fileInBytes);       
				bean.setPhoto(stutentImageInByte);
				int count2=ss.getMemberPhotoSave(bean);
				
				memberBean.setPhoto1(null);
				memberBean.setPhoto(null);
				bean.setPhoto(null);
				}
				request.setAttribute("ss", memberBean);				
				indexPage = "/Member/index.jsp?check=UpdateMember";				
				dispatch(request, response, indexPage);	
				
			}
			  
			if(flag.equals("delete"))
			{
				log4jLogger.info("start===========delete=====");	
				
				MemberBean ob = ss.getMemberSearch(member_code);

				if (ob.getCode()!=null) {			
					request.setAttribute("ss", ob);				
					indexPage ="/Member/index.jsp?check=deleteCheck";			
				} else {					
					request.setAttribute("ss", ob);
				indexPage ="/Member/index.jsp?check=FailMember";				
				}
				dispatch(request, response, indexPage);
			}
			
			
			if(flag.equals("clearphoto"))
			{
				log4jLogger.info("start===========delete=====");	
				
				ss.getMemberClear(member_code);//For Clear User Photo
				
				MemberBean ob1 = ss.getMemberSearch(member_code);
				if (ob1.getCode()!=null) {						
								
					request.setAttribute("ss", ob1);				
					indexPage ="/Member/index.jsp?check=searchMember";
				} else {					
					request.setAttribute("ss", ob1);
				indexPage ="/Member/index.jsp?check=FailMember";				
				}

				dispatch(request, response, indexPage);
			}
			
			if(flag.equals("Confirmdete"))
			{
				log4jLogger.info("start===========Confirmdete=====");
				int issue_mas_check = ss.getIssueMasCheck(member_code);
				
				if (issue_mas_check >0)
					
				{
					
					indexPage ="/Member/index.jsp?check=ReferredMember";
				}
				else
				{
					int count = ss.getMemberDelete(member_code);
					indexPage ="/Member/index.jsp?check=DeleteMember";
				}
				memberBean.setPhoto1(null);
					dispatch(request, response, indexPage);
			}
			
			
			if(flagphoto.equalsIgnoreCase("photo") && memberBean.getPhoto1()!=null)
		      {
				log4jLogger.info("start===========photo save=====");
		    	
		      }
			
			
			
		} catch (Exception sss) {
			//throw new ServletException(sss);
                 sss.printStackTrace();
		} finally {
			try {
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Instance variable for SQL statement property
	 */


	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
	private static BufferedImage resizeImage(BufferedImage originalImage, int type)
	{
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
	 
		return resizedImage;
	}
}
