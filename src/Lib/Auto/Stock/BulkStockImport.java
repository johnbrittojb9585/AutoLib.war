package Lib.Auto.Stock;

import java.io.*;
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
import org.apache.commons.io.output.*;


public class BulkStockImport extends HttpServlet implements Serializable  {
   
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private static Logger log4jLogger = Logger.getLogger(BulkStockImport.class);

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
   	
	   
	   StockDataMessage message =new StockDataMessage();
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
     
      
      /**  Properties properties=new Properties();
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
                
        	log4jLogger.info("StockBulkImport:File Path: "+filePath+" AbsolutePath:"+file.getAbsolutePath()+" File:"+file);        	
            
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
        	          	  
        	  BulkStockData stockData = new BulkStockData();
        	  row = sheet.getRow(i);       	  
        	  
        	  errorCode = i;
        	  // Checking Cell Type and Value
        	  
        	  cell=row.getCell((short) 0);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {   
        		  message = new StockDataMessage();
        		  message = ValidateError("Access_No",i);
        		  break;
              }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  stockData.setAccessNo(String.valueOf(row.getCell((short) 0).getStringCellValue()));  
        		  
        		/**  message = new StockDataMessage();
        		  //message = ss.CheckAccess_no(bookData);
        		  
        		  if(message.getCount() > 0)
        		  {
        			  message = new StockDataMessage();
            		  message = ValidateError("Access_No Already Exists in Table",i);
            		  break;        			  
        		  } */

        		  if(!set.add(stockData.getAccessNo())) {     // For Checking Duplicate Access_No in Excel
        			  message = new StockDataMessage();
            		  message = ValidateError("Access_No Already Exists in Excel",i);
            		  break; 
        		  }
        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  stockData.setAccessNo(String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));
        		  
        		  /**message = new StockDataMessage();
        		  message = ss.getCheckAccessNo(bookData);
        		  
        		  if(message.getCount() > 0)
        		  {
        			  message = new StockDataMessage();
            		  message = ValidateError("Access_No Already Exists in Table",i);
            		  break;        			  
        		  }*/
        		  
        		  if(!set.add(stockData.getAccessNo())) {
        			  message = new StockDataMessage();
            		  message = ValidateError("Access_No Already Exists in Excel",i);
            		  break; 
        		  }
        	  }
        	  else	{  
        		  log4jLogger.info(">>>>>>>>>>>>>Error in AccessNo:"+i);
        	  }        	  
        	  
        	  saveDetail.add(stockData);

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
	  message = new StockDataMessage();
	  message = ValidateError("Invalid Row ",errorCode);
} catch (Exception e) { 
	log4jLogger.info("AutoLibError:OtherException:"+e);
}
finally{
	set.clear();
}
      
  if(errorException.equals("InvalidFile"))
  {
	    indexPage ="/Stock/bulkindex.jsp?check=InvalidFile";
  }else {
        	
    if(!message.getErrorMsg().isEmpty() && message.getErrorMsg() != null)
    {   request.setAttribute("bean",message); 	
    	indexPage ="/Stock/bulkindex.jsp?check=dataerror";
    	
    }else
    {
    	if(saveDetail != null && !saveDetail.isEmpty())
    	{
    		int save = ss.getBulkStockSave(saveDetail);
    		indexPage ="/Stock/bulkindex.jsp?check=success";
    	}else
    	{
    		indexPage ="/Stock/bulkindex.jsp?check=ErrorToSave";
    	}
    }
  }   
    
    dispatch(request, response, indexPage);
    
    
      
   }catch(Exception ex) {
	   indexPage ="/Stock/bulkindex.jsp?check=LargeFile";
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
   
   
       
   public StockDataMessage ValidateError(String error, int i)
   {	   
	   log4jLogger.info("ValidateError:Error:"+error+" Row:"+i);
	   StockDataMessage message = new StockDataMessage();	   
	   
	   message.setErrorMsg("Error : "+error+" at Row No: "+(i+1));	   
	   return message;
	   
   }
   
   
   
    
}