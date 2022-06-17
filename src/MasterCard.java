import java.time.YearMonth;

public class MasterCard extends Cartao {
    private static String validadorRegex = "^5[1-5][0-9]{14}$|^2(?:2(?:2[1-9]|[3-9][0-9])|[3-6][0-9][0-9]|7(?:[01][0-9]|20))[0-9]{12}$"; 

    public MasterCard(Paciente paciente, String numero, Integer codigo, String nome, YearMonth validade) {
        super(paciente, ValidarCartao.validarNumero(numero, validadorRegex), codigo, nome, validade);
    }
    
}
