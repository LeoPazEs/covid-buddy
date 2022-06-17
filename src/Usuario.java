import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Usuario {
    private String cpf;
    private String senha;
    private String nome; 
    private LocalDate dataNascimento; 
    private String telefone; 
    private String sexo;
    
    public Usuario(String cpf, String senha, String nome, LocalDate dataNascimento, String telefone, String sexo) {
        setCpf(cpf);
        this.senha = senha; 
        setNome(nome); 
        setDataNascimento(dataNascimento); 
        setTelefone(telefone); 
        this.sexo = sexo;
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
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(validarNome(nome))
        this.nome = nome; 
        else 
        throw new IllegalArgumentException("Nome inválido.");
    }
    
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        if (validarDataNascimento(dataNascimento))
        this.dataNascimento = dataNascimento; 
        else
        throw new IllegalArgumentException("Data de nascimento inválida.");
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if(validarTelefone(telefone))
        this.telefone = telefone; 
        else 
        throw new IllegalArgumentException("Telefone inválido.");
    }
    
    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    private boolean validarCpf(String cpf){
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

    private boolean validarNome(String nome) { 
        return nome.matches("^[ A-Za-z]+$");
    } 
        
    private boolean validarTelefone(String telefone){
        return telefone.matches("^[(][1-9][1-9][)]9\\d{4}-\\d{4}$");
    } 

    private boolean validarDataNascimento(LocalDate dataNascimento){
        return LocalDate.now().getYear() - dataNascimento.getYear() > 1;
    }
}
