package br.com.api.cliente.entrypoint.form;

import java.time.LocalDate;

import br.com.api.cliente.entrypoint.json.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ClientForm {
	private String name;
	private String cpf;
	private LocalDate birthDate;
	
	
	public ClientForm(Client client) {
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.birthDate = client.getBirthDate();
	}
	
}
