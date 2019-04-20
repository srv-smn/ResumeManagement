package resume;
import java.sql.*;

public class DBConnection {
	private static Connection con;
	static
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		}
		catch (Exception e){}
		
	}
	public static Connection getCon()
	{
	return con;
	}

}
