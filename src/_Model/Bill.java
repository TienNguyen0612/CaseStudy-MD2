package _Model;

import java.time.LocalDate;

public class Bill {
    public static int VALUE;
    private int idBill;
    private String roomName, customerName, staffName;
    private LocalDate startDate, endDate;

    public Bill() {
    }

    public Bill(String roomName, String customerName, String staffName, LocalDate startDate, LocalDate endDate) {
        this.idBill = ++VALUE;
        this.roomName = roomName;
        this.customerName = customerName;
        this.staffName = staffName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Bill(int idBill, String roomName, String customerName, String staffName, LocalDate startDate, LocalDate endDate) {
        this.idBill = idBill;
        this.roomName = roomName;
        this.customerName = customerName;
        this.staffName = staffName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getIdBill() {
        return idBill;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "idBill=" + idBill +
                ", roomName='" + roomName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", staffName='" + staffName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
