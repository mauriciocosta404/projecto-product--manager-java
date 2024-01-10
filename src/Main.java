import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Producto> estoque = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();

        while (true) {
            System.out.println("### MENU ###");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Realizar Venda");
            System.out.println("3. Ver Quantidade em Estoque");
            System.out.println("4. Ver Todas as Vendas Realizadas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    cadastrarProducto(scanner, estoque);
                    break;
                case 2:
                    realizarVenda(scanner, estoque, vendas);
                    break;
                case 3:
                    verQuantidadeEstoque(estoque);
                    break;
                case 4:
                    verTodasVendas(vendas);
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void verQuantidadeEstoque(List<Producto> estoque) {
        System.out.println("### QUANTIDADE EM ESTOQUE ###");
        for (Producto produto : estoque) {
            System.out.println(produto.getNome() + " - Quantidade: " + produto.getQuantidadeEmEstoque());
        }
    }

    private static void verTodasVendas(List<Venda> vendas) {
        System.out.println("### VENDAS REALIZADAS ###");
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
            return;
        }
        for (Venda venda : vendas) {
            System.out.println("ID da Venda: " + venda.getIdVenda() +
                    ", Cliente: " + venda.getCliente().getNome() +
                    ", Total: R$" + venda.calcularTotal());
        }
    }
    private static void cadastrarProducto(Scanner scanner, List<Producto> estoque) {
        Producto producto = new Producto();
        System.out.println("### CADASTRAR PRODUTO ###");
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        producto.setNome(nome);
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        producto.setPreco(preco);
        System.out.print("Digite a quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        producto.setQuantidadeEmEstoque(quantidade);

        estoque.add(producto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void realizarVenda(Scanner scanner, List<Producto> estoque, List<Venda> vendas) {
        if (estoque.isEmpty()) {
            System.out.println("Não há produtos em estoque para realizar vendas.");
            return;
        }

        System.out.println("### REALIZAR VENDA ###");
        System.out.println("Produtos disponíveis:");
        for (int i = 0; i < estoque.size(); i++) {
            Producto producto = estoque.get(i);
            System.out.println((i + 1) + ". " + producto.getNome() + " - R$" + producto.getPreco());
        }

        System.out.print("Escolha o número do produto para vender: ");
        int escolhaProducto = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (escolhaProducto < 1 || escolhaProducto > estoque.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Producto productoSelecionado = estoque.get(escolhaProducto - 1);
        if (productoSelecionado.getQuantidadeEmEstoque() <= 0) {
            System.out.println("Este produto está fora de estoque.");
            return;
        }

        Cliente cliente = new Cliente();
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        cliente.setNome(nomeCliente);

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setIdVenda(vendas.size() + 1);
        venda.adicionarProduto(productoSelecionado);
        vendas.add(venda);

        productoSelecionado.setQuantidadeEmEstoque(productoSelecionado.getQuantidadeEmEstoque() - 1);

        System.out.println("Venda realizada com sucesso!");
    }
}