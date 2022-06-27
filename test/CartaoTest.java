import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class CartaoTest {
    private GerenciadorDeConexao conexaoBanco;

    @BeforeEach
    void setup() throws SQLException{
        conexaoBanco = new GerenciadorDeConexao("jdbc:sqlite:/home/leopazes/projects/covid-buddy/test/resources/test123.db");
        Modelo.setGerenciadorDeConexao(conexaoBanco); 
        conexaoBanco.execute("DROP TABLE IF EXISTS visa");
        conexaoBanco.execute("CREATE TABLE IF NOT EXISTS visa(id INTEGER PRIMARY KEY autoincrement, pacienteId INTEGER, numero TEXT, codigo TEXT, nome TEXT, validade TEXT)"); 
        conexaoBanco.execute("DROP TABLE IF EXISTS mastercard");
        conexaoBanco.execute("CREATE TABLE IF NOT EXISTS mastercard(id INTEGER PRIMARY KEY autoincrement, pacienteId INTEGER, numero TEXT, codigo TEXT, nome TEXT, validade TEXT)");
        conexaoBanco.execute("DROP TABLE IF EXISTS paciente");
        conexaoBanco.execute("CREATE TABLE IF NOT EXISTS paciente(id INTEGER PRIMARY KEY autoincrement, cpf TEXT, senha TEXT, nome TEXT, dataNascimento TEXT, telefone TEXT, sexo TEXT)");
    } 

    @AfterEach
    void cleanUp(){
        conexaoBanco.close();
    }

    @Test 
    public void criandoVisaComNumeroInvalido(){
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        new Visa(paciente, "4242 4242 4242 4242", "342", "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1)));

        Throwable numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Visa(paciente, "424 4242 4242 4242", "342", "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1))));
        Assertions.assertEquals( "Número inválido.", numeroInvalido.getMessage()); 

        numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Visa(paciente, "4244 4242 4242 4242", "342", "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1))));
        Assertions.assertEquals( "Número inválido.", numeroInvalido.getMessage());
        
    }

    @Test 
    public void criandoMasterComNumeroInvalido(){
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        new MasterCard(paciente, "5555 5555 5555 4444", "342", "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1)));

        Throwable numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new MasterCard(paciente, "424 4242 4242 4242", "342", "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1))));
        Assertions.assertEquals( "Número inválido.", numeroInvalido.getMessage()); 

        numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new MasterCard(paciente, "5556 5555 5555 4444", "342", "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1))));
        Assertions.assertEquals( "Número inválido.", numeroInvalido.getMessage());
    } 

    @Test 
    public void criandoCartaoComValidadeInvalida(){
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        new Visa(paciente, "4242 4242 4242 4242", "342", "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1)));

        Throwable numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Visa(paciente, "4242 4242 4242 4242", "342", "Leonardo Paz Estevam", YearMonth.from(LocalDate.now())));
        Assertions.assertEquals( "Validade inválida.", numeroInvalido.getMessage()); 

        numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Visa(paciente, "4242 4242 4242 4242", "342", "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().minusMonths(1))));
        Assertions.assertEquals( "Validade inválida.", numeroInvalido.getMessage());
    } 

    @Test 
    public void save() throws SQLException{
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        paciente.save();
        Visa visa = new Visa(paciente, "4242 4242 4242 4242", "342", "Cartão Débito", YearMonth.from(LocalDate.now().plusMonths(1))); 
        visa.save(); 
        
        ResultSet result = conexaoBanco.executeResultSet("SELECT COUNT(*) AS total FROM visa"); 
        Assertions.assertEquals(1, result.getInt("total"));

        result = conexaoBanco.executeResultSet("SELECT * FROM visa WHERE id = 1");  
        Assertions.assertEquals(1, result.getInt("id"));
        Assertions.assertEquals(1, result.getInt("pacienteId"));
        Assertions.assertEquals("4242 4242 4242 4242", result.getString("numero"));
        Assertions.assertEquals("342", result.getString("codigo"));
        Assertions.assertEquals("Cartão Débito", result.getString("nome")); 
        Assertions.assertEquals(YearMonth.from(LocalDate.now().plusMonths(1)).toString(), result.getString("validade")); 
    } 

}
