package _Login;

import _Account.AccountAdmin;
import _Account.AccountUser;
import _Account.AccountUserManager;
import _Account.UserManager;
import _Systems.RunByAdmin;
import _Systems.RunByUser;

import javax.xml.bind.SchemaOutputResolver;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private final Scanner scanner = new Scanner(System.in);
    private final RunByAdmin runByAdmin = new RunByAdmin();
    private final RunByUser runByUser = new RunByUser();
    private final AccountAdmin accountAdmin = new AccountAdmin();
    private final AccountUserManager accountUserManager = new AccountUserManager();
    private final UserManager userManager = new UserManager();

    public Login() {
    }

    public void loginSystems() {
        try {
            menuLogin();
        } catch (InputMismatchException e) {
            System.out.println();
            System.err.println("Bạn đã nhập sai dữ liệu, vui lòng nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            scanner.nextLine();
            loginSystems();
        }
    }

    //Menu
    private void menuLogin() {
        System.out.println("CHÀO MỪNG BẠN ĐẾN VỚI NHÀ NGHỈ SEN HỒNG");
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("| 1. Đăng nhập        |");
        System.out.println("| 2. Đăng ký          |");
        System.out.println("| 0. Thoát            |");
        System.out.println("-----------------------");
        System.out.println("Mời bạn nhập lựa chọn:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                loginManager();
                break;
            case 2:
                registerAccountUser();
                break;
            case 0:
                System.exit(0);
        }
    }

    //Đăng nhập
    private void loginManager() throws InputMismatchException {
        System.out.println("Nhập account:");
        String account = scanner.nextLine();
        System.out.println("Nhập password:");
        String password = scanner.nextLine();

        checkAccount(account, password);
    }

    private void checkAccount(String account, String password) {
        try {
            if (checkLoginAccountAdmin(account, password)) {
                System.out.println();
                System.out.println("Đặng nhập hệ thống bởi ADMIN thành công !!!");
                System.out.println("--------------------");
                System.out.println();
                runByAdmin.menuOfAdmin();
            } else {
                checkAccountUser(account, password);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println();
            System.out.println("Đăng nhập thất bại. Vui lòng đăng nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            loginSystems();
        }
    }

    private void checkAccountUser(String account, String password) {
        if (accountUserManager.checkFile()) {
            System.out.println();
            System.out.println("Tài khoản USER chưa tồn tại. Vui lòng kiểm tra lại !!!");
            System.out.println("--------------------");
            System.out.println();
            loginSystems();
        } else if (checkLoginAccountUser(account, password)) {
            System.out.println();
            System.out.println("Đăng nhập hệ thống bởi USER thành công !!!");
            System.out.println("--------------------");
            System.out.println();
            runByUser.menuOfUser();
        } else {
            System.out.println();
            System.out.println("Đăng nhập thất bại. Vui lòng kiểm tra lại !!!");
            System.out.println("--------------------");
            System.out.println();
            loginSystems();
        }
    }

    private boolean checkLoginAccountAdmin(String account, String password) {
        for (AccountAdmin accountAdmin : accountAdmin.getAccountAdminList()) {
            boolean checkAccountAdmin = account.equals(accountAdmin.getAdminAcc()) && password.equals(accountAdmin.getAdminPass());
            if (checkAccountAdmin) {
                return true;
            }
        }
        return false;
    }

    private boolean checkLoginAccountUser(String account, String password) {
        for (AccountUser accountUser1 : accountUserManager.getAccountUserList()) {
            boolean checkAccountUser = account.equals(accountUser1.getAccount()) && password.equals(accountUser1.getPassword());
            if (checkAccountUser) {
                return true;
            }
        }
        return false;
    }

    //Đăng ký
    public void registerAccountUser() throws InputMismatchException {
        System.out.println("Mời bạn nhập thông tin:");
        System.out.println("--------------------");
        System.out.println("Nhập tài khoản:");
        String accountUser = scanner.nextLine();
        System.out.println("Nhập passwword:");
        String passwordUser = scanner.nextLine();
        System.out.println("Nhập tên:");
        String name = scanner.nextLine();
        System.out.println("Nhập tuổi:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập địa chỉ:");
        String address = scanner.nextLine();
        System.out.println("Nhập số điện thoại:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Nhập email:");
        String email = scanner.nextLine();

        checkAccountUser(accountUser, passwordUser, name, age, address, phoneNumber, email);
    }

    private void checkAccountUser(String accountUser, String passwordUser, String name, int age, String address, String phoneNumber, String email) {
        if (accountUserManager.checkFile()) {
            writeAccountUserAndUser(accountUser, passwordUser, name, age, address, phoneNumber, email);
            System.out.println();
            System.out.println("Đăng ký thành công. Mời đăng nhập vào hệ thống !!!");
            System.out.println("--------------------");
            System.out.println();
        } else if (checkAccount(accountUser)) {
            System.out.println();
            System.out.println("Tài khoản đã tồn tại. Vui lòng đăng ký lại !!!");
            System.out.println("--------------------");
            System.out.println();
        } else {
            writeAccountUserAndUser(accountUser, passwordUser, name, age, address, phoneNumber, email);
            System.out.println();
            System.out.println("Đăng ký thành công. Mời đăng nhập vào hệ thống !!!");
            System.out.println("--------------------");
            System.out.println();
        }
        loginSystems();
    }

    private boolean checkAccount(String accountUser) {
        for (AccountUser accountUser2 : accountUserManager.getAccountUserList()) {
            boolean checkAccountUser = accountUser.equals(accountUser2.getAccount());
            if (checkAccountUser) {
                return true;
            }
        }
        return false;
    }

    private void writeAccountUserAndUser(String accountUser, String passwordUser, String name, int age, String address, String phoneNumber, String email) {
        accountUserManager.setListUser(accountUser, passwordUser);
        userManager.setListUser(accountUser, passwordUser, name, age, address, phoneNumber, email);

    }
}
