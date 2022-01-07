package _ModelManager;

import _Model.Bill;
import _Model.Service;
import _WriteReadFile.IOFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceManager {
    public static final String PATHNAME_SERVICE = "FileData/services";
    private final ArrayList<Service> serviceList;
    private final Scanner scanner = new Scanner(System.in);
    private final IOFile<Service> ioFile = new IOFile<>();

    public ServiceManager() {
        if (new File(PATHNAME_SERVICE).length() == 0) {
            this.serviceList = new ArrayList<>();
        } else {
            this.serviceList = ioFile.readFile(PATHNAME_SERVICE);
        }
    }

    public ArrayList<Service> getServiceList() {
        return serviceList;
    }

    public void addService() {
        boolean check = true;
        Service.VALUE = setValue();
        System.out.println("Nhập tên dịch vụ:");
        String name = scanner.nextLine();
        System.out.println("Nhập giá dịch vụ:");
        double price = Double.parseDouble(scanner.nextLine());
        for (Service service : serviceList) {
            if (service.getServiceName().equalsIgnoreCase(name)) {
                check = false;
                break;
            }
        }
        Service service = null;
        if (check) {
            service = new Service(name, price);
            serviceList.add(service);
            ioFile.writeFile(serviceList, PATHNAME_SERVICE);
            writeValue();
            System.out.println("⛔ Thêm dịch vụ " + name + " thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("⛔ Tên bị trùng, mời nhập lại !!!");
            System.out.println("--------------------");
        }

    }

    public void editService(String name) {
        Service editService = null;
        for (Service service : serviceList) {
            if (service.getServiceName().equalsIgnoreCase(name)) {
                editService = service;
            }
        }
        if (editService != null) {
            int index = serviceList.indexOf(editService);
            System.out.println("Nhập giá mới:");
            editService.setPriceOfService(Double.parseDouble(scanner.nextLine()));
            serviceList.set(index, editService);
            ioFile.writeFile(serviceList, PATHNAME_SERVICE);
            System.out.println("⛔ Sửa thành công !!!");
            System.out.println("--------------------");
        }
    }

    public void deleteServiceByName(String name) {
        Service deleteService = null;
        for (Service service : serviceList) {
            if (service.getServiceName().equalsIgnoreCase(name)) {
                deleteService = service;
            }
        }
        if (deleteService != null) {
            serviceList.remove(deleteService);
            ioFile.writeFile(serviceList, PATHNAME_SERVICE);
            System.out.println("⛔ Xóa " + name + " thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("⛔ Không tìm thấy " + name + " !!!");
            System.out.println("--------------------");
        }
    }

    public void displayServiceList() {
        if (serviceList.isEmpty()) {
            System.out.println("⛔ Danh sách dịch vụ chưa được cập nhật !!!");
            System.out.println("--------------------");
            return;
        }
        System.out.println("-----------------------------------------------");
        System.out.printf("| %-5s| %-20s| %-15s|\n", "Id", "Tên Dịch Vụ", "Giá");
        System.out.println("-----------------------------------------------");
        for (Service service : serviceList) {
            System.out.printf("| %-5s| %-20s| %-15.2f|\n", service.getIdService(), service.getServiceName(), service.getPriceOfService());
            System.out.println("-----------------------------------------------");
        }
    }

    public Service getService(String serviceName) {
        Service service = null;
        for (Service service1 : serviceList) {
            if (service1.getServiceName().equals(serviceName)) {
                service = service1;
            }
        }
        return service;
    }

    public void writeValue() {
        try {
            String PATH_NAME = "FileData/valueService.txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_NAME));
            bufferedWriter.write(Service.VALUE);
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public int setValue() {
        try {
            String PATH_NAME = "FileData/valueService.txt";
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
