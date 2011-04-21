package controllers;

import models.User;
import play.mvc.Controller;

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