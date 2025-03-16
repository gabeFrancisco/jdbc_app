import exceptions.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Repository {
    private static Connection conn = null;
    private static ResultSet rs = null;

    public void getAllDepartments(){
        try{
            conn = DB.getConn();
            rs = conn.createStatement().executeQuery("select * from department");

            while(rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("name"));
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeConn();
        }
    }

    public void getAllSellers(){
        try{
            conn = DB.getConn();
            rs = conn.createStatement().executeQuery("select * from seller");

            while(rs.next()){
                System.out.println(rs.getInt("Id")
                        + ", " + rs.getString("name")
                        + ", " + rs.getString("Email")
                        + ", " + rs.getDate("BirthDate")
                        + ", " + rs.getDouble("BaseSalary")
                        + ", " + rs.getInt("DepartmentId"));
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeConn();
        }
    }
}
