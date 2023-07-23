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

    public void gerarRelatorio(Produto[] produtos, String algoritmo, String criterio, String filtro, String parametro) {
        
        Ordenar ordenar = new Ordenar();
        produtos = ordenar.ordena(produtos, algoritmo, criterio);


        try (PrintWriter writer = new PrintWriter("saida" + ".html")) {
            writer.println("<html><head><title>Relatório de Produtos</title></head><body>");
            writer.println("<h1>Relatório de Produtos</h1>");
            writer.println("<table border=\"1\">");
            writer.println("<tr><th>ID</th><th>DESCRIÇÃO</th><th>CATEGORIA</th><th>QUANTIDADE_ESTOQUE</th><th>PREÇO</th><th>NEGRITO</th><th>ITALICO</th><th>COR</th></tr>");

            for (Produto p : produtos) {
                //filtro
                if (filtro.equals(FILTRO_ESTOQUE_MENOR_OU_IQUAL_A)){
                    if (p.getQtdEstoque() > Integer.parseInt(parametro)) continue;
                }
                if (filtro.equals(FILTRO_CATEGORIA_IGUAL_A)){
                    if (!(p.getCategoria().equals(parametro))) continue;
                }

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
            System.out.println("Relatorio gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gerar o relatório: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //INICIO ENTRADA DO USUARIO
        if(args.length < 4){

			System.out.println("Uso:");
			System.out.println("\tjava " + GeradorDeRelatorios.class.getName() + " <algoritmo> <critério de ordenação> <critério de filtragem> <parâmetro de filtragem> <opções de formatação>");
			System.out.println("Onde:");
			System.out.println("\talgoritmo: 'quick' ou 'insertion'");
			System.out.println("\tcriterio de ordenação: 'preco_c' ou 'descricao_c' ou 'estoque_c'");
			System.out.println("\tcriterio de filtragem: 'todos' ou 'estoque_menor_igual' ou 'categoria_igual'"); 
			System.out.println("\tparâmetro de filtragem: argumentos adicionais necessários para a filtragem"); 
			System.out.println("\topções de formatação: 'negrito' e/ou 'italico'");
			System.out.println();
			System.exit(1);
		}

		String opcao_algoritmo = args[0];
		String opcao_criterio_ord = args[1];
		String opcao_criterio_filtro = args[2];
		String opcao_parametro_filtro = args[3];
		
		String [] opcoes_formatacao = new String[2];
		opcoes_formatacao[0] = args[4];
        if (args.length <= 5) opcoes_formatacao[1] = "";
        else opcoes_formatacao[1] = args[5];
		

        boolean negrito = false;
        boolean italico = false;

        if (opcoes_formatacao[0].equals("negrito") || opcoes_formatacao[1].equals("negrito")) negrito = true;
        if (opcoes_formatacao[0].equals("italico") || opcoes_formatacao[1].equals("italico")) italico = true;

        System.out.println(negrito + " " + italico);

        //FIM DA ENTRADA DO USUARIO

        CSVReader csvReader = new CSVReader();
        Produto[] produtos = csvReader.readCSV("produtos.csv");

        GeradorDeRelatorios gerador = new GeradorDeRelatorios();
        gerador.gerarRelatorio(produtos, opcao_algoritmo, opcao_criterio_ord, opcao_criterio_filtro, opcao_parametro_filtro);
    }
}
