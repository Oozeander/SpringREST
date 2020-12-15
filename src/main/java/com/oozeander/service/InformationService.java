package com.oozeander.service;

import java.util.List;

import com.oozeander.model.Information;

public interface InformationService {
	List<Information> get();

	Information get(Long id);

	void save(Information information);
}