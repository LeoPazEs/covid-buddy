import java.time.LocalDate;
import java.time.YearMonth;

public interface ValidarCartao {
    static String validarNumero(String numero, String validadorRegex){ 
        numero = numero.replaceAll("[^\\d.]|\\.","");
        if(!numero.matches(validadorRegex)){
            throw new IllegalArgumentException("Número inválido.");
        }

        Integer somaPar = 0; 
        Integer somaImpar = 0;
        for (int i = numero.length() - 2; i >= 0; i -= 2){
            Integer parMultiplicado = (numero.charAt(i) - '0') * 2;
            if (parMultiplicado > 9) 
                somaPar += parMultiplicado - 9; 
            else 
                somaPar += parMultiplicado;

            somaImpar += numero.charAt(i + 1) - '0';
        } 
        if ((somaImpar + somaPar) % 10 != 0) 
            throw new IllegalArgumentException("Número inválido.");
            
        return numero;     
    } 

    default boolean validarValidade(YearMonth validade){
        return validade.compareTo(YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth())) > 0;
    }
}
