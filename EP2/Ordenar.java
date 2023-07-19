public class Ordenar {

    Produto[] ordena(Produto[] produtos, String criterio, int ini, int fim, String algoritmo){
        if(algoritmo.equals(ALG_INSERTIONSORT)){
            AlgoritmoOrdenacao insertionSort = new InsertionSort();
            return insertionSort.ordena(produtos, criterio, ini, fim);
        }else{
            AlgoritmoOrdenacao quickSort = new QuickSort();
            return quickSort.ordena(produtos, criterio, ini, fim);
        }
    }
}