package controllers;

import models.DateBlob;
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
		notFoundIfNull(user);
	   response.setContentTypeIfNotSet(user.photo.type());
	   renderBinary(user.photo.get());
	}

	public static void addUserWithFileName(File photo) throws FileNotFoundException {
		final User user = new User();
	   user.photoFileName = photo.getName();
	   user.photo = new DateBlob();
	   user.photo.set(new FileInputStream(photo), MimeTypes.getContentType(photo.getName()));
	   user.save();
	   index();
	}

	public static void downloadUserPhoto(long id) {
	   final User user = User.findById(id);
	   response.setContentTypeIfNotSet(user.photo.type());
	   renderBinary(user.photo.get(), user.photoFileName);
	}

	public static void updateUser(User user) {
		user.save();
		index();
	}

	public static void deleteUser(long id) {
		final User user = User.findById(id);
//		user.photo.getFile().delete();
		user.delete();
		index();
	}


}