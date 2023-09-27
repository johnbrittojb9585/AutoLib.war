package Lib.Auto.Dashboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;

import com.google.gson.Gson;

public class DashboardServlet extends HttpServlet{

	String flag="",indexPage="";


	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
		HttpSession session = request.getSession(true);
		String res = Security.checkSecurity(1, session, response, request);
		if (res.equalsIgnoreCase("Failure")) {
			response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
			return;
		}
		
		GraphEntity entity = new GraphEntity();
		
		flag= request.getParameter("flag");
		
		if(flag.equalsIgnoreCase("load")){
			
			Gson gsonObj = new Gson();			
		    Map<String, Object>  chartGraph= null;
		    chartGraph = ss.getDashboardGraph();
		    
		   // String dataPoints = gsonObj.toJson(chartGraph);
		    
		    PrintWriter pw = response.getWriter();
		   // pw.write(dataPoints);
			
			request.setAttribute("dashboard", chartGraph);
			indexPage="/Dashboard/index.jsp?check=graph";
			dispatch(request, response, indexPage);
			
			
		}

	
	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
					throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}


}
