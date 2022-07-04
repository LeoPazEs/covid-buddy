import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Paciente extends Usuario{

    public Paciente(long id, String cpf, String senha, String nome, LocalDate dataNascimento, String telefone,
            String sexo) {
        super(id, cpf, senha, nome, dataNascimento, telefone, sexo);
    }

    public Paciente(String cpf, String senha, String nome, LocalDate dataNascimento, String telefone, String sexo) {
        super(cpf, senha, nome, dataNascimento, telefone, sexo);
    } 

    private static List<Paciente> resultSetToObject(ResultSet resultSet){
        List<Paciente> pacientes = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String cpf = resultSet.getString("cpf");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String dataNascimento = resultSet.getString("dataNascimento"); 
                String sexo = resultSet.getString("sexo"); 
                String telefone = resultSet.getString("telefone");
                Paciente paciente = new Paciente(id, cpf, senha, nome, LocalDate.parse(dataNascimento), telefone, sexo);
                pacientes.add(paciente);
            } 
        }
        catch (SQLException e) {
                e.printStackTrace();
        } 
        return pacientes;
    }

    static List<Paciente> select(String filter){
        String sql = "SELECT * FROM " + MethodHandles.lookup().lookupClass().getName() + " WHERE " +  filter;
        return resultSetToObject(MapeamentoObjetoRelacional.getGerenciadorDeConexao().executeResultSet(sql));
    } 

    static List<Paciente> select(){
        String sql = "SELECT * FROM " + MethodHandles.lookup().lookupClass().getName();
        return resultSetToObject(MapeamentoObjetoRelacional.getGerenciadorDeConexao().executeResultSet(sql));
    }
}
