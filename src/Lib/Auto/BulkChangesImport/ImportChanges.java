package Lib.Auto.BulkChangesImport;

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
import Lib.Auto.Member_Import.MemberDataMessage;

import org.apache.commons.io.output.*;


public class ImportChanges extends HttpServlet implements Serializable  {
   
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private static Logger log4jLogger = Logger.getLogger(ImportChanges.class);

   private boolean isMultipart;
   private String filePath="",tempPath="",indexPage=null;
   private int maxFileSize = 80 * 1024 * 1024;   // File Size should be 70KB
   private int maxMemSize = 4 * 1024 ;    // Memory Size should be 4KB
   private File file ;   
 
   
   
   
   
   
 /**  public void init( ){
	      // Get the file location where it would be stored.
	      filePath = getServletContext().getInitParameter("file-upload"); 
   }*/
   
   
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
		
		
	   AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
	   int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
	   //CalalogingService cs = BusinessServiceFactory.INSTANCE.getCalalogingService();

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
	   
	   
	   ChangesDataMessage message =new ChangesDataMessage();
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
     
      String member_code = "",flag = "";
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
        		 member_code = fi.getString();
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
        	          	  
        	  ChangesDataBean bookData = new ChangesDataBean();
        	  row = sheet.getRow(i);       	  
        	  
        	  errorCode = i;
        	  // Checking Cell Type and Value
        	  
        	  cell=row.getCell((short) 0);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {   
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Access_No",i);
        		  break;
              }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  bookData.setAccessNo(String.valueOf(row.getCell((short) 0).getStringCellValue()));  
        		  
        		  message = new ChangesDataMessage();
        		  message = ss.getCheckAccessNo(bookData);
        		  
        		  if(message.getCount() == 0)
        		  {
        			  message = new ChangesDataMessage();
            		  message = ValidateError("No Data Found of given Access_No",i);
            		  break;        			  
        		  }

