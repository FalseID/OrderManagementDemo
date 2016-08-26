package main.java.OMSDemo.repository;

import main.java.OMSDemo.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	public Product findByBarCode(long barCode);
}
