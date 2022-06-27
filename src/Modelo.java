public abstract class Modelo extends MapeamentoObjetoRelacional{
    private long id;
    
    public Modelo(long id) { 
        this.id = id; 
    }  

    public Modelo() { 
        this.id = nextId(); 
    } 
    
    public long getId() {
        return id;
    } 
}