        		  if(!set.add(bookData.getAccessNo())) {     // For Checking Duplicate Access_No in Excel
        			  message = new ChangesDataMessage();
            		  message = ValidateError("Access_No Already Exists in Excel",i);
            		  break; 
        		  }
        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAccessNo(String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));
        		  
        		  message = new ChangesDataMessage();
        		  message = ss.getCheckAccessNo(bookData);
        		  
        		  if(message.getCount() == 0)
        		  {
        			  message = new ChangesDataMessage();
            		  message = ValidateError("No Data Found of given Access_No",i);
            		  break;        			  
        		  }
        		  
        		  if(!set.add(bookData.getAccessNo())) {
        			  message = new ChangesDataMessage();
            		  message = ValidateError("Access_No Already Exists in Excel",i);
            		  break; 
        		  }
        	  }
        	  else	  {  
        		  log4jLogger.info(">>>>>>>>>>>>>Error in AccessNo:"+i);
        	  }
        	  
        	  
        	  
        	  cell=row.getCell((short) 1);        	    
        		  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		 /** message = new ChangesDataMessage();
        		  message = ValidateError("Call_No",i);
        		  break;*/        		  
        		  bookData.setCallNo("");
              }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setCallNo(String.valueOf(row.getCell((short) 1).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setCallNo(String.valueOf((long)row.getCell((short) 1).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in CallNo:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 2);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
//        		  message = new ChangesDataMessage();
//        		  message = ValidateError("Title",i);
//        		  break;
        		  bookData.setTitle("");
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setTitle(String.valueOf(row.getCell((short) 2).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setTitle(String.valueOf((long)row.getCell((short) 2).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Title:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 3);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  /**message = new ChangesDataMessage();
        		  message = ValidateError("Author",i);
        		  break; */
        		  bookData.setAuthor("");
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		          		  
        		  bookData.setAuthor(String.valueOf(row.getCell((short) 3).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAuthor(String.valueOf((long)row.getCell((short) 3).getNumericCellValue()));
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Author is Invalid",i);
        		  break;
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Author:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 4);       	  // For Checking Date
              
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {        		  
//        		  message = new ChangesDataMessage();
//        		  message = ValidateError("Received_Date",i);
//        		  break;
        		  bookData.setReceiveDate("");
        		  
              }        	  
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  /**  bookData.setReceiveDate(String.valueOf(row.getCell((short) 4).getStringCellValue()));*/
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Received_Date is Invalid.The Format is dd/mm/yyyy",i);
        		  break;        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		 /** bookData.setReceiveDate(String.valueOf((long)row.getCell((short) 4).getNumericCellValue())); */
        		  
        		  try  {
        		  if (HSSFDateUtil.isCellDateFormatted(cell))
            	  {
        			    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		    
        			    SimpleDateFormat receivedDate = new SimpleDateFormat("yyyy-MM-dd");
        			    String rdate = receivedDate.format(date);
        			    bookData.setReceiveDate(rdate);  
        			    
            	  }else {
            		  message = new ChangesDataMessage();
            		  message = ValidateError("Received_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
            	  } 
        		  }catch(Exception e)		  {
        			  log4jLogger.info("^^^ AutoLib ReceivedDate Error ^^^"+e);
        			  message = new ChangesDataMessage();
            		  message = ValidateError("Received_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
        		  }
        		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in ReceivedDate:"+i);
        	  }

        	  
        	  
              cell=row.getCell((short) 5);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  /**message = new ChangesDataMessage();
        		  message = ValidateError("Publisher",i);
        		  break;*/
        		  bookData.setPublisher("");
        		  //bookData.setPublisherCode(1);
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setPublisher(String.valueOf(row.getCell((short) 5).getStringCellValue()));
        		  if(bookData.getPublisher().equalsIgnoreCase("Nil"))
        		  {
        		   message = new ChangesDataMessage();
        		   message = ValidateError("Publisher as Nil not allowed ",i);
        		   break;
        		  }
        		  /**int pub = cs.getBookPubCode(bookData.getPublisher());
        		  bookData.setPublisherCode(pub);*/
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setPublisher(String.valueOf((long)row.getCell((short) 5).getNumericCellValue()));
        		  /**int pub = cs.getBookPubCode(bookData.getPublisher());
        		  bookData.setPublisherCode(pub);*/
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Publisher is Invalid",i);
        		  break;
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Publisher:"+i);
        	  }

        	  
        	  
              cell=row.getCell((short) 6);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setPubyear("");
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setPubyear(String.valueOf(row.getCell((short) 6).getStringCellValue())); 
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Publisher Year is Invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setPubyear(String.valueOf((long)row.getCell((short) 6).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in PubYear:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 7);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		 /** message = new ChangesDataMessage();
        		  message = ValidateError("Supplier",i);
        		  break;*/
        		  bookData.setSupplier("");
        		  //bookData.setSuplierCode(2);
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setSupplier(String.valueOf(row.getCell((short) 7).getStringCellValue()));  
        		  if(bookData.getSupplier().equalsIgnoreCase("Nil"))
        		  {
        		   message = new ChangesDataMessage();
        		   message = ValidateError("Supplier as Nil not allowed ",i);
        		   break;
        		  }
        		  /**int sup = cs.getBookSupplierCode(bookData.getSupplier());
        		  bookData.setSuplierCode(sup);*/
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setSupplier(String.valueOf((long)row.getCell((short) 7).getNumericCellValue()));
        		 /** int sup = cs.getBookSupplierCode(bookData.getSupplier());
        		  bookData.setSuplierCode(sup);*/
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Supplier is Invalid",i);
        		  break;
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Supplier:"+i);
        	  }        	  
        	  
        	  
        	  
              cell=row.getCell((short) 8);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		 /** message = new ChangesDataMessage();
        		  message = ValidateError("Subject",i);
        		  break;*/
        		  bookData.setSubject("");
        		  //bookData.setSubjectCode(1);
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  bookData.setSubject(String.valueOf(row.getCell((short) 8).getStringCellValue()));
        		  if(bookData.getSubject().equalsIgnoreCase("Nil"))
        		  {
        		   message = new ChangesDataMessage();
        		   message = ValidateError("Subject as Nil not allowed ",i);
        		   break;
        		  }
        		  /**int sub = cs.getBookSubCode(bookData.getSubject());
        		  bookData.setSubjectCode(sub);*/    		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setSubject(String.valueOf((long)row.getCell((short) 8).getNumericCellValue()));
        		  /**int sub = cs.getBookSubCode(bookData.getSubject());
        		  bookData.setSubjectCode(sub);*/
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Subject is Invalid",i);
        		  break;        		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Subject:"+i);
        	  }        	  
        	  
        	  
        	  
              cell=row.getCell((short) 9);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  /**message = new ChangesDataMessage();
        		  message = ValidateError("Department",i);
        		  break;*/
        		  bookData.setDepartment("");
        		  //bookData.setDepartmentCode(1);
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setDepartment(String.valueOf(row.getCell((short) 9).getStringCellValue()));  
        		  if(bookData.getDepartment().equalsIgnoreCase("Nil"))
        		  {
        		   message = new ChangesDataMessage();
        		   message = ValidateError("Department as Nil not allowed ",i);
        		   break;
        		  }
        		  /**int dept = cs.getBookDeptCode(bookData.getDepartment());
        		  bookData.setDepartmentCode(dept);*/
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setDepartment(String.valueOf((long)row.getCell((short) 9).getNumericCellValue()));
        		  /**int dept = cs.getBookDeptCode(bookData.getDepartment());
        		  bookData.setDepartmentCode(dept);*/
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Department is Invalid",i);
        		  break; 
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Department:"+i);
        	  }     
        	  
        	  
        	  
              cell=row.getCell((short) 10);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
//        		  message = new ChangesDataMessage();
//        		  message = ValidateError("Document",i);
//        		  break;
        		  bookData.setDocument("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setDocument(String.valueOf(row.getCell((short) 10).getStringCellValue()));
        		  
        		  if(bookData.getDocument().equalsIgnoreCase("BOOK"))
        		  {        	
        			  bookData.setDocument("BOOK");
        		  }
        		  else if(bookData.getDocument().equalsIgnoreCase("BOOK BANK"))
        		  {   
        			  bookData.setDocument("BOOK BANK");
        		  }
        		  else if(bookData.getDocument().equalsIgnoreCase("NON BOOK"))
        		  {   
        			  bookData.setDocument("NON BOOK");
        		  }
        		  else if(bookData.getDocument().equalsIgnoreCase("REPORT"))
        		  {     
        			  bookData.setDocument("REPORT");
        		  }
        		  else if(bookData.getDocument().equalsIgnoreCase("THESIS"))
        		  {
        			  bookData.setDocument("THESIS");     			  
        		  }
        		  else if(bookData.getDocument().equalsIgnoreCase("STANDARD"))
        		  {
        			  bookData.setDocument("STANDARD");
        		  }
        		  else if(bookData.getDocument().equalsIgnoreCase("PROCEEDING"))
        		  { 
        			  bookData.setDocument("PROCEEDING");
        		  }
        		  else if(bookData.getDocument().equalsIgnoreCase("BACK VOLUME"))
        		  {
        			  bookData.setDocument("BACK VOLUME");
        		  }
        		  else  {
        			  message = new ChangesDataMessage();
            		  message = ValidateError("Document  is invalid",i);
            		  break;        			  
        		  }        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setDocument(String.valueOf((long)row.getCell((short) 10).getNumericCellValue()));
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Document is invalid",i);
        		  break;        		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Document:"+i);
        	  }             
        	  
        	  
        	  
              cell=row.getCell((short) 11);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setLanguage("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setLanguage(String.valueOf(row.getCell((short) 11).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setLanguage(String.valueOf((long)row.getCell((short) 11).getNumericCellValue()));
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Language is invalid",i);
        		  break;
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Language:"+i);
        	  }             	  

        	  
        	  
              cell=row.getCell((short) 12);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setLocation("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setLocation(String.valueOf(row.getCell((short) 12).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setLocation(String.valueOf((long)row.getCell((short) 12).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Location:"+i);
        	  }             	  

        	  
        	  cell = row.getCell((short) 13);

				if ((cell == null)|| (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
				{
					bookData.setCurrency("");
				} 
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) 
				{
					System.out.println("============");
					bookData.setCurrency(String.valueOf(row.getCell((short) 13).getStringCellValue()));							
				} 
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) 
				{
					System.out.println("++++++++++");
					bookData.setCurrency(String.valueOf((Double) row.getCell((short) 13).getNumericCellValue()));							
				} 
				else 
				{
					log4jLogger.info(">>>>>>>>>>>>>Error in Currency:"+ i);							
				}

				cell = row.getCell((short) 14);

				if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)) 						
				{
					bookData.setBcost(Double.valueOf("0.0"));
				} 
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) 
				{
					bookData.setBcost(Double.valueOf(row.getCell((short) 14).getStringCellValue()));							
				} 
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) 
				{
					bookData.setBcost(Double.valueOf((long) row.getCell((short) 14).getNumericCellValue()));							
				} 
				else 
				{
					log4jLogger.info(">>>>>>>>>>>>>Error in Bcost:"+ i);						
				}
        	  
        	  
              cell=row.getCell((short) 15);       	  // For Checking Price
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setPrice(0.0); 
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setPrice(0.0); 
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Price is invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setPrice((double)row.getCell((short) 15).getNumericCellValue());
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Price:"+i);
        	  }             	  
        	  
        	  
        	  
              cell=row.getCell((short) 16);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setDiscount(0.0);   
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setDiscount(0.0); 
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Discount is invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setDiscount((double)row.getCell((short) 16).getNumericCellValue());
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Discount:"+i);
        	  }             	  
        	   
        	  
        	  
              cell=row.getCell((short) 17);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setNetprice(0.0); 
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setNetprice(0.0); 
        		  message = new ChangesDataMessage();
        		  message = ValidateError("NetPrice is invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setNetprice((double)row.getCell((short) 17).getNumericCellValue());
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in NetPrice:"+i);
        	  }             	  
        	  
        	  
        	  
              cell=row.getCell((short) 18);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setInvoiceno("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setInvoiceno(String.valueOf(row.getCell((short) 18).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setInvoiceno(String.valueOf((long)row.getCell((short) 18).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in InvoiceNo:"+i);
        	  }       	  
        	  
        	  
        	  
              cell=row.getCell((short) 19);       	 // For Checking Invoice Date 
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setInvoiceDate("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		 /** bookData.setInvoiceDate(String.valueOf(row.getCell((short) 17).getStringCellValue()));  */
        		  message = new ChangesDataMessage();
        		  message = ValidateError("InoviceDate is Invalid.The Format is dd/mm/yyyy",i);
        		  break;        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		 /** bookData.setInvoiceDate(String.valueOf((long)row.getCell((short) 17).getNumericCellValue())); */

        		  try  {
        		  if (HSSFDateUtil.isCellDateFormatted(cell))
            	  {
        			    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		            			    
        			    SimpleDateFormat invoiceDate = new SimpleDateFormat("yyyy-MM-dd");
        			    String idate = invoiceDate.format(date);
        			    bookData.setInvoiceDate(idate); 
        			    
            	  }else {
            		  message = new ChangesDataMessage();
            		  message = ValidateError("InoviceDate is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
            	  } 
        		  }catch(Exception e)		  {
        			  log4jLogger.info("^^^ AutoLib InoviceDate Error ^^^"+e);
        			  message = new ChangesDataMessage();
            		  message = ValidateError("InoviceDate is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
        		  }
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in InoviceDate:"+i);
        	  }     
        	  
        	  
        	  
              cell=row.getCell((short) 20);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setEdition("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setEdition(String.valueOf(row.getCell((short) 20).getStringCellValue()));          		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setEdition(String.valueOf((long)row.getCell((short) 20).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Edition:"+i);
        	  }     
        	          	  
        	  
        	  
              cell=row.getCell((short) 21);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setVolumeNo("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setVolumeNo(String.valueOf(row.getCell((short) 21).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setVolumeNo(String.valueOf((long)row.getCell((short) 21).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in VolumeNo:"+i);
        	  }             	  
        	  
        	          	
        	  
              cell=row.getCell((short) 22);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setPages("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setPages(String.valueOf(row.getCell((short) 22).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setPages(String.valueOf((long)row.getCell((short) 22).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Pages:"+i);
        	  }  
        	  
        	  
        	  
              cell=row.getCell((short) 23);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setKeywords("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setKeywords(String.valueOf(row.getCell((short) 23).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setKeywords(String.valueOf((long)row.getCell((short) 23).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Keywords:"+i);
        	  }  
        	  
        	  
        	  
              cell=row.getCell((short) 24);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setNotes("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setNotes(String.valueOf(row.getCell((short) 24).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setNotes(String.valueOf((long)row.getCell((short) 24).getNumericCellValue()));
        	  }
        	  else{
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Notes:"+i);
        	  }  
        	  
             cell=row.getCell((short) 25);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setStateRes("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setStateRes(String.valueOf(row.getCell((short) 25).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setStateRes(String.valueOf((long)row.getCell((short) 25).getNumericCellValue()));
        	  }
        	  else{
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Statement of Response:"+i);
        	  } 
        	  
        	  cell = row.getCell((short) 26);

				if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
				{
					bookData.setIsbn("");
				} 
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) 
				{
					bookData.setIsbn(String.valueOf(row.getCell((short) 26).getStringCellValue()));							
				} 
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					bookData.setIsbn(String.valueOf((long) row.getCell((short) 26).getNumericCellValue()));				
				} 
				else 
				{
					log4jLogger.info(">>>>>>>>>>>>>Error in Isbn:" + i);
				}

				cell = row.getCell((short) 27);

				if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)) 						
				{
					bookData.setAvailability("");
				}
                else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) 
				{
					bookData.setAvailability(String.valueOf(row.getCell((short) 27).getStringCellValue()));							
				} 
                else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) 
				{
					bookData.setAvailability(String.valueOf((long) row.getCell((short) 27).getNumericCellValue()));							
				} 
                else 
				{
					log4jLogger.info(">>>>>>>>>>>>>Error in Availability:"+ i);					
				}

				cell = row.getCell((short) 28);

				if ((cell == null)|| (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)) 						
				{
					bookData.setPurchaseType("");
				} 
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) 
				{
					bookData.setPurchaseType(String.valueOf(row.getCell((short) 28).getStringCellValue()));							
				} 
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
                {
					bookData.setPurchaseType(String.valueOf((long) row.getCell((short) 28).getNumericCellValue()));							
				} 
				else
				{
					log4jLogger.info(">>>>>>>>>>>>>Error in PurchaseType:"+ i);			
				}

				
        	  
              cell=row.getCell((short) 29);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setAddfield1("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setAddfield1(String.valueOf(row.getCell((short) 29).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAddfield1(String.valueOf((long)row.getCell((short) 29).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Addfield1:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 30);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setAddfield2("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setAddfield2(String.valueOf(row.getCell((short) 30).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAddfield2(String.valueOf((long)row.getCell((short) 30).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Addfield2:"+i);
        	  } 
        	  
        	  
             /* cell=row.getCell((short) 31);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  //bookData.setBranch("NIL");
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Division Name is Invalid",i);
        		  break; 
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setBranch(String.valueOf(row.getCell((short) 31).getStringCellValue()));  
        		  *//**int bcode = cs.getBookBranchCode(bookData.getBranch());
        		  bookData.setBranchCode(bcode);*//*
                  int count = ss.getBranchCode(bookData.getBranch());
        		  
        		  if(count == 0)
        		  {
        			  message = new ChangesDataMessage();
            		  message = ValidateError("Division Not Exists in Table. Create First.",i);
            		  break;        			  
        		  }
        		  
        		  if(branchID > 0)
        		  {
        			  if(branchID!=count)
        			  {
        				  message = new ChangesDataMessage();
                		  message = ValidateError("You can't upload other division book details.",i);
                		  break;
        			  }
        		  }
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setBranch(String.valueOf((long)row.getCell((short) 31).getNumericCellValue()));
        		  message = new ChangesDataMessage();
        		  message = ValidateError("Division Name is Invalid",i);
        		  break; 
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Division:"+i);
        	  } */
        	  
              
        	  cell=row.getCell((short) 31);       // For Budget Code Checking 	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  /*message = new ChangesDataMessage();
        		  message = ValidateError("Budget Name is Invalid",i);
        		  break; */
        		  bookData.setBudget("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setBudget(String.valueOf(row.getCell((short) 31).getStringCellValue()));  
        		  int bcode = ss.getBranchCode(bookData.getBranch());
        		  
                  int count = ss.getBudgetCode(bookData.getBudget());
        		  
        		  if(count == 0)
        		  {
        			  message = new ChangesDataMessage();
            		  message = ValidateError("Budget Name Not Exists in Table. Create First.",i);
            		  break;        			  
        		  }
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setBudget(String.valueOf((long)row.getCell((short) 31).getNumericCellValue()));
        		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Budget:"+i);
        	  } 
        	  
                cell=row.getCell((short) 32);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setScript("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setScript(String.valueOf(row.getCell((short) 32).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setScript(String.valueOf((long)row.getCell((short) 32).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Month:"+i);
        	  } 
        	  
            cell=row.getCell((short) 33);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setAddfield3("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setAddfield3(String.valueOf(row.getCell((short) 33).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAddfield3(String.valueOf((long)row.getCell((short) 33).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Issue No:"+i);
        	  } 
        	  
        	  saveDetail.add(bookData);
        	  
        	  //employeeService(bookData);       // call to spring service layer	  

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
	  message = new ChangesDataMessage();
	  message = ValidateError("Invalid Row ",errorCode);
} catch (Exception e) { 
	log4jLogger.info("AutoLibError:OtherException:"+e);
}
finally{
	set.clear();
}
      
  if(errorException.equals("InvalidFile"))
  {
	    indexPage ="/BulkChangesImport/index.jsp?check=InvalidFile";
  }else {
        	
    if(!message.getErrorMsg().isEmpty() && message.getErrorMsg() != null)
    {    	
    	request.setAttribute("bean",message);
    	indexPage ="/BulkChangesImport/index.jsp?check=dataerror";
    	
    }else
    {
    	if(saveDetail != null && !saveDetail.isEmpty())
    	{
    		int save = ss.getImportChangesData(saveDetail);
    		indexPage ="/BulkChangesImport/index.jsp?check=success";
    	}else
    	{
    		indexPage ="/BulkChangesImport/index.jsp?check=ErrorToSave";
    	}
    }
  }   
    
    dispatch(request, response, indexPage);
    
    
      
   }catch(Exception ex) {
	   indexPage ="/BulkChangesImport/index.jsp?check=LargeFile";
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
   
   
   
   
   
   private void employeeService(ChangesDataBean employee)
   {
	   log4jLogger.info("=======Inside employeeService=========="+employee.getAccessNo());
   	
   	try{               	  
   	       Class.forName("com.mysql.jdbc.Driver").newInstance();
   	       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autolib","root","admin");
   	       Statement stat=con.createStatement();       
   	 
  	       
   	       int k=stat.executeUpdate("insert into excel values('"+employee.getAccessNo()+"','"+employee.getTitle()+"','"+employee.getAuthor()+"')");
   	       log4jLogger.info("Data is inserted");
   	       stat.close();
   	       con.close();
   	   }
   	   catch(Exception e){	  
   		log4jLogger.info("AutoLibError:DBConnect:"+e);
   	   }   	
   }
   
   
   public ChangesDataMessage ValidateError(String error, int i)
   {	   
	   log4jLogger.info("ValidateError:Error:"+error+" Row:"+i);
	   ChangesDataMessage message = new ChangesDataMessage();	   
	   
	   message.setErrorMsg("Error : "+error+" at Row No: "+(i+1));	   
	   return message;
	   
   }
   
   
   
    
}