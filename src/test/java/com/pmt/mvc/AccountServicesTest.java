package com.pmt.mvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pmt.mvc.dao.AccountRepository;
import com.pmt.mvc.dto.AccountDto;
import com.pmt.mvc.excpetion.AccountNotFoundException;
import com.pmt.mvc.model.Account;
import com.pmt.mvc.services.AccountServices;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class AccountServicesTest {

	@InjectMocks
	AccountServices accountservices;

	@MockBean
	AccountRepository accountrepo;
	@MockBean
	ModelMapper mapper;

	@Test
	void shouldRetrunFalseWhenAccountIsNotDeletedByAccountName() throws Exception {

		when(accountrepo.deleteAccountByName("shubham")).thenReturn(1);
		assertTrue(accountservices.deleteAccountByAccountName("shubham"));

	}

	@Test
	void testPassword() {
		Account account = new Account();
		account.setAccountName("shubham");
		account.setAccountPassword("yadavji81");
		account.setAccountUrl("shubham.com");
		Optional<Account> optional = Optional.of(account);
		when(accountrepo.getAccountByName("shubham")).thenReturn(optional);
		assertTrue(accountservices.updatePasswordByAccountName("shubham", "123"));
		verify(accountrepo, times(1)).updatePasswordByUserName("shubham", "123");

	}

	@Test
	void testGetAccount() {
		Account account = new Account();
		account.setAccountName("shubham");
		account.setAccountPassword("yadavji81");
		account.setAccountUrl("shubham.com");
		Optional<Account> optional = Optional.of(account);
		when(accountrepo.getAccountByName("shubham")).thenReturn(optional);
		assertEquals("shubham", accountservices.getAccount("shubham").getAccountName());

	}

	@Test
	void testGetAccountDto() {
		Account account = new Account();
		account.setAccountName("shubham");
		account.setAccountPassword("yadavji81");
		account.setAccountUrl("shubham.com");
		Optional<Account> optional = Optional.of(account);
		when(accountrepo.getAccountByName("shubham")).thenReturn(optional);

		assertEquals("shubham", accountservices.getAccountRecord("shubham").getAccountName());

	}

}
