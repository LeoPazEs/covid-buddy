import java.time.LocalDate;

public abstract class Usuario extends Modelo{
    private String cpf;
    private String senha;
    private String nome; 
    private LocalDate dataNascimento; 
    private String telefone; 
    private String sexo;
    
    
    public Usuario(long id, String cpf, String senha, String nome, LocalDate dataNascimento, String telefone,
            String sexo) {
        super(id);
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.sexo = sexo;
    }


    public Usuario(String cpf, String senha, String nome, LocalDate dataNascimento, String telefone, String sexo) {
        super();
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.sexo = sexo;
        validar();
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) { 
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
        this.nome = nome;  
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento; 
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;  
    }
    
    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    } 

    private void validar(){
        if(!Validador.telefone(telefone))
            throw new IllegalArgumentException("Telefone inválido.");

        if (!Validador.dataNascimento(dataNascimento))
            throw new IllegalArgumentException("Data de nascimento inválida.");

        if(!Validador.nome(nome))
            throw new IllegalArgumentException("Nome inválido.");

        if (!Validador.cpf(cpf))
            throw new IllegalArgumentException("CPF inválido.");
    }
}
