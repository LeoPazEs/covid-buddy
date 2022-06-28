import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class PacienteTest {
    private GerenciadorDeConexao conexaoBanco;

    @BeforeEach
    void setup() throws SQLException{
        conexaoBanco = new GerenciadorDeConexao("jdbc:sqlite:/home/leopazes/projects/covid-buddy/test/resources/test123.db");
        Modelo.setGerenciadorDeConexao(conexaoBanco); 
        conexaoBanco.execute("DROP TABLE IF EXISTS paciente");
        conexaoBanco.execute("CREATE TABLE IF NOT EXISTS paciente(id INTEGER PRIMARY KEY autoincrement, cpf TEXT, senha TEXT, nome TEXT, dataNascimento TEXT, telefone TEXT, sexo TEXT)");
        
    } 

    @AfterEach
    void cleanUp(){
        conexaoBanco.close();
    }
    
    @Test
    public void criandoPacienteCpfInvalido(){
        new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");

        Throwable cpfInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Paciente("123.456.789.10", "1234", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"));
        Assertions.assertEquals( "CPF inválido.", cpfInvalido.getMessage());

        cpfInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Paciente("123.456.789-12", "1234", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"));
        Assertions.assertEquals( "CPF inválido.", cpfInvalido.getMessage());

        cpfInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Paciente("777.777.777-77", "1234", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"));
        Assertions.assertEquals( "CPF inválido.", cpfInvalido.getMessage());
    }

    @Test
    public void criandoPacienteNomeInvalido(){
        Throwable nomeInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Paciente("529.982.247-25", "12345", "L3onardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"));
        Assertions.assertEquals( "Nome inválido.", nomeInvalido.getMessage()); 
        new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");
    } 

    @Test
    public void criandoPacienteTelefoneInvalido(){
        Throwable telefoneInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99192696", "Masculino"));
        Assertions.assertEquals( "Telefone inválido.", telefoneInvalido.getMessage()); 

        telefoneInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)9999192696", "Masculino"));
        Assertions.assertEquals( "Telefone inválido.", telefoneInvalido.getMessage()); 

        telefoneInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)999192696", "Masculino"));
        Assertions.assertEquals( "Telefone inválido.", telefoneInvalido.getMessage()); 

        telefoneInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "8299919-2696", "Masculino"));
        Assertions.assertEquals( "Telefone inválido.", telefoneInvalido.getMessage()); 

        new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");
    } 

    @Test
    public void criandoPacienteDataNascimentoInvalido(){
        Throwable nomeInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(LocalDate.now().getYear(), 4, 5), "(82)99919-2696", "Masculino"));
        Assertions.assertEquals( "Data de nascimento inválida.", nomeInvalido.getMessage()); 

        new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");
    }   

    @Test
    public void save() throws SQLException{
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        paciente.save();
        
        ResultSet result = conexaoBanco.executeResultSet("SELECT COUNT(*) AS total FROM paciente"); 
        Assertions.assertEquals(1, result.getInt("total"));

        result = conexaoBanco.executeResultSet("SELECT * FROM paciente WHERE id = 1");  
        Assertions.assertEquals(paciente.getId(), result.getInt("id"));
        Assertions.assertEquals(paciente.getCpf(), result.getString("cpf"));
        Assertions.assertEquals(paciente.getSenha(), result.getString("senha"));
        Assertions.assertEquals(paciente.getNome(), result.getString("nome"));
        Assertions.assertEquals(paciente.getDataNascimento().toString(), result.getString("dataNascimento")); 
        Assertions.assertEquals(paciente.getSexo(), result.getString("sexo")); 
        Assertions.assertEquals(paciente.getTelefone(), result.getString("telefone")); 
    } 

    @Test
    public void select(){
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        paciente.save();

        Paciente paciente2 = new Paciente("529.982.247-25", "12345", "Valdemiro Santiago",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        paciente2.save(); 

        Assertions.assertEquals(2, Paciente.select().size()); 

        Paciente pacienteFromSelect = Paciente.select("nome = 'Leonardo Paz Estevam' ").get(0); 

        Assertions.assertEquals(paciente.getId(), pacienteFromSelect.getId());
        Assertions.assertEquals(paciente.getCpf(), pacienteFromSelect.getCpf());
        Assertions.assertEquals(paciente.getSenha(), pacienteFromSelect.getSenha());
        Assertions.assertEquals(paciente.getNome(), pacienteFromSelect.getNome());
        Assertions.assertEquals(paciente.getDataNascimento(), pacienteFromSelect.getDataNascimento()); 
        Assertions.assertEquals(paciente.getSexo(), pacienteFromSelect.getSexo()); 
        Assertions.assertEquals(paciente.getTelefone(), pacienteFromSelect.getTelefone()); 

    }

}
