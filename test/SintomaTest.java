import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SintomaTest {
    @Test 
    public void criandoSintomaComGravidadeInvalida(){
        Usuario usuario = new Usuario("529.982.247-25", "12345", "Leonardo Paz Estevam",  LocalDate.of(2001, 4, 5), "(82)99919-2696", "Masculino");

        Throwable gravidadeInvalida =  Assertions.assertThrows(IllegalArgumentException.class, () -> new Sintoma(usuario, "Tosse", 'M'));
        Assertions.assertEquals( "Gravidade inválida.", gravidadeInvalida.getMessage()); 

        new Sintoma(usuario, "Tosse", 'G');
        new Sintoma(usuario, "Tosse", 'l');
    }
}
