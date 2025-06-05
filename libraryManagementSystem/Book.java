package libraryManagementSystem;

public class Book {
    
    private String id;
    private String title;
    private String auther;
    private int year;
    private String status;

    // コンストラクタ
    public Book(String id, String title, String auther, int year, String status){
        setId(id);
        setTitle(title);
        setAuther(auther);
        setYear(year);
        setStatus(status);
    }

    // セッタ
    public void setId(String id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuther(String auther){
        this.auther = auther;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setStatus(String status){
        this.status = status;
    }


    // ゲッタ
    public String getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getAuther(){
        return auther;
    }

    public int getYear(){
        return year;
    }

    public String getStatus(){
        return status;
    }

}
