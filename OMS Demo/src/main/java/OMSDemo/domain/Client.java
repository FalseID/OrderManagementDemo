package OMSDemo.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long securityCode;

    @Size(min = 2, max = 30)
    private String firstName;

    @Size(min = 2, max = 30)
    private String lastName;

    @Size(min = 5, max = 30)
    private String phoneNumber;

    private String country;

    @Size(min = 2, max = 50)
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<StoreOrder> order;

    protected Client() {
    }

    public Client(String firstName, String lastName,
            String phone_number, String country, String address) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phone_number;
        this.country = country;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client [security_code=" + securityCode + ", firstName="
                + firstName + ", lastName=" + lastName + ", phone_number="
                + phoneNumber + ", country=" + country + ", address="
                + address + "]";
    }

    public Set<StoreOrder> getOrder() {
        return order;
    }

    public long getSecurityCode() {
        return securityCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
