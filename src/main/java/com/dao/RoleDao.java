package com.dao;

import java.util.List;

import com.entity.PartRole;
import com.entity.Role;
import com.entity.SubRole;

public interface RoleDao {

		public List<Role> getList();

	

		public Role getById(int id);



		






		Role getByRole(int pr);
}
