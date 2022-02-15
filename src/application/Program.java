package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityConstraintViolationException;

public class Program {

	public static void main(String[] args) {
		
	//	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
	//	Statement st = null;
    //  ResultSet rs = null;
		PreparedStatement ps = null;
		
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
			/*ps = conn.prepareStatement("UPDATE seller "
				+ "SET BaseSalary = BaseSalary + ? "
				+ "WHERE "
				+ "(DepartmentId = ?)");
			
			ps.setDouble(1, 500.00);
			ps.setInt(2, 4); */
			
			ps = conn.prepareStatement("DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");
			
			ps.setInt(1, 4);
			
			int linhasAfetadas = ps.executeUpdate();
			System.out.println("Done! rows affected: " + linhasAfetadas);
			
			
		}catch(SQLException e) {
			throw new DbIntegrityConstraintViolationException(e.getMessage());
		}
		
		finally {
			DB.closeConnection();
			DB.closeStatement(ps);
		}
		
		
	}

}
