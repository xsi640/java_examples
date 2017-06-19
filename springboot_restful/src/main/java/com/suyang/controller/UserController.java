package com.suyang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suyang.domain.User;

@RestController
public class UserController {
	private static List<User> userList = new ArrayList<User>();

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User findOne(@PathVariable("id") final int id) {
		User u = userList.stream().filter(new Predicate<User>() {

			@Override
			public boolean test(User user) {
				return id == user.getId();
			}
		}).findFirst().get();

		return u;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> findAll() {
		return userList;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User create(int id,String name) {
		User user = new User(id, name);
		userList.add(user);
		return user;
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public User modify(final int id, String name) {
		User existsUser = userList.stream().filter(new Predicate<User>() {

			@Override
			public boolean test(User u) {
				return id == u.getId();
			}
		}).findFirst().get();
		if (existsUser != null) {
			userList.remove(existsUser);
		}
		User user = new User(id, name);
		userList.add(user);
		return user;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") final int id) {
		User existsUser = userList.stream().filter(new Predicate<User>() {

			@Override
			public boolean test(User u) {
				return id == u.getId();
			}
		}).findFirst().get();
		if (existsUser != null) {
			userList.remove(existsUser);
		}
	}
}
