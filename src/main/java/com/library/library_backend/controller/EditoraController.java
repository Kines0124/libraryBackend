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

import com.library.library_backend.dto.EditoraGetDTO;
import com.library.library_backend.dto.EditoraPostDTO;
import com.library.library_backend.dto.EditoraPutDTO;
import com.library.library_backend.model.Editora;
import com.library.library_backend.repository.EditoraRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/editora")
public class EditoraController {

	@Autowired
	private EditoraRepository repository;
	
	@GetMapping
	public List<Editora> get(){
		List<Editora> list = repository.findAll();
		return list;
	}
	
	@GetMapping ("/{nome}")
	public ResponseEntity<Editora> getByNome(@PathVariable String nome){
		ResponseEntity<Editora> ret = ResponseEntity.notFound().build();
		Optional<Editora> search = repository.findByNome(nome);
		if(search.isPresent()) {
			Editora item = search.get();
			ret = ResponseEntity.ok(item);
		}else {
			System.out.println("Não existe editora com o nome: " + nome + " no banco...");
		}
		return ret;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EditoraGetDTO> post (@RequestBody @Valid EditoraPostDTO body, UriComponentsBuilder uriBuilder){
		ResponseEntity<EditoraGetDTO> ret = ResponseEntity.unprocessableEntity().build();
		Editora item = body.convert();
		Optional<Editora> search = repository.findByNome(item.getNome());
		if(!search.isPresent()) {
			repository.save(item);
			URI uri = uriBuilder.path("/editora/{id}").buildAndExpand(item.getId()).toUri();
			ret = ResponseEntity.created(uri).body(new EditoraGetDTO(item));
		}else {
			System.out.println("A editora " + item.getNome() + "ja existe no banco...");
		}
		return ret;
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EditoraGetDTO> put(@PathVariable("id") int id, @RequestBody @Valid EditoraPutDTO body, UriComponentsBuilder uriBuilder){
		ResponseEntity<EditoraGetDTO> ret =  ResponseEntity.notFound().build();
		Optional<Editora> search = repository.findById(id);
		if (search.isPresent()){
			Editora item = search.get();
			boolean found = false;
			Optional<Editora> other = repository.findByCnpj(body.getCnpj());
			if(other.isPresent()){
				Editora old = other.get();
				if(item.getId() != old.getId()){
					found = true;
					System.out.println("Ja existe editora com esse CNPJ...");
					ret = ResponseEntity.unprocessableEntity().build();
				}
			}
			if(!found){
				body.update(item);
				URI uri = uriBuilder.path("/editora/{id}").buildAndExpand(item.getId()).toUri();
				ret = ResponseEntity.created(uri).body(new EditoraGetDTO(item));
			}
		}else{
			System.out.println("Editora não encontrada...");
		}
		return ret;
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete (@PathVariable("id") int id){
		ResponseEntity<Editora> ret = ResponseEntity.notFound().build();
		Optional<Editora> search = repository.findById(id);
		if(search.isPresent()){
			Editora item = search.get();
			repository.delete(item);
			ret = ResponseEntity.ok().build();
		} else {
			System.out.println("Editora não encontrada...");
		}
		return ret;
	}
}