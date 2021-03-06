package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
	//	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
	//	Statement st = null;
    //  ResultSet rs = null;
		Statement ps = null;
		
		/*try {
			
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("Select * from department");
			
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeConnection();
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}*/
		

		/* try {
			conn = DB.getConnection();
			
			//EXAMPLO 1
			ps = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, "Aline Paz");
			ps.setString(2, "aline@gmail.com");
			ps.setDate(3, new java.sql.Date(sdf.parse("02/10/1994").getTime()));
			ps.setDouble(4, 4000.0);
			ps.setInt(5, 4);
			
			// EXAMPLE 2:
						//ps = conn.prepareStatement(
						//		"insert into department (Name) values ('D1'),('D2')", 
						//		Statement.RETURN_GENERATED_KEYS);
		
			
			int linhasAfetadas = ps.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id: " + id);
				}
				
			}
			else {
				System.out.println("No rows affected");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeConnection();
			DB.closeStatement(ps);
		} */
		
		try {
			
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			/*ps = conn.prepareStatement("UPDATE seller "
				+ "SET BaseSalary = BaseSalary + ? "
				+ "WHERE "
				+ "(DepartmentId = ?)");
			
			ps.setDouble(1, 500.00);
			ps.setInt(2, 4); */
			
			/*ps = conn.prepareStatement("DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");
			
			ps.setInt(1, 4);
			
			int linhasAfetadas = ps.executeUpdate();
			System.out.println("Done! rows affected: " + linhasAfetadas);*/
			ps = conn.createStatement();
			int rows1 = ps.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			int x = 1;
			if (x < 2) {
				throw new SQLException("Fake error");
			}
			
			int rows2 = ps.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();
			
			System.out.println("rows1 = " + rows1);
			System.out.println("rows2 = " + rows2);
			
			
		}catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
				
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
				
			}
		}
		
		finally {
			DB.closeConnection();
			DB.closeStatement(ps);
		}
		
		
	}

}
