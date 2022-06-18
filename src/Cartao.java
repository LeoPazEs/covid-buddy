import java.time.YearMonth;
import java.time.LocalDate;

public abstract class Cartao {
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
    
    private boolean validarNumero(String numero, String validadorRegex){ 
        numero = numero.replaceAll("[^\\d.]|\\.","");
        if(!numero.matches(validadorRegex)){
            return false;
        }

        Integer somaPar = 0; 
        Integer somaImpar = 0;
        for (int i = numero.length() - 2; i >= 0; i -= 2){
            Integer parMultiplicado = (numero.charAt(i) - '0') * 2;
            if (parMultiplicado > 9) 
                somaPar += parMultiplicado - 9; 
            else 
                somaPar += parMultiplicado;

            somaImpar += numero.charAt(i + 1) - '0';
        } 
        if ((somaImpar + somaPar) % 10 != 0) 
            return false;

        return true;     
    } 

    private boolean validarValidade(YearMonth validade){
        return validade.compareTo(YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth())) > 0;
    }
}
