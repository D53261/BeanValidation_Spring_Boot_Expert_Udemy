package com.example.cursoudemy.libraryapi.controller.dto;

import com.example.cursoudemy.libraryapi.models.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

// DTO - Data Transfer Object
// Objeto de transferencia de dados
// Usado para transferir dados entre camadas da aplicacao, evitando expor diretamente as entidades do dominio
public record AutorDTO(
        UUID id,
        @NotBlank( // Validacao: o campo nao pode ser nulo ou vazio, sendo especifico para Strings
                message = "O nome do autor nao pode ser vazio" // Mensagem de erro caso a validacao falhe
        )
        String nome,
        @NotNull ( // Validacao: o campo nao pode ser nulo
                message = "A data de nascimento do autor nao pode ser nula" // Mensagem de erro caso a validacao falhe
        )
        LocalDate dataNascimento,
        @NotBlank( // Validacao: o campo nao pode ser nulo ou vazio, sendo especifico para Strings
                message = "A nacionalidade do autor nao pode ser vazia" // Mensagem de erro caso a validacao falhe
        )
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
