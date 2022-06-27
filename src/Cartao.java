import java.time.YearMonth;

public abstract class Cartao {
    private Paciente paciente;
    private String numero; 
    private Integer codigo; 
    private String nome; 
    private YearMonth validade;
    private String validadorRegex;
    
    
    public Cartao(Paciente paciente,String numero, Integer codigo, String nome, YearMonth validade, String validadorRegex) {
        this.validadorRegex = validadorRegex;
        this.paciente = paciente;
        this.numero = numero;
        this.codigo = codigo;
        this.nome = nome;
        this.validade = validade;  
        validar();
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
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
