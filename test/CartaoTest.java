import java.time.LocalDate;
import java.time.YearMonth;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CartaoTest {
    @Test 
    public void criandoVisaComNumeroInvalido(){
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        new Visa(paciente, "4242 4242 4242 4242", 342, "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1)));

        Throwable numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Visa(paciente, "424 4242 4242 4242", 342, "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1))));
        Assertions.assertEquals( "Número inválido.", numeroInvalido.getMessage()); 

        numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Visa(paciente, "4244 4242 4242 4242", 342, "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1))));
        Assertions.assertEquals( "Número inválido.", numeroInvalido.getMessage());
    }

    @Test 
    public void criandoMasterComNumeroInvalido(){
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        new MasterCard(paciente, "5555 5555 5555 4444", 342, "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1)));

        Throwable numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new MasterCard(paciente, "424 4242 4242 4242", 342, "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1))));
        Assertions.assertEquals( "Número inválido.", numeroInvalido.getMessage()); 

        numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new MasterCard(paciente, "5556 5555 5555 4444", 342, "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1))));
        Assertions.assertEquals( "Número inválido.", numeroInvalido.getMessage());
    } 

    @Test 
    public void criandoCartaoComValidadeInvalida(){
        Paciente paciente = new Paciente("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"); 
        new Visa(paciente, "4242 4242 4242 4242", 342, "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().plusMonths(1)));

        Throwable numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Visa(paciente, "4242 4242 4242 4242", 342, "Leonardo Paz Estevam", YearMonth.from(LocalDate.now())));
        Assertions.assertEquals( "Validade inválida.", numeroInvalido.getMessage()); 

        numeroInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Visa(paciente, "4242 4242 4242 4242", 342, "Leonardo Paz Estevam", YearMonth.from(LocalDate.now().minusMonths(1))));
        Assertions.assertEquals( "Validade inválida.", numeroInvalido.getMessage());
    }
}
