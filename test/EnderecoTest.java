import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class EnderecoTest {
    @Test
    public void criandoEnderecoCepInvalido(){
        Throwable cepInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Endereco("57051190", 384, "Ap 1111 Bloco 2", "Maceió", "Alagoas", "Ao lado do marista", "Av Governador"));
        Assertions.assertEquals( "CEP inválido.", cepInvalido.getMessage());  

        cepInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Endereco("7051-190", 384, "Ap 1111 Bloco 2", "Maceió", "Alagoas", "Ao lado do marista", "Av Governador"));
        Assertions.assertEquals( "CEP inválido.", cepInvalido.getMessage());  

        cepInvalido = Assertions.assertThrows(IllegalArgumentException.class, () -> new Endereco("57051-1190", 384, "Ap 1111 Bloco 2", "Maceió", "Alagoas", "Ao lado do marista", "Av Governador"));
        Assertions.assertEquals( "CEP inválido.", cepInvalido.getMessage());  
        
        new Endereco("57051-190", 384, "Ap 1111 Bloco 2", "Maceió", "Alagoas", "Ao lado do marista", "Av Governador");
    }
}
