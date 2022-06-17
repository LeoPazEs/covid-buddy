import java.time.YearMonth;


public abstract class Cartao implements ValidarCartao{
    private Paciente paciente;
    private String numero; 
    private Integer codigo; 
    private String nome; 
    private YearMonth validade;
    
    public Cartao(Paciente paciente,String numero, Integer codigo, String nome, YearMonth validade) {
        this.paciente = paciente;
        this.numero = numero;
        this.codigo = codigo;
        this.nome = nome;
        setValidade(validade);
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
        if (validarValidade(validade))
            this.validade = validade; 
        else 
        throw new IllegalArgumentException("Validade inválida.");
    }  
}
