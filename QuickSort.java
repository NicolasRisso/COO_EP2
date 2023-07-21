public class QuickSort implements AlgoritmoOrdenacao {

    @Override
    public Produto[] ordenar(Produto[] produtos, String criterio, int ini, int fim) {
        if (ini < fim) {
            int posicaoPivo = particionar(produtos, criterio, ini, fim);
            ordenar(produtos, criterio, ini, posicaoPivo - 1);
            ordenar(produtos, criterio, posicaoPivo + 1, fim);
        }
        return produtos;
    }

    private int particionar(Produto[] produtos, String criterio, int ini, int fim) {
        Produto pivo = produtos[ini];
        int i = ini + 1;
        int f = fim;

        while (i <= f) {
            if (criterio.equals(AlgoritmoOrdenacao.CRIT_DESC_CRESC)) {
                if (produtos[i].getDescricao().compareToIgnoreCase(pivo.getDescricao()) <= 0) {
                    i++;
                } else if (pivo.getDescricao().compareToIgnoreCase(produtos[f].getDescricao()) < 0) {
                    f--;
                } else {
                    Produto troca = produtos[i];
                    produtos[i] = produtos[f];
                    produtos[f] = troca;
                    i++;
                    f--;
                }
            } else if (criterio.equals(AlgoritmoOrdenacao.CRIT_PRECO_CRESC)) {
                if (produtos[i].getPreco() <= pivo.getPreco()) {
                    i++;
                } else if (pivo.getPreco() < produtos[f].getPreco()) {
                    f--;
                } else {
                    Produto troca = produtos[i];
                    produtos[i] = produtos[f];
                    produtos[f] = troca;
                    i++;
                    f--;
                }
            } else if (criterio.equals(AlgoritmoOrdenacao.CRIT_ESTOQUE_CRESC)) {
                if (produtos[i].getQtdEstoque() <= pivo.getQtdEstoque()) {
                    i++;
                } else if (pivo.getQtdEstoque() < produtos[f].getQtdEstoque()) {
                    f--;
                } else {
                    Produto troca = produtos[i];
                    produtos[i] = produtos[f];
                    produtos[f] = troca;
                    i++;
                    f--;
                }
            } else {
                throw new RuntimeException("Criterio invalido!");
            }
        }

        produtos[ini] = produtos[f];
        produtos[f] = pivo;
        return f;
    }

    @Override
    public Produto[] ordenar(Produto[] produtos) {
        // Implementação do segundo método de ordenação
        return ordenar(produtos, AlgoritmoOrdenacao.CRIT_PRECO_CRESC, 0, produtos.length - 1);
    }
}
