import java.io.IOException;
import java.io.PrintWriter;

public class GeradorDeRelatorios {

    private Produto[] produtos;
    private String algoritmo;
    private String criterio;
    private String filtro;
    private String parametro;

    // Construtor
    public GeradorDeRelatorios(Produto[] produtos, String algoritmo, String criterio, String filtro, String parametro) {
        this.produtos = produtos;
        this.algoritmo = algoritmo;
        this.criterio = criterio;
        this.filtro = filtro;
        this.parametro = parametro;
    }

    public void gerarRelatorio() {
        
        Ordenar ordenar = new Ordenar();
        produtos = ordenar.ordena(produtos, algoritmo, criterio);


        try (PrintWriter writer = new PrintWriter("saida" + ".html")) {

            writer.println("<html><head><title>Relatório de Produtos</title></head><body>");
            writer.println("<h1>Relatório de Produtos</h1>");
            writer.println("<table border=\"1\">");
            writer.println("<tr><th>ID</th><th>DESCRIÇÃO</th><th>CATEGORIA</th><th>QUANTIDADE_ESTOQUE</th><th>PREÇO</th><th>NEGRITO</th><th>ITALICO</th><th>COR</th></tr>");

            for (Produto p : produtos) {

                Filtro filtrador = new Filtro(p, filtro, parametro);
                if(!filtrador.filtrar()) continue;

                ProdutoFormatado pf = new ProdutoFormatado(p);

                writer.println("<tr>");
                writer.println("<td>" + pf.getId() + "</td>");
                writer.println("<td>" + pf.getDescricao() + "</td>");
                writer.println("<td>" + pf.getCategoria() + "</td>");
                writer.println("<td>" + pf.getQtdEstoque() + "</td>");
                writer.println("<td>" + pf.getPreco() + "</td>");
                writer.println("<td>" + pf.isNegrito() + "</td>");
                writer.println("<td>" + pf.isItalico() + "</td>");
                writer.println("<td>" + pf.getCor() + "</td>");
                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("</body></html>");
            System.out.println("Relatorio gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gerar o relatório: " + e.getMessage());
        }
    }
}
