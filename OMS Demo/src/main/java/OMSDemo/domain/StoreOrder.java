package OMSDemo.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "StoreOrder")
public class StoreOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;

    @ManyToOne
    @JoinColumn(name = "client_securityCode")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_barCode")
    private Product product;

    private String formattedPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    protected StoreOrder() {
    }

    public StoreOrder(Client client, Product product, String formattedPrice, Date date) {
        super();
        this.client = client;
        this.product = product;
        this.formattedPrice = formattedPrice;
        this.transactionDate = date;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Product getProduct() {
        return product;
    }

    public Client getClient() {
        return client;
    }

    public long getNumber() {
        return number;
    }
}
