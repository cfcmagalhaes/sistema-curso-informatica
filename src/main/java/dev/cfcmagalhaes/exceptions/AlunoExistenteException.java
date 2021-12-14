package dev.cfcmagalhaes.exceptions;

public class AlunoExistenteException extends RuntimeException
{
    public AlunoExistenteException( String mensagem )
    {
        super( mensagem );
    }
}
