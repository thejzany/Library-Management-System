package com.library.service;

import java.util.ArrayList;
import java.util.List;

import com.library.dao.AdminDao;
import com.library.dao.LibrarianDao;
import com.library.dto.Admin;
import com.library.dto.Librarian;

public class AdminService {

	AdminDao adminDao = new AdminDao();
	LibrarianDao librarianDao = new LibrarianDao();

	public Admin saveAdmin(Admin admin) {
		return adminDao.saveAdmin(admin);
	}

	public Admin deleteAdminById(int id) {
		Admin admin = getAdminById(id);
		if (admin != null) {
			return adminDao.deleteAdminById(id);
		}
		return null;
	}

	public Admin updateNameById(int id, String newname) {
		Admin admin = getAdminById(id);
		if (admin != null) {
			return adminDao.updateNameById(id, newname);
		}
		return null;
	}

	public Admin updateUsernameByIdPass(int id, String username, String password, String newusername) {
		if (validateAdmin(id, username, password) == true) {
			Admin admin = getAdminById(id);
			if (admin != null) {
				return adminDao.updateUsernameByIdPass(id, newusername);
			}
		}
		return null;
	}

	public Admin updatePasswordByIdPass(int id, String username, String password, String newpassword) {
		if (validateAdmin(id, username, password) == true) {
			Admin admin = getAdminById(id);
			if (admin != null) {
				return adminDao.updatePasswordByIdPass(id, newpassword);
			}
		}
		return null;
	}

	public Admin getAdminById(int id) {
		return adminDao.getAdminById(id);
	}

	public List<Admin> getAllAdmins() {
		return adminDao.getAllAdmins();
	}

	public boolean validateAdmin(int id, String loginusername, String loginpassword) {
		Admin admin = adminDao.getAdminById(id);
		if (admin.getUsername().equals(loginusername) && admin.getPassword().equals(loginpassword)) {
			System.out.println("Login Successfull");
			return true;
		} else {
			System.out.println("Invalid Credentials");
			return false;
		}
	}

	public boolean approveLibrarianById(int libid, int adminid) {
		Librarian librarian = librarianDao.getLibrarianById(libid);
		Admin admin = adminDao.getAdminById(adminid);
		if (librarian != null && admin != null) {
			librarian.setStatus("Approved");
			librarian.setAdmin(admin);
			System.out.println(librarian.getName() + " Approved");
			return librarianDao.approveReject(librarian);
		}
		return false;
	}

	public boolean rejectLibrarianById(int libid, int adminid) {
		Librarian librarian = librarianDao.getLibrarianById(libid);
		Admin admin = adminDao.getAdminById(adminid);
		if (librarian != null && admin != null) {
			librarian.setStatus("Rejected");
			librarian.setAdmin(admin);
			System.out.println(librarian.getName() + " Rejected");
			return librarianDao.approveReject(librarian);
		}
		return false;
	}

	public List<Librarian> viewAllUnapprovedLibrarians() {
		LibrarianService librarianService = new LibrarianService();
		List<Librarian> librarians = librarianService.getAllLibrarians();
		List<Librarian> unapprovedLibs = new ArrayList<Librarian>();
		for (Librarian librarian : librarians) {
			if (librarian.getStatus().equalsIgnoreCase("Unapproved")) {
				unapprovedLibs.add(librarian);
			}
		}
		return unapprovedLibs;
	}
}
