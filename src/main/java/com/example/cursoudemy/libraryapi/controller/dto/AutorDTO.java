package com.example.cursoudemy.libraryapi.controller.dto;

import com.example.cursoudemy.libraryapi.models.Autor;

import java.time.LocalDate;

// DTO - Data Transfer Object
// Objeto de transferencia de dados
// Usado para transferir dados entre camadas da aplicacao, evitando expor diretamente as entidades do dominio
public record AutorDTO(String nome,
                       LocalDate dataNascimento,
                       String nacionalidade) {
    // Mapeia o DTO para a entidade Autor
    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
