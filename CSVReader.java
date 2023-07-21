import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader{

    public Produto[] readCSV(String path){
        String csvFile = path;
        String csvSplitBy = ",";

        List<Produto> produtos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Ignorar a primeira linha (cabeçalhos)
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                int id = Integer.parseInt(data[0].trim());
                String descricao = data[1].trim();
                String categoria = data[2].trim();
                int qtdEstoque = Integer.parseInt(data[3].trim());
                double preco = Double.parseDouble(data[4].trim());
                boolean negrito = Boolean.parseBoolean(data[5].trim());
                boolean italico = Boolean.parseBoolean(data[6].trim());
                String cor = data[7].trim();
                produtos.add(new ProdutoPadrao(id, descricao, categoria, qtdEstoque, preco, negrito, italico, cor));
            }
        }catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
            return null;
        }

        return produtos.toArray(new Produto[0]);
    }
}