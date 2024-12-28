import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Endereço, Conta e Senha do Banco de Dados (BD) MySQL no meu servidor no XAMPP
        String url = "jdbc:mysql://localhost/bdcalculadora"; // Conexão feita atravez do acesso "localhost",
                                                             // mude de acordo com o seu servidor.
        String user = "root";
        String password = "";

        // ENTRADA DOS DADOS PARA AS OPERAÇÇÕES:
        System.out.print("Digite um número: ");
        double num1 = input.nextDouble();

        System.out.print("Escolha o Operador: ( + , - , * , / ): ");
        String operador = input.next();

        System.out.print("Digite outro número: ");
        double num2 = input.nextDouble();

        // VARIAVEIS PARA AS OPERAÇÕES:
        double calculo, confirmacao;
        String resultado = "";

        // OPERAÇÕES:
        switch (operador) {
            // ADIÇÂO:
            case "+":
                calculo = (num1 + num2); // Efetua o calculo.
                confirmacao = (calculo % 2); // Verifica se o resultado é um número Inteiro Natural ou Real.
                if (confirmacao == 0) {
                    // Se o número for inteiro natural ele o formata para um númeoro inteiro.
                    resultado = String.format("%.0f", calculo); // Transformando o valor Real em String para subir pro
                                                                // (BD).
                    System.out.println("O Resultado é " + resultado); // Saida pro console.
                } else {
                    // Se o número for real, ele imprime de forma padrão.
                    resultado = String.format("%s", calculo); // Transformando o valor Real em String para subir pro
                                                              // (BD).
                    System.out.println("O Resultado é " + resultado); // Saida pro console.
                }
                break;

            // SUBTRAÇÂO:
            case "-":
                calculo = (num1 - num2); // Efetua o calculo.
                confirmacao = (calculo % 2); // Verifica se o resultado é um número Inteiro Natural ou Real.
                if (confirmacao == 0) {
                    // Se o número for inteiro natural ele o formata para um númeoro inteiro.
                    resultado = String.format("%.0f", calculo); // Transformando o valor Real em String para subir pro
                                                                // (BD).
                    System.out.println("O Resultado é " + resultado); // Saida pro console.
                } else {
                    // Se o número for real, ele imprime de forma padrão.
                    resultado = String.format("%s", calculo); // Transformando o valor Real em String para subir pro
                                                              // (BD).
                    System.out.println("O Resultado é " + resultado); // Saida pro console.
                }
                break;

            // MULTIPLICAÇÃO:
            case "*":
                calculo = (num1 * num2); // Efetua o calculo.
                confirmacao = (calculo % 2); // Verifica se o resultado é um número Inteiro Natural ou Real.
                if (confirmacao == 0) {
                    // Se o número for inteiro natural ele o formata para um númeoro inteiro.
                    resultado = String.format("%.0f", calculo); // Transformando o valor Real em String para subir pro
                                                                // (BD).
                    System.out.println("O Resultado é " + resultado); // Saida pro console.
                } else {
                    // Se o número for real, ele imprime de forma padrão.
                    resultado = String.format("%s", calculo); // Transformando o valor Real em String para subir pro
                                                              // (BD).
                    System.out.println("O Resultado é " + resultado); // Saida pro console.
                }
                break;

            // DIVISÂO:
            case "/":
                calculo = (num1 / num2);// Efetua o calculo.
                confirmacao = (calculo % 2); // Verifica se o resultado é um número Inteiro Natural ou Real.
                if (confirmacao == 0) {
                    resultado = String.format("%.0f", calculo); // Transformando o valor Real em String para subir pro
                                                                // (BD).
                    System.out.println("O Resultado é " + resultado); // Saida pro console.
                } else {
                    // Se o número for real, ele imprime de forma padrão.
                    resultado = String.format("%s", calculo); // Transformando o valor Real em String para subir pro
                                                              // (BD).
                    System.out.println("O Resultado é " + resultado); // Saida pro console.
                }
                break;

            // ERRO:
            default:
                System.out.println("\n Algo de Errado"); // Saida pro console.
                break;
        }

        // Inserindo os dados no Banco de Dados chamado "bdcalculadora".
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO historicodecalculadora (Resultados, Horario) VALUES (?, NOW())";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, resultado);
                statement.executeUpdate();
                System.out.println("\nResultado inserido no banco de dados com sucesso!");
            } catch (SQLException e) {
                System.err.println("\nErro ao executar a inserção no banco de dados: " +
                        e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}