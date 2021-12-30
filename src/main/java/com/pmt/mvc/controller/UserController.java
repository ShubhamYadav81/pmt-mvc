package com.pmt.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.pmt.mvc.dto.AccountDto;
import com.pmt.mvc.excpetion.AccountAlreadyExistsException;
import com.pmt.mvc.excpetion.AccountNotFoundException;
import com.pmt.mvc.model.Account;
import com.pmt.mvc.services.AccountServices;
import com.pmt.mvc.services.GroupServices;

@Controller
public class UserController {
	@Autowired
	GroupServices groupServices;

	@Autowired
	AccountServices accountServices;

	@RequestMapping("/")
	public String home() {
		return "index.jsp";
	}

	@PostMapping("addUser")
	public ModelAndView addAccount(AccountDto grp) {

		ModelAndView mv = new ModelAndView();

		try {
			groupServices.addAccount(grp);
			mv.setViewName("addUserSucess.jsp");
			
		} 
		catch (AccountAlreadyExistsException e)
		{
			mv.setViewName("addAccountFail.jsp");
		}

		return mv;

	}

	@GetMapping("displayAllRecord")
	public ModelAndView viewAllRecord() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("displayAllRecord.jsp");
		mv.addObject("groups", groupServices.allRecordOfAccount());
		
		
		return mv;

	}

	@GetMapping("displayRecordByUserName")
	public ModelAndView viewUserRecord(String user) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewDataByAccount.jsp");
		mv.addObject("user", accountServices.getAccountRecord(user));
		
		return mv;
	}

	@RequestMapping("deleteRecordByUserName")
	public ModelAndView deleteAccountByUserName(String user) {
		ModelAndView mv = new ModelAndView();
		if (accountServices.deleteAccountByAccountName(user)) {
			mv.setViewName("deleteRecordSucess.jsp");
		} else {
			mv.setViewName("deleteRecordfail.jsp");

		}
		return mv;
	}

	@RequestMapping("deleteRecordByGroupName")
	public ModelAndView deleteAccountByGroupName(String groupName) {
		ModelAndView mv = new ModelAndView();
		if (groupServices.deleteAccountByGroupName(groupName)) {
			mv.setViewName("deleteRecordSucess.jsp");
		} else {
			mv.setViewName("deleteRecordfail.jsp");
		}
		return mv;
	}

	@RequestMapping("UpdatePasswordByUserName")
	public ModelAndView updatePasswordByAccountName(String user, String password) {
		ModelAndView mv = new ModelAndView();

		if (accountServices.updatePasswordByAccountName(user, password)) {
			mv.setViewName("updateRecordSucess.jsp");
		} else {
			mv.setViewName("updateRecordFail.jsp");
		}
		return mv;
	}

	@RequestMapping("updateGroup")
	public ModelAndView updateGroupName(String groupName, String updatedGroupName) {
		ModelAndView mv = new ModelAndView();
		if (groupServices.updateGroupNameByGroupName(groupName, updatedGroupName)) {
			mv.setViewName("updateRecordSucess.jsp");
		} else {
			mv.setViewName("updateRecordFail.jsp");
		}
		return mv;
	}

	@RequestMapping("/addAccount")
	public String addAccount() {
		return "addAccount.jsp";
	}
}
