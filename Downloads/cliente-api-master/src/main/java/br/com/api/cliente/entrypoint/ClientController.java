package br.com.api.cliente.entrypoint;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.cliente.entrypoint.form.ClientForm;
import br.com.api.cliente.entrypoint.form.PutClientForm;
import br.com.api.cliente.entrypoint.json.Client;
import br.com.api.cliente.entrypoint.json.dto.ClientDto;
import br.com.api.cliente.repository.ClientRepository;

@RequestMapping("/client")
@RestController
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
		Optional<Client> optional = clientRepository.findById(id);
		if (optional.isPresent()) {

		}

		return null;

	}

	@GetMapping
	public Page<ClientDto> getClientByParam(@RequestParam String name, @RequestParam String cpf,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 2) Pageable pagination) {

		if (name == null && cpf == null) {
			Page<Client> clients = clientRepository.findAll(pagination);
			return ClientDto.converts(clients);
		} else {
			Page<Client> clients = clientRepository.findByName(name, pagination);
			return ClientDto.converts(clients);
		}

	}

	@PostMapping
	public ResponseEntity<ClientDto> postClient(@RequestBody ClientForm clientForm, UriComponentsBuilder uriBuilder) {
		Client save = clientRepository.save(clientForm);
		URI uri = uriBuilder.path("client/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClientDto(save));

	}

	@PatchMapping
	public String patchClient(String client) {
		return client;
	}

	@PutMapping("{id}")
	public ResponseEntity<ClientDto> putClient(@PathVariable Long id, @RequestBody PutClientForm putForm) {
		Optional<Client> optional = clientRepository.findById(id);
		if (optional.isPresent()) {
			Client client = putForm.put(id, clientRepository);
			return ResponseEntity.ok(new ClientDto(client));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping
	public ResponseEntity<?> deleteClient(@PathVariable Long id) {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			clientRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}
