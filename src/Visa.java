import java.time.YearMonth;

public class Visa extends Cartao{
    private static String validadorRegex = "^4[0-9]{12}(?:[0-9]{3})?$";

    public Visa(Paciente paciente, String numero, Integer codigo, String nome, YearMonth validade) {
        super(paciente, ValidarCartao.validarNumero(numero, validadorRegex), codigo, nome, validade);
    }
}
