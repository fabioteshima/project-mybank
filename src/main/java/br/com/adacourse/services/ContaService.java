package br.com.adacourse.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContaService {

//    private final List<Conta> contas = new ArrayList<>();
//    private static final AtomicLong numeroId = new AtomicLong();
//    private static final AtomicLong numeroConta = new AtomicLong();
//
//    @Inject
//    ClienteService clienteService;
//
//    // Recebe e retorna ENTIDADE Conta
//    public Conta criarConta(Conta conta){
//        if (conta == null || conta.getTitular() == null || conta.getTitular().getId() == null) {
//            throw new IllegalArgumentException("Requisição inválida: titular.id é obrigatório");
//        }
//
//        Cliente titular = clienteService.buscarClientePorId(conta.getTitular().getId());
//        if (titular == null) {
//            throw new IllegalArgumentException("Cliente com id " + conta.getTitular().getId() + " não encontrado");
//        }
//
//        conta.setId(numeroId.incrementAndGet());
//        conta.setNumero(String.valueOf(numeroConta.incrementAndGet()));
//        conta.setTitular(titular);
//        conta.setSaldo(0.00);
//        contas.add(conta);
//        return conta;
//    }
//
//    public List<Conta> listarContas(){
//        return List.copyOf(contas);
//    }
//
//    public Conta buscarContaPorId(Long id){
//        return contas.stream()
//                .filter(c -> c.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
}

