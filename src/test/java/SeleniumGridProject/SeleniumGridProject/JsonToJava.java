package SeleniumGridProject.SeleniumGridProject;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, StreamWriteException, DatabindException, IOException {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "root");
		Statement st = con.createStatement();
		ResultSet rs =  st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");
		ArrayList<Object> al = new ArrayList<Object>();
		while(rs.next()) {
			CustomerDetails cd = new CustomerDetails();
			cd.setCourseName(rs.getString(1));
			cd.setPurchasedDate(rs.getString(2));
			cd.setAmount(rs.getInt(3));
			cd.setLocation(rs.getString(4));
			al.add(cd);
			
		}
		for(int i = 0; i < al.size(); i++) {
		ObjectMapper om = new ObjectMapper();
		om.writeValue(new File("D:\\Selenium\\Projects\\APIAutomation\\SeleniumGridProject\\Files\\CustomerDetails"+i+".json"), al.get(i));
		
		}
		con.close();
	}

}
