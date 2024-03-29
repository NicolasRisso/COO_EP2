public class InsertionSort implements AlgoritmoOrdenacao {

    @Override
    public Produto[] ordenar(Produto[] produtos, String criterio, int ini, int fim) throws RuntimeException {
        for (int i = ini; i <= fim; i++) {
            Produto x = produtos[i];
            int j = i - 1;

            while (j >= ini) {
                if (criterio.equals(AlgoritmoOrdenacao.CRIT_DESC_CRESC)) {
                    if (x.getDescricao().compareToIgnoreCase(produtos[j].getDescricao()) < 0) {
                        produtos[j + 1] = produtos[j];
                        j--;
                    } else {
                        break;
                    }
                } else if (criterio.equals(AlgoritmoOrdenacao.CRIT_PRECO_CRESC)) {
                    if (x.getPreco() < produtos[j].getPreco()) {
                        produtos[j + 1] = produtos[j];
                        j--;
                    } else {
                        break;
                    }
                } else if (criterio.equals(AlgoritmoOrdenacao.CRIT_ESTOQUE_CRESC)) {
                    if (x.getQtdEstoque() < produtos[j].getQtdEstoque()) {
                        produtos[j + 1] = produtos[j];
                        j--;
                    } else {
                        break;
                    }
                } else {
                    throw new RuntimeException("Criterio invalido!");
                }
            }

            produtos[j + 1] = x;
        }

        return produtos;
    }

    @Override
    public Produto[] ordenar(Produto[] produtos) {
        return ordenar(produtos, AlgoritmoOrdenacao.CRIT_PRECO_CRESC, 0, produtos.length - 1);
    }
}
