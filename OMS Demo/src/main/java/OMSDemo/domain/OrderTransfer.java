package main.java.OMSDemo.domain;

/**
 * User for the spring thymeleaf order form to save form data about our client and 
 * product before creating StoreOrder instance.
 * @author Janar
 *
 */
public class OrderTransfer {
	private long securityCode;
	private long barCode;
	
	public OrderTransfer(long securityCode, long barCode){
		this.securityCode = securityCode;
		this.barCode = barCode;
	}
	
	public long getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(long securityCode) {
		this.securityCode = securityCode;
	}
	public long getBarCode() {
		return barCode;
	}
	public void setBarCode(long barCode) {
		this.barCode = barCode;
	}
}
