import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class MasterCard extends Cartao {
    private static String validadorRegex = "^5[1-5][0-9]{14}$|^2(?:2(?:2[1-9]|[3-9][0-9])|[3-6][0-9][0-9]|7(?:[01][0-9]|20))[0-9]{12}$"; 

    public MasterCard(Paciente paciente, String numero, String codigo, String nome, YearMonth validade) {
        super(paciente, numero, codigo, nome, validade, validadorRegex);
    } 

    public MasterCard(Long id, Paciente paciente, String numero, String codigo, String nome, YearMonth validade) {
        super(id, paciente, numero, codigo, nome, validade, validadorRegex);
    } 
     
    private static List<MasterCard> resultSetToObject(ResultSet resultSet){
        List<MasterCard> cartoes = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");

                Integer pacienteId = resultSet.getInt("pacienteId"); 
                Paciente paciente = Paciente.select("id = " + pacienteId.toString()).get(0);

                String numero = resultSet.getString("numero");
                String codigo = resultSet.getString("codigo");
                String nome = resultSet.getString("nome"); 
                String validade = resultSet.getString("validade");

                MasterCard cartao = new MasterCard((long)id, paciente, numero, codigo, nome, YearMonth.parse(validade));
                cartoes.add(cartao);
            } 
        }
        catch (SQLException e) {
                e.printStackTrace();
        } 
        return cartoes;
    }

    static List<MasterCard> select(String filter){
        String sql = "SELECT * FROM " + MethodHandles.lookup().lookupClass().getName() + " WHERE " +  filter;
        return resultSetToObject(MapeamentoObjetoRelacional.getGerenciadorDeConexao().executeResultSet(sql));
    } 

    static List<MasterCard> select(){
        String sql = "SELECT * FROM " + MethodHandles.lookup().lookupClass().getName();
        return resultSetToObject(MapeamentoObjetoRelacional.getGerenciadorDeConexao().executeResultSet(sql));
    }
}
