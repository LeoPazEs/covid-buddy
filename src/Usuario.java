import java.time.LocalDate;

public class Usuario implements ValidarUsuario{
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
        if (!validarCpf(cpf))
            throw new IllegalArgumentException("CPF inválido.");
        this.cpf = cpf;
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
        if(!validarNome(nome))
            throw new IllegalArgumentException("Nome inválido.");
        
        this.nome = nome;  
    }
    
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        if (!validarDataNascimento(dataNascimento))
            throw new IllegalArgumentException("Data de nascimento inválida.");
        this.dataNascimento = dataNascimento; 
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if(!validarTelefone(telefone))
            throw new IllegalArgumentException("Telefone inválido.");
        this.telefone = telefone;  
    }
    
    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
