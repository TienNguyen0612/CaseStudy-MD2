package _Model;

import java.time.LocalDate;

public class Bill {
    public static int VALUE;
    private int idBill;
    private String customerName, staffName;
    private LocalDate startDate, endDate;
    private Room room;

    public Bill() {
    }

    public Bill(Room room, String customerName, String staffName, LocalDate startDate, LocalDate endDate) {
        this.idBill = ++VALUE;
        this.room = room;
        this.customerName = customerName;
        this.staffName = staffName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Bill(int idBill, Room room, String customerName, String staffName, LocalDate startDate, LocalDate endDate) {
        this.idBill = idBill;
        this.room = room;
        this.customerName = customerName;
        this.staffName = staffName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getIdBill() {
        return idBill;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public double getTotalPrice() {
        return (room.getRentalPrice() * (endDate.getDayOfYear() - startDate.getDayOfYear()));
    }

    @Override
    public String toString() {
        return "Bill{" +
                "idBill=" + idBill +
                ", roomName='" + room.getRoomName() + '\'' +
                ", customerName='" + customerName + '\'' +
                ", staffName='" + staffName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", Total=" + getTotalPrice() +
                '}';
    }
}
