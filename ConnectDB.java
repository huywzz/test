package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement ps;
	
	
	public ConnectDB() {
		
	}
	public Connection connect() { 
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url= "jdbc:sqlserver://DESKTOP-2EELRF0\\HUY:1433;databaseName=COFFEE;user=sa;password=123";
			conn= DriverManager.getConnection(url);
			System.out.println("Connected...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	public int executeDB(String sql) { //insert, update, delete
        int record = 0;
        try {
            connect();
            stmt = conn.createStatement();
            record = stmt.executeUpdate(sql); //so luong hang thay doi sau khi thuc hien 1 trong 3 cau lenh tren
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                stmt.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return record;
    }

    public int executeDBPrepare(String sql) { //insert, update, delete
        int record = 0;
        try {
            connect();
            ps = conn.prepareStatement(sql);
            record = ps.executeUpdate();
            //record = stmt.executeUpdate(sql); //so luong hang thay doi sau khi thuc hien 1 trong 3 cau lenh tren
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                stmt.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return record;
    }

    //truy xuat csdl
    public ResultSet ListNuoc(String sql) {
        try {
            connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                //conn.close();
                //stmt.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return rs;
    }
}
