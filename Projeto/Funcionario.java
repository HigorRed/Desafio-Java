import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void aumentarSalario(BigDecimal percentual) {
        salario = salario.add(salario.multiply(percentual.divide(new BigDecimal("100"))));
    }

    @Override
    public String toString() {
        return super.toString() + ", Função: " + funcao + ", Salário: R$" + salario;
    }
}
