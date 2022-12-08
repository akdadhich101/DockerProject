package SeleniumGridProject.SeleniumGridProject;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class SingleJsonFile {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Object> al = new ArrayList<>();
		JsonArray ja = new JsonArray();
		Gson g = new Gson();
		CustomerDetails cd = new CustomerDetails();
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root", "root");
		Statement st =  con.createStatement();
		ResultSet rs =  st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");
		
		while(rs.next()) {
			cd.setCourseName(rs.getString("CourseName"));
			cd.setPurchasedDate(rs.getString("PurchasedDate"));
			cd.setAmount(rs.getInt("Amount"));
			cd.setLocation(rs.getString("Location"));
			//al.add(cd);
			String jsonString = g.toJson(cd);
			ja.add(jsonString);
			
		}
		
		/*
		 * for(int i = 0; i < al.size(); i ++) { String jsonString =
		 * g.toJson(al.get(i)); ja.add(jsonString);
		 * 
		 * 
		 * }
		 */
		JSONObject jo = new JSONObject();
		jo.put("data", ja);
		
		String str = StringEscapeUtils.unescapeJson(jo.toJSONString());
		String str1 = str.replace("\"{", "{");
		String str2 = str1.replace("}\"", "}");
		System.out.println(str2);
		System.out.println("user 1 making changes");
		
		  FileWriter fw = new FileWriter("D:\\Selenium\\Projects\\APIAutomation\\SeleniumGridProject\\Files\\SingleJson.json");
		  fw.write(str2);
		  fw.close();
		 
		/*
		 * try { FileWriter file = new FileWriter(
		 * "D:\\\\Selenium\\\\Projects\\\\APIAutomation\\\\SeleniumGridProject\\\\Files\\\\SingleJson.json"
		 * ); file.write(str2); // file.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		
		con.close();
		
	}

}
