package dev.cfcmagalhaes.exceptions;

import dev.cfcmagalhaes.domain.Turma;

public class LimiteExcedidoException extends RuntimeException
{
    public LimiteExcedidoException( )
    {
        super( "Limite de " + Turma.limiteAlunos+ " alunos excedido!" );
    }
}
