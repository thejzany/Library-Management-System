package com.library.controller;

import com.library.service.StudentService;

public class RequestBook {

		public static void main(String[] args) {
			
			StudentService studentService= new StudentService();
			studentService.requestBook(1, 2, 1);
		}

	}
