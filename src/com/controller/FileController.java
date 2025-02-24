package com.controller;

import java.io.IOException;

import com.annotation.AnnotationController;
import com.annotation.MappingAnnotation;
import com.annotation.POST;
import com.annotation.ParamAnnotation;
import com.annotation.PartAnnotation;
import com.utilFrame.ModelView;
import com.utilFrame.PartPerso;

import jakarta.servlet.http.Part;

@AnnotationController
public class FileController {
     
    @MappingAnnotation(url = "/showFormFile")
    public ModelView showForm() {
        return new ModelView("file.jsp");
    }
    
    @POST
    @MappingAnnotation(url = "/submitFile")
    public ModelView handlePost(@ParamAnnotation("nom") String name, @PartAnnotation(value = "filetest") Part file) {
        ModelView modelView = new ModelView("resultFile.jsp");
        

        modelView.addObject("nom", name);
        modelView.addObject("filetest", file); // Now passing PartPerso

        return modelView;
    }
}
