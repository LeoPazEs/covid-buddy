public class Sintoma {
    private Paciente usuario;
    
    private String sintoma; 
    private char gravidade;
    
    public Sintoma(Paciente usuario, String sintoma, char gravidade) {
        this.usuario = usuario;
        this.sintoma = sintoma;
        this.gravidade = Character.toUpperCase(gravidade);
        validar();
    }
    
    public String getSintoma() {
        return sintoma;
    }
    
    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }
    
    public char getGravidade() {
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
