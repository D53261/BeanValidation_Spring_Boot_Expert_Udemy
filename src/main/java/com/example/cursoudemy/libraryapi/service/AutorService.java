package com.example.cursoudemy.libraryapi.service;

import com.example.cursoudemy.libraryapi.exceptions.OperacaoNaoPermitidaException;
import com.example.cursoudemy.libraryapi.models.Autor;
import com.example.cursoudemy.libraryapi.repository.AutorRepository;
import com.example.cursoudemy.libraryapi.repository.LivroRepository;
import com.example.cursoudemy.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Servico para gerenciar operacoes relacionadas a Autor
@Service
@RequiredArgsConstructor // Gera um construtor com argumentos para todos os campos finais (final) com Lombok
public class AutorService {
    // Injeta o AutorRepository via construtor
    private final AutorRepository repository;

    private final AutorValidator validator;

    private final LivroRepository livroRepository;

    // Salva um autor no banco de dados
    public Autor salvar(Autor autor) {
        validator.validar(autor);  // Valida o autor antes de salvar
        // Usa o repositório para salvar o autor e retorna a entidade salva
        return repository.save(autor);
    }

    // Atualizar um autor já existente no banco de dados
    public void atualizar(Autor autor) {
        if (autor.getId() == null) { // Verifica se o ID do autor é nulo
            throw new IllegalArgumentException("O ID do autor não pode ser nulo para atualização.");  // Lança uma exceção se for nulo
        }
        validator.validar(autor);  // Valida o autor antes de atualizar
        // Usa o repositório para atualizar o autor
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }  // Obtém um autor pelo ID

    public void deletar(Autor autor) {
        if (possuiLivro(autor)) { // Verifica se o autor possui livros cadastrados
            throw new OperacaoNaoPermitidaException("Não é permitido exluir um Autor que possui livros cadastrados!");
        }
        repository.delete(autor);
    }  // Deleta um autor do banco de dados

    public List<Autor> pesquisa(String nome, String nacionalidade) {  // Pesquisa autores por nome e/ou nacionalidade
        if (nome != null && nacionalidade != null) {  // Se ambos os parâmetros forem fornecidos
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);  // Pesquisa por ambos
        }

        if (nome != null) {  // Se apenas o nome for fornecido
            return repository.findByNome(nome);  // Pesquisa por nome
        }

        if (nacionalidade != null) {  // Se apenas a nacionalidade for fornecida
            return repository.findByNacionalidade(nacionalidade);  // Pesquisa por nacionalidade
        }

        return repository.findAll();  // Se nenhum parâmetro for fornecido, retorna todos os autores
    }

    public boolean possuiLivro(Autor autor) { // Verifica se o autor possui livros cadastrados
        return livroRepository.existsByAutor(autor);
    }
}
