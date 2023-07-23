public class ProdutoFormatado implements Produto{
    private Produto produto;

    public ProdutoFormatado(Produto produto){
        this.produto = produto;
    }

    @Override
    public int getId() {
        return produto.getId();
    }

    @Override
    public String getDescricao() {
        String formatacao = "";
        String formatacao_saida = "";
        if (produto.isItalico()){
            formatacao = formatacao + "<i>";
            formatacao_saida = formatacao_saida + "</i>";
        }if (produto.isNegrito()){
            formatacao = formatacao + "<b>";
            formatacao_saida = formatacao_saida + "</b>";
        }
        return (formatacao + "<span style=\"color:" + produto.getCor() + "\">" + produto.getDescricao() + "</span>" + formatacao_saida);
    }

    @Override
    public String getCategoria() {
        return produto.getCategoria();
    }

    @Override
    public int getQtdEstoque() {
        return produto.getQtdEstoque();
    }

    @Override
    public double getPreco() {
        return produto.getPreco();
    }

    @Override
    public boolean isNegrito() {
        return produto.isNegrito();
    }

    @Override
    public boolean isItalico() {
        return produto.isItalico();
    }

    @Override
    public String getCor() {
        return produto.getCor();
    }
}