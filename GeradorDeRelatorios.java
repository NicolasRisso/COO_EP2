import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GeradorDeRelatorios {

    public static final int FORMATO_PADRAO = 0b0000;
    public static final int FORMATO_NEGRITO = 0b0001;
    public static final int FORMATO_ITALICO = 0b0010;

    public void gerarRelatorio(Produto[] produtos, String criterio) {
        AlgoritmoOrdenacao algoritmoOrdenacao = new InsertionSort(); // Ou utilize o QuickSort

        produtos = algoritmoOrdenacao.ordenar(produtos, criterio, 0, produtos.length - 1);

        try (PrintWriter writer = new PrintWriter("relatorio_" + criterio + ".html")) {
            writer.println("<html><head><title>Relatório de Produtos</title></head><body>");
            writer.println("<h1>Relatório de Produtos</h1>");
            writer.println("<table border=\"1\">");
            writer.println("<tr><th>ID</th><th>DESCRIÇÃO</th><th>CATEGORIA</th><th>QUANTIDADE_ESTOQUE</th><th>PREÇO</th><th>NEGRITO</th><th>ITALICO</th><th>COR</th></tr>");

            for (Produto p : produtos) {
                writer.println("<tr>");
                writer.println("<td>" + p.getId() + "</td>");
                writer.println("<td>" + p.getDescricao() + "</td>");
                writer.println("<td>" + p.getCategoria() + "</td>");
                writer.println("<td>" + p.getQtdEstoque() + "</td>");
                writer.println("<td>" + p.getPreco() + "</td>");
                writer.println("<td>" + p.isNegrito() + "</td>");
                writer.println("<td>" + p.isItalico() + "</td>");
                writer.println("<td>" + p.getCor() + "</td>");
                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("</body></html>");
            System.out.println("Relatório gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gerar o relatório: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Uso:");
            System.out.println("\tjava " + GeradorDeRelatorios.class.getName() + " <algoritmo> <critério de ordenação> <caminho do arquivo CSV>");
            System.out.println("Onde:");
            System.out.println("\talgoritmo: 'quick' ou 'insertion'");
            System.out.println("\tcritério de ordenação: 'preco_c' ou 'descricao_c' ou 'estoque_c'");
            System.out.println("\tcaminho do arquivo CSV: caminho para o arquivo contendo os dados dos produtos");
            System.out.println();
            System.exit(1);
        }

        String opcao_algoritmo = args[0];
        String opcao_criterio_ord = args[1];
        String csvFile = args[2];
        String csvSplitBy = ",";

        List<Produto> produtos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Ignorar a primeira linha (cabeçalhos)
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                int id = Integer.parseInt(data[0].trim());
                String descricao = data[1].trim();
                String categoria = data[2].trim();
                int qtdEstoque = Integer.parseInt(data[3].trim());
                double preco = Double.parseDouble(data[4].trim());
                boolean negrito = Boolean.parseBoolean(data[5].trim());
                boolean italico = Boolean.parseBoolean(data[6].trim());
                String cor = data[7].trim();
                produtos.add(new ProdutoPadrao(id, descricao, categoria, qtdEstoque, preco, negrito, italico, cor));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
            return;
        }

        GeradorDeRelatorios gerador = new GeradorDeRelatorios();
        gerador.gerarRelatorio(produtos.toArray(new Produto[0]), opcao_criterio_ord);
    }
}
