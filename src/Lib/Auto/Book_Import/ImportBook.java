package Lib.Auto.Book_Import;

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


public class ImportBook extends HttpServlet implements Serializable  {
   
   /**
	 * 
	 */
private static final long serialVersionUID = 1L;
private static Logger log4jLogger = Logger.getLogger(ImportBook.class);

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

       //  get Path from Property file 
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
       
	   
	   BookDataMessage message =new BookDataMessage();
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
      
      
     /**   Properties properties=new Properties();
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
            fi.write( file ) ;         // This code is write the input File to D:\Java Library 2013\Development\AAAAA\Sample Library\jboss-4.2.0\bin.  when you are not set filepath from properties file. 
           
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
        	          	  
        	  BookDataBean bookData = new BookDataBean();
        	  row = sheet.getRow(i);       	  
        	  
        	  errorCode = i;
        	  // Checking Cell Type and Value
        	  
        	  cell=row.getCell((short) 0);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {   
        		  message = new BookDataMessage();
        		  message = ValidateError("Access_No",i);
        		  break;
              }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  bookData.setAccessNo(String.valueOf(row.getCell((short) 0).getStringCellValue()));  
        		  
        		  message = new BookDataMessage();
        		  message = ss.getCheckAccessNo(bookData);
        		  
        		  if(message.getCount() > 0)
        		  {
        			  message = new BookDataMessage();
            		  message = ValidateError("Access_No Already Exists in Table",i);
            		  break;        			  
        		  }

        		  if(!set.add(bookData.getAccessNo())) {     // For Checking Duplicate Access_No in Excel
        			  message = new BookDataMessage();
            		  message = ValidateError("Access_No Already Exists in Excel",i);
            		  break; 
        		  }
        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAccessNo(String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));
        		  
        		  message = new BookDataMessage();
        		  message = ss.getCheckAccessNo(bookData);
        		  
        		  if(message.getCount() > 0)
        		  {
        			  message = new BookDataMessage();
            		  message = ValidateError("Access_No Already Exists in Table",i);
            		  break;        			  
        		  }
        		  
        		  if(!set.add(bookData.getAccessNo())) {
        			  message = new BookDataMessage();
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
        		 /** message = new BookDataMessage();
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
        		  message = new BookDataMessage();
        		  message = ValidateError("Title",i);
        		  break;
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
        		  /**message = new BookDataMessage();
        		  message = ValidateError("Author",i);
        		  break; */
        		  bookData.setAuthor("NIL");
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		          		  
        		  bookData.setAuthor(String.valueOf(row.getCell((short) 3).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAuthor(String.valueOf((long)row.getCell((short) 3).getNumericCellValue()));
        		  message = new BookDataMessage();
        		  message = ValidateError("Author is Invalid",i);
        		  break;
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Author:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 4);       	  // For Checking Date
              
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {        		  
        		  message = new BookDataMessage();
        		  message = ValidateError("Received_Date",i);
        		  break;        		  
              }        	  
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  /**  bookData.setReceiveDate(String.valueOf(row.getCell((short) 4).getStringCellValue()));*/
        		  message = new BookDataMessage();
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
            		  message = new BookDataMessage();
            		  message = ValidateError("Received_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
            	  } 
        		  }catch(Exception e)		  {
        			  log4jLogger.info("^^^ AutoLib ReceivedDate Error ^^^"+e);
        			  message = new BookDataMessage();
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
        		  /**message = new BookDataMessage();
        		  message = ValidateError("Publisher",i);
        		  break;*/
        		  bookData.setPublisher("NIL");
        		  bookData.setPublisherCode(1);
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setPublisher(String.valueOf(row.getCell((short) 5).getStringCellValue()));
        		  /**int pub = cs.getBookPubCode(bookData.getPublisher());
        		  bookData.setPublisherCode(pub);*/
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setPublisher(String.valueOf((long)row.getCell((short) 5).getNumericCellValue()));
        		  /**int pub = cs.getBookPubCode(bookData.getPublisher());
        		  bookData.setPublisherCode(pub);*/
        		  message = new BookDataMessage();
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
        		  message = new BookDataMessage();
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
        		 /** message = new BookDataMessage();
        		  message = ValidateError("Supplier",i);
        		  break;*/
        		  bookData.setSupplier("NIL");
        		  bookData.setSuplierCode(2);
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setSupplier(String.valueOf(row.getCell((short) 7).getStringCellValue()));  
        		  /**int sup = cs.getBookSupplierCode(bookData.getSupplier());
        		  bookData.setSuplierCode(sup);*/
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setSupplier(String.valueOf((long)row.getCell((short) 7).getNumericCellValue()));
        		 /** int sup = cs.getBookSupplierCode(bookData.getSupplier());
        		  bookData.setSuplierCode(sup);*/
        		  message = new BookDataMessage();
        		  message = ValidateError("Supplier is Invalid",i);
        		  break;
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Supplier:"+i);
        	  }        	  
        	  
        	  
        	  
