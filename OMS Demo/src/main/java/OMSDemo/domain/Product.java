package main.java.OMSDemo.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long barCode;
	
	private String name;
	private BigDecimal price;
	private String desc;
	
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<StoreOrder> storeOrder; 
	
	protected Product() {
	};
	
	public Product(String name, BigDecimal price, String desc, Date releaseDate) {
		super();
		this.name = name;
		this.price = price;
		this.desc = desc;
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setStoreOrder(Set<StoreOrder> storeOrder) {
		this.storeOrder = storeOrder;
	}

	public long getBarCode() {
		return barCode;
	}

	public Set<StoreOrder> getStoreOrder() {
		return storeOrder;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	 
	
	 

}
