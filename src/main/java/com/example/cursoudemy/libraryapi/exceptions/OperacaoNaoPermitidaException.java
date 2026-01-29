package com.example.cursoudemy.libraryapi.exceptions; // declara o pacote onde a exceção pertence

public class OperacaoNaoPermitidaException extends RuntimeException { // exceção customizada (não verificada) para sinalizar operações proibidas pela regra de negócio;
    // criar exceções personalizadas ajuda a identificar cenários específicos e tratá-los centralizadamente (ex.: em um @ControllerAdvice)

    public OperacaoNaoPermitidaException(String mensagem) { // construtor que aceita uma mensagem descritiva sobre o motivo da exceção
        super(mensagem); // repassa a mensagem para RuntimeException, preservando stacktrace e comportamento padrão da exceção
    } // fim do construtor

} // fim da classe OperacaoNaoPermitidaException
