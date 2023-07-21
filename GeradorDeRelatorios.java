import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GeradorDeRelatorios {

    public static final int FORMATO_PADRAO  = 0b0000;
    public static final int FORMATO_NEGRITO = 0b0001;
    public static final int FORMATO_ITALICO = 0b0010;

    public void gerarRelatorio(Produto[] produtos, String algoritmo, String criterio) {

        Ordenar ordenar = new Ordenar();
        produtos = ordenar.ordena(produtos, algoritmo, criterio); //Chama o algoritmo de ordenacao escolhido sem utilizar filtros para ini e fim.

        try (PrintWriter writer = new PrintWriter(new File("relatorio_" + criterio + ".html"))) {
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
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao gerar o relatório: " + e.getMessage());
        }
    }

public static void main(String[] args) {

    //LEITOR DE ENTRADA DO USUARIO --------------------------

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
    opcoes_formatacao[0] = args.length > 4 ? args[4] : null;
    opcoes_formatacao[1] = args.length > 5 ? args[5] : null;
    int formato = FORMATO_PADRAO;
    
    for(int i = 0; i < opcoes_formatacao.length; i++) {

        String op = opcoes_formatacao[i];
        formato |= (op != null ? op.equals("negrito") ? FORMATO_NEGRITO : (op.equals("italico") ? FORMATO_ITALICO : 0) : 0); 
    }

    //FINAL ENTRADA DO USUARIO

    Produto[] produtos = new Produto[32];
    produtos[0] = new ProdutoPadrao(1, "O Hobbit", "Livros", 2, 34.90, true, false, "#FF0000");
    produtos[1] = new ProdutoPadrao(2, "Notebook Core i7", "Informatica", 5, 1999.90, true, true, "#000000");
    produtos[2] = new ProdutoPadrao(3, "Resident Evil 4", "Games", 7, 79.90, false, true, "#00FF00");
    produtos[3] = new ProdutoPadrao(4, "iPhone", "Telefonia", 8, 4999.90, false, false, "#000000");
    produtos[4] = new ProdutoPadrao(5, "Calculo I", "Livros", 20, 55.00, true, false, "#000000");
    produtos[5] = new ProdutoPadrao(6, "Power Glove", "Games", 3, 499.90, false, true, "#00FF00");
    produtos[6] = new ProdutoPadrao(7, "Microsoft HoloLens", "Informatica", 1, 19900.00, true, true, "#000000");
    produtos[7] = new ProdutoPadrao(8, "OpenGL Programming Guide", "Livros", 4, 89.90, true, false, "#000000");
    produtos[8] = new ProdutoPadrao(9, "Vectrex", "Games", 1, 799.90, false, true, "#00FF00");
    produtos[9] = new ProdutoPadrao(10, "Carregador iPhone", "Telefonia", 15, 499.90, false, false, "#000000");
    produtos[10] = new ProdutoPadrao(11, "Introduction to Algorithms", "Livros", 7, 315.00, true, false, "#000000");
    produtos[11] = new ProdutoPadrao(12, "Daytona USA (Arcade)", "Games", 1, 12000.00, false, true, "#00FF00");
    produtos[12] = new ProdutoPadrao(13, "Neuromancer", "Livros", 5, 45.00, true, false, "#000000");
    produtos[13] = new ProdutoPadrao(14, "Nokia 3100", "Telefonia", 4, 249.99, false, false, "#000000");
    produtos[14] = new ProdutoPadrao(15, "Oculus Rift", "Games", 1, 3600.00, false, true, "#00FF00");
    produtos[15] = new ProdutoPadrao(16, "Trackball Logitech", "Informatica", 1, 250.00, true, true, "#000000");
    produtos[16] = new ProdutoPadrao(17, "After Burner II (Arcade)", "Games", 2, 8900.00, false, true, "#00FF00");
    produtos[17] = new ProdutoPadrao(18, "Assembly for Dummies", "Livros", 30, 129.90, true, false, "#000000");
    produtos[18] = new ProdutoPadrao(19, "iPhone (usado)", "Telefonia", 3, 3999.90, false, false, "#000000");
    produtos[19] = new ProdutoPadrao(20, "Game Programming Patterns", "Livros", 1, 299.90, true, false, "#FF0000");
    produtos[20] = new ProdutoPadrao(21, "Playstation 2", "Games", 10, 499.90, false, true, "#00FF00");
    produtos[21] = new ProdutoPadrao(22, "Carregador Nokia", "Telefonia", 14, 89.00, false, false, "#000000");
    produtos[22] = new ProdutoPadrao(23, "Placa Aceleradora Voodoo 2", "Informatica", 4, 189.00, true, true, "#0000FF");
    produtos[23] = new ProdutoPadrao(24, "Stunts", "Games", 3, 19.90, false, true, "#00FF00");
    produtos[24] = new ProdutoPadrao(25, "Carregador Generico", "Telefonia", 9, 30.00, false, false, "#000000");
    produtos[25] = new ProdutoPadrao(26, "Monitor VGA 14 polegadas", "Informatica", 2, 199.90, true, true, "#0000FF");
    produtos[26] = new ProdutoPadrao(27, "Nokia N-Gage", "Telefonia", 9, 699.00, false, false, "#000000");
    produtos[27] = new ProdutoPadrao(28, "Disquetes Maxell 5.25 polegadas (caixa com 10 unidades)", "Informatica", 23, 49.00, true, true, "#0000FF");
    produtos[28] = new ProdutoPadrao(29, "Alone in The Dark", "Games", 11, 59.00, false, true, "#00FF00");
    produtos[29] = new ProdutoPadrao(30, "The Art of Computer Programming Vol. 1", "Livros", 3, 240.00, true, false, "#000000");
    produtos[30] = new ProdutoPadrao(31, "The Art of Computer Programming Vol. 2", "Livros", 2, 200.00, true, false, "#FF0000");
    produtos[31] = new ProdutoPadrao(32, "The Art of Computer Programming Vol. 3", "Livros", 4, 270.00, true, false, "#000000");

    GeradorDeRelatorios gerador = new GeradorDeRelatorios();
    gerador.gerarRelatorio(produtos, opcao_criterio_ord, AlgoritmoOrdenacao.CRIT_DESC_CRESC);
    gerador.gerarRelatorio(produtos, opcao_criterio_ord, AlgoritmoOrdenacao.CRIT_PRECO_CRESC);
    gerador.gerarRelatorio(produtos, opcao_criterio_ord, AlgoritmoOrdenacao.CRIT_ESTOQUE_CRESC);
}
}
