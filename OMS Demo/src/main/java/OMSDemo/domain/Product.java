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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long barCode;
	
	@Size(min=2, max=50)
	private String name;
	
	private BigDecimal price;
	
	@Size(min=0, max=500)
	private String desc;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@OneToMany
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

	public void setBarCode(long barCode) {
		this.barCode = barCode;
	}
	 
	
	 

}
