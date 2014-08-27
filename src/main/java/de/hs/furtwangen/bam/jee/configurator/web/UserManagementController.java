package de.hs.furtwangen.bam.jee.configurator.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hs.furtwangen.bam.jee.configurator.Exception.DuplicateUserException;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.service.UserManagementService;
import de.hs.furtwangen.bam.jee.configurator.web.domain.UserEventAdd;
import de.hs.furtwangen.bam.jee.configurator.web.domain.UserEventEdit;

/**
 * 
 * @author Christian Henle
 *
 *         This Controller provides functions to add, edit, delete and disable
 *         users.
 *
 */

@Controller
@RequestMapping(value = "/management/user")
public class UserManagementController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserManagementController.class);

	@Autowired
	private UserManagementService userManagementService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUserPage(Model model) {
		logger.info("addUserPage");
		model.addAttribute("pageHeader", "management.user.form.add.pageHeader");
		model.addAttribute("passwordField", true);
		model.addAttribute("user",
				userManagementService.getNewUserWithAllRoles());
		model.addAttribute("action", "add");

		return "/management/user/form";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") UserEventAdd user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {
		logger.info("addUser");
		model.addAttribute("pageHeader", "management.user.form.add.pageHeader");
		model.addAttribute("passwordField", true);

		user.setAllRoles(userManagementService.findAllRole());
		model.addAttribute("user", user);

		if (!user.passwordEquals()) {
			model.addAttribute("passwordError",
					"management.user.form.add.error.pasword.equals");
			// Alle Rollen für Form setzen
			return "/management/user/form";
		}
		if (bindingResult.hasErrors()) {
			// Problem with username Variable ex: to Long
			// Problem with password Variable ex: to Long, to Short, not Equals
			return "/management/user/form";
		}

		// No Role Selected
		if (null == user.getRolesChecked()) {
			model.addAttribute("roleError",
					"management.user.form.add.error.role.notSelected");
			return "/management/user/form";
		}

		// Username not unique
		try {
			userManagementService.saveUser(user);
		} catch (DuplicateUserException e) {
			model.addAttribute("usernameError",
					"management.user.form.add.error.username.unique");
			return "/management/user/form";
		}

		// Succesfully saved
		model.addAttribute("user",
				userManagementService.getNewUserWithAllRoles());
		return "/management/user/form";
	}

	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public String tableUser(Model model) {
		model.addAttribute("users", userManagementService.findAllUser());
		for (User user : userManagementService.findAllUser()) {
			System.out.println("User: " + user.getUsername() + " "
					+ user.getEnabled() + " " + user.getVersion());
		}
		return "/management/user/table";
	}

	@RequestMapping(value = "/table/edit", method = RequestMethod.GET)
	public String editUserTable(Model model) {
		model.addAttribute("users", userManagementService.findAllUser());
		model.addAttribute("edit", true);

		return "/management/user/table";
	}

	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
	public String editUserPage(@PathVariable Long userId, Model model) {
		model.addAttribute("pageHeader", "management.user.edit.pageHeader");
		model.addAttribute("user", userManagementService.findUserbyId(userId));
		model.addAttribute("passwordField", false);

		return "/management/user/form";
	}

	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
	public String editUser(@PathVariable Long userId,
			@Valid @ModelAttribute("user") UserEventEdit user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {

		model.addAttribute("pageHeader", "management.user.edit.pageHeader");
		model.addAttribute("passwordField", false);

		user.setAllRoles(userManagementService.findAllRole());
		model.addAttribute("user", user);

		if (bindingResult.hasErrors()) {
			// Problem with username Variable ex: to Long
			// Problem with password Variable ex: to Long, to Short, not Equals
			return "/management/user/form";
		}

		// No Role Selected
		if (null == user.getRolesChecked()) {
			model.addAttribute("roleError",
					"management.user.edit.error.role.notSelected");
			return "/management/user/form";
		}

		try {
			userManagementService.updateUser(userId, user);
			// Username not unique
		} catch (DuplicateUserException e) {
			model.addAttribute("usernameError",
					"management.user.edit.error.username.unique");
			return "/management/user/form";
		}

		return "/management/user/form";
	}

	@RequestMapping(value = "/table/enable", method = RequestMethod.GET)
	public String enableUserTable(Model model) {
		// TODO
		model.addAttribute("users", userManagementService.findAllUser());
		model.addAttribute("enable", true);

		return "/management/user/table";
	}

	@RequestMapping(value = "/enable/{userId}", method = RequestMethod.GET)
	public String enableUserPage(@PathVariable Long userId, Model model) {
		
		model.addAttribute("user", userManagementService.findUserbyId(userId));
		boolean[] array = new boolean[2];
		array[0] = true;
		array[1] = false;
		model.addAttribute("enabledOptions", array);
					
		return "/management/user/enable";
	}

	@RequestMapping(value = "/enable/", method = RequestMethod.POST)
	public String enableUser(@Valid @ModelAttribute("user") UserEventEdit user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) 
	{
		// TODO
		model.addAttribute("users", userManagementService.findAllUser());
		model.addAttribute("enable", true);
		
		System.out.println("User from Post "+user.getUsername()+ " "+user.isEnabled());
		
		

		return "/management/user/table";
	}

}
