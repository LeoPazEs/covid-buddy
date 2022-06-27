import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class EnderecoTest {
    private GerenciadorDeConexao conexaoBanco;

    @BeforeEach
    void setup() throws SQLException{
        conexaoBanco = new GerenciadorDeConexao("jdbc:sqlite:/home/leopazes/projects/covid-buddy/test/resources/test123.db");
        Modelo.setGerenciadorDeConexao(conexaoBanco); 
        conexaoBanco.execute("DROP TABLE IF EXISTS paciente");
        conexaoBanco.execute("CREATE TABLE IF NOT EXISTS paciente(id INTEGER PRIMARY KEY autoincrement, cpf TEXT, senha TEXT, nome TEXT, dataNascimento TEXT, telefone TEXT, sexo TEXT)");
        conexaoBanco.execute("DROP TABLE IF EXISTS endereco");
        conexaoBanco.execute("CREATE TABLE IF NOT EXISTS endereco(id INTEGER PRIMARY KEY autoincrement, usuario INTEGER, cep TEXT, numero TEXT, cidade TEXT, estado TEXT, referencia TEXT, logradouro TEXT, complemento TEXT)");
    } 

    @AfterEach
    void cleanUp(){
        conexaoBanco.close();
    }
    
    @Test
    public void criandoEnderecoCepInvalido(){
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        paciente.save();

        Throwable cepInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Endereco(paciente, "57051190", 384, "Ap 1111 Bloco 2", "Maceió", "Alagoas", "Ao lado do marista", "Av Governador"));
        Assertions.assertEquals( "CEP inválido.", cepInvalido.getMessage());  

        cepInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Endereco(paciente, "7051-190", 384, "Ap 1111 Bloco 2", "Maceió", "Alagoas", "Ao lado do marista", "Av Governador"));
        Assertions.assertEquals( "CEP inválido.", cepInvalido.getMessage());  

        cepInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Endereco(paciente, "57051-1190", 384, "Ap 1111 Bloco 2", "Maceió", "Alagoas", "Ao lado do marista", "Av Governador"));
        Assertions.assertEquals( "CEP inválido.", cepInvalido.getMessage());  
        
        new Endereco(paciente, "57051-190", 384, "Ap 1111 Bloco 2", "Maceió", "Alagoas", "Ao lado do marista", "Av Governador");
    } 

    @Test 
    void save() throws SQLException{
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        paciente.save();

        Endereco endereco = new Endereco(paciente, "57051-190", 384, "Ap 1111 Bloco 2", "Maceió", "Alagoas", "Ao lado do marista", "Av Governador");
        endereco.save();
    
        ResultSet result = conexaoBanco.executeResultSet("SELECT COUNT(*) AS total FROM endereco"); 
        Assertions.assertEquals(1, result.getInt("total"));

        result = conexaoBanco.executeResultSet("SELECT * FROM endereco WHERE id = 1");  
        Assertions.assertEquals(endereco.getId(), result.getInt("id"));
        Assertions.assertEquals(endereco.getUsuario().getId(), result.getInt("usuario"));
        Assertions.assertEquals(endereco.getCep(), result.getString("cep"));
        Assertions.assertEquals(endereco.getNumero(), result.getInt("numero"));
        Assertions.assertEquals(endereco.getComplemento(), result.getString("complemento")); 
        Assertions.assertEquals(endereco.getCidade(), result.getString("cidade")); 
        Assertions.assertEquals(endereco.getEstado(), result.getString("estado")); 
        Assertions.assertEquals(endereco.getReferencia(), result.getString("referencia")); 
        Assertions.assertEquals(endereco.getLogradouro(), result.getString("logradouro")); 
    }
}
