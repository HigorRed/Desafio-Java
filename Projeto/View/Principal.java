package View;
import java.math.BigDecimal;
import java.util.*;
import Model.Funcionario;
import Controllers.FuncionarioController;

public class Principal {
   

    public static void main(String[] args) {
        List<Funcionario> funcionarios = FuncionarioController.inicializarFuncionarios();

        FuncionarioController.imprimirTitulo("Funcionários antes da remoção");
        FuncionarioController.imprimirFuncionarios(funcionarios);

        FuncionarioController.removerFuncionario(funcionarios, "João");

        FuncionarioController.aplicarAumento(funcionarios, new BigDecimal("10"));

        FuncionarioController.imprimirTitulo("Funcionários com salário atualizado");
        FuncionarioController.imprimirFuncionarios(funcionarios);

        FuncionarioController.imprimirAgrupadosPorFuncao(funcionarios);
        FuncionarioController.imprimirAniversariantes(funcionarios, Arrays.asList(10, 12));
        FuncionarioController.imprimirFuncionarioMaisVelho(funcionarios);
        FuncionarioController.imprimirOrdemAlfabetica(funcionarios);
        FuncionarioController.imprimirTotalSalarios(funcionarios);
        FuncionarioController.imprimirSalariosMinimos(funcionarios);
    }
}

