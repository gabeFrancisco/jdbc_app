import exceptions.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Repository {
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public void getAllDepartments() {
        try {
            conn = DB.getConn();
            rs = conn.createStatement().executeQuery("select * from department");

            while (rs.next()) {
                System.out.println(rs.getInt("Id") + ", " + rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeConn();
        }
    }

    public void getAllSellers() {
        try {
            conn = DB.getConn();
            rs = conn.createStatement().executeQuery("select * from seller");

            while (rs.next()) {
                System.out.println(rs.getInt("Id")
                        + ", " + rs.getString("name")
                        + ", " + rs.getString("Email")
                        + ", " + rs.getDate("BirthDate")
                        + ", " + rs.getDouble("BaseSalary")
                        + ", " + rs.getInt("DepartmentId"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeConn();
        }
    }

    public void insertSeller(String name, String email, String birthDate, double baseSalary, int departmentId) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            conn = DB.getConn();
            ps = conn.prepareStatement(
                    "insert into seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                            + "values "
                            + "(?,?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setDate(3, new java.sql.Date(sdf.parse(birthDate).getTime()));
            ps.setDouble(4, baseSalary);
            ps.setInt(5, departmentId);

            int rows = ps.executeUpdate();

            System.out.println("Done! Rows affected: " + rows);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
