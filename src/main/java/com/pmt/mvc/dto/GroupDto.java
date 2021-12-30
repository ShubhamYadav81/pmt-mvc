package com.pmt.mvc.dto;

import java.util.List;

import com.pmt.mvc.model.Account;

public class GroupDto {
	private int groupId;
	private String groupName;
	private List<Account> account;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}
	
}
