package com.library.controller;

import com.library.service.LibrarianService;

public class RemoveBook {
	public static void main(String[] args) {

		LibrarianService librarianService = new LibrarianService();
		librarianService.removeBookById(8);
	}
}
