// java
package com.example.cursoudemy.libraryapi.validator; // pacote do validador

import com.example.cursoudemy.libraryapi.exceptions.RegistroDuplicadoException; // exceção personalizada lançada quando há duplicidade
import com.example.cursoudemy.libraryapi.models.Autor; // modelo de domínio Autor
import com.example.cursoudemy.libraryapi.repository.AutorRepository; // repositório para consultar autores
import org.springframework.stereotype.Component; // anotação Spring para tornar a classe um bean gerenciado

import java.util.Optional; // tipo que representa presença/ausência de valor

@Component // registra a classe como componente Spring para injeção de dependência
public class AutorValidator { // validador responsável por regras relacionadas a Autor
    private AutorRepository autorRepository; // repositório usado para verificar existência no banco

    public AutorValidator(AutorRepository autorRepository) { // construtor para injeção do repositório
        this.autorRepository = autorRepository; // atribui o repositório fornecido ao campo da classe
    }

    public void validar(Autor autor) { // método público chamado antes de salvar/atualizar um Autor
        if (existe(autor)) { // verifica se já existe outro autor com mesmo nome, data e nacionalidade
            throw new RegistroDuplicadoException("Autor ja cadastrado no sistema."); // lança exceção específica para sinalizar duplicidade
        }
    }

    private boolean existe(Autor autor) { // método auxiliar que consulta o repositório e decide se existe duplicata
        Optional<Autor> autorEncontrado = autorRepository // realiza consulta no repositório
                .findByNomeAndDataNascimentoAndNacionalidade( // método derivado do Spring Data que busca por três campos
                        autor.getNome(), // parâmetro: nome do autor a ser validado
                        autor.getDataNascimento(), // parâmetro: data de nascimento do autor
                        autor.getNacionalidade() // parâmetro: nacionalidade do autor
                );

        if (autor.getId() == null) { // se o autor não tem id, é um novo registro (create)
            return autorEncontrado.isPresent(); // existe duplicata se a consulta retornou algum resultado
        }

        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent(); // se for update, verifica se o id é diferente do encontrado
    }
}
