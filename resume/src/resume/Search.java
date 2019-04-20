package resume;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
public class Search extends GenericServlet {  
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String sr = req.getParameter("sc");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from resume where adm = ? ");
			ps.setString(1, sr);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				pw.println("<br>"+"<br>"+"<br>"+"Name: "+rs.getString(1)+" "+rs.getString(2)+"<br>"+"Course: "+rs.getString(3)+"<br>"+"Branch: "+rs.getString(4)+"<br>"+"Year: "+rs.getString(5)+"<br>"+"Address: "+rs.getString(6)+"<br>"+"Admission Number: "+rs.getString(7)+"<br>"+"Mobile Number: "+rs.getString(8)+"<br>"+"Email Id: "+rs.getString(9));
				Blob b = rs.getBlob(12);
				byte by[] = b.getBytes(1,(int)b.length());
				FileOutputStream fos = new FileOutputStream("G:\\resume.pdf");
				fos.write(by);
			}
			else
			{
				pw.println("invalid admission number");
				RequestDispatcher rd = req.getRequestDispatcher("login1.html");
						rd.include(req, res);
			}
		} catch(Exception e){}
		pw.println("<a href = search.html>Another Search</a>");
	}
	
}
