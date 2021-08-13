package com.brito.sistemapedidos;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brito.sistemapedidos.domain.Address;
import com.brito.sistemapedidos.domain.Category;
import com.brito.sistemapedidos.domain.City;
import com.brito.sistemapedidos.domain.Client;
import com.brito.sistemapedidos.domain.OrderedItem;
import com.brito.sistemapedidos.domain.Payment;
import com.brito.sistemapedidos.domain.PaymentWithBillet;
import com.brito.sistemapedidos.domain.PaymentWithCard;
import com.brito.sistemapedidos.domain.Product;
import com.brito.sistemapedidos.domain.Request;
import com.brito.sistemapedidos.domain.State;
import com.brito.sistemapedidos.domain.enums.StatePayment;
import com.brito.sistemapedidos.domain.enums.TipoClient;
import com.brito.sistemapedidos.repositories.AddressRepository;
import com.brito.sistemapedidos.repositories.CategoryRepository;
import com.brito.sistemapedidos.repositories.CityRepository;
import com.brito.sistemapedidos.repositories.ClientRepository;
import com.brito.sistemapedidos.repositories.OrderedItemRepository;
import com.brito.sistemapedidos.repositories.PaymentRepository;
import com.brito.sistemapedidos.repositories.ProductRepository;
import com.brito.sistemapedidos.repositories.RequestRepository;
import com.brito.sistemapedidos.repositories.StateRepository;

@SpringBootApplication
public class SistemaPedidosApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderedItemRepository orderedItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(SistemaPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritótio");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumária");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2,cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9 ,p10 ,p11));
		
		State sta1 = new State(null, "Minas Gerais");
		State sta2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", sta1);
		City c2 = new City(null, "São Paulo", sta2);
		City c3 = new City(null, "Campinas", sta2);
		
		sta1.getCities().addAll(Arrays.asList(c1));
		sta2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(sta1, sta2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "11111111111", TipoClient.PESSOAFISICA);
		
		cli1.getPhones().addAll(Arrays.asList("86 999999999", "86 9888888888"));
		
		Address a1 = new Address(null, "Rua Flores", "300", "Apto 003", "Barra Grande", "7752", cli1, c1);
		Address a2 = new Address(null, "Avenida Matos", "100", "Sala 800", "Parque Poty", "5984", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(a1, a2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(a1, a2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Request ped1 = new Request(null, sdf.parse("30/07/2021 10:32"), cli1, a1);
		Request ped2 = new Request(null, sdf.parse("10/07/2021 19:35"), cli1, a2);
		
		Payment pagto1 = new PaymentWithCard(null, StatePayment.QUITADO, ped1, 6);
		ped1.setPayment(pagto1);
		
		Payment pagto2 = new PaymentWithBillet(null, StatePayment.PENDENTE, ped2, sdf.parse("20/07/2021 00:00"), null);
		ped2.setPayment(pagto2);
		
		cli1.getRequests().addAll(Arrays.asList(ped1, ped2));
		
		requestRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		OrderedItem ip1 = new OrderedItem(ped1, p1, 0.00, 1, 2000.00);
		OrderedItem ip2 = new OrderedItem(ped1, p3, 0.00, 2, 80.00);
		OrderedItem ip3 = new OrderedItem(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		orderedItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
