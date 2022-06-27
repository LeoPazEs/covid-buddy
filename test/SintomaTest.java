import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class SintomaTest {
    private GerenciadorDeConexao conexaoBanco;

    @BeforeEach
    void setup() throws SQLException{
        conexaoBanco = new GerenciadorDeConexao("jdbc:sqlite:/home/leopazes/projects/covid-buddy/test/resources/test123.db");
        Modelo.setGerenciadorDeConexao(conexaoBanco); 
        conexaoBanco.execute("DROP TABLE IF EXISTS paciente");
        conexaoBanco.execute("CREATE TABLE IF NOT EXISTS paciente(id INTEGER PRIMARY KEY autoincrement, cpf TEXT, senha TEXT, nome TEXT, dataNascimento TEXT, telefone TEXT, sexo TEXT)");
        conexaoBanco.execute("DROP TABLE IF EXISTS sintoma");
        conexaoBanco.execute("CREATE TABLE IF NOT EXISTS sintoma(id INTEGER PRIMARY KEY autoincrement, usuario INTEGER, nome TEXT, gravidade TEXT)");
        
    } 

    @AfterEach
    void cleanUp(){
        conexaoBanco.close();
    }

    @Test 
    public void criandoSintomaComGravidadeInvalida(){
        Paciente usuario = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");
        usuario.save();

        Throwable gravidadeInvalida =  Assertions.assertThrows(IllegalArgumentException.class, () -> new Sintoma(usuario, "Tosse", 'M'));
        Assertions.assertEquals( "Gravidade inválida.", gravidadeInvalida.getMessage()); 

        new Sintoma(usuario, "Tosse", 'G');
        new Sintoma(usuario, "Tosse", 'l');
    } 

    @Test 
    void save() throws SQLException{
        Paciente usuario = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");
        usuario.save(); 

        Sintoma sintoma = new Sintoma(usuario, "Tosse", 'G');
        sintoma.save(); 

        ResultSet result = conexaoBanco.executeResultSet("SELECT COUNT(*) AS total FROM sintoma"); 
        Assertions.assertEquals(1, result.getInt("total"));

        result = conexaoBanco.executeResultSet("SELECT * FROM sintoma WHERE id = 1");  
        Assertions.assertEquals(sintoma.getId(), result.getInt("id"));
        Assertions.assertEquals(sintoma.getUsuario().getId(), result.getInt("usuario"));
        Assertions.assertEquals(sintoma.getNome(), result.getString("nome"));
        Assertions.assertEquals(sintoma.getGravidade().toString(), result.getString("gravidade"));        
    }
}
