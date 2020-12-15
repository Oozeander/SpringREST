package com.oozeander.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oozeander.model.Product;
import com.oozeander.repository.ProductRepository;
import com.oozeander.service.ProductService;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> get() {
		return productRepository.findAll();
	}

	@Override
	public Product get(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public void save(Product product) {
		productRepository.saveAndFlush(product);
	}

	@Override
	public boolean update(Long id, Product product) {
		boolean isDone = false;
		Product p = get(id);
		if (p != null) {
			isDone = true;
			p.setName(product.getName());
			p.setDescription(product.getDescription());
		}
		return isDone;
	}

	@Override
	public void delete(Long id) {
		productRepository.delete(id);
	}
}