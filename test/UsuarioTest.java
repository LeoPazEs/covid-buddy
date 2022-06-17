import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UsuarioTest {
    @Test
    public void criandoUsuarioCpfInvalido(){
        new Usuario("529.982.247-25", "12345");
        Throwable cpfInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("123.456.789.10", "1234"));
        Assertions.assertEquals( "CPF inválido.", cpfInvalido.getMessage());
        cpfInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("123.456.789-12", "1234"));
        Assertions.assertEquals( "CPF inválido.", cpfInvalido.getMessage());
        cpfInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("777.777.777-77", "1234"));
        Assertions.assertEquals( "CPF inválido.", cpfInvalido.getMessage());
    }
}
