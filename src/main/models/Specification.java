package main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specifications")
public class Specification implements Serializable{

	private int specificationID;
	private String specificationTitle;
	private String specificationDescription;
	private int specificationStatus;
	private int specificationTime;
	private int sprintID;
	private int projectID;

	
	
	public Specification() {
		super();
	}

	public Specification(int specificationID, String specificationTitle, String specificationDescription,
			int specificationStatus, int specificationTime, int sprintID, int projectID) {
		super();
		this.specificationID = specificationID;
		this.specificationTitle = specificationTitle;
		this.specificationDescription = specificationDescription;
		this.specificationStatus = specificationStatus;
		this.specificationTime = specificationTime;
		this.sprintID = sprintID;
		this.projectID = projectID;
	}

	@Id
	@Column(name = "SpecificationID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getSpecificationID() {
		return specificationID;
	}

	public void setSpecificationID(int specificationID) {
		this.specificationID = specificationID;
	}

	@Column(name = "SpecificationTitle")
	public String getSpecificationTitle() {
		return specificationTitle;
	}

	public void setSpecificationTitle(String specificationTitle) {
		this.specificationTitle = specificationTitle;
	}

	@Column(name = "SpecificationDescription")
	public String getSpecificationDescription() {
		return specificationDescription;
	}

	public void setSpecificationDescription(String specificationDescription) {
		this.specificationDescription = specificationDescription;
	}

	@Column(name = "SpecificationStatus")
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

	@Column(name = "ProjectID")
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	@Override
	public String toString() {
		return "Specification [specificationID=" + specificationID + ", specificationTitle=" + specificationTitle
				+ ", specificationDescription=" + specificationDescription + ", specificationStatus="
				+ specificationStatus + ", specificationTime=" + specificationTime + ", sprintID=" + sprintID
				+ ", projectID=" + projectID + "]";
	}
}
