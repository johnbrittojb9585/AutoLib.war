package Common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
