package _Account;

import _WriteReadFile.IOFile;

import java.io.File;
import java.util.ArrayList;

public class AccountUserManager {
    private final ArrayList<AccountUser> accountUserList;
    private final IOFile<AccountUser> ioFile = new IOFile<>();
    private final String PATHNAME_OF_ACCOUNT_USER = "FileData/accountuser";

    public AccountUserManager() {
        if (new File(PATHNAME_OF_ACCOUNT_USER).length() == 0) {
            this.accountUserList = new ArrayList<>();
        } else {
            this.accountUserList = ioFile.readFile(PATHNAME_OF_ACCOUNT_USER);
        }
    }

    public ArrayList<AccountUser> getAccountUserList() {
        return accountUserList;
    }

    public void setListUser(String account, String password) {
        accountUserList.add(new AccountUser(account, password));
        ioFile.writeFile(accountUserList, PATHNAME_OF_ACCOUNT_USER);
    }
}
