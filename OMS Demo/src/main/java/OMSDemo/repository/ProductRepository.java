package OMSDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import OMSDemo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product findByBarCode(long barCode);
}
