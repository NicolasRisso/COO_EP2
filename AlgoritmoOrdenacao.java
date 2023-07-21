public interface AlgoritmoOrdenacao {
    String CRIT_DESC_CRESC = "DESC_CRESC";
    String CRIT_PRECO_CRESC = "PRECO_CRESC";
    String CRIT_ESTOQUE_CRESC = "ESTOQUE_CRESC";

    String ALG_INSERTIONSORT = "quick";
	String ALG_QUICKSORT = "insertion";

    Produto[] ordenar(Produto[] produtos, String criterio, int ini, int fim);
    Produto[] ordenar(Produto[] produtos);
}
