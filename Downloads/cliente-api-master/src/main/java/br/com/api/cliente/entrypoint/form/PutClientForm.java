package br.com.api.cliente.entrypoint.form;

import java.time.LocalDate;

import br.com.api.cliente.entrypoint.json.Client;
import br.com.api.cliente.repository.ClientRepository;
import lombok.Data;

@Data
public class PutClientForm {
	
	private String name;
	private String cpf;
	private LocalDate birthDate;

	
	public Client put(Long id, ClientRepository repository) {
		Client client = repository.getOne(id);
		client.setBirthDate(this.birthDate);
		client.setCpf(this.cpf);
		client.setName(this.name);
		
		return client;
	}
	
}
