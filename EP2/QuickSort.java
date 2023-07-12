public class QuickSort implements AlgoritmoOrdenacao {

    //arrumar codigo
    @Override
    public void ordenar(Produto[] produtos, String criterio) {
        if(ini < fim) {

            int q = particiona(ini, fim);
            
            ordena(ini, q);
            ordena(q + 1, fim);
        }
    }
}