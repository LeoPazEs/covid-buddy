import java.time.YearMonth;


public abstract class Cartao implements ValidarCartao{
    private Paciente paciente;
    private String numero; 
    private Integer codigo; 
    private String nome; 
    private YearMonth validade;
    protected String validadorRegex;
    
    
    public Cartao(Paciente paciente,String numero, Integer codigo, String nome, YearMonth validade, String validadorRegex) {
        this.validadorRegex = validadorRegex;
        this.paciente = paciente;
        setNumero(numero);
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
        if (!validarNumero(numero, validadorRegex))
            throw new IllegalArgumentException("Número inválido.");

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
        
    public void setValidadorRegex(String validadorRegex) {
        this.validadorRegex = validadorRegex;
    }    
}
