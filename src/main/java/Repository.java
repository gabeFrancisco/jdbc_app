import exceptions.DbException;
import exceptions.DbIntegrityException;

import java.sql.*;
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
                            + "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setDate(3, new java.sql.Date(sdf.parse(birthDate).getTime()));
            ps.setDouble(4, baseSalary);
            ps.setInt(5, departmentId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }

            System.out.println("Done! Rows affected: " + rows);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertDepartment(String name) {
        try {
            conn = DB.getConn();
            ps = conn.prepareStatement("insert into department "
                    + "(Name) "
                    + "values "
                    + "(?)", Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, name);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done creating Department! Id = " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void updateDepartment(String name, int id) {
        try {
            conn = DB.getConn();
            ps = conn.prepareStatement("update department "
                    + "set name = ? "
                    + "where "
                    + "id = ?"
            );

            ps.setString(1, name);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            System.out.println("Done! Department updated!");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void deleteDepartment(int id){
        try{
            conn = DB.getConn();
            ps = conn.prepareStatement("delete from department" +
                    "where " +
                    "Id = ?");

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            System.out.println("Department of ID: " + id + " deleted!");
        }
        catch (SQLException e){
            throw new DbIntegrityException(e.getMessage());
        }
    }
}
