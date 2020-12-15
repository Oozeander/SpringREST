package com.oozeander.service;

import java.util.List;

import com.oozeander.model.Product;

public interface ProductService {
	List<Product> get();

	Product get(Long id);

	void save(Product product);

	boolean update(Long id, Product product);

	void delete(Long id);
}