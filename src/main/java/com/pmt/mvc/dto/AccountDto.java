package com.pmt.mvc.dto;

import lombok.Data;

@Data
public class AccountDto {
	private int accountId;

	private String accountName;
	private String accountUrl;
	private String accountPassword;
	private int groupId;
	private String groupName;

}
