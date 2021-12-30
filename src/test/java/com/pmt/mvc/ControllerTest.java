package com.pmt.mvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.pmt.mvc.dto.AccountDto;
import com.pmt.mvc.model.Account;
import com.pmt.mvc.model.Group;
import com.pmt.mvc.services.AccountServices;
import com.pmt.mvc.services.GroupServices;

/*@WebMvcTest(UserController.class)
@ContextConfiguration(classes = { UserController.class })
@ExtendWith(MockitoExtension.class)*/
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class ControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	AccountServices accountServices;
	@MockBean
	GroupServices groupServices;

	@Test
	void shouldReturnAccountWhenValidAcountName() throws Exception {

		when(accountServices.getAccount("shubham")).thenReturn(new Account());
		mockMvc.perform(get("/displayRecordByUserName?user=shubham")).andExpect(status().isOk())
				.andExpect(view().name(("viewDataByAccount.jsp")));

	}

	@Test
	void shouldReturnSucessPageWhenDataIsInsterted() throws Exception {
		AccountDto acc = new AccountDto();
		acc.setAccountName("shubham");
		acc.setAccountPassword("hello");
		acc.setAccountUrl("hi.com");
		acc.setGroupName("social");
		doNothing().when(groupServices).addAccount(acc);
		groupServices.addAccount(acc);
		verify(groupServices, times(1)).addAccount(acc);
	}

	@Test
	void shouldReturnGroupListWhenGroupIsPresent() throws Exception {
		when(groupServices.allRecord()).thenReturn(new ArrayList<Group>());
		mockMvc.perform(get("/displayAllRecord")).andExpect(status().isOk())
				.andExpect(view().name(("displayAllRecord.jsp")));

	}

	@Test
	void shouldReturnTrueWhenAccountDeletedByAccountName() throws Exception {
		when(accountServices.deleteAccountByAccountName("fb")).thenReturn(true);
		mockMvc.perform(get("/deleteRecordByUserName?user=fb")).andExpect(status().isOk())
				.andExpect(view().name(("deleteRecordSucess.jsp")));
	}
	@Test
	void shouldReturnFalseWhenAccountNotDeletedByAccountName() throws Exception {
		when(accountServices.deleteAccountByAccountName("fb")).thenReturn(true);
		mockMvc.perform(get("/deleteRecordByUserName?user=fb1")).andExpect(status().isOk())
				.andExpect(view().name(("deleteRecordfail.jsp")));
	}

	@Test
	void shouldReturnTrueWhenPasswordIsUpdatedByAccountName() throws Exception {
		when(accountServices.updatePasswordByAccountName("fb", "shubham")).thenReturn(true);
		mockMvc.perform(get("/UpdatePasswordByUserName?user=fb&password=shubham")).andExpect(status().isOk())
				.andExpect(view().name(("updateRecordSucess.jsp")));
	}
	@Test
	void shouldReturFalseWhenPasswordIsUpdatedByAccountName() throws Exception {
		when(accountServices.updatePasswordByAccountName("fb", "shubham")).thenReturn(true);
		mockMvc.perform(get("/UpdatePasswordByUserName?user=fb1&password=shubham1")).andExpect(status().isOk())
				.andExpect(view().name(("updateRecordFail.jsp")));
	}

	

	@Test
	void shouldReturnTrueWhenAccountDeletedByGroupName() throws Exception {
		when(groupServices.deleteAccountByGroupName("fb")).thenReturn(true);
		mockMvc.perform(get("/deleteRecordByGroupName?groupName=fb")).andExpect(status().isOk())
				.andExpect(view().name(("deleteRecordSucess.jsp")));
	}

	@Test
	void shouldRetuFalseWhenAccountNotDeletedByGroupName() throws Exception {
		when(groupServices.deleteAccountByGroupName("fb")).thenReturn(true);
		mockMvc.perform(get("/deleteRecordByGroupName?groupName=fb1")).andExpect(status().isOk())
				.andExpect(view().name(("deleteRecordfail.jsp")));
	}

	@Test
	void shouldReturnTrueWhenGroupNameIsUpdated() throws Exception {
		when(groupServices.updateGroupNameByGroupName("fb", "shubham")).thenReturn(true);
		mockMvc.perform(get("/updateGroup?groupName=fb&updatedGroupName=shubham")).andExpect(status().isOk())
				.andExpect(view().name(("updateRecordSucess.jsp")));
	}

	@Test
	void shouldReturnFalseWhenGroupNameIsNotUpdated() throws Exception {
		when(groupServices.updateGroupNameByGroupName("fb", "shubham")).thenReturn(true);
		mockMvc.perform(get("/updateGroup?groupName=fb1&updatedGroupName=shubham1")).andExpect(status().isOk())
				.andExpect(view().name(("updateRecordFail.jsp")));
	}

	@Test
	void add() throws Exception {
		// when(groupServices.addAccount(null)).thenReturn(true);
		mockMvc.perform(post("/addUser?accountName=fb&accountPassword=yadav&accountUrl=shu.com&groupName=social"))
				.andExpect(status().isOk()).andExpect(view().name(("addUserSucess.jsp")));
	}

}
