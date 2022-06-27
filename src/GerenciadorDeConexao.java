
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GerenciadorDeConexao { 
    private Connection conexao;
    
    private String dadosConexao;
    
    public GerenciadorDeConexao(String dadosConexao) {
        this.dadosConexao = dadosConexao;
        setConexao(dadosConexao);
    }

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(String dadosConexao) {
        try{
            this.conexao =  DriverManager.getConnection(dadosConexao);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } 
    }
    
    public String getDadosConexao() {
        return dadosConexao;
    }

    public void setDadosConexao(String dadosConexao) {
        this.dadosConexao = dadosConexao;
    }

    
    public Statement statement() { 
        try{
            Statement statement = getConexao().createStatement();
            return statement;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }  

    public PreparedStatement preparedStatement(String sql){
        try{
            PreparedStatement preparedStatement = getConexao().prepareStatement(sql);
            return preparedStatement;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
    
    public void execute(String sql){
        try {
            statement().execute(sql);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    } 

    public ResultSet executeResultSet(String sql){
        ResultSet result; 
        try {
            result = preparedStatement(sql).executeQuery();
            return result;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    } 

    public boolean close(){
        // Retorna true se a conexão foi fechada.
        try {
            this.conexao.close();
        } catch (SQLException e) {
            return false;
        } 
        return true;
    }
}
