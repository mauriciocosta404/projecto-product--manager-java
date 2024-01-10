import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int idVenda;
    private Cliente cliente;
    private List<Producto> produtos;

    // Construtor
    public Venda() {
        this.produtos = new ArrayList<>(); // Inicializa a lista de produtos
    }

    // Getters e Setters
    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Producto> produtos) {
        this.produtos = produtos;
    }

    // Método para adicionar um produto à venda
    public void adicionarProduto(Producto produto) {
        this.produtos.add(produto);
    }

    // Método para remover um produto da venda
    public void removerProduto(Producto produto) {
        this.produtos.remove(produto);
    }

    // Método para calcular o total da venda
    public double calcularTotal() {
        double total = 0;
        for (Producto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }
}
