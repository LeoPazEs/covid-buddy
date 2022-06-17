public class Sintoma {
    private Paciente usuario;
    
    private String sintoma; 
    private char gravidade;
    
    public Sintoma(Paciente usuario, String sintoma, char gravidade) {
        this.usuario = usuario;
        this.sintoma = sintoma;
        setGravidade(Character.toUpperCase(gravidade));
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
        if (validarGravidade(gravidade))
        this.gravidade = gravidade; 
        else
        throw new IllegalArgumentException("Gravidade inválida.");
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    private boolean validarGravidade(char gravidade) {
        if (gravidade != 'G' && gravidade != 'L')
            return false;
    
        return true;
    }
}
