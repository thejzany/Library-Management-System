package com.library.controller;

import com.library.service.LibrarianService;

public class IssueBook {
	public static void main(String[] args) {
		LibrarianService librarianService = new LibrarianService();
		librarianService.issueBookById(9, 3, 1);
	}
}
