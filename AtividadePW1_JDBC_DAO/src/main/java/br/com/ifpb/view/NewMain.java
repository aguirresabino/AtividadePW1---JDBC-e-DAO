/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.view;

import br.com.ifpb.controller.ClienteCtrl;
import br.com.ifpb.controller.PedidoCtrl;
import br.com.ifpb.model.Cliente;
import br.com.ifpb.model.ClienteAtivoEnum;
import br.com.ifpb.model.Pedido;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author aguirre
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ClienteCtrl clienteCtrl = new ClienteCtrl();
        PedidoCtrl pedidoCtrl = new PedidoCtrl();
        Cliente cliente = null;
        Pedido pedido = null;
        
        System.out.println("Testando métodos implementados para Cliente e Pedido");
        System.out.println("_____________________________________________________");
        System.out.println("Cadastrando um Cliente");
        
        String nome, documento = null;
        Double saldo = null;
        System.out.print("Digite o nome do Cliente: ");
        nome = scan.next();
        System.out.print("Digite o documento do cliente: ");
        documento = scan.next();
        System.out.print("Digite o seu saldo: ");
        saldo = scan.nextDouble();
        
        cliente = new Cliente(nome, documento, saldo, ClienteAtivoEnum.ATIVO);
        if(clienteCtrl.incluir(cliente)){
            System.out.println("Cadastro realizado com sucessso!");
        }else{
            System.out.println("Cadastro de cliente não realizado!");
        }
        System.out.println("_____________________________________________________");
        
        System.out.println("\nCadastrando um pedido para o usuário criado.");
        pedido = new Pedido(LocalDate.now(), clienteCtrl.getClienteByDocumento(cliente.getDocumento()), 127.53);
        if(pedidoCtrl.incluir(pedido)){
            System.out.println("Pedido cadastrado!");
        }else{
            System.out.println("Pedido não foi cadastrado!");
        }
        System.out.println("_____________________________________________________");
        
        System.out.println("\nCadastrando outro pedido para o usuário criado.");
        pedido = new Pedido(LocalDate.now(), clienteCtrl.getClienteByDocumento(cliente.getDocumento()), 5000);
        if(pedidoCtrl.incluir(pedido)){
            System.out.println("Pedido cadastrado!");
        }else{
            System.out.println("Pedido não foi cadastrado!");
        }
        System.out.println("_____________________________________________________");
        
        System.out.println("Listando todos os pedidos do cliente cadastrado");
        System.out.println(pedidoCtrl.getPedidoByCliente(clienteCtrl.getClienteByDocumento(cliente.getDocumento())));
        System.out.println("_____________________________________________________");

        System.out.println("Alterando pedido cadastrado.");
        
        List<Pedido> pedidos = pedidoCtrl.getPedidoByCliente(clienteCtrl.getClienteByDocumento(cliente.getDocumento()));
        
        System.out.println("Escolha um pedido para alterar");
        for(int i = 0; i < pedidos.size(); i++){
            System.out.println(i + ". " + pedidos.get(i).toString());
        }
        int pedidoEscolhido = scan.nextInt();
        
        Pedido novoPedido = new Pedido(pedidos.get(pedidoEscolhido).getId(), LocalDate.now(), pedido.getCliente(), 777);
        if(pedidoCtrl.alterar(novoPedido)){
            System.out.println("O pedido foi alterado");
        }else{
            System.out.println("O pedido não foi alterado");
        }
        System.out.println("_____________________________________________________");        
        
        
        System.out.println("Excluindo último pedido cadastrado para o usuário");
        System.out.println("Escolha um pedido para excluir");
        pedidos = pedidoCtrl.getPedidoByCliente(clienteCtrl.getClienteByDocumento(cliente.getDocumento()));
        for(int i = 0; i < pedidos.size(); i++){
            System.out.println(i + ". " + pedidos.get(i).toString());
        }
        pedidoEscolhido = scan.nextInt();
        Pedido pedidoExcluir = pedidos.get(pedidoEscolhido);
        if(pedidoCtrl.excluir(pedidoExcluir)){
            System.out.println("Pedido excluído!");
        }else{
            System.out.println("O pedido não foi excluído");
        }
        System.out.println("_____________________________________________________");
        
        System.out.println("Cadastrando outro cliente.");
        Cliente novoCliente;
        String nomeCliente, documentoCliente = null;
        Double saldoCliente = null;
        System.out.print("Digite o nome do Cliente: ");
        nomeCliente = scan.next();
        System.out.print("Digite o documento do cliente: ");
        documentoCliente = scan.next();
        System.out.print("Digite o seu saldo: ");
        saldoCliente = scan.nextDouble();
        
        novoCliente = new Cliente(nomeCliente, documentoCliente, saldoCliente, ClienteAtivoEnum.ATIVO);
        if(clienteCtrl.incluir(novoCliente)){
            System.out.println("Cadastro realizado com sucessso!");
        }else{
            System.out.println("Cadastro de cliente não realizado!");
        }
        System.out.println("_____________________________________________________");
        
        System.out.println("Alterando novo cliente cadastrado");
        novoCliente = clienteCtrl.getClienteByDocumento(novoCliente.getDocumento());
        novoCliente.setDocumento("Meu documento");
        novoCliente.setNome("Nome Alterado");
        if(clienteCtrl.alterar(novoCliente)){
            System.out.println("Cliente alterado");
        }else{
            System.out.println("Cliente não foi alterado");
        }
        System.out.println("_____________________________________________________");
        
        System.out.println("Removendo primeiro cliente");
        pedidos = pedidoCtrl.getPedidoByCliente(clienteCtrl.getClienteByDocumento(cliente.getDocumento()));
        for(int i = 0; i < pedidos.size(); i++){
            pedidoCtrl.excluir(pedidos.get(i));
        }
        if(clienteCtrl.excluir(clienteCtrl.getClienteByDocumento(cliente.getDocumento()))){
            System.out.println("Cliente removido");
        }else{
            System.out.println("Cliente não foi removido");
        }
    }
}
