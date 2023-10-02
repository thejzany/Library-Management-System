package com.library.service;

import java.util.ArrayList;
import java.util.List;

import com.library.dao.BookDao;
import com.library.dao.LibrarianDao;
import com.library.dao.StudentDao;
import com.library.dto.Book;
import com.library.dto.Librarian;
import com.library.dto.Student;

public class StudentService {
	StudentDao studentDao = new StudentDao();
	LibrarianDao librarianDao = new LibrarianDao();
	BookDao bookDao = new BookDao();
	Student student = new Student();

	public Student saveStudent(Student student) {
		return studentDao.saveStudent(student);
	}

	public Student deleteStudentById(int id) {
		Student student = getStudentById(id);
		if (student != null) {
			return studentDao.deleteStudentById(id);
		}
		return null;
	}

	public Student updateStudentNameById(int id, String newname) {
		Student student = getStudentById(id);
		if (student != null) {
			return studentDao.updateStudentNameById(id, newname);
		}
		return null;
	}

	public Student updateStudentMailById(int id, String newmail) {
		Student student = getStudentById(id);
		if (student != null) {
			return studentDao.updateStudentMailById(id, newmail);
		}
		return null;
	}

	public Student getStudentById(int id) {
		return studentDao.getStudentById(id);
	}

	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	public boolean requestBook(int bookid, int studid, int libid) {

		Book book = bookDao.getBookById(bookid);
		Student student = studentDao.getStudentById(studid);
		Librarian librarian = librarianDao.getLibrarianById(libid);

		if (librarian != null && book != null && student != null && book.getStatus().equalsIgnoreCase("Available")
				&& librarian.getStatus().equalsIgnoreCase("Approved")) {
			book.setStatus("InRequest");
			book.setStudent(student);
			book.setLibrarian(librarian);
			return bookDao.requestBook(book);
		} else {
			System.out.println("Book Unavailable for Requesting");
		}
		return true;
	}

	public List<Book> viewAllAvaliableBooks() {
		BookService bookService = new BookService();
		List<Book> books = bookService.getAllBooks();
		List<Book> avaliableBooks = new ArrayList<Book>();
		for (Book book : books) {
			if (book.getStatus().equalsIgnoreCase("Available")) {
				avaliableBooks.add(book);
			}
		}
		return avaliableBooks;
	}

}
