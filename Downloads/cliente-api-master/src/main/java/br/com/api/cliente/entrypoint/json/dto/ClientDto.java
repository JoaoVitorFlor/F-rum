package br.com.api.cliente.entrypoint.json.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.api.cliente.entrypoint.json.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ClientDto {
	private Long id;
	private String name;
	private String cpf;
	private LocalDate birthDate;
	
	
	public ClientDto(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.birthDate = client.getBirthDate();
	}


	public static Page<ClientDto> converts(Page<Client> client) {
		
		
		return client.map(ClientDto::new);
	}
	
}
