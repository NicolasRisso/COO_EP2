public class Main {
    public static void main(String[] args) {
        //INICIO ENTRADA DO USUARIO
        if(args.length < 4){

			System.out.println("Uso:");
			System.out.println("\tjava " + GeradorDeRelatorios.class.getName() + " <algoritmo> <critério de ordenação> <critério de filtragem> <parâmetro de filtragem>");
			System.out.println("Onde:");
			System.out.println("\talgoritmo: 'quick' ou 'insertion'");
			System.out.println("\tcriterio de ordenacao: 'preco_c' ou 'descricao_c' ou 'estoque_c'");
			System.out.println("\tcriterio de filtragem: 'todos' ou 'estoque_menor_igual' ou 'categoria_igual' ou 'preco_entre' ou 'com_palavra'"); 
			System.out.println("\tparametro de filtragem: argumentos adicionais necessarios para a filtragem, se for passar mais de um, utilize '10,20' por exemplo."); 
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

        GeradorDeRelatorios gerador = new GeradorDeRelatorios(produtos, opcao_algoritmo, opcao_criterio_ord, opcao_criterio_filtro, opcao_parametro_filtro);
        gerador.gerarRelatorio();
    }
}
