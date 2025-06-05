package libraryManagementSystem;

import java.util.ArrayList;

public class User {

    private String id;
    private String name;
    private String registerDate;
    private ArrayList<String> borrowList;

    // コンストラクタ
    public User(String id, String name, String registerDate, ArrayList<String> borrowList){
        setId(id);
        setName(name);
        setRegisterDate(registerDate);
        setBorrowList(borrowList);
    }

    // セッタ
    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRegisterDate(String registerDate){
        this.registerDate = registerDate;
    }

    public void setBorrowList(ArrayList<String> borrowList){
        this.borrowList = borrowList;
    }

    public void addBorrowList(String FullId){
        borrowList.add(FullId);
    }


    // ゲッタ
    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getRegisterDate(){
        return registerDate;
    }

    public ArrayList<String> getBorrowList(){
        return borrowList;
    }
}
