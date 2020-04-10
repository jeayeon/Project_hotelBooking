package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	public static Connect a =null;
	private Connection conn;
	
	
	private Connect() {
		
	}
	
	public static Connect getInstance() {
		if(a==null) {
			a = new Connect();
		}
		return a;
	}
	
	public void orclelode() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("클래스 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 실패");
		}


	}
	public Connection connect() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			System.out.println("db접속 완료");
		} catch (SQLException e) {
			System.out.println("실패");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
