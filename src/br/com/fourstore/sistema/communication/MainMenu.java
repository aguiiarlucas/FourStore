package br.com.fourstore.sistema.communication;

import br.com.fourstore.sistema.controller.ClientController;
import br.com.fourstore.sistema.controller.MenuController;
import br.com.fourstore.sistema.controller.ProductController;
import br.com.fourstore.sistema.controller.SaleController;
import br.com.fourstore.sistema.enums.PaymentMethodEnum;
import br.com.fourstore.sistema.model.Product;
import br.com.fourstore.sistema.utils.Validations;

import java.util.Objects;
import java.util.Scanner;

public class MainMenu {

    private Scanner sc;
    private MenuController menuController;
    private SaleController saleController;
    private ClientController clientController;
    private ProductController productController;


    private Validations validations;

    private Product product;




    public MainMenu() {
        this.menuController = new MenuController();
        this.sc = new Scanner(System.in);
        this.saleController = new SaleController();
        this.clientController = new ClientController();
        this.productController = new ProductController();
        this.validations = new Validations();
    }

    public void mainMenu() throws Exception {
        firstMenu();
    }

    private void firstMenu() throws Exception {
        int opc = -1;
        String input;

        while (opc != 0) {
            System.out.println("\n\n==========FOURSTORE=============||");
            System.out.println("1 - Products                    ||");
            System.out.println("2 - Sale                      ||");
            System.out.println("0 - Exit              ||");
            System.out.print("Digite uma das opções: ");
            input = sc.next();
            System.out.println("----------------------------------\n");

            opc = menuController.validationRegexMenu(input, "[0-6]");

            switch (opc) {
                case 0 -> System.out.println("\n Sistema encerrado ");
                case 1 -> this.menuProducts();
                case 2 -> this.menuSale();
                default -> System.out.println("\n Opção invalida , tente novamente. \n");
            }

        }
    }

    private void menuSale() throws Exception {
        int opc = -1;
        String input;

        while (opc != 0) {
            System.out.println("""
                    1 - Realizar Venda
                    2 - Visualizar Historico
                    0 - Exit""");
            input = sc.next();
            opc = menuController.validationRegexMenu(input, "[0-6]");
            switch (opc) {
                case 0 -> {
                    firstMenu();
                    break;
                }
                case 1 -> {
                    saleMenu();
                    break;
                }
                case 2 -> {
                    String result = saleController.consultSale();
                    System.out.println(result);
                    break;
                }
            }

        }
    }

