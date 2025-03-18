import dao.DaoFactory;
import dao.SellerDao;
import entities.Seller;
import exceptions.DbException;

public static void main(String[] args) {
    SellerDao sellerDao = DaoFactory.createSellerDao();

    Seller seller = sellerDao.findById(3);

    System.out.println(seller);
}