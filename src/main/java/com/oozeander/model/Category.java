package com.oozeander.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "categories", schema = "default_schema", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "category_id" }) })
@JsonIgnoreProperties({ "id" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(exclude = { "products" })
public class Category {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "category_id")
	private Long id;

	@NonNull
	private String name;

	@OneToMany(mappedBy = "category")
	@JsonManagedReference
	private List<Product> products;
}