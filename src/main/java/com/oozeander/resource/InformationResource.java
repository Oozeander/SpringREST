package com.oozeander.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oozeander.model.Information;
import com.oozeander.service.InformationService;

@RestController
@RequestMapping("/api/informations")
@CrossOrigin
public class InformationResource {
	@Autowired
	private InformationService informationService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Information>> get() {
		return ResponseEntity.status(HttpStatus.OK).body(informationService.get());
	}
}