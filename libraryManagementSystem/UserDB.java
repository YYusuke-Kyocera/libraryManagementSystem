package libraryManagementSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

public class UserDB {
    
    ArrayList<User> userList = new ArrayList<>();

    public UserDB(){

        File usersFile = new File("libraryManagementSystem/csv/users.csv");
        
        try {

            String line;
            BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(usersFile), Charset.forName("UTF-8"))
                );
            br.readLine();  // ヘッダ行は読み飛ばす
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");

                ArrayList<String> borrowList;
                if(split.length == 4){
                   borrowList = new ArrayList<>(Arrays.asList(split[3].split(";")));
                }
                else{
                    borrowList = new ArrayList<>();
                }
                User user = new User(split[0], split[1], split[2], borrowList);
                userList.add(user);
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

    public void lendProcess(String userid, String fullId){
        for (User u : userList){
            if (u.getId().equals(userid)){
                u.addBorrowList(fullId);
                return;
            }
        }
    }

    public void receiveProcess(String userid, String fullId){
        for (User u : userList){
            if (u.getId().equals(userid)){
                u.getBorrowList().remove(fullId);
                return;
            }
        }
    }
    
    public void showAllUsers(){
        System.out.println("\n******** All Users ********");
        for (User u : userList){
            System.out.println("Id: " + u.getId());
            System.out.println("Name: " + u.getName());
            System.out.println("RegistDate: " + u.getRegisterDate());
            System.out.println("BorrowList: " + u.getBorrowList());
            System.out.println("---------------------------");
        }
    }
}
