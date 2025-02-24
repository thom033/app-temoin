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
public class Controller1 {

    @POST
    @MappingAnnotation(url = "/submitData")
    public ModelView handlePost(@ParamAnnotation("nom") String name, @ParamAnnotation("age") String age) {
        ModelView modelView = new ModelView("result2.jsp");
        modelView.addObject("nom", name); 
        modelView.addObject("age", age);
        return modelView;
    }

    @Get
    @MappingAnnotation(url = "/submitData")
    public ModelView hendleGet(@ParamAnnotation("nom") String name, @ParamAnnotation("age") String age) {
        ModelView modelView = new ModelView("result2.jsp");
        modelView.addObject("nom", name); 
        modelView.addObject("age", age);
        return modelView;
    }

    @RestApi
    @MappingAnnotation(url = "/allMethods")
    public static String getName() {
        return "Ninah";
    }

    @MappingAnnotation(url = "/allAge")
    public static int getAge() {
        return 19;
    }

    @RestApi
    @MappingAnnotation(url = "/list")
    public static ModelView modelViewExample() {
        ModelView modelView = new ModelView("page.jsp");
        modelView.addObject("message", "Ninah is there");
        return modelView;
    }

  
    
    @MappingAnnotation(url = "/showForm")
    public ModelView showForm() {
        return new ModelView("form.jsp");
    }

    @MappingAnnotation(url = "/submitForm")
    public ModelView submitForm(String name, @ParamAnnotation("age") String age) {
        ModelView modelView = new ModelView("result.jsp");
        modelView.addObject("nom", name);
        modelView.addObject("age", age);
        return modelView;
    }

    @MappingAnnotation(url = "/showFormEmp")
    public ModelView showFormEmp() {
        return new ModelView("empForm.jsp");
    }

    @MappingAnnotation(url = "/FormObjectResult")
    public ModelView submitForm2(@ParamObjectAnnotation(objName = "emp") Employe emp) {
        System.out.println("Received emp: " + emp);
        ModelView modelview = new ModelView("empResult.jsp");
        if (emp != null) {
            Employe employe = new Employe(emp.getNom(), emp.getAge());
            modelview.addObject("emp", employe);
        }
        return modelview;
    }
}
