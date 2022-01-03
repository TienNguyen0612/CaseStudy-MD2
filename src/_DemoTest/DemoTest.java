package _DemoTest;

import _Model.Room;
import _ModelManager.BillManager;
import _ModelManager.RoomManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DemoTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RoomManager roomManager = new RoomManager();
        BillManager billManager = new BillManager();
        int choice;
        do {
            System.out.println("Nhập lựa chọn:");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Nhập room:");
                    String roomName1 = scanner.nextLine();
                    Room room = roomManager.getRoom(roomName1);
                    if (room != null) {
                        billManager.addBill(room);
                    } else {
                        System.out.println("Nhập sai tên phòng, mời nhập lại !!!");
                    }
                    break;
                case 2:
                    billManager.displayBillList();
                    break;
                case 3:
                    roomManager.displayRoomList();
                    break;
                case 4:
                    System.out.println("Nhập id:");
                    int idEdit = Integer.parseInt(scanner.nextLine());
                    billManager.editBill(idEdit);
                    break;
                case 5:
                    System.out.println("Nhập vào tháng:");
                    int month = Integer.parseInt(scanner.nextLine());
                    System.out.println("Nhập vào năm:");
                    int year = Integer.parseInt(scanner.nextLine());
                    billManager.getTotalBillInAMonth(month, year);
                    break;
                case 6:
                    try {
                        System.out.println("Nhập room:");
                        String name = scanner.nextLine();
                        System.out.println("Nhập before (dd-mm-yyyy):");
                        String before = scanner.nextLine();
                        LocalDate beforeDate = LocalDate.parse(before, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        System.out.println("Nhập after:");
                        String after = scanner.nextLine();
                        LocalDate afterDate = LocalDate.parse(after, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        billManager.checkRoomStatus(name, beforeDate, afterDate);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 7:
                    roomManager.displayAll();
                    break;
                case 8:
                    roomManager.addRoom();
                    break;
            }
        } while (choice != 0);
    }
}
