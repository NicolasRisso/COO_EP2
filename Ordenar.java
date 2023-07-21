public class Ordenar {

    public Produto[] ordena(Produto[] produtos, String algoritmo, String criterio) {
        return ordena(produtos, algoritmo, criterio, 0, produtos.length - 1);
    }

    public Produto[] ordena(Produto[] produtos, String algoritmo, String criterio, int ini, int fim) {
        AlgoritmoOrdenacao algoritmoOrdenacao;
        algoritmoOrdenacao = new InsertionSort(); //Inicia o algoritmo como insertion sort por padrão para utiliza-lo na busca do if.
        if (algoritmo.equals(algoritmoOrdenacao.ALG_QUICKSORT)) {
            algoritmoOrdenacao = new QuickSort();
        }

        return algoritmoOrdenacao.ordenar(produtos, criterio, ini, fim);
    }
}
