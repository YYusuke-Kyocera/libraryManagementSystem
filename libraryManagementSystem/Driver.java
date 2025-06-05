package libraryManagementSystem;

public class Driver {
    public static void main(String[] args) {
        
        Library lib = new Library();

        lib.confirmBookStock("羅生門");

        lib.lendBook("10002_2", "U002");

        lib.confirmBookStock("羅生門");

        lib.receiveBooks("10002_2", "U002");
        
        lib.confirmBookStock("羅生門");
    }
}
