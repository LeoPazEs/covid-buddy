import java.util.Arrays;
import java.util.List;

public class Usuario {
    private String cpf;
    private String senha;
    
    public Usuario(String cpf, String senha) {
        setCpf(cpf);
        this.senha = senha;
    } 

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) { 
        if (validarCpf(cpf))
            this.cpf = cpf;
        else 
            throw new IllegalArgumentException("CPF inválido.");
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    } 

    public Boolean validarCpf(String cpf){
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
}
