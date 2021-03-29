package com.suyang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suyang.domain.User;

@RestController
public class UserController {
	/**
	 * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
	 */
	public static final String DEMO_CACHE_NAME = "userCache";

	private static List<User> userList = new ArrayList<User>();

	@Cacheable(value = DEMO_CACHE_NAME, key = "'user_'+#id")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User findOne(@PathVariable("id") final int id) {
		System.out.println("没有走缓存！" + id);
		User u = userList.stream().filter(new Predicate<User>() {

			@Override
			public boolean test(User user) {
				return id == user.getId();
			}
		}).findFirst().get();

		return u;
	}

	@Cacheable(value = DEMO_CACHE_NAME)
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> findAll() {
		System.out.println("没有走缓存！all");
		return userList;
	}

	@CacheEvict(value = DEMO_CACHE_NAME, key = "'user'")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User create(int id, String name) {
		User user = new User(id, name);
		userList.add(user);
		return user;
	}

	/**
	 * 在支持Spring
	 * Cache的环境下，对于使用@Cacheable标注的方法，Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，
	 * 如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
	 * @CachePut也可以声明一个方法支持缓存功能。
	 * 与@Cacheable不同的是使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，
	 * 并将执行结果以键值对的形式存入指定的缓存中。 @CachePut也可以标注在类上和方法上。
	 * 使用@CachePut时我们可以指定的属性跟@Cacheable是一样的。
	 */
	@CachePut(value = DEMO_CACHE_NAME, key = "'user_'+#id")
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

	@CacheEvict(value = DEMO_CACHE_NAME, key = "'user_'+#id") // 这是清除缓存.
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
