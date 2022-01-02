package _ModelManager;

import _Model.Bill;
import _Model.Room;
import _WriteReadFile.IOFile;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BillManager {
    public static final String PATHNAME_BILL = "FileData/bills";
    private final ArrayList<Bill> billList;
    private final Scanner scanner = new Scanner(System.in);
    private final IOFile<Bill> ioFile = new IOFile<>();

    public BillManager() {
        this.billList = ioFile.readFile(PATHNAME_BILL);
    }

    public ArrayList<Bill> getBillList() {
        return billList;
    }

    public boolean checkRoom(String status) {
        return (status.equals("Đang trống") || status.equals("Sẵn sàng"));
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

    public Bill addBill(Room room) {
        Bill.VALUE = setValue();
        System.out.println("Nhập tên khách thuê:");
        String customerName = scanner.nextLine();
        System.out.println("Nhập tên nhân viên cho thuê:");
        String staffName = scanner.nextLine();
        LocalDate startDate = null;
        LocalDate endDate = null;
        try {
            System.out.println("Nhập ngày bắt đầu(dd-mm-yyyy):");
            String start = scanner.next();
            startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
            System.out.println("Nhập ngày kết thúc(dd-mm-yyyy):");
            String end = scanner.next();
            endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assert startDate != null;
        assert endDate != null;
        Bill bill = null;
        if (startDate.isBefore(endDate) && checkRoom(room.getRoomStatus()) && checkDate(room.getRoomName(), startDate, endDate)) {
            bill = new Bill(room, customerName, staffName, startDate, endDate);
            billList.add(bill);
            ioFile.writeFile(billList, PATHNAME_BILL);
            writeValue();
            System.out.println("Thêm bill của khách hàng " + customerName + " thành công !!!");
        } else {
            System.out.println("Phòng đang sửa hoặc nhập sai dữ liệu, mời nhập lại !!!");
        }
        return bill;
    }

    public Bill editBill(int id) {
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
        }
        return editBill;
    }

    public void displayBillList() {
        if (billList.isEmpty()) {
            System.out.println("Danh sách bill chưa được cập nhật !!!");
            return;
        }
        System.out.printf("| %-10s%-10s%-10s%-10s%-10s%-10s%s |", "Id", "Room", "Customer", "Staff", "Check-in", "Check-out", "Total");
        for (Bill bill : billList) {
            System.out.printf("| %-10s%-10s%-10s%-10s%-10s%-10s%s |", bill.getIdBill(), bill.getRoom().getRoomName(), bill.getCustomerName(), bill.getStaffName(), bill.getStartDate(), bill.getEndDate(), bill.getTotalPrice());
            System.out.println();
        }
    }

    public double getTotalBillInAMonth(int month, int year) {
        double totalBill = 0;
        for (Bill bill : billList) {
            if (bill.getStartDate().getMonth().getValue() == month && bill.getStartDate().getYear() == year) {
                totalBill += bill.getRoom().getRentalPrice() * (bill.getEndDate().getDayOfYear() - bill.getStartDate().getDayOfYear());
            }
        }
        return totalBill;
    }

    public void checkRoomStatus(String name, LocalDate beforeDate, LocalDate afterDate) {
        ArrayList<Bill> billArrayList = new ArrayList<>();
        for (Bill bill : billList) {
            if (bill.getRoom().getRoomName().equals(name)) {
                billArrayList.add(bill);
            }
        }
        billArrayList.removeIf(bill -> bill.getEndDate().isBefore(beforeDate) || bill.getStartDate().isAfter(afterDate));
        System.out.println("Trạng thái phòng từ " + beforeDate + " đến " + afterDate + ":");
        for (Bill bill : billArrayList) {
            System.out.println("Từ " + bill.getStartDate() + " đến " + bill.getEndDate() + ": 'Đã thuê'");
        }
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
