package org.ldw.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.io.*;
import org.ldw.moudles.*;


public class TreeData extends HttpServlet {
	private handleTree handle = new handleTree();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		handleData(req, resp);

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		handleData(req, resp);
	}
	
	private void handleData(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		handle.initVar(req.getParameter("id"), req.getParameter("n"), 
				req.getParameter("lv")
				);
		
		resp.setContentType("text/html;charset=gbk");
		PrintWriter writer = resp.getWriter();
		
		try{
			writer.println(handle.getData());
		}catch(Exception e){
			writer.println(e.getStackTrace());
		}
		
	}
	
	
}
