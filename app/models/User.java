package models;

import play.db.jpa.Blob;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.io.File;

@Entity
public class User extends Model {

	public String photoFileName;
//	public Blob photo;
	public DateBlob photo;

	@Override
	public void _delete() {
		super._delete();
		photo.getFile().delete();
	}
}
