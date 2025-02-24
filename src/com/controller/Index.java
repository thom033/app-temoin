package com.controller;

import com.annotation.AnnotationController;
import com.annotation.Get;
import com.annotation.ParamAnnotation;
import com.annotation.ParamObjectAnnotation;
import com.annotation.RestApi;
import com.annotation.MappingAnnotation;
import com.annotation.POST;

import com.utilFrame.ModelView;
import com.need.Employe;

@AnnotationController
public class Index {

    @Get
    @MappingAnnotation(url = "/index")
    public ModelView index() {
        ModelView modelView = new ModelView("index.jsp");
        return modelView;
    }
}