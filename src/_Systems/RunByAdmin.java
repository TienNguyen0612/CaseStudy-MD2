package _Systems;

import _Account.UserManager;
import _Login.Login;
import _Model.Room;
import _ModelManager.BillManager;
import _ModelManager.RoomManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunByAdmin {
    private final Scanner scan = new Scanner(System.in);
    private final BillManager billManager = new BillManager();
    private final RoomManager roomManager = new RoomManager();
    private final UserManager userManager = new UserManager();

    public RunByAdmin() {
    }

    public void menuOfAdmin() {
        try {
            do {
                int choice = choiceOfAdmin();
                if (choice < 0 || choice > 4) {
                    System.out.println();
                    System.out.println("Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                }
                switch (choice) {
                    case 1:
                        menuRoomManager();
                        break;
                    case 2:
                        menuBillManager();
                        break;
                    case 3:
                        userManager.displayUserList();
                        break;
                    case 4:
                        System.out.println("Nhập tài khoản muốn xóa:");
                        String accountDelete = scan.nextLine();
                        userManager.deleteByName(accountDelete);
                        break;
                    case 0:
                        exitOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuOfAdmin();
        }
    }

    //Menu của Hệ thống Admin
    private int choiceOfAdmin() {
        System.out.println("╔===================================================╗ ");
        System.out.println("║         ▂ ▃ ▅ ▆ █ HỆ THỐNG ADMIN █ ▆ ▅ ▃ ▂        ║ ");
        System.out.println("╠===================================================╣");
        System.out.println("║>[1]. Quản lý phòng                                ║");
        System.out.println("║>[2]. Quản lý hóa đơn                              ║");
        System.out.println("║>[3]. Hiển thị thông tin USER                      ║");
        System.out.println("║>[4]. Xóa USER                                     ║");
        System.out.println("║>[0]. Đăng xuất                                    ║");
        System.out.println("╚===================================================╝");
        System.out.println("Mời bạn nhập lựa chọn:");
        return Integer.parseInt(scan.nextLine());
    }

    private void exitOfAdmin() {
        System.out.println();
        System.out.println("Đã thoát khỏi hệ thống ADMIN !!!");
        System.out.println("--------------------");
        System.out.println();
        (new Login()).loginSystems();
        System.out.println();
    }

    //Menu quản lý phòng
    private void menuRoomManager() {
        try {
            do {
                System.out.println("╔===================================================╗");
                System.out.println("║         ▂ ▃ ▅ ▆ █ QUẢN LÝ PHÒNG █ ▆ ▅ ▃ ▂         ║");
                System.out.println("╠===================================================╣");
                System.out.println("║>[1]. Thêm phòng                                   ║");
                System.out.println("║>[2]. Sửa phòng                                    ║");
                System.out.println("║>[3]. Xóa phòng                                    ║");
                System.out.println("║>[4]. Hiển thị danh sách phòng                     ║");
                System.out.println("║>[5]. Tìm kiếm phòng còn trống theo giá            ║");
                System.out.println("║>[6]. Kiểm tra trạng thái phòng                    ║");
                System.out.println("║>[0]. Thoát                                        ║");
                System.out.println("╚===================================================╝");
                System.out.println("Mời bạn nhập lựa chọn:");
                int choiceRoom = Integer.parseInt(scan.nextLine());
                if (choiceRoom < 0 || choiceRoom > 7) {
                    System.out.println();
                    System.out.println("Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                    System.out.println();
                    menuRoomManager();
                }
                switch (choiceRoom) {
                    case 1:
                        roomManager.addRoom();
                        break;
                    case 2:
                        System.out.println("Nhập Id phòng muốn sửa:");
                        int idEdit = scan.nextInt();
                        roomManager.editRoom(idEdit);
                        break;
                    case 3:
                        System.out.println("Nhập Id phòng muốn xóa:");
                        int idDelete = scan.nextInt();
                        roomManager.deleteByIdRoom(idDelete);
                        break;
                    case 4:
                        roomManager.displayRoomList();
                        break;
                    case 5:
                        System.out.println("Nhập giá dưới:");
                        double lowerPrice = scan.nextDouble();
                        System.out.println("Nhập giá trên:");
                        double abovePrice = scan.nextDouble();
                        if (lowerPrice > abovePrice) {
                            System.out.println("Nhập sai dữ liệu, mời nhập lại !!!");
                            System.out.println("--------------------");
                            return;
                        }
                        roomManager.searchByPriceAndStatus(lowerPrice, abovePrice);
                        break;
                    case 6:
                        System.out.println("Nhập tên phòng:");
                        String name = scan.nextLine();
                        System.out.println("Nhập ngày bắt đầu(dd-mm-yyyy):");
                        String before = scan.nextLine();
                        LocalDate beforeDate = LocalDate.parse(before, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        System.out.println("Nhập ngày kết thúc(dd-mm-yyyy):");
                        String after = scan.nextLine();
                        LocalDate afterDate = LocalDate.parse(after, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        billManager.checkRoomStatus(name, beforeDate, afterDate);
                        break;
                    case 7:
                        roomManager.displayAll();
                        break;
                    case 0:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuRoomManager();
        }
    }

    //Menu quản lý hóa đơn
    private void menuBillManager() {
        try {
            do {
                System.out.println("╔===================================================╗");
                System.out.println("║        ▂ ▃ ▅ ▆ █ QUẢN LÝ HÓA ĐƠN █ ▆ ▅ ▃ ▂        ║");
                System.out.println("╠===================================================╣");
                System.out.println("║>[1]. Thêm hóa đơn                                 ║");
                System.out.println("║>[2]. Sửa hóa đơn                                  ║");
                System.out.println("║>[3]. Xóa hóa đơn                                  ║");
                System.out.println("║>[4]. Hiển thị danh sách hóa đơn                   ║");
                System.out.println("║>[5]. Tổng doanh thu của tháng                     ║");
                System.out.println("║>[0]. Thoát                                        ║");
                System.out.println("╚===================================================╝");
                System.out.println("Mời bạn nhập lựa chọn:");
                int choiceBill = Integer.parseInt(scan.nextLine());
                switch (choiceBill) {
                    case 1:
                        System.out.println("Nhập vào phòng muốn thuê:");
                        String name = scan.nextLine();
                        Room room = roomManager.getRoom(name);
                        if (room != null) {
                            billManager.addBill(room);
                        } else {
                            System.out.println("Phòng trên không tồn tại !!!");
                            System.out.println("--------------------");
                        }
                        break;
                    case 2:
                        System.out.println("Nhập Id hóa đơn muốn sửa:");
                        int editId = scan.nextInt();
                        billManager.editBill(editId);
                        break;
                    case 3:
                        System.out.println("Nhập Id hóa đơn muốn xóa:");
                        int deleteId = scan.nextInt();
                        billManager.deleteByIdBill(deleteId);
                        break;
                    case 4:
                        billManager.displayBillList();
                        break;
                    case 5:
                        System.out.println("Nhập vào tháng:");
                        int month = scan.nextInt();
                        System.out.println("Nhập vào năm:");
                        int year = scan.nextInt();
                        if (month < 1 || month > 12 || year < 2015) {
                            System.out.println("Nhập sai dữ liệu, mời nhập lại !!!");
                            System.out.println("--------------------");
                        } else {
                            billManager.getTotalBillInAMonth(month, year);
                        }
                        break;
                    case 0:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuBillManager();
        }
    }
}
