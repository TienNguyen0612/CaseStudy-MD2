package _ModelManager;

import _Model.Room;
import _WriteReadFile.IOFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomManager {
    public static final String PATHNAME_ROOM = "FileData/rooms";
    private final ArrayList<Room> roomList;
    private final Scanner scanner = new Scanner(System.in);
    private final IOFile<Room> ioFile = new IOFile<>();

    public RoomManager() {
        this.roomList = ioFile.readFile(PATHNAME_ROOM);
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public String getStatus(int choice) {
        String status = "";
        switch (choice) {
            case 1:
                status = "Sẵn sàng";
                break;
            case 2:
                status = "Đang trống";
                break;
            case 3:
                status = "Đang sửa";
                break;
            case 4:
                status = "Đã thuê";
                break;
        }
        return status;
    }

    public Room addRoom() {
        Room.VALUE = setValue();
        System.out.println("Nhập tên phòng:");
        String roomName = scanner.nextLine();
        System.out.println("Nhập số lượng phòng ngủ:");
        int numberBedrooms = scanner.nextInt();
        System.out.println("Nhập số lượng nhà vệ sinh:");
        int numberToilets = scanner.nextInt();
        System.out.println("Nhập giá phòng:");
        double rentalPrice = scanner.nextDouble();
        System.out.println("Nhập trạng thái phòng:");
        System.out.println("1. Sẵn sàng");
        System.out.println("2. Đang trống");
        System.out.println("3. Đang sửa");
        System.out.println("4. Đã thuê");
        int status = scanner.nextInt();
        scanner.nextLine();
        for (Room room1 : roomList) {
            if (room1.getRoomName().equals(roomName)) {
                System.out.println("Tên phòng bị trùng, mời nhập lại !!!");
                return null;
            }
        }
        Room room = new Room(roomName, numberBedrooms, numberToilets, rentalPrice, getStatus(status));
        roomList.add(room);
        ioFile.writeFile(roomList, PATHNAME_ROOM);
        writeValue();
        System.out.println("Thêm phòng " + roomName + " thành công !!!");
        return room;
    }

    public Room editRoom(int id) {
        Room editRoom = null;
        for (Room room : roomList) {
            if (room.getId() == id) {
                editRoom = room;
            }
        }
        if (editRoom != null) {
            int index = roomList.indexOf(editRoom);
            System.out.println("Nhập tên phòng mới:");
            editRoom.setRoomName(scanner.nextLine());
            System.out.println("Nhập số lượng phòng ngủ mới:");
            editRoom.setNumberBedrooms(scanner.nextInt());
            System.out.println("Nhập số lượng nhà vệ sinh mới:");
            editRoom.setNumberToilets(scanner.nextInt());
            System.out.println("Nhập giá phòng mới:");
            editRoom.setRentalPrice(scanner.nextDouble());
            System.out.println("Nhập trạng thái phòng mới:");
            System.out.println("1. Sẵn sàng");
            System.out.println("2. Đang trống");
            System.out.println("3. Đang sửa");
            System.out.println("4. Đã thuê");
            int status = scanner.nextInt();
            scanner.nextLine();
            for (Room room : roomList) {
                if (room.getRoomName().equals(editRoom.getRoomName())) {
                    System.out.println("Tên phòng bị trùng, mời nhập lại !!!");
                    return null;
                }
            }
            editRoom.setRoomStatus(getStatus(status));
            roomList.set(index, editRoom);
            ioFile.writeFile(roomList, PATHNAME_ROOM);
        }
        return editRoom;
    }

    public void displayAll() {
        if (roomList.isEmpty()) {
            System.out.println("Danh sách phòng chưa được cập nhật !!!");
            return;
        }
        System.out.printf("| %-10S%-10S%S |", "Tên", "Giá", "Trạng thái");
        for (Room room : roomList) {
            System.out.printf("| %-10s%-10f%s |", room.getRoomName(), room.getRentalPrice(), room.getRoomStatus());
            System.out.println();
        }
    }

    public void searchByPriceAndStatus(double lowerPrice, double abovePrice) {
        ArrayList<Room> rooms = new ArrayList<>();
        boolean checkRoom = false;
        for (Room room : roomList) {
            if (room.getRentalPrice() >=lowerPrice && room.getRentalPrice() <= abovePrice && room.getRoomStatus().equals("Đang trống")) {
                rooms.add(room);
                checkRoom = true;
            }
        }
        if (checkRoom) {
            System.out.printf("| %-10S%-10S%S |", "Tên", "Giá", "Trạng thái");
            for (Room room : rooms) {
                System.out.printf("| %-10s%-10f%s |", room.getRoomName(), room.getRentalPrice(), room.getRoomStatus());
                System.out.println();
            }
        } else {
            System.out.println("Không tìm thấy phòng nào !!!");
        }
    }

    public void writeValue() {
        try {
            String PATH_NAME = "FileData/valueRoom.txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_NAME));
            bufferedWriter.write(Room.VALUE);
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public int setValue() {
        try {
            String PATH_NAME = "FileData/valueRoom.txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_NAME));
            int i;
            if ((i = bufferedReader.read()) != -1) {
                return i;
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return 0;
    }
}
