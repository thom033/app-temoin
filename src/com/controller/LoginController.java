package com.controller;


import com.annotation.AnnotationController;
import com.annotation.MappingAnnotation;
import com.annotation.ParamAnnotation;

import com.utilFrame.ModelView;
import com.utilFrame.MySession;

import com.need.User;
import com.need.UserStore;

@AnnotationController
public class LoginController {

    private MySession session;

    public void setSession(MySession session) {
        this.session = session;
    }

    @MappingAnnotation(url = "/loginForm")
    public ModelView showLoginForm() {
        ModelView mv = new ModelView("Login.jsp");
        return mv;
    }

    @MappingAnnotation(url = "/submitLogin")
    //@MappingAnnotation(url = "/submitData")
    public ModelView submitConnection(@ParamAnnotation("nom") String name) {
        User user = UserStore.getUserByName(name);

        if (user != null) {
            session.add("user", user);
            ModelView modelview = new ModelView("user_page.jsp");
            return modelview;
        } else {
            ModelView modelview = new ModelView("Login.jsp");
            modelview.addObject("error", "No user found");
            return modelview;
        }
    }

    @MappingAnnotation(url = "/logout")
    public ModelView logout() {
        session.delete("user");
        ModelView mv = new ModelView("Login.jsp");
        return mv;
    }

    @MappingAnnotation(url = "/checkSession")
    public ModelView check(@ParamAnnotation("nom") String name) {
        User user = UserStore.getUserByName(name);

        session.add("user", user);
        ModelView modelview = new ModelView("check_session.jsp");
        return modelview;
    }

    @MappingAnnotation(url = "/submitLogin1")
    public ModelView submitLogin1(@ParamAnnotation("nom") String name) {
        User user = UserStore.getUserByName(name);

        if (user != null) {
            session.add("user", user);
            ModelView modelview = new ModelView("user_page.jsp");
            return modelview;
        } else {
            ModelView modelview = new ModelView("Login.jsp");
            modelview.addObject("error", "No user found");
            return modelview;
        }
    }
}
