package com.oozeander.service;

import java.util.List;

import com.oozeander.model.Category;

public interface CategoryService {
	List<Category> get();

	Category get(Long id);

	void save(Category category);
}