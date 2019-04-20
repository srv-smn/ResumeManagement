package resume;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
public class AdminLogin extends GenericServlet {
	public void service(ServletRequest req , ServletResponse res) throws IOException, ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String uname = req.getParameter("uid");
		String pword = req.getParameter("pword");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from resumeadmin where uname = ? and pword = ?");
			ps.setString(1, uname);
			ps.setString(2, pword);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{ 
				System.out.println("hello");
				pw.println("suscessfull login \n Welome Admin"+"<br>"+"welcome to resume management system "+"<b>"+rs.getString(1)+"</b>");
				pw.println("<a href = search.html>Another Search</a>");
			
			}
			else
			{
				
				pw.println("invalid login information");
				RequestDispatcher rd = req.getRequestDispatcher("login2.html");
				rd.include(req, res);
						
			}
		} catch(Exception e){}
		
		
	
	}
	
	
}