    private void saleMenu() {
        String sku;
        int qtt = 0;
        int opc;
        saleController.clearCart();

        while (true) {
            while (true) {
                System.out.println("\nDigite o sku: ");
                sku = sc.next();
                if (Objects.equals(productController.getProductBySku(sku), "Nao existe um produto com o sku " + sku)) {
                    System.out.println("Produto nao existe");
                } else if (!(productController.validateSku(sku))) {
                    System.out.println("SKU nulo");
                } else {
                    break;
                }
            }
            while (true) {
                System.out.println("\n Insira a quantidade: ");
                qtt = sc.nextInt();
                if (qtt <= 1) {
                    System.out.println("Digite 1 ou mais ");
                } else if (!productController.haveStock(sku, qtt)) {
                    System.out.println("Quantidade maior que possuimo em estoque");
                    continue;
                } else {
                    break;
                }
            }
            ProductController.decrementProduct(sku, qtt);

            System.out.println(saleController.addToCart(qtt, sku));

            System.out.println("Deseja inserir um novo produto? \n 1- sim \n 2- não ");
            opc = sc.nextInt();
            if (opc == 1) {
                continue;
            } else if (opc == 2) {
                break;
            } else {
                System.out.println("Opcão invalida");
            }
        }

        int x;
        String cpf = null;
        String nome;

        while (true) {
            System.out.println("Deseja inserir o cpf ? 1 - S , 2 - N ");
            x = sc.nextInt();
            if (x == 1) {
                while (true) {
                    System.out.println("Digite o cpf: ");
                    cpf = sc.next();
                    if (!menuController.validarCpf(cpf)) {
                        System.out.println("O cpf deve ter o  formato xxx.xxx.xxx-xx");
                    } else if (menuController.validarCpf(cpf)) {
                        System.out.println("Digite o nome do cliente ");
                        nome = sc.next();
                        clientController.clientIsRegister(nome, cpf);
                        break;
                    } else {
                        System.out.println("CPF invalido");
                    }
                }
                break;
            } else if (x == 2) {
                break;
            } else {
                System.out.println("Insira  uma resposta valida");

            }
        }
        Integer opcao;
        String creditoCard;
        String debitCard;
        String pix;
        PaymentMethodEnum paymentmethod;

        while (true) {
            System.out.println(
                    """
                            Digite a forma de pagamento:\s
                            1- cartao de credito\s
                            2 -cartao de debito\s
                            3- dinheiro\s
                            4-pix""");
            opcao = sc.nextInt();

            switch (opc) {
                case 1:
                    paymentmethod = PaymentMethodEnum.CREDITCARD;
                    System.out.println("Digite o numero do cartão");
                    creditoCard = sc.next();
                    sc.nextLine();
                    if (!menuController.validationCard(creditoCard)) {
                        System.out.println("Cartão invalido");
                        continue;
                    }
                    break;
                case 2:
                    paymentmethod = PaymentMethodEnum.DEBITCARD;
                    debitCard = sc.next();
                    sc.nextLine();
                    if (!menuController.validationCard(debitCard)) {
                        System.out.println("Cartão invalido");
                        continue;
                    }
                    break;
                case 3:
                    paymentmethod = PaymentMethodEnum.MONEY;
                    break;
                case 4:
                    paymentmethod = PaymentMethodEnum.PIX;
                    pix = sc.next();
                    if (cpf == null) {
                        clientController.registerPix(pix);
                    } else {
                        clientController.registerPixByCpf(cpf, pix);
                        continue;
                    }
                    break;
                default:
                    System.out.println("Opção Invalida");
                    continue;
            }
            break;
        }
        if (cpf != null) {
            System.out.println(saleController.saleRegister(paymentmethod, cpf));
        } else {
            System.out.println(saleController.saleRegister(paymentmethod));
        }

    }


    private void menuProducts() throws Exception {
        int opc = -1;
        String input;

        while (opc != 0) {
            System.out.println("""
                    1 - Cadastrar Produto
                    2 - Buscar Produto por id
                    3 - Buscar Produto por sku
                    4 - Lista Produtos
                    5 - Atualizar Produto por id
                    6 - Atualizar produto por sku
                    7 - Excluir Produto pelo id
                    8 - Excluir Produto pelo sku
                    0 - Para voltar""");

            input = sc.next();
            opc = menuController.validationRegexMenu(input, "[0-8]");


            switch (opc) {
                case 0: {
                    firstMenu();
                    break;
                }
                case 1: {
                    this.cadProduct();
                    break;
                }
                case 2: {
                    this.getProductById();
                    break;
                }
                case 3: {
                    this.getProductBySku();
                    break;
                }
                case 4: {
                    String aux = productController.listProducts();
                    System.out.println(aux);
                    break;
                }
                case 5: {
                    updateProductById();
                    break;
                }
                case 6: {
                    updateProductBySku();
                    break;
                }
                case 7: {
                    this.deleteProductById();
                    break;
                }
                case 8: {
                    this.deleteProductBySku();
                    break;
                }
                default:
                    System.out.println("\nOpcao Invalida. Tente Novamente \n");
            }
        }
    }


    private void updateProductBySku() {
        String sku;
        while (true) {
            System.out.println("\n insira o ID do produto:");
            sku = sc.next();
            String idIsValid = productController.getProductById(sku);
            if (idIsValid.equals("Não existe produto com este sku" + sku)) {
                System.out.println(idIsValid = ". Tente novamente");
                continue;
            }
            break;
        }
        this.updateProduct(null, null);
    }








