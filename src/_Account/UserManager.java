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

    public void setListUser(String name, int age, String address, String phoneNumber, String email) {
        ArrayList<User> users;
        if (checkFile()) {
            users = userList;
        } else {
            users = getUserList();
        }
        users.add(new User(name, age, address, phoneNumber, email));
        ioFile.writeFile(users, PATHNAME_OF_USER);
    }

    public boolean checkFile() {
        ArrayList<User> userList = getUserList();
        return userList == null;
    }
}
