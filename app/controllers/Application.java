package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void addUser(User user) {
        user.save();
        index();
    }

    public static void userPhoto(long id) {
        final User user = User.findById(id);
        renderBinary(user.photo.get());
    }
}