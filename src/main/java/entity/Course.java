package entity;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import com.google.gson.annotations.SerializedName;

public class Course {
	@BsonId
	@BsonProperty("_id")
	@SerializedName("_id")
	private String id;
	private String name;
	private String description;
	private int credit;
	private List<Section> sections;
	public Course() {
		// TODO Auto-generated constructor stub
	}
	public Course(String id, String name, String description, int credit, List<Section> sections) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.credit = credit;
		this.sections = sections;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", credit=" + credit
				+ ", sections=" + sections + "]";
	}
	
}
