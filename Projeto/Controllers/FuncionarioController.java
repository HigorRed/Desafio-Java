package Controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

import Model.Funcionario;

public class FuncionarioController {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat FORMATO_SALARIO = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static List<Funcionario> inicializarFuncionarios() {
        return new ArrayList<>(List.of(
            new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
            new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
            new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
            new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
            new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
            new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
            new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
            new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
            new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
            new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));
    }

    public static void imprimirTitulo(String titulo) {
        System.out.println("\n===============================");
        System.out.println(titulo);
        System.out.println("===============================");
    }

    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.forEach(f -> System.out.println(
            "Nome: " + f.getNome() +
            ", Data de Nascimento: " + f.getDataNascimento().format(FORMATO_DATA) +
            ", Salário: " + FORMATO_SALARIO.format(f.getSalario())
        ));
    }

    public static void removerFuncionario(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }

    public static void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        funcionarios.forEach(f -> f.aumentarSalario(percentual));
    }

    public static void imprimirAgrupadosPorFuncao(List<Funcionario> funcionarios) {
        imprimirTitulo("Funcionários agrupados por função");
        Map<String, List<Funcionario>> agrupados = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));

        agrupados.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
        });
    }

    public static void imprimirAniversariantes(List<Funcionario> funcionarios, List<Integer> meses) {
        imprimirTitulo("Aniversariantes em meses específicos");
        funcionarios.stream()
            .filter(f -> meses.contains(f.getDataNascimento().getMonthValue()))
            .forEach(f -> System.out.println("Nome: " + f.getNome() + ", Mês: " + f.getDataNascimento().getMonthValue()));
    }

    public static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        imprimirTitulo("Funcionário com maior idade");
        funcionarios.stream()
            .min(Comparator.comparing(Funcionario::getDataNascimento))
            .ifPresent(f -> System.out.println(
                "Nome: " + f.getNome() +
                ", Data de Nascimento: " + f.getDataNascimento().format(FORMATO_DATA) +
                ", Função: " + f.getFuncao() +
                ", Salário: " + FORMATO_SALARIO.format(f.getSalario())
            ));
    }

    public static void imprimirOrdemAlfabetica(List<Funcionario> funcionarios) {
        imprimirTitulo("Funcionários em ordem alfabética");
        funcionarios.stream()
            .sorted(Comparator.comparing(Funcionario::getNome))
            .forEach(f -> System.out.println("  - " + f.getNome()));
    }

    public static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        imprimirTitulo("Total dos salários");
        BigDecimal total = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total: " + FORMATO_SALARIO.format(total));
    }

    public static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        imprimirTitulo("Salários equivalentes em salários mínimos");
        funcionarios.forEach(f -> {
            BigDecimal equivalencia = f.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println("Nome: " + f.getNome() + ", Equivalente a: " + equivalencia + " salários mínimos");
        });
    }
}