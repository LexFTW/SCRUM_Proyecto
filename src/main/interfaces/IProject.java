package main.interfaces;

import java.util.ArrayList;

import main.models.Project;

public interface IProject {

	public Project project = new Project();

	public void insertProject(Project project,boolean replic);

	public ArrayList<Project> getAllProjects();

	public ArrayList<Project> getAllProjects(int id);

	public ArrayList<Project> getAllProjectsDevelopers(int userID);

	public Project getProject(String projectTitle);

	public String getProductOwner(int id);

	public String getScrumMaster(int id);

	public Project getProjectSelected();

	
}
