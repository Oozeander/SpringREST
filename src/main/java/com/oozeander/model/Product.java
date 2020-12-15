package com.oozeander.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@Table(name = "products", schema = "default_schema", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "product_id" }) })
@JsonPropertyOrder({ "name", "description" })
@JsonIgnoreProperties({ "id" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(exclude = { "information", "category" })
public class Product {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "product_id")
	private Long id;

	@NonNull
	private String name, description;

	@NonNull
	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "product")
	@JsonManagedReference
	private Information information;

	@NonNull
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "category_id")
	@JsonBackReference
	private Category category;
}