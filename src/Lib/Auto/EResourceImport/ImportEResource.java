package Lib.Auto.EResourceImport;


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
import Lib.Auto.EResource.EResourceBean;
import Lib.Auto.EResourceImport.ImportEResource;
import Lib.Auto.EResourceImport.EResourceDataMessage;

import org.apache.commons.io.output.*;



public class ImportEResource extends HttpServlet implements Serializable {
	
	/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private static Logger log4jLogger = Logger.getLogger(ImportEResource.class);

   private boolean isMultipart;
   private String filePath="",tempPath="",indexPage=null;
   private int maxFileSize = 80 * 1024 * 1024;   // File Size should be 70KB
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
			HttpServletResponse response) throws ServletException{


	   try {
		   
		   HttpSession session = request.getSession(true);
		   String res = Security.checkSecurity(1, session, response, request);		
		   if(res.equalsIgnoreCase("Failure"))
		   {
			   response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
			   return;
		   }
			
			
		   AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();

	       //	 get Path from Property file 
		   Properties properties=new Properties();
		   properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
	  		
		   filePath = properties.getProperty("book.import.filepath");		
	   	   
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
		   
		   
	       EResourceDataMessage message =new EResourceDataMessage();
		   List<Object> saveDetail = new ArrayList<Object>();
		   int errorCode=0;
		   String errorException = "";
		   
	      // Check that we have a file upload request
	      isMultipart = ServletFileUpload.isMultipartContent(request);         
	            
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File(tempPath));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );

	      try{ 
	      // Parse the request to get file items.
	  
	      List fileItems = upload.parseRequest(request);
	      // Process the uploaded file items
	      Iterator j = fileItems.iterator();
	     
	      String code = "",flag = "";
	      byte[] fileInBytes = null;
	      
	   /**   
	      Properties properties=new Properties();
		  properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
			
		  filePath = properties.getProperty("book.import.filepath");	*/
	      
	      
	      while ( j.hasNext () ) 
	      {
	         FileItem fi = (FileItem)j.next();
	         if ( !fi.isFormField () )	
	         {
	            // Get the uploaded file parameters
	            String fieldName = fi.getFieldName();
	            String fileName = fi.getName();
	            String contentType = fi.getContentType();
	            boolean isInMemory = fi.isInMemory();
	            long sizeInBytes = fi.getSize(); 
	            
	            
	            // Write the file
	            if( fileName.lastIndexOf("\\") >= 0 ){
	               file = new File( filePath + 
	               fileName.substring( fileName.lastIndexOf("\\"))) ;
	            }else{
	               file = new File( filePath + 
	               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	            }           
	            fi.write( file ) ;         // This code is write the input File to D:\Java Librarry 2013\Development\AAAAA\Sample Library\jboss-4.2.0\bin.  when you are not set filepath from properties file. 
	           
	        	fileInBytes=fi.get();           
	                       
	        	log4jLogger.info("BookImport:File Path: "+filePath+" AbsolutePath:"+file.getAbsolutePath()+" File:"+file);
	        	
	            
	         }else if(fi.isFormField())
	         {       	 
	        	 if("member".equalsIgnoreCase(fi.getFieldName())){        		
	        		 code = fi.getString();
	        	 }
	        	 if("flag".equalsIgnoreCase(fi.getFieldName())){
	        		 flag = fi.getString();
	        	 }	
	         }        
	      }      
	      
	      Set set = new HashSet();
	      
	      try{   	  
	    	  
	          FileInputStream input = new FileInputStream(file.getAbsolutePath());  
	          POIFSFileSystem fs = new POIFSFileSystem( input );  
	          HSSFWorkbook wb = new HSSFWorkbook(fs);  
	          HSSFSheet sheet = wb.getSheetAt(0);  
	          HSSFRow row;
	          HSSFCell cell;          
	          
	          
	          for(int i=1; i<=sheet.getLastRowNum(); i++)
	          {
	        	          	  
	        	  EResourceBean userData = new EResourceBean();
	        	  row = sheet.getRow(i);       	  
	        	  
	        	  errorCode = i;
	        	  // Checking Cell Type and Value
	        	  
	        	  cell=row.getCell((short) 0);       	  
	        	  
	        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
	              {   
	        		  message = new EResourceDataMessage();
	        		  message = ValidateError("Code",i);
	        		  break;
	              }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
	        	  {        		  
	        		  
	        		  String code_temp = (String.valueOf(row.getCell((short) 0).getStringCellValue()));
	        		  userData.setEcode(Integer.parseInt(code_temp));
	        		  
	        		  message = new EResourceDataMessage();
	        		  message = ss.getCheckcode(userData);
	        		  
	        		  if(message.getCount() > 0)
	        		  {
	        			  message = new EResourceDataMessage();
	            		  message = ValidateError("Code Already Exists in Table",i);
	            		  break;        			  
	        		  }

	        		  if(!set.add(userData.getEcode())) {     // For Checking Duplicate Access_No in Excel
	        			  message = new EResourceDataMessage();
	            		  message = ValidateError("Code Already Exists in Excel",i);
	            		  break; 
	        		  }
	        		  
	        	  }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
	        	  {
	        		  String code_temp = (String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));
	        		  userData.setEcode(Integer.parseInt(code_temp));
	        		  
	        		  message = new EResourceDataMessage();
	        		  message = ss.getCheckcode(userData);
	        		  
	        		  if(message.getCount() > 0)
	        		  {
	        			  message = new EResourceDataMessage();
	            		  message = ValidateError("Code Already Exists in Table",i);
	            		  break;        			  
	        		  }
	        		  
	        		  if(!set.add(userData.getEcode())) {
	        			  message = new EResourceDataMessage();
	            		  message = ValidateError("Code Already Exists in Excel",i);
	            		  break; 
	        		  }
	        	  }
	        	  else	  {  
	        		  log4jLogger.info(">>>>>>>>>>>>>Error in Code:"+i);
	        	  }
	        	  
	        	  
	        	  
	        	  cell=row.getCell((short) 0);        	    // for code
	        		  
	        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
	              {        		 		  
	        		  message = new EResourceDataMessage();
	        		  message = ValidateError("Code",i);
	        		  break;
	              }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
	        	  {
	        		  String code_temp = (String.valueOf(row.getCell((short) 0).getStringCellValue()));        	
	        		  userData.setEcode(Integer.parseInt(code_temp));
	        	  }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
	        	  {
	        		  String code_temp = (String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));
	        		  userData.setEcode(Integer.parseInt(code_temp));
	        	  }
	        	  else	  {
	        		  log4jLogger.info(">>>>>>>>>>>>>Error in Code:"+i);
	        	  }
	        	  
	        	  
	        	  
	        	  
	        	  cell=row.getCell((short) 1);        	    // for WebSite
	        		  
	        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
	              {        		 		  
	        		  message = new EResourceDataMessage();
	        		  message = ValidateError("WebSite",i);
	        		  break;
	              }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
	        	  {
	        		  userData.setEsite(String.valueOf(row.getCell((short) 1).getStringCellValue()));        		  
	        	  }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
	        	  {
	        		  userData.setEsite(String.valueOf((long)row.getCell((short) 1).getNumericCellValue()));
	        	  }
	        	  else	  {
	        		  log4jLogger.info(">>>>>>>>>>>>>Error in WebSite:"+i);
	        	  }
	        	  
	        	  
	        	  
	        	  cell=row.getCell((short) 2);        	    // for WebDetails
        		  
	        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
	              {        		 		  
	        		  userData.setEdetails(""); 
	              }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
	        	  {
	        		  userData.setEdetails(String.valueOf(row.getCell((short) 2).getStringCellValue()));        		  
	        	  }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
	        	  {
	        		  userData.setEdetails(String.valueOf((long)row.getCell((short) 2).getNumericCellValue()));
	        	  }
	        	  else	  {
	        		  log4jLogger.info(">>>>>>>>>>>>>Error in WebDetails:"+i);
	        	  }
	        	  
	        	  
	        	  
	        	  cell=row.getCell((short) 3);        	    // for WebTitle
        		  
	        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
	              {        		 		  
	        		  userData.setEtitle("");  
	              }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
	        	  {
	        		  userData.setEtitle(String.valueOf(row.getCell((short) 3).getStringCellValue()));        		  
	        	  }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
	        	  {
	        		  userData.setEtitle(String.valueOf((long)row.getCell((short) 3).getNumericCellValue()));
	        	  }
	        	  else	  {
	        		  log4jLogger.info(">>>>>>>>>>>>>Error in WebTitle:"+i);
	        	  }
	        	  
	        	  cell=row.getCell((short) 4);        	    // for WebSubTitle
        		  
	        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
	              {        		 		  
	        		  userData.setEsubtitle("");
	              }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
	        	  {
	        		  userData.setEsubtitle(String.valueOf(row.getCell((short) 4).getStringCellValue()));        		  
	        	  }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
	        	  {
	        		  userData.setEsubtitle(String.valueOf((long)row.getCell((short) 4).getNumericCellValue()));
	        	  }
	        	  else	  {
	        		  log4jLogger.info(">>>>>>>>>>>>>Error in WebSubTitle:"+i);
	        	  }
	        	  
	        	  cell=row.getCell((short) 5);        	    // for WebType
        		  
	        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
	              {        		 		  
	        		  userData.setType("E-Journal");
	              }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
	        	  {
	        		  userData.setType(String.valueOf(row.getCell((short) 5).getStringCellValue()));        		  
	        	  }
	        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
	        	  {
	        		  userData.setType(String.valueOf((long)row.getCell((short) 5).getNumericCellValue()));
	        	  }
	        	  else	  {
	        		  log4jLogger.info(">>>>>>>>>>>>>Error in WebSubTitle:"+i);
	        	  }
	        	  
	        	  
	        	  
	        	  
	        	  saveDetail.add(userData);
	        	  
	        	  //employeeService(userData);       // call to spring service layer	  

	          } 
	          
	          
	          
	} catch (FileNotFoundException ec) {	
		log4jLogger.info("AutoLibError:FileNotFound:"+ec);
	    ec.printStackTrace();
	} catch (IOException e) {
		log4jLogger.info("AutoLibError:IOException:"+e);
		errorException = "InvalidFile";	
		//e.printStackTrace();
	} catch (NumberFormatException e) {
		log4jLogger.info("AutoLibError:NumberFormat:"+e);
	    e.printStackTrace();
	} catch (NullPointerException e) {
		log4jLogger.info("AutoLibError:NullPointer:"+e);
		  message = new EResourceDataMessage();
		  message = ValidateError("Invalid Row ",errorCode);
	} catch (Exception e) { 
		log4jLogger.info("AutoLibError:OtherException:"+e);
	}
	finally{
		set.clear();
	}
	      
	  if(errorException.equals("InvalidFile"))
	  {
		    indexPage ="/EResourceImport/index.jsp?check=InvalidFile";
	  }else {
	        	
	    if(!message.getErrorMsg().isEmpty() && message.getErrorMsg() != null)
	    {    	
	    	request.setAttribute("bean",message);
	    	indexPage ="/EResourceImport/index.jsp?check=dataerror";
	    	
	    }else
	    {
	    	if(saveDetail != null && !saveDetail.isEmpty())
	    	{
	    		int save = ss.getImportEResourceData(saveDetail);
	    		indexPage ="/EResourceImport/index.jsp?check=success";
	    	}else
	    	{
	    		indexPage ="/EResourceImport/index.jsp?check=ErrorToSave";
	    	}
	    }
	  }   
	    
	    dispatch(request, response, indexPage);
	    
	    
	      
	   }catch(Exception ex) {
		   indexPage ="/EResourceImport/index.jsp?check=LargeFile";
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
	   
	   
	   
	   
	   
	   private void employeeService(EResourceBean employee)
	   {
		   log4jLogger.info("=======Inside EResourceService=========="+employee.getEcode());
	   	
	   	try{               	  
	   	       Class.forName("com.mysql.jdbc.Driver").newInstance();
	   	       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autolib","root","admin");
	   	       Statement stat=con.createStatement();       
	   	 
	  	       
	   	       int k=stat.executeUpdate("insert into excel values('"+employee.getEcode()+"','"+employee.getEsite()+"')");
	   	       log4jLogger.info("Data is inserted");
	   	       stat.close();
	   	       con.close();
	   	   }
	   	   catch(Exception e){	  
	   		log4jLogger.info("AutoLibError:DBConnect:"+e);
	   	   }   	
	   }
	   
	   
	   public EResourceDataMessage ValidateError(String error, int i)
	   {	   
		   log4jLogger.info("ValidateError:Error:"+error+" Row:"+i);
		   EResourceDataMessage message = new EResourceDataMessage();	   
		   
		   message.setErrorMsg("Error : "+error+" at Row No: "+(i+1));	   
		   return message;
		   
	   }
	   
	   
	   
	    
	}
   