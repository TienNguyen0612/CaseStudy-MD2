package _DemoTest;

import _ModelManager.RoomManager;

import java.util.Scanner;

public class DemoTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RoomManager roomManager = new RoomManager();

        int choice;
        do {
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    roomManager.addRoom();
                    break;
                case 2:
                    System.out.println("Nhập id:");
                    int idEdit = scanner.nextInt();
                    scanner.nextLine();
                    roomManager.editRoom(idEdit);
                    break;
                case 3:
                    roomManager.displayRoomList();
                    break;
                case 4:
                    System.out.println("Giá dưới:");
                    double low = scanner.nextDouble();
                    System.out.println("Giá trên:");
                    double above = scanner.nextDouble();
                    roomManager.searchByPriceAndStatus(low, above);
                    break;
                case 5:
                    roomManager.displayAll();
                    break;
            }
        } while (choice != 0);
    }
}
