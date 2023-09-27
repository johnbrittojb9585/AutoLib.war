package Lib.Auto.Stock;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Lib.Auto.Counter.COUNTER_QUERY;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class StockReportServlet
  extends HttpServlet
  implements Serializable, COUNTER_QUERY
{
  private static final long serialVersionUID = 1L;
  private static Logger log4jLogger = Logger.getLogger(StockReportServlet.class);
  
  int count_mas = 0; int count_yes = 0; int count_lost = 0; int count_issued = 0;
  int count_binding = 0; int count_damaged = 0; int count_transfer = 0;
  StockBean SB = new StockBean();
  String flag = ""; String protocol = "";
  String indexPage = null;
  
  ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
  
  public StockReportServlet() {}
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    performTask(request, response);
  }
  

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException
  {
    performTask(request, response);
  }
  
  public void performTask(HttpServletRequest request, HttpServletResponse response)
    throws ServletException
  {
    try
    {
      HttpSession session = request.getSession(true);
      String res = Security.checkSecurity(1, session, response, request);
      if (res.equalsIgnoreCase("Failure")) {
        response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
        return;
      }
      



      ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE
        .getImportExportXMLService();
      ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE
        .getExceImportExportService();
      
      AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
      
     // int branch_code = Integer.parseInt(String.valueOf(session
     //   .getAttribute("UserBranchID")));
      

      if (request.getParameter("radioOption").equals("report")) {
        log4jLogger.info("start===========report=====" + 
          request.getParameter("radioOption"));
        
        SB.setflag(request.getParameter("flag"));
        SB.setdoctype(request.getParameter("txtdoctype"));
       // SB.setBranchCode(branch_code);
        
        List prepareSearchCriteriaLst = importExportXMLService
          .getStockMasDisplayList(SB);
        

        Map<Object, Object> excelTitleMap = new HashMap();
        
//        excelTitleMap.put("fromAccNo", "");
//        excelTitleMap.put("toAccNo", "");
        excelTitleMap.put("documentType", request.getParameter("txtdoctype"));
        
        Iterator studentDataItr = prepareSearchCriteriaLst.iterator();
        


        StockExportRecord recordProcessor = new StockExportRecord(
          excelTitleMap);
        response.setContentType("text/csv");
        response.setHeader(
          "Content-Disposition", 
          "attachment; filename=" + 
          recordProcessor.getExcelFileName());
        csvImportExportService.Export(studentDataItr, recordProcessor, 
          response.getOutputStream());
      }
    }
    catch (Exception localException)
    {
      localException = localException;
    } catch (Throwable localThrowable) {
      localThrowable = localThrowable;
    }
    finally {}
  }
  








  public void dispatch(HttpServletRequest request, HttpServletResponse response, String indexPage)
    throws ServletException, IOException
  {
    RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
    dispatch.forward(request, response);
  }
}