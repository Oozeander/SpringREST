package com.oozeander.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "product_information", schema = "default_schema", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "product_info_id" }) })
@JsonIgnoreProperties({ "id" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(exclude = { "product" })
public class Information {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "product_info_id")
	private Long id;

	@NonNull
	@Column(name = "additional_information")
	private String additionalInformation;

	@OneToOne
	@ToString.Exclude
	@JoinColumn(name = "product_id")
	@JsonBackReference
	private Product product;
}