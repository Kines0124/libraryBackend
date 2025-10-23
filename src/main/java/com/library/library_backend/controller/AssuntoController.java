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

import com.library.library_backend.dto.AssuntoGetDTO;
import com.library.library_backend.dto.AssuntoPostDTO;
import com.library.library_backend.dto.AssuntoPutDTO;
import com.library.library_backend.model.Assunto;
import com.library.library_backend.repository.AssuntoRepository;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/assunto")
public class AssuntoController {
    
@Autowired
private AssuntoRepository repository;

//Esse aqui não faz nada, só retorna todos os generos q tem
@GetMapping
public List<AssuntoGetDTO> get() {
    List<Assunto> list = repository.findAll();
    return AssuntoGetDTO.convert(list);
}


@PostMapping
@Transactional
public ResponseEntity<AssuntoGetDTO> post(@RequestBody @Valid AssuntoPostDTO body, UriComponentsBuilder uriBuilder) {
	ResponseEntity<AssuntoGetDTO> ret = ResponseEntity.unprocessableEntity().build(); //Mensagem de erro padrao caso de problema
	Assunto item = body.convert(); //Recebe um body do tipo AssuntoPostDTO e transforma em um model Assunto
	Optional<Assunto> search = repository.findByDescricao(item.getDescricao()); // Faz uma busca no banco, baseada na descrição 
	if (!search.isPresent()) { // Verifica se a busca nao encontrou nada
		repository.save(item); // Salva o novo model enviado
		URI uri = uriBuilder.path("/assunto/{id}").buildAndExpand(item.getId()).toUri(); // Cria uma uri para o novo item
		ret = ResponseEntity.created(uri).body(new AssuntoGetDTO(item)); // Retorna q foi criado
	} else
		System.out.println("Assunto ja existe...");
	return ret;
}

@PutMapping("/{id}")
@Transactional
	public ResponseEntity<AssuntoGetDTO> put(@PathVariable("id") int id, @RequestBody @Valid AssuntoPutDTO body, UriComponentsBuilder uriBuilder) {
		ResponseEntity<AssuntoGetDTO> ret = ResponseEntity.notFound().build(); // Mensagem de erro padrao caso não caia nas condicionais
		Optional<Assunto> search = repository.findById(id); // Busca no banco um Assunto com o id passado
		if (search.isPresent()) { //Verifica se foi encontrado
			Assunto item = search.get(); // Cria um Assunto com os valores encontrados no banco
			boolean found = false; // Verificação para caso exista outro assunto com a mesma descricao
			Optional<Assunto> other = repository.findByDescricao(body.getDescricao());  // Busca oturo assunto com a mesma descrição do body
			if (other.isPresent()) { // Verifica se existe outro Assunto com a mesma descrição do body
				Assunto old = other.get(); // Se existe instancia um modelo Assunto para ele
				if (old.getId()!=item.getId()) { // Verifica se tem ids diferentes 
					found = true;  // Muda o found para dizer que existe outro id com a mesma descrição
					System.out.println("Nome do Assunto ja existente"); 
					ret = ResponseEntity.unprocessableEntity().build(); // Retorna mensagem de erro
				}
			}
			if (!found) { // Verificação se não tem outro assunto igual
				body.update(item);	//atualiza os campos de item com os valores recebidos
				URI uri = uriBuilder.path("/assunto/{id}").buildAndExpand(item.getId()).toUri(); // Cria uma uri para o novo item
				ret = ResponseEntity.created(uri).body(new AssuntoGetDTO(item)); // Define ret como a resposta dizendo q foi criado + body atualizado
			}
		} else
			System.out.println("Assunto nao encontrado");
		return ret;
	}


@DeleteMapping("/{id}")
@Transactional
public ResponseEntity<?> delete(@PathVariable("id") int id ){
	ResponseEntity<Assunto> ret = ResponseEntity.notFound().build();
	Optional<Assunto> search = repository.findById(id);
	if(search.isPresent()){
		Assunto item = search.get();
		repository.delete(item);
		ret = ResponseEntity.ok().build();
	} else {
		System.out.println("Assunto não encontrado");
	}
	return ret;
}

}
