public class Endereco extends Modelo {
    private Usuario usuario;
    
    private String cep; 
    private Integer numero; 
    private String complemento; 
    private String cidade; 
    private String estado; 
    private String referencia;
    private String logradouro;
    
    public Endereco(Usuario usuario, String cep, Integer numero, String complemento, String cidade, String estado, String referencia, String logradouro) {
        this.usuario = usuario;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.referencia = referencia; 
        this.logradouro = logradouro;
        validar();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCep() {
        return cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;  
    }
    
    public Integer getNumero() {
        return numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
    public String getComplemento() {
        return complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getReferencia() {
        return referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    } 
    
    public String getLogradouro() {
        return logradouro;
    }
    
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    } 

    private void validar(){
        if (!Validador.cep(cep))
            throw new IllegalArgumentException("CEP inválido.");
    }
}
