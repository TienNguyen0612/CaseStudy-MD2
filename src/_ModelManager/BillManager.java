package _ModelManager;

import _Model.Bill;
import _Model.Room;
import _WriteReadFile.IOFile;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BillManager {
    public static final String PATHNAME_BILL = "FileData/bills";
    private final ArrayList<Bill> billList;
    private final Scanner scanner = new Scanner(System.in);
    private final IOFile<Bill> ioFile = new IOFile<>();

    public BillManager() {
        if (new File(PATHNAME_BILL).length() == 0) {
            this.billList = new ArrayList<>();
        } else {
            this.billList = ioFile.readFile(PATHNAME_BILL);
        }
    }

    public ArrayList<Bill> getBillList() {
        return billList;
    }

    public boolean checkRoom(String status) {
        return (status.equals("Đang trống"));
    }

    public boolean checkDate(String name, LocalDate start, LocalDate end) {
        ArrayList<Bill> billArrayList = new ArrayList<>();
        boolean check = true;
        for (Bill bill : billList) {
            if (bill.getRoom().getRoomName().equals(name)) {
                billArrayList.add(bill);
            }
        }
        for (Bill bill : billArrayList) {
            if (end.isBefore(bill.getStartDate()) || start.isAfter(bill.getEndDate())) {
            } else {
                check = false;
                break;
            }
        }
        return check;
    }

    public void addBill(Room room) {
        Bill.VALUE = setValue();
        System.out.println("Nhập tên khách thuê:");
        String customerName = scanner.nextLine();
        System.out.println("Nhập tên nhân viên cho thuê:");
        String staffName = scanner.nextLine();
        System.out.println("Nhập ngày bắt đầu(dd-mm-yyyy):");
        String start = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        System.out.println("Nhập ngày kết thúc(dd-mm-yyyy):");
        String end = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        Bill bill = null;
        if (startDate.isBefore(endDate) && checkRoom(room.getRoomStatus()) && checkDate(room.getRoomName(), startDate, endDate)) {
            bill = new Bill(room, customerName, staffName, startDate, endDate);
            billList.add(bill);
            ioFile.writeFile(billList, PATHNAME_BILL);
            writeValue();
            System.out.println("Thêm bill của khách hàng " + customerName + " thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("- Phòng đang sửa hoặc đã có người thuê");
            System.out.println("- Nhập sai dữ liệu, xin mời nhập lại !!!");
            System.out.println("--------------------");
        }
    }

    public void addBillByUser(Room room) {
        Bill.VALUE = setValue();
        System.out.println("Nhập tên khách thuê:");
        String customerName1 = scanner.nextLine();
        String staffName1 = "Online";
        System.out.println("Nhập ngày bắt đầu(dd-mm-yyyy): ");
        String start1 = scanner.nextLine();
        LocalDate startDate1 = LocalDate.parse(start1, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        System.out.println("Nhập ngày kết thúc(dd-mm-yyyy):");
        String end1 = scanner.nextLine();
        LocalDate endDate1 = LocalDate.parse(end1, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        Bill bill = null;
        if (startDate1.isBefore(endDate1) && checkRoom(room.getRoomStatus()) && checkDate(room.getRoomName(), startDate1, endDate1)) {
            bill = new Bill(room, customerName1, staffName1, startDate1, endDate1);
            billList.add(bill);
            ioFile.writeFile(billList, PATHNAME_BILL);
            writeValue();
            System.out.println("Đặt phòng của khách hàng " + customerName1 + " thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("- Phòng đang sửa hoặc đã có người thuê");
            System.out.println("- Nhập sai dữ liệu, xin mời nhập lại !!!");
            System.out.println("--------------------");
        }
    }

    public void editBill(int id) {
        Bill editBill = null;
        for (Bill bill : billList) {
            if (bill.getIdBill() == id) {
                editBill = bill;
            }
        }
        if (editBill != null) {
            int index = billList.indexOf(editBill);
            System.out.println("Nhập tên khách thuê mới:");
            editBill.setCustomerName(scanner.nextLine());
            System.out.println("Nhập tên nhân viên cho thuê mới:");
            editBill.setStaffName(scanner.nextLine());
            try {
                System.out.println("Nhập ngày bắt đầu(dd-mm-yyyy):");
                String start = scanner.next();
                editBill.setStartDate(LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-LL-yyyy")));
                System.out.println("Nhập ngày kết thúc(dd-mm-yyyy):");
                String end = scanner.next();
                editBill.setEndDate(LocalDate.parse(end, DateTimeFormatter.ofPattern("dd-LL-yyyy")));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            billList.set(index, editBill);
            ioFile.writeFile(billList, PATHNAME_BILL);
            System.out.println("Sửa thành công !!!");
            System.out.println("--------------------");
        }
    }

    public void deleteByIdBill(int id) {
        Bill bill = null;
        for (Bill bill1 : billList) {
            if (bill1.getIdBill() == id) {
                bill = bill1;
            }
        }
        if (bill != null) {
            billList.remove(bill);
            ioFile.writeFile(billList, PATHNAME_BILL);
            System.out.println("Xóa bill " + id + " thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("Không tìm thấy Id cần xóa !!!");
            System.out.println("--------------------");
        }
    }

    public void displayBillList() {
        if (billList.isEmpty()) {
            System.out.println("Danh sách bill chưa được cập nhật !!!");
            System.out.println("--------------------");
            return;
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("| %-5s| %-5s| %-10s| %-10s| %-10s| %-10s| %-15s|\n", "Id", "Room", "Customer", "Staff", "Check-in", "Check-out", "Total");
        System.out.println("--------------------------------------------------------------------------------");
        for (Bill bill : billList) {
            System.out.printf("| %-5s| %-5s| %-10s| %-10s| %-10s| %-10s| %-15.2f|", bill.getIdBill(), bill.getRoom().getRoomName(), bill.getCustomerName(), bill.getStaffName(), bill.getStartDate(), bill.getEndDate(), bill.getTotalPrice());
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    public void displayBillListByRoom(String roomName) {
        ArrayList<Bill> billsRoom = new ArrayList<>();
        for (Bill bill : billList) {
            if (bill.getRoom().getRoomName().equals(roomName)) {
                billsRoom.add(bill);
            }
        }
        if (billsRoom.isEmpty()) {
            System.out.println("Không tìm thấy phòng nào !!!");
            System.out.println("--------------------");
        } else {
            billsRoom.sort(Comparator.comparingInt(o -> o.getStartDate().getDayOfMonth()));
            System.out.println("-------------------------------------------------------------------------------- ");
            System.out.printf("| %-5s| %-5s| %-10s| %-10s| %-10s| %-10s| %-15s|\n", "Id", "Room", "Customer", "Staff", "Check-in", "Check-out", "Total");
            System.out.println("-------------------------------------------------------------------------------- ");
            for (Bill bill : billsRoom) {
                System.out.printf("| %-5s| %-5s| %-10s| %-10s| %-10s| %-10s| %-15.2f|", bill.getIdBill(), bill.getRoom().getRoomName(), bill.getCustomerName(), bill.getStaffName(), bill.getStartDate(), bill.getEndDate(), bill.getTotalPrice());
                System.out.println();
                System.out.println("-------------------------------------------------------------------------------- ");
            }
        }
    }

    public void getTotalBillInAMonth(int month, int year) {
        double totalBill = 0;
        for (Bill bill : billList) {
            if (bill.getStartDate().getMonth().getValue() == month && bill.getStartDate().getYear() == year) {
                totalBill += bill.getTotalPrice();
            }
        }
        System.out.println("Tổng doanh thu tháng " + month + "/" + year + ": " + totalBill);
        System.out.println("--------------------");
    }

    public void checkRoomStatus(String name, LocalDate beforeDate, LocalDate afterDate) {
        ArrayList<Bill> billArrayList = new ArrayList<>();
        Bill firstBill = null;
        Bill lastBill = null;
        int checkDate = 0;
        int checkName = 0;
        if (beforeDate.isAfter(afterDate)) {
            System.out.println("Nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            return;
        }
        for (Bill bill : billList) {
            if (bill.getRoom().getRoomName().equals(name)) {
                if (bill.getStartDate().isBefore(beforeDate) && bill.getEndDate().isAfter(beforeDate)) {
                    firstBill = bill;
                } else if (bill.getStartDate().isBefore(afterDate) && bill.getEndDate().isAfter(afterDate)) {
                    lastBill = bill;
                } else if (bill.getStartDate().isEqual(beforeDate) || (bill.getStartDate().isAfter(beforeDate) && bill.getEndDate().isBefore(afterDate)) || bill.getEndDate().isEqual(afterDate)) {
                    billArrayList.add(bill);
                    checkDate++;
                }
                checkName++;
            }
        }
        if (checkName == 0) {
            System.out.println("⛔ Không tìm thấy phòng !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("Trạng thái phòng " + name + " từ " + beforeDate + " đến " + afterDate + ":");
            if (checkDate == 0) {
                if (firstBill == null) {
                    if (lastBill == null) {
                        System.out.println("⛔ Đang trống !!!");
                        System.out.println("--------------------");
                    } else {
                        System.out.println("----------------------------------------------------");
                        System.out.printf("| %-15s| %-15s| %-15s|\n", "Từ ngày", "Đến ngày", "Trạng thái");
                        System.out.println("----------------------------------------------------");
                        System.out.printf("| %-15s| %-15s| %-15s|", lastBill.getStartDate(), afterDate, "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    }
                } else {
                    System.out.println("----------------------------------------------------");
                    System.out.printf("| %-15s| %-15s| %-15s|\n", "Từ ngày", "Đến ngày", "Trạng thái");
                    System.out.println("----------------------------------------------------");
                    if (lastBill == null) {
                        System.out.printf("| %-15s| %-15s| %-15s|", beforeDate, firstBill.getEndDate(), "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    } else {
                        System.out.printf("| %-15s| %-15s| %-15s|", beforeDate, firstBill.getEndDate(), "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                        System.out.printf("| %-15s| %-15s| %-15s|", lastBill.getStartDate(), afterDate, "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    }
                }
            } else {
                billArrayList.sort(Comparator.comparingInt(o -> o.getStartDate().getDayOfMonth()));
                System.out.println("----------------------------------------------------");
                System.out.printf("| %-15s| %-15s| %-15s|\n", "Từ ngày", "Đến ngày", "Trạng thái");
                System.out.println("----------------------------------------------------");
                if (firstBill == null) {
                    if (lastBill == null) {
                        for (Bill bill : billArrayList) {
                            System.out.printf("| %-15s| %-15s| %-15s|", bill.getStartDate(), bill.getEndDate(), "ĐÃ THUÊ");
                            System.out.println();
                            System.out.println("----------------------------------------------------");
                        }
                    } else {
                        for (Bill bill : billArrayList) {
                            System.out.printf("| %-15s| %-15s| %-15s|", bill.getStartDate(), bill.getEndDate(), "ĐÃ THUÊ");
                            System.out.println();
                            System.out.println("----------------------------------------------------");
                        }
                        System.out.printf("| %-15s| %-15s| %-15s|", lastBill.getStartDate(), afterDate, "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    }
                } else {
                    System.out.printf("| %-15s| %-15s| %-15s|", beforeDate, firstBill.getEndDate(), "ĐÃ THUÊ");
                    System.out.println();
                    System.out.println("----------------------------------------------------");
                    for (Bill bill : billArrayList) {
                        System.out.printf("| %-15s| %-15s| %-15s|", bill.getStartDate(), bill.getEndDate(), "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    }
                    if (lastBill != null) {
                        System.out.printf("| %-15s| %-15s| %-15s|", lastBill.getStartDate(), afterDate, "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    }
                }
            }
        }
    }

    public Bill getBill(String roomName, LocalDate orderDate) {
        Bill bill = null;
        for (Bill bill1 : billList) {
            boolean checkOrderDate = (bill1.getStartDate().isEqual(orderDate) || bill1.getEndDate().isEqual(orderDate) || (bill1.getStartDate().isBefore(orderDate) && bill1.getEndDate().isAfter(orderDate)));
            if (bill1.getRoom().getRoomName().equals(roomName) && checkOrderDate) {
                bill = bill1;
            }
        }
        return bill;
    }

    public void writeValue() {
        try {
            String PATH_NAME = "FileData/valueBill.txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_NAME));
            bufferedWriter.write(Bill.VALUE);
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public int setValue() {
        try {
            String PATH_NAME = "FileData/valueBill.txt";
            File file = new File(PATH_NAME);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_NAME));
                int i;
                if ((i = bufferedReader.read()) != -1) {
                    return i;
                }
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return 0;
    }
}
