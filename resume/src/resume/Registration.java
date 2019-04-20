package resume;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
public class Registration extends GenericServlet{
public void service(ServletRequest req, ServletResponse res)
throws IOException, ServletException{
	PrintWriter pw =res.getWriter();
	res.setContentType("text/html");
	
	String fname = req.getParameter("fname");
	String lname = req.getParameter("lname");
	String course =req.getParameter("course");
	String branch = req.getParameter("branch");
	String year = req.getParameter("year");
	String addr =req.getParameter("addr");
	String admn = req.getParameter("admn");
	String mob = req.getParameter("mno");
	String eid =req.getParameter("eid");
	String uname = req.getParameter("uname");
	String pword = req.getParameter("pword");
	String rpword = req.getParameter("rpword");
	String resume =req.getParameter("resume");
	
   File f= new File(resume);
   FileInputStream fis = new FileInputStream(f);
	
	
	if(pword.equals(rpword))
	{
		try{
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("insert into resume values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, course);
			ps.setString(4, branch);
			ps.setString(5, year);
			ps.setString(6, addr);
			ps.setString(7, admn);
			ps.setString(8, mob);
			ps.setString(9, eid);
			ps.setString(10, uname);
			ps.setString(11, pword);
			ps.setBinaryStream(12,fis,(int)f.length());
			int k = ps.executeUpdate();
			if(k==1)
			{
				pw.println("User Registration Sucessfull");
				RequestDispatcher rd = req.getRequestDispatcher("login1.html");
				rd.include(req, res);
			}
			else
			{
				pw.println("Invalid Registration");
				RequestDispatcher rd = req.getRequestDispatcher("registration.html");
				rd.include(req, res);
			}
		}
		catch(Exception e){}
	}
		else
		{
			pw.println("Password and Repassword should be same \n Please try again ");
			RequestDispatcher rd = req.getRequestDispatcher("registration.html");
			rd.include(req, res);
		}	
    }
}
