package phoneBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;





public class DB_Access implements DB_vars {
	
	private Connection con;
	private Statement st;
	
	public DB_Access() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uname, upass);
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
									ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<String> getAllData(){
		String all = new String();
		ArrayList<String> s = new ArrayList<String>();
		
		String sql = "SELECT id, name, p_number FROM pbook";
		
		try {
			ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
				
					all += String.format("%10s%20s%20s\n",rs.getInt(1),rs.getString(2),rs.getString(3));						
					}
		s.add(all);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
				
	}

	public ArrayList<String> searchContect(String val){
		String all = new String();
		ArrayList<String> s = new ArrayList<String>();
		
		String sql = "SELECT id, name, p_number FROM pbook " +
		"WHERE name LIKE '%"+val+"%' OR p_number LIKE '"+val+"' ";
		
		try {
			ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
				
			all += String.format("%10s%20s%20s\n",rs.getInt(1),rs.getString(2),rs.getString(3));
					}
		s.add(all);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s ;
	} 
	
	public boolean insertContect(Contect c) {
		boolean success = true;
		
		
		String sql = "select id, name, p_number from pbook";
		
		try {
		ResultSet rs = st.executeQuery(sql);
		rs.moveToInsertRow();
		rs.updateString(2, c.getName());
		rs.updateString(3, c.getNumber());
		rs.insertRow();
		}
		catch (SQLException e) {
		success= false;
		e.printStackTrace();
		}
		return success;
	}
	public boolean deletContect(int id) {
		boolean success = true;
		
		String sql = "select id, name, p_number " +
					"from pbook where id = ?";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				rs.deleteRow();
			}
			else
				success = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			success = false;
			e.printStackTrace();
		}
		
		return success;
	}
	public boolean modifyContect(int id, Contect c) {
		boolean success = true;
		
		String sql = "update pbook set name=?, p_number=? " +
					"where id =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
									ResultSet.CONCUR_UPDATABLE);
			pst.setString(1, c.getName());
			pst.setString(2, c.getNumber());
			pst.setInt(3, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return success;
	}
}