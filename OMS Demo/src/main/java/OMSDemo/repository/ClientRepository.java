package main.java.OMSDemo.repository;

import main.java.OMSDemo.domain.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.*;

public interface ClientRepository extends JpaRepository<Client, Long> {
	public Client findBySecurityCode(long securityCode);
}
