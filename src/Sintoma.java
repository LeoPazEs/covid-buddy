public class Sintoma extends Modelo{
    private Paciente usuario;
    
    private String nome; 
    private char gravidade;
    
    public Sintoma(Paciente usuario, String nome, char gravidade) {
        this.usuario = usuario;
        this.nome = nome;
        this.gravidade = Character.toUpperCase(gravidade);
        validar();
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Character getGravidade() {
        return gravidade;
    }
    
    public void setGravidade(char gravidade) {
        this.gravidade = Character.toUpperCase(gravidade);
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    private void validar(){
        if (!Validador.gravidade(gravidade))
            throw new IllegalArgumentException("Gravidade inválida.");
    }
}
