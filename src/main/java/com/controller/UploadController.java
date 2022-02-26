package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lcltech.upload.FileBean;



@Controller
@RequestMapping("/upload")
public class UploadController {

	
	
    @RequestMapping(method = RequestMethod.POST)
    public String upload(FileBean uploadItem, BindingResult result) {

        return "import/importDone";
    }
}
