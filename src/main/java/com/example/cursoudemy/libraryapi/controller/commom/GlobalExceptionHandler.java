package com.example.cursoudemy.libraryapi.controller.commom;

import com.example.cursoudemy.libraryapi.controller.dto.ErroCampo;
import com.example.cursoudemy.libraryapi.controller.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrors.stream().map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação", listaErros);
    }

    /**
     * EXPLICANDO MELHOR O CÓDIGO ACIMA:
     *
     * @RestControllerAdvice: Esta anotação indica que a classe é um "conselheiro" global para controladores REST. Ela permite capturar e tratar exceções lançadas por qualquer controlador REST na aplicação.
     *
     * @ExceptionHandler(MethodArgumentNotValidException.class): Esta anotação especifica que o metodo handleMethodArgumentNotValidException deve ser invocado sempre que uma exceção do tipo MethodArgumentNotValidException for lançada. Essa exceção é comum quando há falhas de validação em argumentos de métodos, como quando um DTO não atende às restrições definidas.
     *
     * @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY): Esta anotação define o status HTTP que será retornado na resposta quando a exceção for tratada. No caso, o status 422 Unprocessable Entity indica que a requisição foi bem formada, mas não pôde ser processada devido a erros de validação.
     *
     * public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e): Este é o metodo que trata a exceção. Ele recebe a exceção como parâmetro e retorna um objeto do tipo ErroResposta, que encapsula as informações sobre o erro.
     *
     * List<FieldError> fieldErrors = e.getFieldErrors();: Aqui, o metodo obtém a lista de FieldErrors da exceção. Cada FieldError representa um erro específico de validação em um campo individual.
     *
     * List<ErroCampo> listaErros = fieldErrors.stream().map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());: Este trecho mapeia cada FieldError para um objeto ErroCampo personalizado, que contém o nome do campo que falhou na validação e a mensagem de erro associada. O resultado é uma lista de ErroCampo.
     *
     * return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação", listaErros);: Finalmente, o metodo retorna um objeto ErroResposta que inclui o status HTTP 422, uma mensagem geral de erro ("Erro de Validação") e a lista detalhada de erros de campo.
     */
}
