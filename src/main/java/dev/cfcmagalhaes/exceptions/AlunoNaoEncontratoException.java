package dev.cfcmagalhaes.exceptions;

public class AlunoNaoEncontratoException extends RuntimeException
{
    public AlunoNaoEncontratoException( Integer codigo )
    {
        super( "Aluno com codigo " + codigo + " não está matriculado." );
    }
}
