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
        String formatacao;
        String formatacao_saida;
        if (produto.isItalico()){
            formatacao = "<i>";
            formatacao_saida = "</i>";
        }else if (produto.isNegrito()){
            formatacao = "<b>";
            formatacao_saida = "</b>";
        }else if (produto.isNegrito() && produto.isItalico()){
            formatacao = "<b><i>";
            formatacao_saida = "</b></i>";
        }  
        return "<span style=\"color:" + produto.getCor() + "\">" + produto.getDescricao() + "</span>";
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