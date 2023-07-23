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
    public static final int FORMATO_NEGRITO_ITALICO = 0b0011;

    public static final String FILTRO_ESTOQUE_MENOR_OU_IQUAL_A = "estoque_menor_igual";
	public static final String FILTRO_CATEGORIA_IGUAL_A = "categoria_igual";
    public static final String FILTRO_PRECO_ENTRE_X_Y = "preco_entre";
    public static final String FILTRO_COM_PALAVRA_NA_DESC = "com_palavra";

    public void gerarRelatorio(Produto[] produtos, String algoritmo, String criterio, String filtro, String parametro) {
        
        Ordenar ordenar = new Ordenar();
        produtos = ordenar.ordena(produtos, algoritmo, criterio);


        try (PrintWriter writer = new PrintWriter("saida" + ".html")) {
            writer.println("<html><head><title>Relatório de Produtos</title></head><body>");
            writer.println("<h1>Relatório de Produtos</h1>");
            writer.println("<table border=\"1\">");
            writer.println("<tr><th>ID</th><th>DESCRIÇÃO</th><th>CATEGORIA</th><th>QUANTIDADE_ESTOQUE</th><th>PREÇO</th><th>NEGRITO</th><th>ITALICO</th><th>COR</th></tr>");

            //Prepara o filtro
            int[] maxmin = new int[2];
            if (filtro.equals(FILTRO_PRECO_ENTRE_X_Y)){
                String[] partes = parametro.split(",");
                for (int i = 0; i < partes.length; i++) maxmin[i] = Integer.parseInt(partes[i]);
            }

            for (Produto p : produtos) {
                //filtro
                if (filtro.equals(FILTRO_ESTOQUE_MENOR_OU_IQUAL_A)){
                    if (p.getQtdEstoque() > Integer.parseInt(parametro)) continue;
                }
                if (filtro.equals(FILTRO_CATEGORIA_IGUAL_A)){
                    if (!(p.getCategoria().equals(parametro))) continue;
                }
                if (filtro.equals(FILTRO_PRECO_ENTRE_X_Y)){
                    if (p.getPreco() < maxmin[0] || p.getPreco() > maxmin[1]) continue;
                }
                if (filtro.equals(FILTRO_COM_PALAVRA_NA_DESC)){
                    if (!(p.getDescricao().contains(parametro))) continue;
                }

                writer.println("<tr>");
                writer.println("<td>" + p.getId() + "</td>");
                writer.println("<td>" + "<span style=\"color:" + p.getCor() + "\">" + p.getDescricao() + "</span>" + "</td>");
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
            System.out.println("Relatorio gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gerar o relatório: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //INICIO ENTRADA DO USUARIO
        if(args.length < 4){

			System.out.println("Uso:");
			System.out.println("\tjava " + GeradorDeRelatorios.class.getName() + " <algoritmo> <critério de ordenação> <critério de filtragem> <parâmetro de filtragem>");
			System.out.println("Onde:");
			System.out.println("\talgoritmo: 'quick' ou 'insertion'");
			System.out.println("\tcriterio de ordenação: 'preco_c' ou 'descricao_c' ou 'estoque_c'");
			System.out.println("\tcriterio de filtragem: 'todos' ou 'estoque_menor_igual' ou 'categoria_igual' ou 'preco_entre' ou 'com_palavra'"); 
			System.out.println("\tparâmetro de filtragem: argumentos adicionais necessários para a filtragem, se for passar mais de um, utilize '10,20' por exemplo."); 
			System.out.println();
			System.exit(1);
		}

		String opcao_algoritmo = args[0];
		String opcao_criterio_ord = args[1];
		String opcao_criterio_filtro = args[2];
		String opcao_parametro_filtro = args[3];
        //FIM DA ENTRADA DO USUARIO

        CSVReader csvReader = new CSVReader();
        Produto[] produtos = csvReader.readCSV("produtos.csv");

        GeradorDeRelatorios gerador = new GeradorDeRelatorios();
        gerador.gerarRelatorio(produtos, opcao_algoritmo, opcao_criterio_ord, opcao_criterio_filtro, opcao_parametro_filtro);
    }
}