              cell=row.getCell((short) 8);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		 /** message = new BookDataMessage();
        		  message = ValidateError("Subject",i);
        		  break;*/
        		  bookData.setSubject("NIL");
        		  bookData.setSubjectCode(1);
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  bookData.setSubject(String.valueOf(row.getCell((short) 8).getStringCellValue()));
        		  /**int sub = cs.getBookSubCode(bookData.getSubject());
        		  bookData.setSubjectCode(sub);*/    		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setSubject(String.valueOf((long)row.getCell((short) 8).getNumericCellValue()));
        		  /**int sub = cs.getBookSubCode(bookData.getSubject());
        		  bookData.setSubjectCode(sub);*/
        		  message = new BookDataMessage();
        		  message = ValidateError("Subject is Invalid",i);
        		  break;        		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Subject:"+i);
        	  }        	  
        	  
        	  
        	  
              cell=row.getCell((short) 9);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  /**message = new BookDataMessage();
        		  message = ValidateError("Department",i);
        		  break;*/
        		  bookData.setDepartment("NIL");
        		  bookData.setDepartmentCode(1);
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setDepartment(String.valueOf(row.getCell((short) 9).getStringCellValue()));  
        		  /**int dept = cs.getBookDeptCode(bookData.getDepartment());
        		  bookData.setDepartmentCode(dept);*/
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setDepartment(String.valueOf((long)row.getCell((short) 9).getNumericCellValue()));
        		  /**int dept = cs.getBookDeptCode(bookData.getDepartment());
        		  bookData.setDepartmentCode(dept);*/
        		  message = new BookDataMessage();
        		  message = ValidateError("Department is Invalid",i);
        		  break; 
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Department:"+i);
        	  }     
        	  
        	  
        	  
              cell=row.getCell((short) 10);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  message = new BookDataMessage();
        		  message = ValidateError("Document",i);
        		  break;
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
        			  message = new BookDataMessage();
            		  message = ValidateError("Document  is invalid",i);
            		  break;        			  
        		  }        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setDocument(String.valueOf((long)row.getCell((short) 10).getNumericCellValue()));
        		  message = new BookDataMessage();
        		  message = ValidateError("Document is invalid",i);
        		  break;        		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Document:"+i);
        	  }             
        	  
        	  
        	  
              cell=row.getCell((short) 11);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setLanguage("ENGLISH");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setLanguage(String.valueOf(row.getCell((short) 11).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setLanguage(String.valueOf((long)row.getCell((short) 11).getNumericCellValue()));
        		  message = new BookDataMessage();
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

        	  
        	  
              cell=row.getCell((short) 13);       	  // For Checking Price
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setPrice(0.0); 
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setPrice(0.0); 
        		  message = new BookDataMessage();
        		  message = ValidateError("Price is invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setPrice((double)row.getCell((short) 13).getNumericCellValue());
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Price:"+i);
        	  }             	  
        	  
        	  
        	  
              cell=row.getCell((short) 14);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setDiscount(0.0);   
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setDiscount(0.0); 
        		  message = new BookDataMessage();
        		  message = ValidateError("Discount is invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setDiscount((double)row.getCell((short) 14).getNumericCellValue());
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Discount:"+i);
        	  }             	  
        	   
        	  
        	  
              cell=row.getCell((short) 15);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setNetprice(0.0); 
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setNetprice(0.0); 
        		  message = new BookDataMessage();
        		  message = ValidateError("NetPrice is invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setNetprice((double)row.getCell((short) 15).getNumericCellValue());
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in NetPrice:"+i);
        	  }             	  
        	  
        	  
        	  
              cell=row.getCell((short) 16);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setInvoiceno("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setInvoiceno(String.valueOf(row.getCell((short) 16).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setInvoiceno(String.valueOf((long)row.getCell((short) 16).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in InvoiceNo:"+i);
        	  }       	  
        	  
        	  
        	  
              cell=row.getCell((short) 17);       	 // For Checking Invoice Date 
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setInvoiceDate("1800-01-01");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		 /** bookData.setInvoiceDate(String.valueOf(row.getCell((short) 17).getStringCellValue()));  */
        		  message = new BookDataMessage();
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
            		  message = new BookDataMessage();
            		  message = ValidateError("InoviceDate is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
            	  } 
        		  }catch(Exception e)		  {
        			  log4jLogger.info("^^^ AutoLib InoviceDate Error ^^^"+e);
        			  message = new BookDataMessage();
            		  message = ValidateError("InoviceDate is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
        		  }
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in InoviceDate:"+i);
        	  }     
        	  
        	  
        	  
              cell=row.getCell((short) 18);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setEdition("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setEdition(String.valueOf(row.getCell((short) 18).getStringCellValue()));          		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setEdition(String.valueOf((long)row.getCell((short) 18).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Edition:"+i);
        	  }     
        	          	  
        	  
        	  
              cell=row.getCell((short) 19);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setVolumeNo("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setVolumeNo(String.valueOf(row.getCell((short) 19).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setVolumeNo(String.valueOf((long)row.getCell((short) 19).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in VolumeNo:"+i);
        	  }             	  
        	  
        	          	
        	  
              cell=row.getCell((short) 20);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setPages("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setPages(String.valueOf(row.getCell((short) 20).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setPages(String.valueOf((long)row.getCell((short) 20).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Pages:"+i);
        	  }  
        	  
        	  
        	  
              cell=row.getCell((short) 21);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setKeywords("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setKeywords(String.valueOf(row.getCell((short) 21).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setKeywords(String.valueOf((long)row.getCell((short) 21).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Keywords:"+i);
        	  }  
        	  
        	  
        	  
              cell=row.getCell((short) 22);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setIsbn("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setIsbn(String.valueOf(row.getCell((short) 22).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setIsbn(String.valueOf((long)row.getCell((short) 22).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in ISBN:"+i);
        	  }  
        	  
               cell=row.getCell((short) 23);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setPurchaseType("Purchase");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setPurchaseType(String.valueOf(row.getCell((short) 23).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setPurchaseType(String.valueOf((long)row.getCell((short) 23).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Purcahse_Type:"+i);
        	  } 
        	  
            cell=row.getCell((short) 24);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setAvailability("YES");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setAvailability(String.valueOf(row.getCell((short) 24).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAvailability(String.valueOf((long)row.getCell((short) 24).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Availaility:"+i);
        	  } 
        	  
        	  
              cell=row.getCell((short) 25);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setNotes("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setNotes(String.valueOf(row.getCell((short) 25).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setNotes(String.valueOf((long)row.getCell((short) 25).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Notes:"+i);
        	  }   
        	  
        	  
        	  
              cell=row.getCell((short) 26);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setAddfield1("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setAddfield1(String.valueOf(row.getCell((short) 26).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAddfield1(String.valueOf((long)row.getCell((short) 26).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Addfield1:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 27);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setAddfield2("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setAddfield2(String.valueOf(row.getCell((short) 27).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAddfield2(String.valueOf((long)row.getCell((short) 27).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Addfield2:"+i);
        	  } 
        	  
        	  
              cell=row.getCell((short) 28);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  //bookData.setBranch("NIL");
        		  message = new BookDataMessage();
        		  message = ValidateError("Branch Name is Invalid",i);
        		  break; 
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setBranch(String.valueOf(row.getCell((short) 28).getStringCellValue()));  
        		  /**int bcode = cs.getBookBranchCode(bookData.getBranch());
        		  bookData.setBranchCode(bcode);*/
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setBranch(String.valueOf((long)row.getCell((short) 28).getNumericCellValue()));
        		  message = new BookDataMessage();
        		  message = ValidateError("Branch Name is Invalid",i);
        		  break; 
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Addfield2:"+i);
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
	  message = new BookDataMessage();
	  message = ValidateError("Invalid Row ",errorCode);
} catch (Exception e) { 
	log4jLogger.info("AutoLibError:OtherException:"+e);
}
finally{
	set.clear();
}
      
  if(errorException.equals("InvalidFile"))
  {
	    indexPage ="/Book_Import/index.jsp?check=InvalidFile";
  }else {
        	
    if(!message.getErrorMsg().isEmpty() && message.getErrorMsg() != null)
    {    	
    	request.setAttribute("bean",message);
    	indexPage ="/Book_Import/index.jsp?check=dataerror";
    	
    }else
    {
    	if(saveDetail != null && !saveDetail.isEmpty())
    	{
    		int save = ss.getImportBookData(saveDetail);
    		indexPage ="/Book_Import/index.jsp?check=success";
    	}else
    	{
    		indexPage ="/Book_Import/index.jsp?check=ErrorToSave";
    	}
    }
  }   
    
    dispatch(request, response, indexPage);
    
    
      
   }catch(Exception ex) {
	   indexPage ="/Book_Import/index.jsp?check=LargeFile";
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
   
   
   
   
   
   private void employeeService(BookDataBean employee)
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
   
   
   public BookDataMessage ValidateError(String error, int i)
   {	   
	   log4jLogger.info("ValidateError:Error:"+error+" Row:"+i);
	   BookDataMessage message = new BookDataMessage();	   
	   
	   message.setErrorMsg("Error : "+error+" at Row No: "+(i+1));	 
	
	   if(error.contains("Already Exists")){
		   message.setSolution("Solution:  1) Please check the Access_no in book master and if it already exists please delete the old record 2) Or Duplicate access_no in excel file please remove it  3) Or this excel file is already imported  ");   
	   }
	   if(error.contains("Author")){
		   message.setSolution("Solution: Author_name Column is Changed, The excel column order is Access_no,Call_no,Title,Author_name");   
	   }
	   else if(error.contains("Received_Date")){
           message.setSolution("Solution:   select the column and right click ->format cells -> custom-> change genral to the format dd/mm/yyyy and click ok and save  (/) symbol only accepted ");
	   }
	   else if(error.contains("Document")){
           message.setSolution("Solution: This is a mandatory field enter the value BOOK with case sensitive");
	   }
	   else if(error.contains("InoviceDate")){
           message.setSolution("Solution:   select the column and right click ->format cells -> custom-> change genral to the format dd/mm/yyyy and click ok and save  (/) symbol only accepted ");
	   }
	   else if(error.contains("Branch")){
           message.setSolution("Solution: Division and budjet is mandatory fields if you dont have this fields please enter NIL 2) Note Column : The Last Column is AD ");
	   }
	   else if(error.contains("Publisher Year")){
           message.setSolution("Solution: Number Value Only Accepted Varchar Not Accepted,Ex.1997 ");
	   }
	   else if(error.contains("NetPrice")){
           message.setSolution("Solution: Number Value Only Accepted Varchar Not Accepted,Ex.0.00 ");
	   }
	   else if(error.contains("Access_No")){
           message.setSolution("Solution: The Row is empty, Delete Empty Row following the steps,1.click empty row number in left corner, click button  ctrl+shift+down arrow and right click delete and save  ");
	   }
	   
	   return message;
	   
   }
   
   
   
    
}