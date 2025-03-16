import exceptions.DbException;

public static void main(String[] args) {
    System.out.println("God bless you!");

    Repository repository = new Repository();
    repository.getAllDepartments();
    System.out.println("\n------------------");
    repository.getAllSellers();

}