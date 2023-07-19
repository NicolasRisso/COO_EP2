public interface AlgoritmoOrdenacao {

    String CRIT_DESC_CRESC = "descricao_c";
    String CRIT_PRECO_CRESC = "preco_c";
    String CRIT_ESTOQUE_CRESC = "estoque_c";

    Produto[] ordenar(Produto[] produtos, String criterio, int ini, int fim);

    Produto[] ordenar2(Produto[] produtos);
}
