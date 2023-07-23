public interface AlgoritmoOrdenacao {
	String CRIT_DESC_CRESC = "descricao_c";
	String CRIT_PRECO_CRESC = "preco_c";
	String CRIT_ESTOQUE_CRESC = "estoque_c";

    String ALG_INSERTIONSORT = "insertion";
	String ALG_QUICKSORT = "quick";

    Produto[] ordenar(Produto[] produtos, String criterio, int ini, int fim);
    Produto[] ordenar(Produto[] produtos);
}
