package com.library.library_backend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.library.library_backend.dto.ClienteGetDTO;
import com.library.library_backend.dto.ClientePostDTO;
import com.library.library_backend.dto.ClientePutDTO;
import com.library.library_backend.model.Cliente;
import com.library.library_backend.repository.ClienteRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteRepository   repository;

    @GetMapping
	public List<Cliente> get(){
		List<Cliente> list = repository.findAll();
		return list;
	}
	
	@GetMapping ("/{nome}")
	public ResponseEntity<Cliente> getByNome(@PathVariable String nome){
		ResponseEntity<Cliente> ret = ResponseEntity.notFound().build();
		Optional<Cliente> search = repository.findByNome(nome);
		if(search.isPresent()) {
			Cliente item = search.get();
			ret = ResponseEntity.ok(item);
		}else {
			System.out.println("Não existe cliente com o nome: " + nome + " no banco...");
		}
		return ret;
	}

    @PostMapping
	@Transactional
	public ResponseEntity<ClienteGetDTO> post (@RequestBody @Valid ClientePostDTO body, UriComponentsBuilder uriBuilder){
		ResponseEntity<ClienteGetDTO> ret = ResponseEntity.unprocessableEntity().build();
		Cliente item = body.convert();
		Optional<Cliente> search = repository.findByNome(item.getNome());
		if(!search.isPresent()) {
			repository.save(item);
			URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(item.getId()).toUri();
			ret = ResponseEntity.created(uri).body(new ClienteGetDTO(item));
		}else {
			System.out.println("O cliente " + item.getNome() + "ja existe no banco...");
		}
		return ret;
	}

    @PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteGetDTO> put(@PathVariable("id") int id, @RequestBody @Valid ClientePutDTO body, UriComponentsBuilder uriBuilder){
		ResponseEntity<ClienteGetDTO> ret =  ResponseEntity.notFound().build();
		Optional<Cliente> search = repository.findById(id);
		if (search.isPresent()){
			Cliente item = search.get();
			boolean found = false;
			Optional<Cliente> other = repository.findByCpf(body.getCpf());
			if(other.isPresent()){
				Cliente old = other.get();
				if(item.getId() != old.getId()){
					found = true;
					System.out.println("Ja existe cliente com esse CPF...");
					ret = ResponseEntity.unprocessableEntity().build();
				}
			}
			if(!found){
				body.update(item);
				URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(item.getId()).toUri();
				ret = ResponseEntity.created(uri).body(new ClienteGetDTO(item));
			}
		}else{
			System.out.println("Cliente não encontrado...");
		}
		return ret;
	}

    @DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete (@PathVariable("id") int id){
		ResponseEntity<Cliente> ret = ResponseEntity.notFound().build();
		Optional<Cliente> search = repository.findById(id);
		if(search.isPresent()){
			Cliente item = search.get();
			repository.delete(item);
			ret = ResponseEntity.ok().build();
		} else {
			System.out.println("Cliente não encontrado...");
		}
		return ret;
	}
}
