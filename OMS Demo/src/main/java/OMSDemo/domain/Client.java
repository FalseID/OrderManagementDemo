package main.java.OMSDemo.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "client")
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long securityCode;
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String country;
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<StoreOrder> order;
	
	protected Client(){
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

	public void setOrder(Set<StoreOrder> order) {
		this.order = order;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
