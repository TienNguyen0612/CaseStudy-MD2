package _Account;

import _WriteReadFile.IOFile;

import java.util.ArrayList;

public class AccountUserManager {
    private final ArrayList<AccountUser> accountUserList = new ArrayList<>();
    private final IOFile<AccountUser> ioFile = new IOFile<>();
    private final String PATHNAME_OF_ACCOUNT_USER = "FileData/accountuser";

    public AccountUserManager() {
    }

    public ArrayList<AccountUser> getAccountUserList() {
        return ioFile.readFile(PATHNAME_OF_ACCOUNT_USER);
    }

    public void setListUser(String account, String password) {
        ArrayList<AccountUser> accountUsers;
        if (checkFile()) {
            accountUsers = accountUserList;
        } else {
            accountUsers = getAccountUserList();
        }
        accountUsers.add(new AccountUser(account, password));
        ioFile.writeFile(accountUsers, PATHNAME_OF_ACCOUNT_USER);
    }

    public boolean checkFile() {
        ArrayList<AccountUser> accountUserList = getAccountUserList();
        return accountUserList == null;
    }
}
