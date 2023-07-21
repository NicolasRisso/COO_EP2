public class ProdutoPadrao implements Produto {
    private int id;
    private String descricao;
    private String categoria;
    private int qtdEstoque;
    private double preco;
    private boolean negrito;
    private boolean italico;
    private String cor;

    public ProdutoPadrao(int id, String descricao, String categoria, int qtdEstoque, double preco, boolean negrito, boolean italico, String cor) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.qtdEstoque = qtdEstoque;
        this.preco = preco;
        this.negrito = negrito;
        this.italico = italico;
        this.cor = cor;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getCategoria() {
        return categoria;
    }

    @Override
    public int getQtdEstoque() {
        return qtdEstoque;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public boolean isNegrito() {
        return negrito;
    }

    @Override
    public boolean isItalico() {
        return italico;
    }

    @Override
    public String getCor() {
        return cor;
    }
}
