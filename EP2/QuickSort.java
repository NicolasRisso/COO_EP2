public class QuickSort implements AlgoritmoOrdenacao {

    private Produto [] produtos;
    private String criterio;
    private int ini;
    private int fim;

    //arrumar codigo
    @Override
    public Produto[] ordenar(Produto[] produtos, String criterio, int ini, int fim) {
        this.produtos = produtos;
        this.criterio = criterio;
        this.ini = ini;
        this.fim = fim;
        ordena(0, produtos.length - 1);
        return produtos;
    }

    private int particiona(int ini, int fim){

		Produto x = produtos[ini];
		int i = (ini - 1);
		int j = (fim + 1);

		while(true){

			if(criterio.equals(CRIT_DESC_CRESC)){

				do{ 
					j--;

				} while(produtos[j].getDescricao().compareToIgnoreCase(x.getDescricao()) > 0);
			
				do{
					i++;

				} while(produtos[i].getDescricao().compareToIgnoreCase(x.getDescricao()) < 0);
			}
			else if(criterio.equals(CRIT_PRECO_CRESC)){

				do{ 
					j--;

				} while(produtos[j].getPreco() > x.getPreco());
			
				do{
					i++;

				} while(produtos[i].getPreco() < x.getPreco());
			}

			else if(criterio.equals(CRIT_ESTOQUE_CRESC)){

				do{ 
					j--;

				} while(produtos[j].getQtdEstoque() > x.getQtdEstoque());
			
				do{
					i++;

				} while(produtos[i].getQtdEstoque() < x.getQtdEstoque());

			}
			else{

				throw new RuntimeException("Criterio invalido!");
			}

			if(i < j){
				Produto temp = produtos[i];
				produtos[i] = produtos[j]; 				
				produtos[j] = temp;
			}
			else return j;
		}
	}

    private void ordena(int ini, int fim){

        if(ini < fim) {

            int q = particiona(ini, fim);
            
            ordena(ini, q);
            ordena(q + 1, fim);
        }
	}
}