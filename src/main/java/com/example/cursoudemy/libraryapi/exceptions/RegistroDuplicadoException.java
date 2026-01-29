package com.example.cursoudemy.libraryapi.exceptions; // pacote onde a exceção está localizada

public class RegistroDuplicadoException extends RuntimeException{ // exceção personalizada; estende RuntimeException para ser unchecked (não obriga try/catch)
    public RegistroDuplicadoException(String mensagem) { // construtor que recebe uma mensagem descritiva sobre o erro de duplicidade
        super(mensagem); // encaminha a mensagem para a superclasse (disponível via getMessage())
    } // final do construtor
} // final da classe
