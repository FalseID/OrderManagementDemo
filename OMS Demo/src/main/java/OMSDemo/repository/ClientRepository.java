package OMSDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import OMSDemo.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client findBySecurityCode(long securityCode);
}
