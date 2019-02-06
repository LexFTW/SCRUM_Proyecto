package main.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "specifications")
public class Specification {

	private int specificationID;
	private String specificationTitle;
	private String specificationDescription;
	private int specificationStatus;
	private int specificationTime;
	private int sprintID;
	
	@Id
	@Column(name ="SpecificationID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getSpecificationID() {
		return specificationID;
	}
	public void setSpecificationID(int specificationID) {
		this.specificationID = specificationID;
	}
	
	@Column(name ="SpecificationTitle")
	public String getSpecificationTitle() {
		return specificationTitle;
	}
	public void setSpecificationTitle(String specificationTitle) {
		this.specificationTitle = specificationTitle;
	}
	
	@Column(name ="SpecificationDescription")
	public String getSpecificationDescription() {
		return specificationDescription;
	}
	public void setSpecificationDescription(String specificationDescription) {
		this.specificationDescription = specificationDescription;
	}
	
	@Column(name ="SpecificationStatus")
	public int getSpecificationStatus() {
		return specificationStatus;
	}
	public void setSpecificationStatus(int specificationStatus) {
		this.specificationStatus = specificationStatus;
	}
	
	@Column(name ="SpecificationTime")
	public int getSpecificationTime() {
		return specificationTime;
	}
	public void setSpecificationTime(int specificationTime) {
		this.specificationTime = specificationTime;
	}
	
	@Column(name ="SprintID")
	public int getSprintID() {
		return sprintID;
	}
	public void setSprintID(int sprintID) {
		this.sprintID = sprintID;
	}
	
	
}
