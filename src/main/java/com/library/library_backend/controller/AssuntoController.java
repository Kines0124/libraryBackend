package com.library.library_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library_backend.dto.AssuntoGetDTO;
import com.library.library_backend.model.Assunto;
import com.library.library_backend.repository.AssuntoRepository;

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




}
