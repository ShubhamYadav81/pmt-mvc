package com.pmt.mvc.model;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "Account_TBL")
@Embeddable
@Data
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int accountId;
	private String accountName;
	private String accountUrl;
	private String accountPassword;
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID")
	private Group group;
	

}
