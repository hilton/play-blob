package models;

import play.db.jpa.Blob;
import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class User extends Model {

    public String name;
    public Blob photo;
}
