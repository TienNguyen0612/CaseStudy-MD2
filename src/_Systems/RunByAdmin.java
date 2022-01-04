package _Systems;

import _Model.Room;
import _ModelManager.BillManager;
import _ModelManager.RoomManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunByAdmin {
    private final Scanner scan = new Scanner(System.in);
    private final BillManager billManager = new BillManager();
    private final RoomManager roomManager = new RoomManager();

    public RunByAdmin() {
    }

    public void menuOfAdmin() {
        try {
            do {
                int choice = choiceOfAdmin();
                if (choice < 0 || choice > 2) {
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
                }
            } while (true);
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            scan.nextLine();
            menuOfAdmin();
        }
    }

    //Menu của Hệ thống Admin
    private int choiceOfAdmin() {
        System.out.println("----------HỆ THỐNG ADMIN----------");
        System.out.println("1. Quản lý phòng");
        System.out.println("2. Quản lý hóa đơn");
        System.out.println("3. Quản lý người dùng");
        System.out.println("0. Đăng xuất");
        System.out.println("Mời bạn nhập lựa chọn:");
        return scan.nextInt();
    }

    //Menu quản lý phòng
    private void menuRoomManager() {
        try {
            do {
                System.out.println("----------QUẢN LÝ PHÒNG----------");
                System.out.println("1. Thêm phòng");
                System.out.println("2. Sửa phòng");
                System.out.println("3. Xóa phòng");
                System.out.println("4. Hiển thị danh sách phòng");
                System.out.println("5. Tìm kiếm phòng còn trống theo giá");
                System.out.println("6. Kiểm tra trạng thái phòng");
                System.out.println("0. Thoát");
                System.out.println("Mời bạn nhập lựa chọn:");
                int choiceRoom = scan.nextInt();
                scan.nextLine();
                if (choiceRoom < 0 || choiceRoom > 6) {
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
                }
            } while (true);
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            scan.nextLine();
            menuRoomManager();
        }
    }

    //Menu quản lý hóa đơn
    private void menuBillManager() {
        try {
            do {
                System.out.println("----------QUẢN LÝ HÓA ĐƠN----------");
                System.out.println("1. Thêm hóa đơn");
                System.out.println("2. Sửa hóa đơn");
                System.out.println("3. Xóa hóa đơn");
                System.out.println("4. Hiển thị danh sách hóa đơn");
                System.out.println("5. Tổng doanh thu của tháng");
                System.out.println("0. Thoát");
                System.out.println("Mời bạn nhập lựa chọn:");
                int choiceBill = scan.nextInt();
                scan.nextLine();
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
                }
            } while (true);
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            scan.nextLine();
            menuBillManager();
        }
    }
}
