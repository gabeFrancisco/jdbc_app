package dao;

import impl.DepartmentDaoJDBC;
import impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConn());
    }
    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDaoJDBC();
    }
}
