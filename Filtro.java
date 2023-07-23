public class Filtro {
    
    public static final String FILTRO_ESTOQUE_MENOR_OU_IQUAL_A = "estoque_menor_igual";
	public static final String FILTRO_CATEGORIA_IGUAL_A = "categoria_igual";
    public static final String FILTRO_PRECO_ENTRE_X_Y = "preco_entre";
    public static final String FILTRO_COM_PALAVRA_NA_DESC = "com_palavra";

    private Produto produto;
    private String filtro;
    private String parametro;

    public Filtro(Produto produto, String filtro, String parametro){
        this.produto = produto;
        this.filtro = filtro;
        this.parametro = parametro;
    }

    public Boolean filtrar(){

        //Setup para realizar o filtro
        int[] maxmin = new int[2];
        if (filtro.equals(FILTRO_PRECO_ENTRE_X_Y)){
            String[] partes = parametro.split(",");
            for (int i = 0; i < partes.length; i++) maxmin[i] = Integer.parseInt(partes[i]);
        }

        //Filtro em si
        if (filtro.equals(FILTRO_ESTOQUE_MENOR_OU_IQUAL_A)){
            if (produto.getQtdEstoque() > Integer.parseInt(parametro)) return false;
        }
        if (filtro.equals(FILTRO_CATEGORIA_IGUAL_A)){
            if (!(produto.getCategoria().equals(parametro))) return false;
        }
        if (filtro.equals(FILTRO_PRECO_ENTRE_X_Y)){
            if (produto.getPreco() < maxmin[0] || produto.getPreco() > maxmin[1]) return false;
        }
        if (filtro.equals(FILTRO_COM_PALAVRA_NA_DESC)){
            if (!(produto.getDescricao().contains(parametro))) return false;
        }
        return true;
    }

}
