package controllers;

import models.User;
import play.db.jpa.Blob;
import play.libs.MimeTypes;
import play.mvc.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
	   response.setContentTypeIfNotSet(user.photo.type());
	   renderBinary(user.photo.get());
	}

	public static void addUserWithFileName(File photo) throws FileNotFoundException {
	   final User user = new User();
	   user.photoFileName = photo.getName();
	   user.photo = new Blob();
	   user.photo.set(new FileInputStream(photo), MimeTypes.getContentType(photo.getName()));
	   user.save();
	   index();
	}

	public static void downloadUserPhoto(long id) {
	   final User user = User.findById(id);
	   response.setContentTypeIfNotSet(user.photo.type());
	   response.setHeader("Content-Disposition", "attachment; filename=\"" + user.photoFileName + "\"");
	   renderBinary(user.photo.get());
	}

}