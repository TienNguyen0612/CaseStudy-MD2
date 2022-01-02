package _Model;

import java.io.Serializable;

public class Room implements Serializable {
    public static int VALUE;
    private int id, numberBedrooms, numberToilets;
    String roomName, roomStatus;
    double rentalPrice;

    public Room(String roomName, int numberBedrooms, int numberToilets, double rentalPrice, String roomStatus) {
        this.id = ++VALUE;
        this.roomName = roomName;
        this.numberBedrooms = numberBedrooms;
        this.numberToilets = numberToilets;
        this.rentalPrice = rentalPrice;
        this.roomStatus = roomStatus;

    }

    public Room(int id, String roomName, int numberBedrooms, int numberToilets, double rentalPrice, String roomStatus) {
        this.id = id;
        this.roomName = roomName;
        this.numberBedrooms = numberBedrooms;
        this.numberToilets = numberToilets;
        this.rentalPrice = rentalPrice;
        this.roomStatus = roomStatus;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public int getNumberBedrooms() {
        return numberBedrooms;
    }

    public void setNumberBedrooms(int numberBedrooms) {
        this.numberBedrooms = numberBedrooms;
    }

    public int getNumberToilets() {
        return numberToilets;
    }

    public void setNumberToilets(int numberToilets) {
        this.numberToilets = numberToilets;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", numberBedrooms=" + numberBedrooms +
                ", numberToilets=" + numberToilets +
                ", rentalPrice=" + rentalPrice +
                ", roomStatus='" + roomStatus + '\'' +
                '}';
    }
}
