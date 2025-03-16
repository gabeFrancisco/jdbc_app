import exceptions.DbException;

public static void main(String[] args) {
    System.out.println("God bless you!");

    Repository repository = new Repository();
    repository.getAllDepartments();
    System.out.println("\n------------------");
    repository.getAllSellers();
    System.out.println("\n------------------");
    repository.insertSeller("Gabriel Francisco", "gabriel@mail.com", "29/03/1999", 3700.00, 1);
    System.out.println("\n------------------");
    repository.getAllSellers();

}