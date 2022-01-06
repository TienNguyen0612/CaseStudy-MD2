package _Login;

import _Account.AccountAdmin;
import _Account.AccountUser;
import _Account.AccountUserManager;
import _Account.UserManager;
import _Systems.RunByAdmin;
import _Systems.RunByUser;
import _Validate.Validate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private final Scanner scanner = new Scanner(System.in);
    private final RunByAdmin runByAdmin = new RunByAdmin();
    private final RunByUser runByUser = new RunByUser();
    private final AccountAdmin accountAdmin = new AccountAdmin();
    private final AccountUserManager accountUserManager = new AccountUserManager();
    private final UserManager userManager = new UserManager();
    private final Validate validate = new Validate();

    public Login() {
    }

    public void loginSystems() {
        try {
            menuLogin();
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println();
            System.out.println("Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
           loginSystems();
        }
    }

    //Menu
    private void menuLogin() throws NumberFormatException {
        do {
            System.out.println();
            System.out.println("               \uD83C\uDFE8 NHÀ NGHỈ SEN HỒNG \uD83C\uDFE8");
            System.out.println();
            System.out.println("╔===================================================╗");
            System.out.println("║       ▂ ▃ ▅ ▆ █ QUẢN LÝ KHÁCH SẠN █ ▆ ▅ ▃ ▂       ║");
            System.out.println("╠===================================================╣");
            System.out.println("║>[1]. Đăng nhập                                    ║");
            System.out.println("║>[2]. Đăng ký                                      ║");
            System.out.println("║>[0]. Thoát                                        ║");
            System.out.println("╚===================================================╝");
            System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    loginManager();
                    break;
                case 2:
                    registerAccountUser();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        } while (true);
    }

    //Đăng nhập
    private void loginManager() throws InputMismatchException {
        System.out.println("┎──────────────[ĐĂNG NHẬP]──────────────┒");
        System.out.print("┠ ▹ Nhập tài khoản: ");
        String account = scanner.nextLine();
        System.out.print("┠ ▹ Nhập mật khẩu: ");
        String password = scanner.nextLine();
        System.out.println("┖───────────────────────────────────────┚");

        checkAccount(account, password);
    }

    private void checkAccount(String account, String password) {
        try {
            if (checkLoginAccountAdmin(account, password)) {
                System.out.println();
                System.out.println("⛔ Đặng nhập hệ thống bởi ADMIN thành công !!!");
                System.out.println("--------------------");
                System.out.println();
                runByAdmin.menuOfAdmin();
            } else {
                checkAccountUser(account, password);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println();
            System.out.println("⛔ Đăng nhập thất bại. Vui lòng đăng nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            loginSystems();
        }
    }

    private void checkAccountUser(String account, String password) {
        if (checkLoginAccountUser(account, password)) {
            System.out.println();
            System.out.println("⛔ Đăng nhập hệ thống bởi USER thành công !!!");
            System.out.println("--------------------");
            System.out.println();
            runByUser.menuOfUser();
        } else {
            System.out.println();
            System.out.println("⛔ Tài khoản USER chưa tồn tại. Vui lòng kiểm tra lại !!!");
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
        System.out.println("┎──────────────[ĐĂNG KÝ]──────────────┒");
        System.out.println("[\uD83D\uDD11] Mời bạn nhập thông tin:");
        System.out.println("--------------------");
        String accountUser = enterAccount();
        String passwordUser = enterPassword();
        System.out.print("┠ ▹ Nhập tên: ");
        String name = scanner.nextLine();
        int age = enterAge();
        System.out.print("┠ ▹ Nhập địa chỉ: ");
        String address = scanner.nextLine();
        String phoneNumber = enterPhoneNumber();
        String email = enterEmail();
        System.out.println("┖─────────────────────────────────────┚");

        checkAccountUser(accountUser, passwordUser, name, age, address, phoneNumber, email);
    }

    private int enterAge() {
        int age;
        while (true) {
            System.out.print("┠ ▹ Nhập tuổi: ");
            int inputAge = scanner.nextInt();
            boolean checkAge = (inputAge >= 18 && inputAge < 70);
            scanner.nextLine();
            if (!checkAge) {
                System.out.println("⛔ Tuổi không hợp lệ !!!");
                System.out.println(">[Chú ý]: Tuổi trong khoảng từ 18 - 70");
                System.out.println("--------------------");
            } else {
                age = inputAge;
                break;
            }
        }
        return age;
    }

    private String enterAccount() {
        String accountUser;
        while (true) {
            System.out.print("┠ ▹ Nhập tài khoản: ");
            String account = scanner.nextLine();
            if (!validate.validateAccount(account)) {
                System.out.println("⛔ Tài khoản không hợp lệ !!!");
                System.out.println(">[Chú ý]: Tài khoản phải từ 8 - 12 ký tự (a,1,...)");
                System.out.println("--------------------");
            } else {
                accountUser = account;
                break;
            }
        }
        return accountUser;
    }

    private String enterPassword() {
        String passwordUser;
        while (true) {
            System.out.print("┠ ▹ Nhập passwword: ");
            String password = scanner.nextLine();
            if (!validate.validatePassword(password)) {
                System.out.println("⛔ Mật khẩu không hợp lệ !!!");
                System.out.println(">[Chú ý]: Mật khẩu phải từ 8 - 16 ký tự (a,A,1,...) bao gồm 1 ký tự đặc biệt (@,#,$)");
                System.out.println("--------------------");
            } else {
                passwordUser = password;
                break;
            }
        }
        return passwordUser;
    }

    private String enterPhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.print("┠ ▹ Nhập số điện thoại: ");
            String phone = scanner.nextLine();
            if (!validate.validatePhone(phone)) {
                System.out.println("⛔ Số điện thoại không hợp lệ !!!");
                System.out.println(">[Chú ý]: Số điện thoại phải có 10 số (0 - 9) định dạng: (+84)-911112222");
                System.out.println("--------------------");
            } else {
                phoneNumber = phone;
                break;
            }
        }
        return phoneNumber;
    }

    private String enterEmail() {
        String email;
        while (true) {
            System.out.print("┠ ▹ Nhập email: ");
            String inputEmail = scanner.nextLine();
            if (!validate.validateEmail(inputEmail)) {
                System.out.println("⛔ Email không hợp lệ !!!");
                System.out.println(">[Chú ý]: Email phải có dạng: abc.company@yahoo.com/abc12.company@gmail.vn/...");
                System.out.println("--------------------");
            } else {
                email = inputEmail;
                break;
            }
        }
        return email;
    }

    private void checkAccountUser(String accountUser, String passwordUser, String name, int age, String address, String phoneNumber, String email) {
        if (checkAccount(accountUser)) {
            System.out.println();
            System.out.println("⛔ Tài khoản đã tồn tại. Vui lòng đăng ký lại !!!");
            System.out.println("--------------------");
            System.out.println();
        } else {
            writeAccountUserAndUser(accountUser, passwordUser, name, age, address, phoneNumber, email);
            System.out.println();
            System.out.println("⛔ Đăng ký thành công. Mời đăng nhập vào hệ thống !!!");
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
