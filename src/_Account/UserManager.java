package _Account;

import _WriteReadFile.IOFile;

import java.util.ArrayList;

public class UserManager {
    private final ArrayList<User> userList = new ArrayList<>();
    private final IOFile<User> ioFile = new IOFile<>();
    private final String PATHNAME_OF_USER = "FileData/userinfor";

    public UserManager() {
    }

    public ArrayList<User> getUserList() {
        return ioFile.readFile(PATHNAME_OF_USER);
    }

    public void displayUserList() {
        if (userList.isEmpty()) {
            System.out.println("Chưa có người dùng nào đăng ký !!!");
            System.out.println("--------------------");
        } else {
            System.out.printf("| %-15s| %-15s| %-15s| %-15s|\n", "Tên", "Tài khoản", "Tuổi", "Số điện thoại");
            System.out.println("---------------------------------------------------------------------");
            for (User user : userList) {
                System.out.printf("| %-15s| %-15s| %-15d| %-15s|\n", user.getName(), user.getAccount(), user.getAge(), user.getPhoneNumber());
                System.out.println("---------------------------------------------------------------------");
            }
        }
    }

    public void setListUser(String accountUser, String passwordUser, String name, int age, String address, String phoneNumber, String email) {
        ArrayList<User> users;
        if (checkFile()) {
            users = userList;
        } else {
            users = getUserList();
        }
        users.add(new User(accountUser,passwordUser, name, age, address, phoneNumber, email));
        ioFile.writeFile(users, PATHNAME_OF_USER);
    }

    public boolean checkFile() {
        ArrayList<User> userList = getUserList();
        return userList == null;
    }
}
