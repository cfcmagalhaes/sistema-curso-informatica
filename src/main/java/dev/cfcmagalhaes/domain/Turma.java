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
        if( listaEstaCheia( ) )
            throw new LimiteExcedidoException( );

        if( buscarAluno( aluno.getCodigo( ) ) != null )
            throw new AlunoExistenteException( "Aluno com o codigo " + aluno.getCodigo( ) + " já consta na lista.");

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

    public Integer buscarIndexAluno( Integer codigo )
    {
        if( this.listaNaoEstaVazia( ) )
        {
            for( int i = 0; i < this.limiteAlunos; i++ )
            {
                if( this.listaAlunos[i] != null && this.listaAlunos[i].getCodigo() == codigo )
                    return i;
            }

            return -1;
        }

        throw new ListaVaziaException( );
    }

    public Aluno buscarAluno( Integer codigo )
    {
        for( int i = 0; i < this.limiteAlunos; i++ )
        {
            if( this.listaAlunos[i] != null && this.listaAlunos[i].getCodigo( ) == codigo ) {
                return this.listaAlunos[i];
            }
        }

        return null;
    }

    public void registrarFalta( Integer codigo )
    {
        if( listaNaoEstaVazia( ) && alunoExiste( codigo ) )
        {
            this.listaAlunos[buscarIndexAluno( codigo )].adicionaFalta( );
            return;
        }

        throw new AlunoNaoEncontratoException(codigo);
    }

    public void atribuirMedia( Integer codigo, Double media )
    {
        if( listaNaoEstaVazia( ) && alunoExiste( codigo ) )
        {
           if( this.listaAlunos[buscarIndexAluno( codigo )].setMediaFinal( media ) )
               System.out.println( "Média adicionada.");
           else
               System.out.println( "Média ignorada: valor precisa estar entre 0.0 e 10.0." );

        return;
        }

        throw new AlunoNaoEncontratoException( codigo );
    }

    public void listarTodos( )
    {
        this.imprimirLista( this.listaAlunos );
    }

    public void listarAprovados( )
    {
        Aluno[] lista = new Aluno[limiteAlunos];

        int i = 0;
        int j = 0;

        while( this.listaAlunos[i] != null )
        {
            if( this.listaAlunos[i].getMediaFinal( ) >= 6.0 && calcularFrequencia( this.listaAlunos[i] ) > 75 )
            {
                lista[j] = this.listaAlunos[i];
                j++;
            }

            i++;
        }

        this.imprimirLista( lista );
    }

    private boolean alunoExiste( Integer codigo )
    {
        if( buscarIndexAluno( codigo ) != -1 )
            return true;

        return false;
    }

    private boolean listaEstaCheia( )
    {
        if( this.listaAlunos[limiteAlunos - 1] == null )
            return false;

        return true;
    }

    private boolean listaNaoEstaVazia( )
    {
        if( this.listaAlunos[0] == null )
            return false;

        return true;
    }

    private Integer calcularFrequencia(Aluno aluno )
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
            saida += lista[i].toString( ) + ", presença: " + this.calcularFrequencia( lista[i] ) + "%}\n";
            i++;
        }

        saida += "-------------------------------------------\n ";

        System.out.println( saida );
    }
}
