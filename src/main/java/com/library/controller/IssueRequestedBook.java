package com.library.controller;

import com.library.service.LibrarianService;

public class IssueRequestedBook {
	public static void main(String[] args) {
		LibrarianService librarianService = new LibrarianService();
		librarianService.issueRequestedBookById(1, 1);
	}
}
