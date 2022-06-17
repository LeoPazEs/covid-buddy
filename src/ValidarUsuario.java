
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public interface ValidarUsuario {
    default boolean validarCpf(String cpf){
        cpf = cpf.replaceAll("[^\\d.]|\\.","");  
        
        List<String> cpfInvalidosConhecidos = Arrays.asList("00000000000", "11111111111","22222222222","33333333333","44444444444","55555555555","66666666666","77777777777","88888888888", "99999999999");
        if (cpfInvalidosConhecidos.contains(cpf) || cpf.length() !=  11)
        return false;
        
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

    default boolean validarNome(String nome) { 
        return nome.matches("^[ A-Za-z]+$");
    } 
        
    default boolean validarTelefone(String telefone){
        return telefone.matches("^[(][1-9][1-9][)]9\\d{4}-\\d{4}$");
    } 

    default boolean validarDataNascimento(LocalDate dataNascimento){
        return LocalDate.now().getYear() - dataNascimento.getYear() > 1;
    }
}
