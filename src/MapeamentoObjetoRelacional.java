import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Essa linguagem não permite um abstract static, ou uma interface com um method static para ser implementado na Classe. Então eu não consigo forçar a implementação do select nas classes.
// https://stackoverflow.com/questions/2689312/is-there-a-way-to-make-sure-classes-implementing-an-interface-implement-static-m
// Também não consigo saber qual classe que chamou o method static implementado na SuperClasse sem entrar no StackTrace (coisa que ninguém recomenda) 
// https://stackoverflow.com/questions/18647613/get-caller-class-name-from-inherited-static-method

public abstract class MapeamentoObjetoRelacional {
    private static GerenciadorDeConexao gerenciadorDeConexao;
    
    protected void save(){
        String keys = keysToString();
        String values = valuesToString();
        gerenciadorDeConexao.execute("INSERT OR REPLACE INTO " + this.getClass().getName() + "( " + keys + ")"+ " VALUES (" + values + ")"); 
    }

    protected void delete(){
        gerenciadorDeConexao.execute("DELETE FROM " + this.getClass().getName() + " WHERE id = " + attributeValue("id").toString());
    } 

    private  Object attributeValue(String fieldName){
        return ReflectHelper.runGetter(ReflectHelper.getField(this.getClass(), fieldName), this);
    }

    private Map<String, Object> attributesValues(){
        Map<String, Object> objectData = new HashMap<String, Object>(); 

        for (Field field : ReflectHelper.getAllFields(this.getClass())){
            if ( ReflectHelper.runGetter(field, this) != null){
                objectData.put(field.getName(), ReflectHelper.runGetter(field, this));
            }
        }
        return objectData;
    }
    
    private String valuesToString() {
        List<Object> attributesValues = new ArrayList<>(attributesValues().values());
        String values = "";
        for (int i = 0 ; i < attributesValues.size() ; i++){
            if (attributesValues.get(i) instanceof String || attributesValues.get(i) instanceof Character || attributesValues.get(i).getClass().getPackage().getName().equals("java.time") ||  attributesValues.get(i) instanceof char[] ){
                values += "'" + attributesValues.get(i).toString() + "'"; 
            }
            else{
                if (!ReflectHelper.isWrapperType(attributesValues.get(i).getClass()))
                    values += ReflectHelper.runMethod("getId", attributesValues.get(i)).toString();
                else
                    values += attributesValues.get(i).toString(); 
            }
            
            if (i < attributesValues.size() - 1) 
                values += ",";
        }
        return values;
    }

    private String keysToString() {
        return attributesValues().keySet().toString().replaceAll("\\[|\\]","");
    }; 


    private long getMaxId(){ 
        ResultSet result = gerenciadorDeConexao.executeResultSet("SELECT MAX(id) from " + this.getClass().getName());
        try {
            long maxId = result.getInt(1);
            return maxId;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } 
    } 

    protected long nextId(){
        return getMaxId() + 1;
    }

    protected static GerenciadorDeConexao getGerenciadorDeConexao() {
        return gerenciadorDeConexao;
    }
    
    protected static void setGerenciadorDeConexao(GerenciadorDeConexao gerenciadorDeConexao) {
        MapeamentoObjetoRelacional.gerenciadorDeConexao = gerenciadorDeConexao;
    } 
}
