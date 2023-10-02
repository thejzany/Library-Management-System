package com.library.controller;

import com.library.dto.Librarian;
import com.library.service.LibrarianService;

public class LibrarianSave {
	public static void main(String[] args) {
		LibrarianService librarianService = new LibrarianService();
		Librarian librarian = new Librarian();
		librarian.setName("lib6");
		librarian.setMail("lib6@mail.com");
		librarian.setStatus("Approved");
		librarianService.saveLibrarian(librarian);
	}
}
