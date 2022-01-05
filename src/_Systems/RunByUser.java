package _Systems;

import _Login.Login;
import _Model.Room;
import _ModelManager.BillManager;
import _ModelManager.RoomManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunByUser {
    private final Scanner scanner = new Scanner(System.in);
    private final RoomManager roomManager = new RoomManager();
    private final BillManager billManager = new BillManager();

    public RunByUser() {
    }

    public void menuOfUser() {
        try {
            do {
                int choice = choiceOfUser();
                scanner.nextLine();
                if (choice < 0 || choice > 4) {
                    System.out.println();
                    System.out.println("Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                }
                switch (choice) {
                    case 1:
                        roomManager.displayRoomList();
                        break;
                    case 2:
                        searchRoomByPrice();
                        break;
                    case 3:
                        checkRoomStatus();
                        break;
                    case 4:
                        System.out.println("Nhập vào phòng muốn thuê:");
                        String name = scanner.nextLine();
                        Room room = roomManager.getRoom(name);
                        if (room != null) {
                            billManager.addBillByUser(room);
                        } else {
                            System.out.println("Phòng trên không tồn tại !!!");
                            System.out.println("--------------------");
                        }
                        break;
                    case 0:
                        exitOfUser();
                        break;
                }
            } while (true);
        } catch (InputMismatchException | DateTimeParseException | NullPointerException e) {
            System.out.println();
            System.out.println("Bạn đã nhập sai dữ liệu, vui lòng nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuOfUser();
        }
    }

    //Menu Hệ thống User
    private int choiceOfUser() throws InputMismatchException {
        System.out.println("╔===================================================╗");
        System.out.println("║         ▂ ▃ ▅ ▆ █ HỆ THỐNG USER █ ▆ ▅ ▃ ▂         ║");
        System.out.println("╠===================================================╣");
        System.out.println("║>[1]. Hiển thị danh sách phòng                     ║");
        System.out.println("║>[2]. Tìm kiếm phòng còn trống theo giá            ║");
        System.out.println("║>[3]. Kiểm tra trạng thái phòng                    ║");
        System.out.println("║>[4]. Đặt phòng                                    ║");
        System.out.println("║>[0]. Đăng xuất                                    ║");
        System.out.println("╚===================================================╝");
        System.out.println("Mời bạn nhập lựa chọn:");
        return scanner.nextInt();
    }

    private void exitOfUser() {
        System.out.println();
        System.out.println("Đã thoát khỏi hệ thống USER !!!");
        System.out.println("--------------------");
        System.out.println();
        (new Login()).loginSystems();
        System.out.println();
    }

    private void searchRoomByPrice() throws InputMismatchException {
        System.out.println("Nhập giá dưới:");
        double lowerPrice = scanner.nextDouble();
        System.out.println("Nhập giá trên:");
        double abovePrice = scanner.nextDouble();
        if (lowerPrice > abovePrice) {
            System.out.println("Nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            return;
        }
        roomManager.searchByPriceAndStatus(lowerPrice, abovePrice);
    }

    private void checkRoomStatus() throws InputMismatchException, DateTimeParseException {
        System.out.println("Nhập tên phòng:");
        String name = scanner.nextLine();
        System.out.println("Nhập ngày bắt đầu(dd-mm-yyyy):");
        String before = scanner.nextLine();
        LocalDate beforeDate = LocalDate.parse(before, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        System.out.println("Nhập ngày kết thúc(dd-mm-yyyy):");
        String after = scanner.nextLine();
        LocalDate afterDate = LocalDate.parse(after, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        billManager.checkRoomStatus(name, beforeDate, afterDate);
    }
}
