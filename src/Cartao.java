import java.time.YearMonth;



public abstract class Cartao extends Modelo{
    private Paciente pacienteId;
    private String numero; 
    private String codigo; 
    private String nome; 
    private YearMonth validade;
    private String validadorRegex;
    
    
    
    public Cartao(Paciente paciente,String numero, String codigo, String nome, YearMonth validade, String validadorRegex) {
        // Com isso eu forço a implementação do validador regex nas classes que extends cartão, mas eu gostaria de não deixar o validador no Objeto. 
        this.validadorRegex = validadorRegex;
        this.pacienteId = paciente;
        this.numero = numero;
        this.codigo = codigo;
        this.nome = nome;
        this.validade = validade;  
        validar();
    }

    public Paciente getPacienteId() {
        return pacienteId;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public YearMonth getValidade() {
        return validade;
    }

    public void setValidade(YearMonth validade) {
       this.validade = validade;
    } 
    
    private void validar(){
        if (!Validador.validadeCartao(this.validade))
            throw new IllegalArgumentException("Validade inválida.");

        if (!Validador.numeroCartao(numero, validadorRegex))
            throw new IllegalArgumentException("Número inválido.");
    }
}
