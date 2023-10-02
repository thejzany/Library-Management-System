package com.library.controller;

import com.library.dto.Admin;
import com.library.service.AdminService;

public class AdminSave {
	public static void main(String[] args) {

		AdminService adminService = new AdminService();
		Admin admin = new Admin();

		admin.setName("admin3");
		admin.setUsername("a3");
		admin.setPassword("a3pass");
		adminService.saveAdmin(admin);
	}
}
