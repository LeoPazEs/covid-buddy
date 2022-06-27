import java.time.YearMonth;

public class Visa extends Cartao{
    private static String validadorRegex = "^4[0-9]{12}(?:[0-9]{3})?$";

    public Visa(Paciente paciente, String numero, String codigo, String nome, YearMonth validade) {
        super(paciente, numero, codigo, nome, validade, validadorRegex);
    }
}
