package OMSDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import OMSDemo.domain.StoreOrder;

public interface OrderRepository extends JpaRepository<StoreOrder, Long> {

    public StoreOrder findByNumber(long number);
}
