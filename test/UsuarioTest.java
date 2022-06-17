import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UsuarioTest {
    @Test
    public void criandoUsuarioCpfInvalido(){
        new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");

        Throwable cpfInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("123.456.789.10", "1234", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"));
        Assertions.assertEquals( "CPF inválido.", cpfInvalido.getMessage());

        cpfInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("123.456.789-12", "1234", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"));
        Assertions.assertEquals( "CPF inválido.", cpfInvalido.getMessage());

        cpfInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("777.777.777-77", "1234", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"));
        Assertions.assertEquals( "CPF inválido.", cpfInvalido.getMessage());
    }

    @Test
    public void criandoUsuarioNomeInvalido(){
        Throwable nomeInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("529.982.247-25", "12345", "L3onardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino"));
        Assertions.assertEquals( "Nome inválido.", nomeInvalido.getMessage()); 
        new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");
    } 

    @Test
    public void criandoUsuarioTelefoneInvalido(){
        Throwable telefoneInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99192696", "Masculino"));
        Assertions.assertEquals( "Telefone inválido.", telefoneInvalido.getMessage()); 

        telefoneInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)9999192696", "Masculino"));
        Assertions.assertEquals( "Telefone inválido.", telefoneInvalido.getMessage()); 

        telefoneInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)999192696", "Masculino"));
        Assertions.assertEquals( "Telefone inválido.", telefoneInvalido.getMessage()); 

        telefoneInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "8299919-2696", "Masculino"));
        Assertions.assertEquals( "Telefone inválido.", telefoneInvalido.getMessage()); 

        new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");
    } 

    @Test
    public void criandoUsuarioDataNascimentoInvalido(){
        Throwable nomeInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(LocalDate.now().getYear(), 4, 5), "(82)99919-2696", "Masculino"));
        Assertions.assertEquals( "Data de nascimento inválida.", nomeInvalido.getMessage()); 

        new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");
    }  

    @Test 
    public void criandoUsuarioComEndeco(){
        Endereco endereco = new Endereco("57051-190", 384, "Ap 1111 Bloco 2", "Maceió", "Alagoas", "Ao lado do marista", "Av Governador");
        new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino", endereco);
    }
}
