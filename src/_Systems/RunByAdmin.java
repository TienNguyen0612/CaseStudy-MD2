package _Systems;

import _Account.UserManager;
import _Login.Login;
import _Model.Bill;
import _Model.Room;
import _Model.Service;
import _ModelManager.BillManager;
import _ModelManager.OrderServiceManager;
import _ModelManager.RoomManager;
import _ModelManager.ServiceManager;

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
    private final ServiceManager serviceManager = new ServiceManager();
    private final OrderServiceManager orderServiceManager = new OrderServiceManager();

    public RunByAdmin() {
    }

    public void menuOfAdmin() {
        try {
            do {
                int choice = choiceOfAdmin();
                if (choice < 0 || choice > 9) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
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
                        menuServiceManager();
                        break;
                    case 4:
                        menuOrderServiceManager();
                        break;
                    case 5:
                        System.out.println("Nhập vào phòng:");
                        String roomName = scan.nextLine();
                        System.out.println("Nhập ngày Check-in(dd-mm-yyyy):");
                        String checkIn = scan.nextLine();
                        LocalDate checkInDate = LocalDate.parse(checkIn, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        checkOut(roomName, checkInDate);
                        break;
                    case 6:
                        userManager.displayUserList();
                        break;
                    case 7:
                        System.out.println("Nhập tài khoản muốn xóa:");
                        String accountDelete = scan.nextLine();
                        userManager.deleteByName(accountDelete);
                        break;
                    case 8:
                        System.out.println("Nhập vào tháng:");
                        int month = Integer.parseInt(scan.nextLine());
                        System.out.println("Nhập vào năm:");
                        int year = Integer.parseInt(scan.nextLine());
                        if (month < 1 || month > 12 || year < 2015) {
                            System.out.println("⛔ Nhập sai dữ liệu, mời nhập lại !!!");
                            System.out.println("--------------------");
                        } else {
                            System.out.println("Tổng doanh thu " + month + "/" + year + ": " + getTotalInAMonth(month, year));
                        }
                        break;
                    case 9:
                        userManager.displayAll();
                        break;
                    case 0:
                        exitOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuOfAdmin();
        }
    }

    private double getTotalInAMonth(int month, int year) {
        return (billManager.getTotalBillInAMonth(month, year) + orderServiceManager.getTotalInAMonth(month, year));
    }

    private double getTotal(String roomName, LocalDate checkInDate) {
        return (billManager.getBillCheckOut(roomName, checkInDate) + orderServiceManager.getTotalCheckOut(roomName, checkInDate));
    }

    private void checkOut(String roomName, LocalDate checkInDate) {
        billManager.displayBillCheckOut(roomName, checkInDate);
        System.out.println();
        System.out.println("⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ");
        System.out.println();
        orderServiceManager.displayByRoomName(roomName, checkInDate);
        System.out.println();
        System.out.println("⛔ Tổng số tiền phải thanh toán: " + getTotal(roomName, checkInDate));
        System.out.println("--------------------");
    }

    //Menu của Hệ thống Admin
    private int choiceOfAdmin() {
        System.out.println("╔===================================================╗");
        System.out.println("║         ▂ ▃ ▅ ▆ █ HỆ THỐNG ADMIN █ ▆ ▅ ▃ ▂        ║");
        System.out.println("╠===================================================╣");
        System.out.println("║>[1]. Quản lý phòng                                ║");
        System.out.println("║>[2]. Quản lý hóa đơn                              ║");
        System.out.println("║>[3]. Quản lý dịch vụ                              ║");
        System.out.println("║>[4]. Khách hàng đặt dịch vụ                       ║");
        System.out.println("║>[5]. Check-Out                                    ║");
        System.out.println("║>[6]. Hiển thị thông tin USER                      ║");
        System.out.println("║>[7]. Xóa USER                                     ║");
        System.out.println("║>[8]. Tính tổng doanh thu theo tháng               ║");
        System.out.println("║>[0]. Đăng xuất                                    ║");
        System.out.println("╚===================================================╝");
        System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
        return Integer.parseInt(scan.nextLine());
    }

    private void exitOfAdmin() {
        System.out.println();
        System.out.println("⛔ Đã thoát khỏi hệ thống ADMIN !!!");
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
                System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
                int choiceRoom = Integer.parseInt(scan.nextLine());
                if (choiceRoom < 0 || choiceRoom > 7) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
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
                        double lowerPrice = Double.parseDouble(scan.nextLine());
                        System.out.println("Nhập giá trên:");
                        double abovePrice = Double.parseDouble(scan.nextLine());
                        if (lowerPrice > abovePrice) {
                            System.out.println("⛔ Nhập sai dữ liệu, mời nhập lại !!!");
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
        } catch (NumberFormatException | DateTimeParseException | InputMismatchException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
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
                System.out.println("║>[5]. Hiển thị danh sách hóa đơn theo phòng        ║");
                System.out.println("║>[0]. Thoát                                        ║");
                System.out.println("╚===================================================╝");
                System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
                int choiceBill = Integer.parseInt(scan.nextLine());
                if (choiceBill < 0 || choiceBill > 5) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                    System.out.println();
                    menuBillManager();
                }
                switch (choiceBill) {
                    case 1:
                        System.out.println("Nhập vào phòng muốn thuê:");
                        String name = scan.nextLine();
                        Room room = roomManager.getRoom(name);
                        if (room != null) {
                            billManager.addBill(room);
                        } else {
                            System.out.println("⛔ Phòng trên không tồn tại !!!");
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
                        System.out.println("Nhập tên phòng:");
                        String roomNameSearch = scan.nextLine();
                        billManager.displayBillListByRoom(roomNameSearch);
                        break;
                    case 0:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuBillManager();
        }
    }

    //Menu Service
    private void menuServiceManager() {
        try {
            do {
                System.out.println("╔===================================================╗");
                System.out.println("║            ▂ ▃ ▅ ▆ █ DỊCH VỤ █ ▆ ▅ ▃ ▂            ║");
                System.out.println("╠===================================================╣");
                System.out.println("║>[1]. Thêm dịch vụ                                 ║");
                System.out.println("║>[2]. Sửa dịch vụ                                  ║");
                System.out.println("║>[3]. Xóa dịch vụ                                  ║");
                System.out.println("║>[4]. Hiển thị các dịch vụ                         ║");
                System.out.println("║>[0]. Thoát                                        ║");
                System.out.println("╚===================================================╝");
                System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
                int choiceService = Integer.parseInt(scan.nextLine());
                if (choiceService < 0 || choiceService > 4) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                    System.out.println();
                    menuServiceManager();
                }
                switch (choiceService) {
                    case 1:
                        serviceManager.addService();
                        break;
                    case 2:
                        System.out.println("Nhập tên dịch vụ muốn sửa:");
                        String editName = scan.nextLine();
                        serviceManager.editService(editName);
                        break;
                    case 3:
                        System.out.println("Nhập tên dịch vụ muốn xóa:");
                        String deleteName = scan.nextLine();
                        serviceManager.deleteServiceByName(deleteName);
                        break;
                    case 4:
                        serviceManager.displayServiceList();
                        break;
                    case 0:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuServiceManager();
        }
    }

    //Menu Order Service
    private void menuOrderServiceManager() {
        try {
            do {
                System.out.println("╔===================================================╗");
                System.out.println("║             ▂ ▃ ▅ ▆ █ ORDER █ ▆ ▅ ▃ ▂             ║");
                System.out.println("╠===================================================╣");
                System.out.println("║>[1]. Đặt dịch vụ                                  ║");
                System.out.println("║>[2]. Hủy dịch vụ                                  ║");
                System.out.println("║>[3]. Hiển thị các dịch vụ đã đặt theo phòng       ║");
                System.out.println("║>[0]. Thoát                                        ║");
                System.out.println("╚===================================================╝");
                System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
                int choiceOrderService = Integer.parseInt(scan.nextLine());
                if (choiceOrderService < 0 || choiceOrderService > 3) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                    System.out.println();
                    menuOrderServiceManager();
                }
                switch (choiceOrderService) {
                    case 1:
                        System.out.println("Nhập tên phòng:");
                        String roomName = scan.nextLine();
                        System.out.println("Nhập ngày đặt dịch vụ(dd-mm-yyyy):");
                        String date = scan.nextLine();
                        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        int choiceService = choiceService();
                        if (choiceService < 0 || choiceService > 8) {
                            System.out.println();
                            System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                            System.out.println("--------------------");
                            System.out.println();
                            choiceService();
                        }
                        Bill bill = billManager.getBill(roomName, orderDate);
                        Service service = serviceManager.getService(choiceService);
                        if (bill != null && service != null) {
                            orderServiceManager.addOrderService(bill, service, orderDate);
                        } else {
                            System.out.println("⛔ Phòng trên không tồn tại");
                            System.out.println("--------------------");
                        }
                        break;
                    case 2:
                        System.out.println("Nhập tên phòng:");
                        String deleteRoomName = scan.nextLine();
                        System.out.println("Nhập tên dịch vụ:");
                        String deleteService = scan.nextLine();
                        orderServiceManager.deleteByRoomNameAndServiceName(deleteRoomName, deleteService);
                        break;
                    case 3:
                        System.out.println("Nhập tên phòng:");
                        String roomServiceName = scan.nextLine();
                        System.out.println("Nhập ngày Check-in(dd-mm-yyyy):");
                        String start = scan.nextLine();
                        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        orderServiceManager.displayByRoomName(roomServiceName, startDate);
                        break;
                    case 0:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuOrderServiceManager();
        }
    }

    //Menu choice Service
    private int choiceService() {
                System.out.println("╔===================================================╗");
                System.out.println("║             ▂ ▃ ▅ ▆ █ SERVICE █ ▆ ▅ ▃ ▂           ║");
                System.out.println("╠===================================================╣");
                System.out.println("║>[1]. Coca                    >[5]. Vina           ║");
                System.out.println("║>[2]. 555                     >[6]. Massage Body   ║");
                System.out.println("║>[3]. Hà Nội City Tour        >[7]. Mỳ Hộp Omachi  ║");
                System.out.println("║>[4]. Lavie                   >[8]. Bia Heniken    ║");
                System.out.println("╚===================================================╝");
                System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
                return Integer.parseInt(scan.nextLine());
    }
}
