package com.pmt.mvc.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pmt.mvc.model.*;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	@Query("select account from Account account where account.accountName = :accountName")
	Optional<Account> getAccountByName(String accountName);

	@Modifying
	@Transactional
	@Query("delete from Account account where account.accountName= :accountName")
	int deleteAccountByName(String accountName);

	@Modifying
	@Transactional
	@Query("update Account acc set acc.accountPassword=:updatePassword where account_name= :accountName")
	void updatePasswordByUserName(String accountName, String updatePassword);
}
