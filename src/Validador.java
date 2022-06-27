import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

public abstract class Validador {

    public static boolean cpf(String cpf){
        cpf = cpf.replaceAll("[^\\d.]|\\.",""); 
        
        if (!cpfsInvalidosConhecidos(cpf) || !cpfDigitosValidadores(cpf))
        return false;
        return true;
    } 
    
    
    private static boolean cpfsInvalidosConhecidos(String cpf){
        List<String> cpfInvalidosConhecidos = Arrays.asList("00000000000", "11111111111","22222222222","33333333333","44444444444","55555555555","66666666666","77777777777","88888888888", "99999999999");
        
        if (cpfInvalidosConhecidos.contains(cpf) || cpf.length() !=  11)
        return false; 
        return true;
    } 
    
    private static boolean cpfDigitosValidadores(String cpf){
        Integer somaPrimeiroDigito = 0;
        Integer somaSegundoDigito = 0;
        
        for(int i = 0, multiplicadorPrimeiroDigito = 10, multiplicadorSegundoDigito = 11; i <= 9; i++, multiplicadorPrimeiroDigito--, multiplicadorSegundoDigito--){
            if (i <= 8)
            somaPrimeiroDigito += multiplicadorPrimeiroDigito * (cpf.charAt(i) - '0');
            somaSegundoDigito += multiplicadorSegundoDigito * (cpf.charAt(i) - '0');
        }
        
        Integer moduloPrimeiroDigito = (somaPrimeiroDigito * 10) % 11;
        Integer moduloSegundoDigito = (somaSegundoDigito * 10) % 11;
        
        if (moduloPrimeiroDigito == 10 || moduloPrimeiroDigito == 0) 
        moduloPrimeiroDigito = 0;
            
        if (moduloSegundoDigito == 10 || moduloSegundoDigito == 0) 
        moduloSegundoDigito = 0;
        
        if ( moduloPrimeiroDigito != cpf.charAt(9) - '0' ||  moduloSegundoDigito != cpf.charAt(10) - '0') 
        return false;  
        return true;
    }
    
    public static boolean nome(String nome) { 
        return nome.matches("^[ A-Za-z]+$");
    } 
    
    public static boolean telefone(String telefone){
        return telefone.matches("^[(][1-9][1-9][)]9\\d{4}-\\d{4}$");
    } 
    
    public static boolean dataNascimento(LocalDate dataNascimento){
        return LocalDate.now().getYear() - dataNascimento.getYear() > 1;
    }
    
    public static boolean gravidade(char gravidade) {
        if (gravidade != 'G' && gravidade != 'L')
        return false;
        return true;
    } 
    
    public static boolean cep(String cep){ 
        return cep.matches("^\\d{5}-\\d{3}$");
    }
    
    public static boolean numeroCartao(String numero, String validadorRegex){ 
        numero = numero.replaceAll("[^\\d.]|\\.","");
        if(!numero.matches(validadorRegex)){
            return false;
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
        return false;
        
        return true;     
    }

    public static boolean validadeCartao(YearMonth validade){
        return validade.compareTo(YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth())) > 0;
    }
}
