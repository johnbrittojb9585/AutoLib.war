package Lib.Auto.ContentUpload;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;

import org.apache.commons.io.output.*;


public class ContentUpload extends HttpServlet implements Serializable  {
   
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private static Logger log4jLogger = Logger.getLogger(ContentUpload.class);

   private boolean isMultipart;
   private String filePath="",tempPath="",indexPage=null;
   private int maxFileSize = 1024 * 1024 * 1024;   // File Size should be 70KB
   private int maxMemSize = 4 * 1024 ;    // Memory Size should be 4KB
   private File file ;   
 
  
   
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, java.io.IOException {
	   
	   performTask(request, response);
	   
   }
   
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, java.io.IOException {
       performTask(request, response);
       throw new ServletException("GET method used with " + getClass( ).getName( )+": POST method required.");
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
		
		
	   CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();

//	 get Path from Property file 
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
            
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File(tempPath));  // by RK

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
  
      List fileItems = upload.parseRequest(request);
      // Process the uploaded file items
      Iterator j = fileItems.iterator();
     
      String accessNo = "",flag = "",document = "";
      byte[] fileInBytes = null;
      
      
      while ( j.hasNext () ) 
      {    	 
         FileItem fi = (FileItem)j.next();
         if(fi.isFormField())
         {       	 
        	 if("accno".equalsIgnoreCase(fi.getFieldName()))     {        		
        		 accessNo = fi.getString();
        	 }
        	 if("flag".equalsIgnoreCase(fi.getFieldName()))      {
        		 flag = fi.getString();
        	 }
        	 if("document".equalsIgnoreCase(fi.getFieldName()))  {
        		 document = fi.getString();
        	 }
         }        
      }  
      
      
      boolean result = false;
      result = ss.getCheckContentsNumber(accessNo,document);
            
      if (result)				
      {   	  
    	/**  
    	Properties properties=new Properties();
    	properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
    		
    	filePath = properties.getProperty("contents.upload.filepath");	    */	  
    	  
    	File dir = new File(filePath+"/"+document);
    	
    	if(dir.exists())
    	{
    		filePath = dir + "/";
    		//log4jLogger.info(">>>>>>>>>>> Directory Exists >>>>>>>>>>>>"+dir+" Path:"+filePath);
    	}else {
    		if(dir.mkdir()){
    		   filePath = dir + "/";
    		   log4jLogger.info(">>>>>>>>>>> Directory Created  >>>>>>>>>>>>"+dir+" Path:"+filePath);
    		}
    	}
    	  
    	Iterator k = fileItems.iterator();  
    	  
        while ( k.hasNext () ) 
        {       	 
          FileItem fi = (FileItem)k.next();    	  
          if ( !fi.isFormField () )	
          {
             // Get the uploaded file parameters
             String fieldName = fi.getFieldName();
             String fileName = fi.getName();
             String contentType = fi.getContentType();
             boolean isInMemory = fi.isInMemory();
             long sizeInBytes = fi.getSize();             
             
             
             if(fileName.lastIndexOf(".") > 0)
             {
               fileName = accessNo + "." + fileName.substring(fileName.lastIndexOf(".")+1);
             }
             
             
             // Write the file
             if( fileName.lastIndexOf("\\") >= 0 )   {           	 
         
            	file = new File( filePath + 
                fileName.substring( fileName.lastIndexOf("\\"))) ;
             }
             else{
             
                file = new File( filePath + 
                fileName.substring(fileName.lastIndexOf("\\")+1)) ;
             }              
             
             fi.write( file ) ;        
             //log4jLogger.info("===========22222222222222====="+fileName+" And "+file);
             
             ss.getUpdateContentFile(accessNo,document,fileName);
           
                 	
          }              	  
        } 	  
    	  
    	  
	      //int count=ss.getMemberPhotoSave(member_code);
		  indexPage ="/ContentUpload/index.jsp?check=success";
		
	/*	}else   {
		  indexPage ="/ContentUpload/index.jsp?check=NotRightUser";
		}*/
	  }
	  else	{
		  indexPage ="/ContentUpload/index.jsp?check=FailMember";
	  }        

    dispatch(request, response, indexPage);    
    
      
   }catch(Exception ex) {
	   indexPage ="/ContentUpload/index.jsp?check=LargeFile";
	   log4jLogger.info("AutoLibError:FileException:"+ex);
	   dispatch(request, response, indexPage);
   }
   
   }catch (IOException e) {
		// TODO Auto-generated catch block	   
		e.printStackTrace();
   }   
   }
   
   public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
   
     
}