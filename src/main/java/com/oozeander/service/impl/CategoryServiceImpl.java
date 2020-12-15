package com.oozeander.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oozeander.model.Category;
import com.oozeander.repository.CategoryRepository;
import com.oozeander.service.CategoryService;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> get() {
		return categoryRepository.findAll();
	}

	@Override
	public Category get(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public void save(Category category) {
		categoryRepository.saveAndFlush(category);
	}
}