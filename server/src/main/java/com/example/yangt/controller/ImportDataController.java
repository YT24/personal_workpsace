package com.example.yangt.controller;

import com.example.yangt.poi.PoiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class ImportDataController {



    @Autowired
    public PoiService poiService;

    @PostMapping("/importUser")
    public String importUser(@RequestParam("file") MultipartFile[] file, HttpServletRequest request, HttpServletResponse response) throws Exception {

        poiService.batchImportUser("",file[0],request,response);

        return null;
    }
}
