package dev.cfcmagalhaes.domain;

public class Aluno
{
    private Integer codigo;
    private String  nome;
    private Double  mediaFinal;
    private Integer qtdFaltas;

    public Aluno( Integer codigo, String nome )
    {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getCodigo( ) {
        return codigo;
    }

    public String getNome( ) {
        return nome;
    }

    public Double getMediaFinal( ) {
        return mediaFinal;
    }

    public Integer getQtdFaltas( ) {
        return qtdFaltas;
    }

    public void adicionaFalta( )
    {
        qtdFaltas += 1;
    }

    public void iniciaQtdFaltas( )
    {
        qtdFaltas = 0;
    }

    public void iniciaMediaFinal( )
    {
        this.mediaFinal = 0.0;
    }

    public boolean setMediaFinal( Double mediaFinal )
    {
        if( mediaFinal >= 0.0 && mediaFinal <= 10.0 )
        {
            this.mediaFinal = mediaFinal;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "Cod: "          + codigo +
                ", Nome: '"      + nome + '\'' +
                ", mediaFinal: " + mediaFinal +
                ", qtdFaltas: " + qtdFaltas;
    }
}
