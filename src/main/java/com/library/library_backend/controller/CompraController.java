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
import com.library.library_backend.dto.ItemCompraPostDTO;
import com.library.library_backend.model.Compra;
import com.library.library_backend.model.CompraLivro;
import com.library.library_backend.model.Livro;
import com.library.library_backend.repository.ClienteRepository;
import com.library.library_backend.repository.CompraLivroRepository;
import com.library.library_backend.repository.CompraRepository;
import com.library.library_backend.repository.LivroRepository;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/compra")
public class CompraController {
    
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CompraLivroRepository compraLivroRepository;

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
        Compra compra = body.convert(clienteRepository);
        compra = repository.save(compra);

        for (ItemCompraPostDTO itemDto : body.getItens()){
            Livro livro = livroRepository.findById(itemDto.getLivroId())
            .orElseThrow(() -> new IllegalArgumentException("Livro com ID " + itemDto.getLivroId() + "não encontrado"));

            CompraLivro compraLivro = new CompraLivro();

            //Essa é a relação de unico livro por compra
            compraLivro.setCompra(compra);
            compraLivro.setLivro(livro);

            compraLivro.setQuantidade(itemDto.getQuantidade());
            compraLivro.setPreco_unitario(livro.getPreco());

            compraLivroRepository.save(compraLivro);

        }

            Float valorTotal = compraLivroRepository.calcularValorTotal(compra.getId()).orElse(0.0f);

            compra.setValorPago(valorTotal);
            repository.save(compra);

            URI uri = uriBuilder.path("/compra/{id}").buildAndExpand(compra.getId()).toUri();
            return ResponseEntity.created(uri).body(new CompraGetDTO(compra));
    }


    @DeleteMapping("/{notaFiscal}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("notaFiscal") String notaFiscal){
        ResponseEntity<Compra> ret = ResponseEntity.notFound().build();
        List<Compra> comprasParaDeletar = repository.findAllByNotaFiscal(notaFiscal);
        if(!comprasParaDeletar.isEmpty()){
            for (Compra compra : comprasParaDeletar){
                compraLivroRepository.deleteAllByCompraId(compra.getId());
            }
            repository.deleteAll(comprasParaDeletar);
            return ResponseEntity.ok().build();
        } else{
            ret = ResponseEntity.notFound().build();
        }
        
        return ret;
    }

}
