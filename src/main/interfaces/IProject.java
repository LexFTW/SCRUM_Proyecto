package main.interfaces;

import java.util.ArrayList;

import main.models.Project;

public interface IProject {
	
	public void insertProject(Project project);
	public ArrayList<Project> getAllProjects();
	public Project getProject(String projectTitle);
	public String getProductOwner(int id);
	public String getScrumMaster(int id);
}
