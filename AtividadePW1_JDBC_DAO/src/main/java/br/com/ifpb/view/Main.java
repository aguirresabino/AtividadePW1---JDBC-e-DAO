/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.view;

/**
 *
 * @author aguirre
 */

import br.com.ifpb.controller.ClienteCtrl;
import br.com.ifpb.controller.PedidoCtrl;
import br.com.ifpb.model.Cliente;
import br.com.ifpb.model.ClienteAtivoEnum;
import br.com.ifpb.model.Pedido;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Cliente cliente = null;

    public static void main(String[] args) {

        ArrayList<Cliente> clientes = new ArrayList<>();

        if(clientes == null) {
            cadastroCliente();
        }

        while (true) {
            System.out.printf("Digite 1 para Cadastro ou 2 para Login\n");
            int aux = scanner.nextInt();

            switch (aux) {
                case 1:
                    cliente = cadastroCliente();
                    break;
                case 2:
                    cliente = login();
                    break;
            }

            if (cliente != null)
                break;

            System.out.printf("Credenciais Inalidas, Tente Novamente\n");
        }

        int aux = 0;
        do {
            System.out.println("O que Deseja: ");
            System.out.println("1 - Cadastrar Pedido");
            System.out.println("2 - Listar Pedidos");
            System.out.println("3 - Remover Pedido");
            System.out.println("4 - Atualizar Pedido");
            System.out.println("5 - Sair.");

            aux = scanner.nextInt();

            switch (aux){
                case 1:
                    cadastroPedido();
                    break;
                case 2:
                    listarPedidos(cliente);
                    break;
                case 3:
                    removerPedido();
                    break;
                case 4:
                    alterarPedido();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.printf("Opçao Invalida, Tente Novamente\n");
                    break;
            }
        } while (aux != 5);

    }

    private static Cliente login() {

        System.out.println("Digite seu nome para autenticar: ");
        String nome = scanner.next();

        for (Cliente cliente: listCliente())
            if (cliente.getNome().equals(nome))
                return cliente;

        return null;
    }

    private static Cliente cadastroCliente() {

        Cliente cliente = new Cliente();

        System.out.printf("\n\nCadastro:\n\n");

        System.out.println("Digite o nome");
        cliente.setNome(scanner.next());
        System.out.println("Digite seu Documento");
        cliente.setDocumento(scanner.next());
        System.out.println("Digite seu Saldo");
        cliente.setSaldo(scanner.nextDouble());
        cliente.setAtivo(ClienteAtivoEnum.ATIVO);

        new ClienteCtrl().incluir(cliente);

        return cliente;
    }

    public static List<Cliente> listCliente() {

        return new ClienteCtrl().listar();
    }

    private static Pedido cadastroPedido() {

        Pedido pedido = new Pedido();

        System.out.println("Digite o Valor do Pedido");
        pedido.setValor(scanner.nextDouble());
        pedido.setCliente(cliente);
        pedido.setData(LocalDate.now());

        new PedidoCtrl().incluir(pedido);

        return pedido;
    }

    private static void listarPedidos(Cliente cliente) {

        System.out.println(new PedidoCtrl().getPedidoByCliente(cliente));
    }

    private static void removerPedido() {

        Pedido pedido = new Pedido();

        System.out.println("Digite o Id do Pedido");
        int aux = scanner.nextInt();

        pedido.setId(aux);

        new PedidoCtrl().excluir(pedido);
    }

    private static void alterarPedido() {

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setData(LocalDate.now());

        System.out.println("Digite o Id do Pedido");
        int aux = scanner.nextInt();
        System.out.println("Digite o Valor do Pedido");
        double valor = scanner.nextDouble();

        pedido.setId(aux);
        pedido.setValor(valor);

        new PedidoCtrl().alterar(pedido);
    }
}
