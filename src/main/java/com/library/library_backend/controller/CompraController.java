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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.library.library_backend.dto.CompraGetDTO;
import com.library.library_backend.dto.CompraPostDTO;
import com.library.library_backend.model.Compra;
import com.library.library_backend.repository.ClienteRepository;
import com.library.library_backend.repository.CompraRepository;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/compra")
public class CompraController {
    
    @Autowired
    private CompraRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<CompraGetDTO> get(){
        List<Compra> list = repository.findAll();
        return CompraGetDTO.convert(list);
    }

    @GetMapping("/{notaFiscal}")
    public ResponseEntity<Compra> getnotaFiscal(@PathVariable String notaFiscal){
        ResponseEntity<Compra> ret = ResponseEntity.notFound().build();
        Optional<Compra> search = repository.findByNotaFiscal(notaFiscal);
        if(search.isPresent()){
            Compra item = search.get();
            ret = ResponseEntity.ok(item);
        } else{
            System.out.println("nota Fiscal não encontrada");
        }
        return ret;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompraGetDTO> post (@RequestBody @Valid CompraPostDTO body, UriComponentsBuilder uriBuilder){
        ResponseEntity<CompraGetDTO> ret = ResponseEntity.unprocessableEntity().build();
        Compra item = body.convert(clienteRepository);
        Optional<Compra> search = repository.findByNotaFiscal(item.getNotaFiscal());
        if(!search.isPresent()){
            repository.save(item);
            URI uri = uriBuilder.path("/compra/{id}").buildAndExpand(item.getId()).toUri();
            ret = ResponseEntity.created(uri).body(new CompraGetDTO(item));
        } else {
            System.out.println("Compra ja existe...");
        }
        return ret;
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        ResponseEntity<Compra> ret = ResponseEntity.notFound().build();
        Optional<Compra> search = repository.findById(id);
        if(search.isPresent()){
            Compra item = search.get();
            repository.delete(item);
            ret = ResponseEntity.ok().build();
        }else{
            System.out.println("Compra não encontrada");
        }
        return ret;
    }

}
