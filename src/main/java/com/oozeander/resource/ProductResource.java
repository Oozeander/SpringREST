package com.oozeander.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oozeander.exception.ProductNotFoundException;
import com.oozeander.model.Product;
import com.oozeander.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
@Api(tags = "Product Resource API")
public class ProductResource {
	@Autowired
	private ProductService productService;

	@ApiOperation(value = "Get all the available products", response = List.class, tags = "Product API")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> get() {
		return ResponseEntity.status(HttpStatus.OK).body(productService.get());
	}

	@ApiOperation(value = "Get one product", response = Product.class, tags = "Product API")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> get(@PathVariable("id") Long id) {
		Product product = productService.get(id);
		if (product != null)
			return ResponseEntity.status(HttpStatus.OK).body(product);
		throw new ProductNotFoundException(Long.toString(id));
	}

	@ApiOperation(value = "Save a product", response = Void.class, tags = "Product API")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> save(@RequestBody Product product) {
		productService.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).location(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri())
				.build();
	}

	@ApiOperation(value = "Modify a product", response = Void.class, tags = "Product API")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Product product) {
		boolean isDone = productService.update(id, product);
		if (isDone)
			return ResponseEntity.status(HttpStatus.CREATED)
					.location(ServletUriComponentsBuilder.fromCurrentRequest().path("").build().toUri()).build();
		throw new ProductNotFoundException(Long.toString(id));
	}

	@ApiOperation(value = "Delete a product", response = Void.class, tags = "Product API")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		Product product = get(id).getBody();
		if (product != null) {
			productService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		throw new ProductNotFoundException(Long.toString(id));
	}
}