package com.oozeander.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oozeander.model.Information;
import com.oozeander.repository.InformationRepository;
import com.oozeander.service.InformationService;

@Service("informationService")
@Transactional
public class InformationServiceImpl implements InformationService {
	@Autowired
	private InformationRepository informationRepository;

	@Override
	public List<Information> get() {
		return informationRepository.findAll();
	}

	@Override
	public Information get(Long id) {
		return informationRepository.findOne(id);
	}

	@Override
	public void save(Information information) {
		informationRepository.saveAndFlush(information);
	}
}