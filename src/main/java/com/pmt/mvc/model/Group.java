package com.pmt.mvc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "group_tbl")
@Data
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int groupId;
	private String groupName;
	@JsonManagedReference
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
	
	private List<Account> account;

}
