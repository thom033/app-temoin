package com.need;


import java.util.ArrayList;
import java.util.List;
import com.need.User;

public class UserStore {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("Ninah", "Mandrimena Iavoloha", 18, "Web Design"));
        users.add(new User("Maha", "Ambohibao", 16, "Dev"));
        users.add(new User("Thomis", "Faravohitra", 21, "Dev"));
    }

    public static User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }
}