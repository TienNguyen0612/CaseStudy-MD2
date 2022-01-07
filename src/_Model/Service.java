package _Model;

import java.io.Serializable;

public class Service implements Serializable {
    public static int VALUE;
    private int idService;
    private String serviceName;
    private double priceOfService;

    public Service() {
    }

    public Service(String serviceName, double priceOfService) {
        this.idService = ++VALUE;
        this.serviceName = serviceName;
        this.priceOfService = priceOfService;
    }

    public int getIdService() {
        return idService;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPriceOfService() {
        return priceOfService;
    }

    public void setPriceOfService(double priceOfService) {
        this.priceOfService = priceOfService;
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", serviceName='" + serviceName + '\'' +
                ", priceOfService=" + priceOfService +
                '}';
    }
}
