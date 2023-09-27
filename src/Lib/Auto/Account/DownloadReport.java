package Lib.Auto.Account;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DownloadReport extends HttpServlet {
	public void service (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

		res.setContentType ("application/text");
		String realPath = req.getRealPath("/Reports").replace('\\','/');

		String filename = req.getParameter ("filename");
		res.setHeader ("Content-Disposition", "attachment; filename=" + filename + ";");
		ServletOutputStream sos = res.getOutputStream ();
		//sos.println(realPath);

		BufferedInputStream bis = new BufferedInputStream (new FileInputStream (realPath+"/" + filename));
		int ch;
		while((ch = bis.read ()) != -1) sos.write (ch);

		bis.close();
		sos.close();
	}
}