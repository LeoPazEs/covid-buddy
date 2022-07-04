import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Visa extends Cartao{
    private static String validadorRegex = "^4[0-9]{12}(?:[0-9]{3})?$";

    public Visa(Paciente paciente, String numero, String codigo, String nome, YearMonth validade) {
        super(paciente, numero, codigo, nome, validade, validadorRegex);
    } 

    public Visa(Long id, Paciente paciente, String numero, String codigo, String nome, YearMonth validade) {
        super(id, paciente, numero, codigo, nome, validade, validadorRegex);
    } 

    private static List<Visa> resultSetToObject(ResultSet resultSet){
        List<Visa> cartoes = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");

                Integer pacienteId = resultSet.getInt("pacienteId"); 
                Paciente paciente = Paciente.select("id = " + pacienteId.toString()).get(0);

                String numero = resultSet.getString("numero");
                String codigo = resultSet.getString("codigo");
                String nome = resultSet.getString("nome"); 
                String validade = resultSet.getString("validade");

                Visa cartao = new Visa((long)id, paciente, numero, codigo, nome, YearMonth.parse(validade));
                cartoes.add(cartao);
            } 
        }
        catch (SQLException e) {
                e.printStackTrace();
        } 
        return cartoes;
    }

    static List<Visa> select(String filter){
        String sql = "SELECT * FROM " + MethodHandles.lookup().lookupClass().getName() + " WHERE " +  filter;
        return resultSetToObject(MapeamentoObjetoRelacional.getGerenciadorDeConexao().executeResultSet(sql));
    } 

    static List<Visa> select(){
        String sql = "SELECT * FROM " + MethodHandles.lookup().lookupClass().getName();
        return resultSetToObject(MapeamentoObjetoRelacional.getGerenciadorDeConexao().executeResultSet(sql));
    }
}
