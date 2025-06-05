package libraryManagementSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class BookDB {

    // DB代わりのリスト
    private ArrayList<BookTypeData> bookTypes = new ArrayList<>();
    private ArrayList<BookCopyData> bookCopies = new ArrayList<>();

    // コンストラクタ
    // 書籍タイトル一覧と個別本一覧をリストに格納
    public BookDB() {
        File bookTypesFile = new File("libraryManagementSystem/csv/book_types.csv");
        File bookCopiesFile = new File("libraryManagementSystem/csv/book_copies.csv");

        BufferedReader br;
        try {

            // 書籍タイトル一覧
            String line;
            br = new BufferedReader(
                new InputStreamReader(new FileInputStream(bookTypesFile), Charset.forName("UTF-8"))
                );
            br.readLine();  // ヘッダ行は読み飛ばす
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                BookTypeData bookType = new BookTypeData(split[0], split[1], split[2], Integer.parseInt(split[3]));
                bookTypes.add(bookType);
            }
            br.close();

            // 個別本一覧
            br = new BufferedReader(
                new InputStreamReader(new FileInputStream(bookCopiesFile), Charset.forName("UTF-8"))
                );
            br.readLine();  // ヘッダ行は読み飛ばす
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                BookCopyData bookCopy = new BookCopyData(split[0], split[1], split[2]);
                bookCopies.add(bookCopy);
            }
            br.close();

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 全書籍データを表示
    public void showAllBooks(){
        
        System.out.println("\n******** All Books ********");
        for(BookCopyData bc : bookCopies){

            BookTypeData bt = searchBookTypeData(bc.bookTypeId);
            
            System.out.println("BookTypeId: " + bc.bookTypeId);
            System.out.println("BookCopyId: " + bc.bookCopyId);
            System.out.println("Title: " + bt.title);
            System.out.println("Auther: " + bt.auther);
            System.out.println("Year: " + bt.year);
            System.out.println("Status: " + bc.status);
            System.out.println("---------------------------");
        }
    }

    // bookTypeIdでBookTypesを検索
    public BookTypeData searchBookTypeData(String keyId){

        for(BookTypeData b : bookTypes){
            if (b.bookTypeId.equals(keyId)){
                return b;
            }
        }
        return null;
    }

    // 所蔵書籍リストを生成
    public ArrayList<Book> createBookList(){

        ArrayList<Book> bookList = new ArrayList<>();
        
        for (BookCopyData bc : bookCopies){
            BookTypeData bt = searchBookTypeData(bc.bookTypeId);
            Book book = new Book(makeFullId(bc.bookTypeId, bc.bookCopyId), bt.title, bt.auther, bt.year, bc.status);
            bookList.add(book);
        }

        return bookList;
    }

    // 貸出処理　指定した書籍のステータスを貸出中に変更
    public void lendProcess(String fullId){
        String[] ids = splitFullId(fullId);

        for(BookCopyData bc : bookCopies){
            if(bc.bookTypeId.equals(ids[0]) && bc.bookCopyId.equals(ids[1])){
                bc.status = "borrowed";
                return;
            }
        }
        System.out.println("id " + fullId + "の本は存在しません");
    }

    // 返却処理 指定した書籍のステータスを貸出可能に変更
    public void receiveProcess(String fullId){
        String[] ids = splitFullId(fullId);

        for(BookCopyData bc : bookCopies){
            if(bc.bookTypeId.equals(ids[0]) && bc.bookCopyId.equals(ids[1])){
                bc.status = "available";
                return;
            }
        }
        System.out.println("id " + fullId + "の本は存在しません");
    }

    // 指定したタイトルの在庫数を数える
    public int countStocks(String title){

        String bookTypeId = null;
        for(BookTypeData bt : bookTypes){

            if (bt.title.equals(title)){
                bookTypeId = bt.bookTypeId;
                break;
            }
        }
        if (bookTypeId == null){
            System.out.println("この本は所蔵していません");
            return 0;
        }

        int count = 0;
        for(BookCopyData bc : bookCopies){

            if (bc.bookTypeId.equals(bookTypeId) && bc.status.equals("available")){
                count++;
            }
        }
        return count;
    }


    // 
    private String makeFullId(String bookTypeId, String bookCopyId){
        return bookTypeId + "_" + bookCopyId;
    }

    private String[] splitFullId(String fullId){
        return fullId.split("_");
    }
}

// 

// タイトル毎に図書データを管理
class BookTypeData {

    String bookTypeId;
    String title;
    String auther;
    int year;

    public BookTypeData(String id, String title, String auther, int year){
        this.bookTypeId = id;
        this.title = title;
        this.auther = auther;
        this.year = year;
    }
}

class BookCopyData {

    String bookTypeId;
    String bookCopyId;
    String status;

    public BookCopyData(String bookTypeId, String bookCopyId, String status){
        this.bookTypeId = bookTypeId;
        this.bookCopyId = bookCopyId;
        this.status = status;
    }
}
