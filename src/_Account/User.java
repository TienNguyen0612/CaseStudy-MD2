package _Account;

import java.io.Serializable;

public class User extends AccountUser implements Serializable {
    private String name, address, phoneNumber, email;
    private int age;

    public User() {
    }

    public User(String account, String password, String name, int age, String address, String phoneNumber, String email) {
        super(account, password);
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User: " +
                "account = '" + super.getAccount() + '\'' +
                ", password = " + super.getPassword() + '\'' +
                ", name = '" + name + '\'' +
                ", age = " + age +
                ", address = '" + address + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", email = '" + email + '\'';
    }
}
