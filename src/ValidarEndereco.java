public interface ValidarEndereco {
    default boolean validarCep(String cep){ 
        return cep.matches("^\\d{5}-\\d{3}$");
    }
}
