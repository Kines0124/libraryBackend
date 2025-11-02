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

import com.library.library_backend.dto.LivroGetDTO;
import com.library.library_backend.dto.LivroPostDTO;
import com.library.library_backend.dto.LivroPutDTO;
import com.library.library_backend.dto.LivroTotalDTO;
import com.library.library_backend.model.Livro;
import com.library.library_backend.repository.GeneroRepository;
import com.library.library_backend.repository.EditoraRepository;
import com.library.library_backend.repository.LivroRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {
    
    @Autowired
    private LivroRepository   repository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping
    public List<LivroGetDTO> get() {
        List<Livro> list = repository.findAll();
        return LivroGetDTO.convert(list);
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<Livro> getByTitulo(@PathVariable String titulo) {
		ResponseEntity<Livro> ret = ResponseEntity.notFound().build();
		Optional<Livro> search = repository.findByTitulo(titulo);
		if (search.isPresent()) {
			Livro item = search.get();
			ret = ResponseEntity.ok(item);
		} else  {
			System.out.println("Livro nao encontrado");
        }

		return ret;
	}

    @PostMapping
    @Transactional
    public ResponseEntity<LivroGetDTO> post(@RequestBody @Valid LivroPostDTO body, UriComponentsBuilder uriBuilder) {
        ResponseEntity<LivroGetDTO> ret = ResponseEntity.unprocessableEntity().build();
        Livro item = body.convert( editoraRepository, generoRepository );
        Optional<Livro> search = repository.findByTitulo(item.getTitulo());
        if (!search.isPresent()) { 
            repository.save(item); 
            URI uri = uriBuilder.path("/livro/{id}").buildAndExpand(item.getId()).toUri();
            ret = ResponseEntity.created(uri).body(new LivroGetDTO(item));
        } else
            System.out.println("Livro ja existe...");
        return ret;
    }

    @PutMapping("/{id}")
    @Transactional
        public ResponseEntity<LivroGetDTO> put(@PathVariable("id") int id, @RequestBody @Valid LivroPutDTO body, UriComponentsBuilder uriBuilder) {
            ResponseEntity<LivroGetDTO> ret = ResponseEntity.notFound().build();
            Optional<Livro> search = repository.findById(id);
            if (search.isPresent()) {
                Livro item = search.get();
                boolean found = false; 
                Optional<Livro> other = repository.findByTitulo(body.getTitulo());
                if (other.isPresent()) {
                    Livro old = other.get();
                    if (old.getId()!=item.getId()) { 
                        found = true; 
                        System.out.println("Nome do Livro ja existente"); 
                        ret = ResponseEntity.unprocessableEntity().build(); 
                    }
                }
                if (!found) { 
                    body.update(item);	
                    URI uri = uriBuilder.path("/livro/{id}").buildAndExpand(item.getId()).toUri(); 
                    ret = ResponseEntity.created(uri).body(new LivroGetDTO(item));
                }
            } else
                System.out.println("Livro nao encontrado");
            return ret;
        }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("id") int id ){
        ResponseEntity<Livro> ret = ResponseEntity.notFound().build();
        Optional<Livro> search = repository.findById(id);
        if(search.isPresent()){
            Livro item = search.get();
            repository.delete(item);
            ret = ResponseEntity.ok().build();
        } else {
            System.out.println("Livro não encontrado");
        }
        return ret;
    }

    @GetMapping("/total")
    public List<LivroTotalDTO> total (){
        List<LivroTotalDTO> l = repository.total();
        return l;
    }

}
