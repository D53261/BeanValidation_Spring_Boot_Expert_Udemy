package com.example.cursoudemy.libraryapi.service;

import com.example.cursoudemy.libraryapi.models.Autor;
import com.example.cursoudemy.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

// Servico para gerenciar operacoes relacionadas a Autor
@Service
public class AutorService {
    // Injeta o AutorRepository via construtor
    private final AutorRepository repository;

    // Construtor para injeção de dependência
    public AutorService(AutorRepository repository) {
        // Atribui o repositório injetado ao campo da classe
        this.repository = repository;
    }

    // Salva um autor no banco de dados
    public Autor salvar(Autor autor) {
        // Usa o repositório para salvar o autor e retorna a entidade salva
        return repository.save(autor);
    }
}
