package dev.cfcmagalhaes.exceptions;
public class ListaVaziaException extends RuntimeException
{
    public ListaVaziaException( )
    {
        super( "Turma vazia." );
    }
}