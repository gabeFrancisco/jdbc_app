import java.sql.Connection;

public static void main(String[] args) {
    System.out.println("God bless you!");

    Connection conn = DB.getConn();
    DB.closeConn();
}