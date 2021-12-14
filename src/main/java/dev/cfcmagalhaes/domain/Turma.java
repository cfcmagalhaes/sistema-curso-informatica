package dev.cfcmagalhaes.domain;

import dev.cfcmagalhaes.exceptions.AlunoExistenteException;
import dev.cfcmagalhaes.exceptions.AlunoNaoEncontratoException;
import dev.cfcmagalhaes.exceptions.LimiteExcedidoException;
import dev.cfcmagalhaes.exceptions.ListaVaziaException;

public class Turma
{
    public static final Integer limiteAlunos = 20;
    private String  nomeCurso;
    private Integer totalAulas;
    private Aluno[] listaAlunos;

    public Turma( String nomeCurso, Integer totalAulas )
    {
        this.nomeCurso   = nomeCurso;
        this.totalAulas  = totalAulas;
        this.listaAlunos = new Aluno[this.limiteAlunos];
    }

    public void matricularAluno( Aluno aluno )
    {
        // Testar Limite
        if( taCheia( ) )
            throw new LimiteExcedidoException( );

        // Testar se já está na lista (codigoAluno)
        if( this.buscarAluno( aluno ) )
            throw new AlunoExistenteException( "Aluno com o codigo " + aluno.getCodigo( ) + " já consta na lista.");

        // SetNota = 0 e setFaltas = 0
        for( int i = 0; i < this.limiteAlunos; i++ )
        {
            if( this.listaAlunos[i] == null)
            {
                this.listaAlunos[i] = aluno;
                this.listaAlunos[i].iniciaMediaFinal( );
                this.listaAlunos[i].iniciaQtdFaltas( );
                return;
            }
        }
    }

    public Integer buscarAluno( Integer codigo )
    {
        if( !this.taVazia( ) )
        {
            for( int i = 0; i < this.limiteAlunos; i++ )
            {
                if( this.listaAlunos[i] != null && this.listaAlunos[i].getCodigo( ) == codigo ) {
                    return i;
                }
            }
            return -1;
        }

        throw new ListaVaziaException( );
    }

    public void registrarFalta( Integer codigo )
    {
        if( !taVazia( ) )
        {
            Integer indice = buscarAluno( codigo );

            if( indice != -1 )
            {
                this.listaAlunos[indice].adicionaFalta( );
                return;
            }

            throw new AlunoNaoEncontratoException( codigo );
        }

        throw new ListaVaziaException( );
    }

    public void atribuirMedia( Integer codigo, Double media )
    {
        if( !taVazia( ) )
        {
            Integer indice = buscarAluno( codigo );

            if( indice != -1 )
            {
                if( media >= 0.0 && media <= 10.0 )
                {
                    this.listaAlunos[indice].setMediaFinal( media );
                    return;
                }
                else
                {
                    System.out.println( "Média ignorada: valor precisa estar entre 0.0 e 10.0." );
                    return;
                }
            }

            throw new AlunoNaoEncontratoException( codigo );

        }

        throw new ListaVaziaException( );
    }

    public void listarTodos( )
    {
        this.imprimirLista( this.listaAlunos );
    }

    public void listaAprovados( )
    {
        Aluno[] lista = new Aluno[limiteAlunos];

        int i = 0;
        int j = 0;

        while( this.listaAlunos[i] != null )
        {
            if( this.listaAlunos[i].getMediaFinal( ) >= 6.0 && calculaFrequencia( this.listaAlunos[i] ) > 75 )
            {
                lista[j] = this.listaAlunos[i];
                j++;
            }

            i++;
        }

        this.imprimirLista( lista );
    }

    private boolean buscarAluno( Aluno aluno )
    {
        if( !this.taVazia( ) )
        {
            for( int i = 0; i < this.limiteAlunos; i++ )
            {
                if( this.listaAlunos[i] != null && this.listaAlunos[i].getCodigo( ) == aluno.getCodigo( ) ) {
                    return true;
                }
            }
            return false;
        }

        return false;
    }

    private boolean taCheia( )
    {
        if( this.listaAlunos[limiteAlunos - 1] == null )
            return false;

        return true;
    }

    private boolean taVazia( )
    {
        if( this.listaAlunos[0] == null )
            return true;

        return false;
    }

    private Integer calculaFrequencia( Aluno aluno )
    {
        Integer presenca = this.totalAulas - aluno.getQtdFaltas( );

        return ( presenca * 100 ) / this.totalAulas;
    }

    private void imprimirLista( Aluno[] lista )
    {
        String saida = "Curso: " + this.nomeCurso + "\n" +
                "-------------------------------------------\n";

        int i = 0;

        while( lista[i] != null )
        {
            saida += lista[i].toString( ) + ", presença: " + this.calculaFrequencia( lista[i] ) + "%}\n";
            i++;
        }

        saida += "-------------------------------------------\n ";

        System.out.println( saida );
    }
}
