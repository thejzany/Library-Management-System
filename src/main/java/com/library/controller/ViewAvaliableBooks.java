package com.library.controller;

import java.util.List;

import com.library.dto.Book;
import com.library.service.StudentService;

public class ViewAvaliableBooks {
	public static void main(String[] args) {

		StudentService studentService = new StudentService();
		List<Book> books = studentService.viewAllAvaliableBooks();

		for (Book b : books) {
			System.out.println("=========================");
			System.out.print(b.getId() + " " + "| ");
			System.out.print(b.getName() + " " + "| ");
			System.out.println(b.getStatus() + " " + "| ");
			System.out.println("=========================");
		}
	}
}
