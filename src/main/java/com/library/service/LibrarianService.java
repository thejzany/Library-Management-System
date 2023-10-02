package com.library.service;

import java.util.List;

import com.library.dao.BookDao;
import com.library.dao.LibrarianDao;
import com.library.dao.StudentDao;
import com.library.dto.Book;
import com.library.dto.Librarian;
import com.library.dto.Student;

public class LibrarianService {

	LibrarianDao librarianDao = new LibrarianDao();
	BookDao bookDao = new BookDao();
	StudentDao studentDao = new StudentDao();
	Librarian librarian = new Librarian();

	public Librarian saveLibrarian(Librarian librarian) {
		librarian.setStatus("Unapproved");
		return librarianDao.saveLibrarian(librarian);
	}

	public Librarian deleteLibrarianById(int id) {
		Librarian librarian = getLibrarianById(id);
		if (librarian != null) {
			return librarianDao.deleteLibrarianById(id);
		}
		return null;
	}

	public Librarian updateLibrarianNameById(int id, String newname) {
		Librarian librarian = getLibrarianById(id);
		if (librarian != null) {
			return librarianDao.updateLibrarianNameById(id, newname);
		}
		return null;
	}

	public Librarian updateLibrarianMailById(int id, String newmail) {
		Librarian librarian = getLibrarianById(id);
		if (librarian != null) {
			return librarianDao.updateLibrarianMailById(id, newmail);
		}
		return null;
	}

	public Librarian getLibrarianById(int id) {
		return librarianDao.getLibrarianById(id);
	}

	public List<Librarian> getAllLibrarians() {
		return librarianDao.getAllLibrarians();
	}

	public Boolean addBook(Book book) {
		bookDao.saveBook(book);
		System.out.println("Book Added");
		return true;
	}

	public Boolean removeBookById(int bookid) {
		bookDao.deleteBookById(bookid);
		System.out.println("Book Removed");
		return true;
	}

	public boolean issueBookById(int bookid, int studid, int libid) {
		Book book = bookDao.getBookById(bookid);
		Student student = studentDao.getStudentById(studid);
		Librarian librarian = librarianDao.getLibrarianById(libid);

		if (librarian != null && book != null && student != null
				&& librarian.getStatus().equalsIgnoreCase("Approved")) {

			if (!book.getStatus().equalsIgnoreCase("Issued") && !book.getStatus().equalsIgnoreCase("InRequest")) {
				book.setStatus("Issued");
				book.setStudent(student);
				book.setLibrarian(librarian);
				System.out.println("Book Issued");
			} else {
				System.out.println("Book Status: " + book.getStatus() + ", Book Currently Unavailable");
			}
			return bookDao.issue(book);
		}
		return false;
	}

	public boolean returnBookById(int bookid) {
		Book book = bookDao.getBookById(bookid);
		if (book != null && book.getStatus().equalsIgnoreCase("Issued")) {
			Student student = book.getStudent();
			Librarian librarian = book.getLibrarian();

			if (student != null && librarian != null) {
				book.setStatus("Available");
				book.setStudent(null);
				book.setLibrarian(null);
				System.out.println("Book Returned");
				return bookDao.returnBook(book);
			}
		}
		return false;
	}

	public boolean issueRequestedBookById(int bookid, int studid) {
		Book book = bookDao.getBookById(bookid);
		Student student = studentDao.getStudentById(studid);

		if (book != null && student != null && book.getStatus().equalsIgnoreCase("InRequest")
				&& studid == book.getStudent().getId()) {
			book.setStatus("Issued");
			System.out.println("Issued Requested Book to the Student who Requested it.");
			return bookDao.requestBook(book);
		} else {
			System.out.println("Book is InRequest by other Student");
		}
		return false;
	}
}