    public void cadProduct() {
        String sku;
        boolean flag = true; // setando a condição de inicialização e finalizado do while.
        do {
            System.out.println("Insira o sku do produto");
            sku = sc.next();
            if (productController.validateSku(sku) && (!(productController.productIsRegistered(sku)))) {
                flag = false; // parar e proseguir  o codigo.
            } else {
                System.out.println("Sku invalido , tente novamente ");
            }

        } while (flag);

        System.out.println("Digite a descricao do produto: ");
        String description = sc.next();

        validations.gerarId();
        validations.regexInteger();
        validations.regexPurchasePrice();
        validations.regexSalePrice();

        String retorno = productController.cadProduct(String.valueOf(Validations.id), sku, description, Integer.parseInt(validations.regexInteger()),Double.parseDouble(validations.regexPurchasePrice()),Double.parseDouble(validations.regexSalePrice()));
        System.out.println(retorno);
    }



    public void updateProductById() {
        String id;
        while (true) {
            System.out.println("\n insira o ID do produto:");
            id = sc.next();
            String idIsValid = productController.getProductById(id);
            if (idIsValid.equals("Não existe produto com este id" + id)) {
                System.out.println(idIsValid = ". Tente novamente");
                continue;
            }
            break;
        }
        this.updateProduct(id, null);

    }

    public void updateProduct(String id, String sku) {
        Integer qtt = Integer.MAX_VALUE;
        double purchasePrice = 0.0;
        double salePrice = 0.0;
        String opc;
        boolean aux = true;

        while (aux) {
            System.out.println("\n Qual operação deseja fazer?");
            System.out.println("1 - Nova quantidade de produto em estoque");
            System.out.println("2 - Novo preço de compra");
            System.out.println("3 - Novo preço de venda");
            System.out.println("4 - Atualização de dados ");
            System.out.println("Insira uma opção");
            opc = sc.next();

            switch (opc) {
                case "1":
                    qtt = this.updateNewQuantityProduct();
                    break;
                case "2":
                    purchasePrice = this.updateNewPurchasePrice();
                    break;
                case "3":
                    salePrice = this.updateNewSalePrice();
                    break;
                case "4":
                    if (id != null) {
                        System.out.println(productController.updateProductById(id, qtt, purchasePrice, salePrice));
                        aux = false;
                    } else if (sku != null) {
                        System.out.println(productController.updateProductBySku(sku, qtt, purchasePrice, salePrice));
                    } else {
                        System.out.println("Não foi possível realizar a atualização dos dados. Tente novamente.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;

            }
        }
    }

    public Integer updateNewQuantityProduct() {
        int qtt;
        while (true) {
            System.out.println("Nova quantidade em estoque : ");
            qtt = sc.nextInt();
            if (qtt < 0) {
                System.out.println("Valor inválido. Tente novamente");
                continue;
            }
            break;
        }
        return qtt;
    }

    public Double updateNewPurchasePrice() {
        double purchasePrice;
        while (true) {
            System.out.println("Novo preço de compra: ");
            purchasePrice = sc.nextDouble();
            if (purchasePrice < 0.0) {
                System.out.println("Valor inválido. Tente novamente");
                continue;
            }
            break;
        }
        return purchasePrice;
    }

    public Double updateNewSalePrice() {
        double salePrice;
        while (true) {
            System.out.println("Novo preço de venda: ");
            salePrice = sc.nextDouble();
            if (salePrice < 0.0) {
                System.out.println("Valor inválido. Tente novamente");
                continue;
            }
            break;
        }
        return salePrice;
    }


    private void deleteProductById() {
        System.out.println("\n Enter a product id: ");
        String id = sc.next();
        System.out.println(productController.deleteProductById(id));
    }

    private void deleteProductBySku() {
        System.out.println("\nEnter product sku: ");
        String sku = sc.next();
        if (!(productController.validateSku(sku))) {
            System.out.println("Invalided Sku");
        } else System.out.println(productController.deleteProductBySku(sku) + "\n");
    }

    private void getProductBySku() {
        System.out.println("\nEnter product sku: ");
        String sku = sc.next();
        if (!(productController.validateSku(sku))) {
            System.out.println("Invalided Sku");
        } else
            System.out.println(productController.getProductBySku(sku) + "\n");
    }

    private void getProductById() {
        System.out.println("\n Enter a product id: ");
        String id = sc.next();
        System.out.println(productController.getProductById(id));
    }



}
