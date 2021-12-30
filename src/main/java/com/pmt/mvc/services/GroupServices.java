package com.pmt.mvc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmt.mvc.dao.AccountRepository;
import com.pmt.mvc.dao.GroupRepository;
import com.pmt.mvc.dto.AccountDto;
import com.pmt.mvc.dto.GroupDto;
import com.pmt.mvc.excpetion.AccountAlreadyExistsException;
import com.pmt.mvc.excpetion.AccountNotFoundException;
import com.pmt.mvc.model.Account;
import com.pmt.mvc.model.Group;

@Service
public class GroupServices {
	@Autowired
	GroupRepository groupRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ModelMapper modelMapper;

	public void addAccount(AccountDto grp) throws AccountAlreadyExistsException {

		Account account = modelMapper.map(grp, Account.class);

		isAccountPresent(account.getAccountName());

		if (groupRepository.existsByGroupName(grp.getGroupName())) {

			Optional<Group> optionalGroup = groupRepository.findByGroupName(grp.getGroupName());
			if (optionalGroup.isPresent()) {
				Group group = optionalGroup.get();
				account.setGroup(group);
				group.getAccount().add(account);
				groupRepository.save(group);
			}
		} else {
			Group newGroup = new Group();
			List<Account> accountList = new ArrayList();
			accountList.add(account);
			newGroup.setGroupName(grp.getGroupName());
			newGroup.setAccount(accountList);
			account.setGroup(newGroup);
			groupRepository.save(newGroup);
		}

	}

	public boolean isAccountPresent(String accountName) throws AccountAlreadyExistsException {
		boolean result = true;
		if (accountRepository.getAccountByName(accountName).isPresent()) {
			throw new AccountAlreadyExistsException("account already exist");
		}
		return result;
	}

	public List<Group> allRecord() {
		return (List<Group>) groupRepository.findAll();
	}

	public boolean deleteAccountByGroupName(String groupName) {
		boolean result = false;

		Optional<Group> optionalGroup = groupRepository.findByGroupName(groupName);
		if (optionalGroup.isPresent()) {
			Group grp = optionalGroup.get();
			groupRepository.delete(grp);
			result = true;
		}
		return result;
	}

	public boolean updateGroupNameByGroupName(String oldGroupName, String newGroupName) {
		boolean result = false;

		Optional<Group> optionalGroup = groupRepository.findByGroupName(oldGroupName);
		if (optionalGroup.isPresent()) {
			groupRepository.updateGroupName(oldGroupName, newGroupName);
			result = true;
		}
		return result;
	}

	public List<GroupDto> allRecordOfAccount() {

		List<Group> groupList = (List<Group>) groupRepository.findAll();
		List<GroupDto> groupsDtoList = new ArrayList<>();

		for (Group g : groupList) {
			GroupDto groupDto = new GroupDto();
			groupDto.setAccount(g.getAccount());
			groupDto.setGroupId(g.getGroupId());
			groupDto.setGroupName(g.getGroupName());
			groupsDtoList.add(groupDto);
		}
		return groupsDtoList;
	}

}
