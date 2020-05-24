package com.compica.impotsrapido.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FinancialFileUploadController {

	@RequestMapping("/upload")
	public @ResponseBody String greeting(Object object) {
		return "Hello, World";
	}
}
