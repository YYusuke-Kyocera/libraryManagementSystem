package libraryManagementSystem;

import java.util.ArrayList;

public class Library {
    
    private BookDB bookDB;
    private UserDB userDB;
    private ArrayList<Book> bookList;
    private ArrayList<User> userList;

    public Library(){
        // 書籍DB、所蔵書籍リストを生成
        bookDB = new BookDB();
        bookList = bookDB.createBookList();

        // ユーザDB、ユーザリストを生成
        userDB = new UserDB();
        userList = userDB.userList;
    }

    // 本の貸出
    public void lendBook(String fullId, String userId){

        bookDB.lendProcess(fullId);
        userDB.lendProcess(userId, fullId);

        // bookDB.showAllBooks();
        // userDB.showAllUsers();
    }

    // 本の返却
    public void receiveBooks(String fullId, String userId){
        bookDB.receiveProcess(fullId);
        userDB.receiveProcess(userId, fullId);
        
        // bookDB.showAllBooks();
        userDB.showAllUsers();
    }

    // 本の在庫確認
    public void confirmBookStock(String title){
        int stock = bookDB.countStocks(title);
        System.out.println(title + "の在庫数は" + stock + "個です");
    }

    public void registUser(String name){
        // User user = new User();
    }

}
