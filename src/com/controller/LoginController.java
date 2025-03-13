package com.controller;

import com.annotation.AnnotationController;
import com.annotation.Get;
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
        ModelView mv = new ModelView("index.jsp");
        return mv;
    }

    @MappingAnnotation(url = "/login")
    public ModelView handleLogin(@ParamAnnotation("username") String username, @com.annotation.Param(name = "password") String password) {
        User user = UserStore.getUserByName(username);

        if (user != null && "password".equals(password)) {
            session.add("user", user);
            ModelView mv = new ModelView("user_page.jsp");
            mv.addObject("message", "Login successful!");
            return mv;
        } else {
            ModelView mv = new ModelView("login.jsp");
            mv.addObject("message", "Invalid username or password.");
            return mv;
        }
    }

    @MappingAnnotation(url = "/logout")
    public ModelView logout() {
        session.delete("user");
        ModelView mv = new ModelView("login.jsp");
        return mv;
    }
}