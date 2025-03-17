import exceptions.DbException;

public static void main(String[] args) {
    System.out.println("God bless you!");

    Repository repository = new Repository();
    repository.getAllDepartments();
    System.out.println("\n------------------");
    repository.getAllSellers();
    System.out.println("\n------------------");
    repository.insertSeller("Sara Limberger", "saralimberger@mail.com", "29/07/2002", 3700.00, 1);
    System.out.println("\n------------------");
    repository.getAllSellers();
    System.out.println("\n------------------");
    repository.insertDepartment("Tools!");
    System.out.println("\n------------------");
    repository.getAllDepartments();

}