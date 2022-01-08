package _ModelManager;

import _Model.Bill;
import _Model.OrderService;
import _Model.Service;
import _WriteReadFile.IOFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderServiceManager {
    private static final String PATHNAME_ORDER_SERVICE = "FileData/orderservices";
    private final ArrayList<OrderService> orderServiceList;
    private final Scanner scanner = new Scanner(System.in);
    private final IOFile<OrderService> ioFile = new IOFile<>();

    public OrderServiceManager() {
        if (new File(PATHNAME_ORDER_SERVICE).length() == 0) {
            this.orderServiceList = new ArrayList<>();
        } else {
            this.orderServiceList = ioFile.readFile(PATHNAME_ORDER_SERVICE);
        }
    }

    public ArrayList<OrderService> getOrderServiceList() {
        return orderServiceList;
    }

    public void addOrderService(Bill bill, Service service, LocalDate orderDate) {
        OrderService.VALUE = setValue();
        System.out.println("Nhập số lượng:");
        int quantity = Integer.parseInt(scanner.nextLine());
        OrderService orderService = new OrderService(bill, service, orderDate, quantity);
        orderServiceList.add(orderService);
        ioFile.writeFile(orderServiceList, PATHNAME_ORDER_SERVICE);
        writeValue();
        System.out.println("⛔ Thêm dịch vụ " + service.getServiceName() + " của phòng " + bill.getRoom().getRoomName() + " thành công !!!");
        System.out.println("--------------------");
    }

    public void deleteByRoomNameAndServiceName(String roomName, String serviceName, LocalDate orderDate) {
        OrderService orderService = null;
        for (OrderService order : orderServiceList) {
            if (order.getBill().getRoom().getRoomName().equals(roomName) && order.getService().getServiceName().equalsIgnoreCase(serviceName) && order.getOrderDate().isEqual(orderDate)) {
                orderService = order;
            }
        }
        if (orderService != null) {
            orderServiceList.remove(orderService);
            ioFile.writeFile(orderServiceList, PATHNAME_ORDER_SERVICE);
            System.out.println("⛔ Xóa dịch vụ " + serviceName + " của phòng " + roomName + " thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("⛔ Không tìm thấy dịch vụ cần xóa !!!");
            System.out.println("--------------------");
        }
    }

    public double getTotalInAMonth(int month, int year) {
        double total = 0;
        for (OrderService orderService : orderServiceList) {
            double getTotalOrderService = orderService.getQuantity() * orderService.getService().getPriceOfService();
            if (orderService.getOrderDate().getMonth().getValue() == month && orderService.getOrderDate().getYear() == year) {
                total += getTotalOrderService;
            }
        }
        return total;
    }

    public double getTotalCheckOut(String roomName, LocalDate startDate) {
        double totalCheckOut = 0;
        for (OrderService orderService : orderServiceList) {
            boolean checkRoomName = orderService.getBill().getRoom().getRoomName().equals(roomName);
            boolean checkDate = orderService.getBill().getStartDate().isEqual(startDate);
            double getTotalOrderService = orderService.getQuantity() * orderService.getService().getPriceOfService();
            if (checkRoomName && checkDate) {
                totalCheckOut += getTotalOrderService;
            }
        }
        return totalCheckOut;
    }

    public void displayByRoomName(String roomName, LocalDate startDate) {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-10s| %-20s| %-15s| %-10s| %-15s|\n", "Ngày", "Tên dịch vụ", "Giá tiền", "Số lượng", "Thành tiền");
        System.out.println("---------------------------------------------------------------------------------");
        for (OrderService orderService : orderServiceList) {
            boolean checkRoomName = orderService.getBill().getRoom().getRoomName().equals(roomName);
            boolean checkDate = orderService.getBill().getStartDate().isEqual(startDate);
            double getTotalOrderService = orderService.getQuantity() * orderService.getService().getPriceOfService();
            if (checkRoomName && checkDate) {
                System.out.printf("| %-10s| %-20s| %-15.2f| %-10s| %-15.2f|\n", orderService.getOrderDate(), orderService.getService().getServiceName(),
                        orderService.getService().getPriceOfService(), orderService.getQuantity(), getTotalOrderService);
                System.out.println("---------------------------------------------------------------------------------");
            }
        }
    }

    public void writeValue() {
        try {
            String PATH_NAME = "FileData/valueOrderService.txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_NAME));
            bufferedWriter.write(OrderService.VALUE);
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public int setValue() {
        try {
            String PATH_NAME = "FileData/valueOrderService.txt";
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
