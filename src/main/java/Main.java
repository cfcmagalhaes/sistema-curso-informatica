import dev.cfcmagalhaes.domain.Aluno;
import dev.cfcmagalhaes.domain.Turma;

public class Main
{
    public static void main( String[] args )
    {
        Turma t = new Turma( "Teste", 10 );

        t.matricularAluno( new Aluno( 10, "Carlos Eduardo"   ) );
        t.matricularAluno( new Aluno( 11, "Pedro Henrique"   ) );
        t.matricularAluno( new Aluno( 12, "Carlos Frederico" ) );
        t.matricularAluno( new Aluno( 13, "Fabiana Sena"     ) );

        t.registrarFalta( 10 );
        t.registrarFalta( 10 );
        t.registrarFalta( 10 );
        t.atribuirMedia( 10, 8.0 ); // NOTA: OK / FALTA: NOK

        t.registrarFalta( 11 );
        t.registrarFalta( 11 );
        t.atribuirMedia( 11, 9.1 ); // NOTA: OK / FALTA: OK

        t.atribuirMedia( 15, 6.5 ); // NOTA: NOK / FALTA: OK

        t.registrarFalta( 13 );
        t.registrarFalta( 13 );
        t.registrarFalta( 13 );
        t.atribuirMedia( 13, 6.2 ); // NOTA: NOK / FALTA: NOK

        t.listarTodos( );
        t.listarAprovados( );

    }
}
