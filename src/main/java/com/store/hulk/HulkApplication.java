package com.store.hulk;

import com.store.hulk.models.customers.Customer;
import com.store.hulk.models.documents.TypeDocument;
import com.store.hulk.models.products.Category;
import com.store.hulk.models.products.Product;
import com.store.hulk.models.users.UserHulk;
import com.store.hulk.services.customers.CustomerService;
import com.store.hulk.services.documents.DocumentService;
import com.store.hulk.services.products.ProductService;
import com.store.hulk.services.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication @Slf4j
public class HulkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HulkApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

		@Bean
		CommandLineRunner run(
				UserService userService,
				CustomerService customerService,
				ProductService productService,
				DocumentService documentService){
		return  args -> {
			log.info("creation users test");
			UserHulk user_admin=new UserHulk(
					0,
					"amosquera",
					"Aldair",
					"Mosquera",
					"Murillo",
					"123");
			userService.save(user_admin);

			log.info("creation customers test");
			Customer customer = new Customer(0,"Wandie","Bernot","Harbinson",false);
			Customer provider = new Customer(0,"Arlan","Dunphy","Siddons",true);

			Customer customer1 = new Customer(0,"Jose Orlando","Ortiz","Moren",false);
			Customer provider1 = new Customer(0,"Maria Lorena","Dunphy","Siddons",true);

			customerService.save(customer);
			customerService.save(provider);

			customerService.save(customer1);
			customerService.save(provider1);

			log.info("creation categories test");
			Category category = productService.saveCategory(new Category(0,"Camisetas","Camisetas estampadas"));
			Category category1 = productService.saveCategory(new Category(0,"Vasos","Vasos con lindos estampados"));
			Category category2 = productService.saveCategory( new Category(0,"Comics","Comics de todoas las epocas"));
			Category category3 = productService.saveCategory(new Category(0,"Juguetes","Juguetes de coleción"));

			log.info("creation products test");

			Product product = new Product(0,"Amazing Spider-Man Omnibus, Vol. 1",
					"En 1962, Stan Lee y Steve Ditko dieron a luz a uno de los íconos más perdurables de los medios populares estadounidenses."
					,(long)3,new BigDecimal(2_350_000) ,Stream.of(category2).collect(Collectors.toList()));

			Product product1 = new Product(0,"Camiseta Batman vs Superman",
					"Camiseta estampada pelicula Batman vs Superman"
					,(long)200,new BigDecimal(250_000) ,Stream.of(category2,category).collect(Collectors.toList()));

			Product product2 = new Product(0,"Vaso Flash La Revolucion De Los Villanos",
					"El Capitán Frío. Ola de Calor. El Amo de los Espejos. El Hechicero del Clima. Patinadora."
					,(long)78,new BigDecimal(80_000) ,Stream.of(category2,category1).collect(Collectors.toList()));

			Product product3 = new Product(0,"Figura de acción Iron Man ",
					"Figura de colección vasada en el comic Ultrón ilimitado"
					,(long)3,new BigDecimal(1_800_000) ,Stream.of(category2,category3).collect(Collectors.toList()));

			Product product4 = new Product(0,"Batman Silencio",
					"Silencio es un arco narrativo de comic books de 2002 a 2003 que se publicó en la serie mensual de Batman."
					,(long)1,new BigDecimal(950_000) ,Stream.of(category2).collect(Collectors.toList()));


			productService.save(product);
			productService.save(product1);
			productService.save(product2);
			productService.save(product3);
			productService.save(product4);

			log.info("creation type documents test");

			TypeDocument typeDocument = new TypeDocument(0,"Facturas",true,
					"Documento para la elaboración de facturas");
			TypeDocument typeDocument1 = new TypeDocument(0,"Compras",false,
					"Documento para la elaboración de compras");

			documentService.saveTypeDocument(typeDocument);
			documentService.saveTypeDocument(typeDocument1);

		};
	}
}
