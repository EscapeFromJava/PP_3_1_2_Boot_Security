package ru.kata.spring.boot_security.demo.util;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator {
    public static List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String login = (Login.values()[new Random().nextInt(Login.values().length)].name()).concat(String.valueOf(new Random().nextInt(100,999)));
            String password = String.valueOf(new Random().nextInt(1000, 9999));
            String firstName = FirstName.values()[new Random().nextInt(FirstName.values().length)].name();
            String secondName = SecondName.values()[new Random().nextInt(SecondName.values().length)].name();
            String email = firstName.concat(".").concat(secondName).concat("@gmail.com").toLowerCase();
            User user = new User(login, password, firstName, secondName, email);
            users.add(user);
        }
        return users;
    }

    enum Login {
        Xavisonat,
        Gnonneyadi,
        Maniab,
        Nardon,
        Amptonath,
        Ulysanj,
        Franzaaaaa,
        Xellenarab,
        Vinahirli,
        Deacobaaaa,
        Pakuji,
        Kordene,
        Kathad,
        Merylerian,
        Wasasien,
        Victoryd,
        Xavisony,
        Bibiannalu,
        Dathiani,
        Burtneyah
    }

    enum FirstName {
        Jose,
        Earl,
        Willie,
        Jesse,
        Patrick,
        Fred,
        Steven,
        Jerry,
        Samuel,
        Gene,
    }

    enum SecondName {
        Sullivan,
        Moreno,
        Hughes,
        Lopez,
        Hale,
        Wilkerson,
        Farmer,
        Holmes,
        Wood,
        Thompson
    }
}
