package main.java.OMSDemo.repository;

import main.java.OMSDemo.domain.StoreOrder;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<StoreOrder, Long> {
	public StoreOrder findByNumber(long number);
}

