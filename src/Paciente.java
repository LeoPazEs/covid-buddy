import java.time.LocalDate;

public class Paciente extends Usuario{

    public Paciente(String cpf, String senha, String nome, LocalDate dataNascimento, String telefone, String sexo) {
        super(cpf, senha, nome, dataNascimento, telefone, sexo);
    }

}
