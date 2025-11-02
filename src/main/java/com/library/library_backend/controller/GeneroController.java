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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.library.library_backend.dto.GeneroGetDTO;
import com.library.library_backend.dto.GeneroPostDTO;
import com.library.library_backend.dto.GeneroPutDTO;
import com.library.library_backend.model.Genero;
import com.library.library_backend.repository.GeneroRepository;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    
@Autowired
private GeneroRepository repository;

//Esse aqui não faz nada, só retorna todos os generos q tem
@GetMapping
public List<GeneroGetDTO> get() {
    List<Genero> list = repository.findAll();
    return GeneroGetDTO.convert(list);
}

@GetMapping("/{genero}")
public ResponseEntity<Genero> getByGenero (@PathVariable String genero) {
	ResponseEntity<Genero> ret = ResponseEntity.notFound().build();
	Optional<Genero> search = repository.findByDescricao(genero);
	if (search.isPresent()){
		Genero item = search.get();
		ret = ResponseEntity.ok(item);
	}else{
		System.out.println("Genero não cadastrado...");
	}
	return ret;
}


@PostMapping
@Transactional
public ResponseEntity<GeneroGetDTO> post(@RequestBody @Valid GeneroPostDTO body, UriComponentsBuilder uriBuilder) {
	ResponseEntity<GeneroGetDTO> ret = ResponseEntity.unprocessableEntity().build(); 
	Genero item = body.convert(); 
	Optional<Genero> search = repository.findByDescricao(item.getDescricao()); 
	if (!search.isPresent()) { 
		repository.save(item);
		URI uri = uriBuilder.path("/genero/{id}").buildAndExpand(item.getId()).toUri(); 
		ret = ResponseEntity.created(uri).body(new GeneroGetDTO(item));
	} else
		System.out.println("Genero ja existe...");
	return ret;
}

@PutMapping("/{id}")
@Transactional
	public ResponseEntity<GeneroGetDTO> put(@PathVariable("id") int id, @RequestBody @Valid GeneroPutDTO body, UriComponentsBuilder uriBuilder) {
		ResponseEntity<GeneroGetDTO> ret = ResponseEntity.notFound().build();
		Optional<Genero> search = repository.findById(id);
		if (search.isPresent()) {
			Genero item = search.get();
			boolean found = false;
			Optional<Genero> other = repository.findByDescricao(body.getDescricao());
			if (other.isPresent()) {
				Genero old = other.get();
				if (old.getId()!=item.getId()) {
					found = true; 
					System.out.println("Nome do Genero ja existente"); 
					ret = ResponseEntity.unprocessableEntity().build();
				}
			}
			if (!found) {
				body.update(item);
				URI uri = uriBuilder.path("/genero/{id}").buildAndExpand(item.getId()).toUri();
				ret = ResponseEntity.created(uri).body(new GeneroGetDTO(item));
			}
		} else
			System.out.println("Genero nao encontrado");
		return ret;
	}


@DeleteMapping("/{id}")
@Transactional
public ResponseEntity<?> delete(@PathVariable("id") int id ){
	ResponseEntity<Genero> ret = ResponseEntity.notFound().build();
	Optional<Genero> search = repository.findById(id);
	if(search.isPresent()){
		Genero item = search.get();
		repository.delete(item);
		ret = ResponseEntity.ok().build();
	} else {
		System.out.println("Genero não encontrado");
	}
	return ret;
}

}
