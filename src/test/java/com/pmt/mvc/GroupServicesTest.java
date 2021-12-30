package com.pmt.mvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.hibernate.cfg.beanvalidation.GroupsPerOperation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pmt.mvc.dao.AccountRepository;
import com.pmt.mvc.dao.GroupRepository;
import com.pmt.mvc.dto.AccountDto;
import com.pmt.mvc.dto.GroupDto;
import com.pmt.mvc.model.Account;
import com.pmt.mvc.model.Group;
import com.pmt.mvc.services.GroupServices;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

class GroupServicesTest {

	@InjectMocks
	GroupServices groupservices;
	

	@MockBean
	GroupRepository groupRepository;

	@MockBean
	ModelMapper mapper;
	
	@MockBean 
	AccountRepository accountRepository;

	@Test
	void testAddWhenGroupIsPresent() throws Exception {

		when(groupRepository.existsByGroupName("social")).thenReturn(true);
		
		Account account=new Account();
		account.setAccountName("shubham1");
		account.setAccountPassword("yadavji81");
		account.setAccountUrl("shubham.com");
		Optional<Account> optional = Optional.of(account);
		when(accountRepository.getAccountByName("shubham1")).thenReturn(optional);
		
		Group group=new Group();
		group.setGroupName("social");
		group.setAccount(new ArrayList<>());
		Optional <Group> grp=Optional.of(group);
		when(groupRepository.findByGroupName("social")).thenReturn(grp);
		when(mapper.map(any(AccountDto.class),any())).thenReturn(new Account());
		AccountDto dto=new AccountDto();
		dto.setAccountName("shubham");
		dto.setAccountUrl("shubham.com");
		dto.setAccountPassword("yadavji81");
		dto.setGroupName("social");
	 
		assertTrue(groupservices.isAccountPresent("shubham"));
		groupservices.addAccount(dto);
		verify(groupRepository,times(1)).existsByGroupName("social");
		verify(groupRepository,times(1)).findByGroupName("social");
		verify(groupRepository,times(1)).save(any(Group.class));
	}
	@Test
	void testAddWhenGroupIsNotPresent() throws Exception {

		when(groupRepository.existsByGroupName("social")).thenReturn(false);
		
		AccountDto dto=new AccountDto();
		dto.setAccountName("shubham");
		dto.setAccountUrl("shubham.com");
		dto.setAccountPassword("yadavji81");
		dto.setGroupName("social");
		when(mapper.map(any(AccountDto.class),any())).thenReturn(new Account());
		groupservices.addAccount(dto);
		verify(groupRepository,times(1)).existsByGroupName("social");
	}
	@Test
	void shouldReturnGroupListWhenGroupIsPresent()throws Exception
	{
		List<Group> groupList=new ArrayList<>();
		Group group=new Group();
		Account account=new Account();
		List<Account>accountList=new ArrayList<>();
		
		account.setAccountName("fb");
		account.setAccountPassword("yadavji81");
		account.setAccountUrl("acc.com");
		accountList.add(account);
		group.setGroupName("social");
		group.setAccount(accountList);
		groupList.add(group);
		
		account.setAccountName("fb1");
		account.setAccountPassword("yadavji811");
		account.setAccountUrl("acc1.com");
		accountList.add(account);
		group.setGroupName("social1");
		group.setAccount(accountList);
		groupList.add(group);
		
		when(groupRepository.findAll()).thenReturn(groupList);
		assertEquals("social1",groupservices.allRecord().get(0).getGroupName());
		
		
	}
	@Test
	void shouldReturnGroupDtoListWhenGroupIsPresent()throws Exception
	{
		List<Group> groupList=new ArrayList<>();
		Group group=new Group();
		Account account=new Account();
		List<Account>accountList=new ArrayList<>();
		
		account.setAccountName("fb");
		account.setAccountPassword("yadavji81");
		account.setAccountUrl("acc.com");
		accountList.add(account);
		group.setGroupName("social");
		group.setAccount(accountList);
		groupList.add(group);
		
		account.setAccountName("fb1");
		account.setAccountPassword("yadavji811");
		account.setAccountUrl("acc1.com");
		accountList.add(account);
		group.setGroupName("social1");
		group.setAccount(accountList);
		groupList.add(group);
		
		
		when(groupRepository.findAll()).thenReturn(groupList);
		assertEquals("social1",groupservices.allRecordOfAccount().get(0).getGroupName());
		
	}
	@Test
	void shouldReturnTrueWhenGroupIsDeleted() throws Exception
	{
		Group group=new Group();
		group.setGroupName("hello");
		Optional<Group> optional=Optional.of(group);
		when(groupRepository.findByGroupName("hello")).thenReturn(optional);
		assertTrue(groupservices.deleteAccountByGroupName("hello"));
	}
	@Test
	void shouldReturFalseWhenGroupIsNotDeleted() throws Exception
	{
		Group group=new Group();
		group.setGroupName("hello");
		Optional<Group> optional=Optional.of(group);
		when(groupRepository.findByGroupName("hello")).thenReturn(optional);
		assertFalse(groupservices.deleteAccountByGroupName("hello1"));
	}
	@Test
	void shouldReturnTrueWhenGroupNameIsUpdated()
	{
		Group group=new Group();
		group.setGroupName("hello");
		Optional<Group> optional=Optional.of(group);
		when(groupRepository.findByGroupName("hello")).thenReturn(optional);
		assertTrue(groupservices.updateGroupNameByGroupName("hello", "shubham"));
	}


}
