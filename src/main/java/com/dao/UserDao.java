package com.dao;

import java.util.List;

import com.entity.User;

public interface UserDao {

	public boolean check(String name, String pass);
	public List<String> getRole(String name);
	public User getUserByName(String name);
	public List<User> getList();
	void insert(User user);
	void delete(int id);
	void update(User user);
	List<User> getListByRole(Integer role);
	
	User getUserById(Integer id);

	
}
