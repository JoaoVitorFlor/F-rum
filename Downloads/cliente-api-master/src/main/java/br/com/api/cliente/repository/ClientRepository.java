package br.com.api.cliente.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.cliente.entrypoint.form.ClientForm;
import br.com.api.cliente.entrypoint.json.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Optional<Client> findById(Long id);

	Client save(ClientForm clientForm);

	Page<Client> findByName(String name, Pageable pagination);
	
}
