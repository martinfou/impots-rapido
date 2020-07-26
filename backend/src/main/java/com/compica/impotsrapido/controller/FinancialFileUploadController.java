package com.compica.impotsrapido.controller;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compica.impotsrapido.service.FileService;
import com.compica.impotsrapido.service.FinancialDataParser;
import com.webcohesion.ofx4j.domain.data.common.TransactionList;
import com.webcohesion.ofx4j.io.OFXParseException;

@Controller
public class FinancialFileUploadController {

    @Autowired
    FileService fileService;
    
    @Autowired
    FinancialDataParser financialDataParser;
    
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException, OFXParseException {

        Path uploadFile = fileService.uploadFile(file);
        
        TransactionList output = financialDataParser.parse(uploadFile.toFile());
        

        redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
}
