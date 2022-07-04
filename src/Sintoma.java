import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sintoma extends Modelo{
    private Paciente usuario;
    
    private String nome; 
    private char gravidade;
    
    public Sintoma(long id, Paciente usuario, String nome, char gravidade) {
        super(id);
        this.usuario = usuario;
        this.nome = nome;
        this.gravidade = gravidade;
    }

    public Sintoma(Paciente usuario, String nome, char gravidade) {
        this.usuario = usuario;
        this.nome = nome;
        this.gravidade = Character.toUpperCase(gravidade);
        validar();
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Character getGravidade() {
        return gravidade;
    }
    
    public void setGravidade(char gravidade) {
        this.gravidade = Character.toUpperCase(gravidade);
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    private void validar(){
        if (!Validador.gravidade(gravidade))
            throw new IllegalArgumentException("Gravidade inválida.");
    } 

    private static List<Sintoma> resultSetToObject(ResultSet resultSet){
        List<Sintoma> sintomas = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");

                Integer pacienteId = resultSet.getInt("usuario"); 
                Paciente paciente = Paciente.select("id = " +  pacienteId.toString()).get(0);

                String nome = resultSet.getString("nome");
                char gravidade = resultSet.getString("gravidade").charAt(0);

                Sintoma sintoma = new Sintoma((long)id, paciente, nome, gravidade);
                sintomas.add(sintoma);
            } 
        }
        catch (SQLException e) {
                e.printStackTrace();
        } 
        return sintomas;
    }

    static List<Sintoma> select(String filter){
        String sql = "SELECT * FROM " + MethodHandles.lookup().lookupClass().getName() + " WHERE " +  filter;
        return resultSetToObject(MapeamentoObjetoRelacional.getGerenciadorDeConexao().executeResultSet(sql));
    } 

    static List<Sintoma> select(){
        String sql = "SELECT * FROM " + MethodHandles.lookup().lookupClass().getName();
        return resultSetToObject(MapeamentoObjetoRelacional.getGerenciadorDeConexao().executeResultSet(sql));
    }
}
