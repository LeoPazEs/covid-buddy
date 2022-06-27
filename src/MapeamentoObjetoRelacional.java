import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
