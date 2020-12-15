package com.oozeander;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.oozeander.model.Category;
import com.oozeander.model.Information;
import com.oozeander.model.Product;
import com.oozeander.service.CategoryService;
import com.oozeander.service.InformationService;
import com.oozeander.service.ProductService;
import com.oozeander.spring.PersistenceConfig;

public class App {
	private static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws JsonProcessingException {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceConfig.class);
		ctx.registerShutdownHook();

		ProductService productService = ctx.getBean(ProductService.class);
		Information info1 = new Information("Additional Description...");
		Category category = new Category("Laptops");
		Product product1 = new Product("HP Laptop", "Heavy Workstation", info1, category);
		info1.setProduct(product1);
		category.setProducts(Arrays.asList(product1));

		productService.save(product1);

		configureMapper(mapper);

		Product p = productService.get(1L);
		Information i = ctx.getBean(InformationService.class).get(1L);
		Category c = ctx.getBean(CategoryService.class).get(1L);
		System.out.println(mapper.writeValueAsString(p) + "\n" + mapper.writeValueAsString(i) + "\n"
				+ mapper.writeValueAsString(c));

		ctx.close();
	}

	private static void configureMapper(ObjectMapper mapper) {
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
}