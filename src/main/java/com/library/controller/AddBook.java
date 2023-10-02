package com.library.controller;

import com.library.dto.Book;
import com.library.service.LibrarianService;

public class AddBook {
	public static void main(String[] args) {

		Book book = new Book();
		book.setName("Book");
		book.setStatus("Available");

		LibrarianService librarianService = new LibrarianService();

		librarianService.addBook(book);
	}
}
