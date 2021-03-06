package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

import com.avaje.ebean.validation.NotNull;

@Entity
public class Area extends Model {

	@Id
	public Long id;

	@NotNull
	public String name;

	public static Finder<Long, Area> find = new Finder(Long.class, Area.class);
	
	public static Area get(Long id){
		return find.byId(id);
	}
	
	public static List<Area> all() {
		return find.all();
	}		

	public static void delete(Long id) {
		find.byId(id).delete();
	}
	
}
