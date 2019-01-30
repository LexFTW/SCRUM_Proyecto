package main.implementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.interfaces.IProject;
import main.models.Project;

public class ProjectSQLRemote implements IProject{

	private EntityManagerFactory factory;
	private EntityManager entityManager;
	private Connection connection;
	private Statement statement;
	
	
	public ProjectSQLRemote() {
		factory = Persistence.createEntityManagerFactory("ScrumHibernate");
		entityManager = factory.createEntityManager();
	}
	
	@Override
	public void insertProject(Project project) {
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
		replicateProject(project);
	}

	private void getConnectionLocal() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void replicateProject(Project project) {
		this.getConnectionLocal();
		if(this.connection != null) {
			try {
				statement = connection.createStatement();

				String sqlQueryProject = "INSERT INTO `projects` (ProjectTitle, ProjectDescription, ScrumMasterID, ProductOwnerID, CreatedAt, UpdatedAt)"
						+ "VALUES('" + project.getProjectName() + "', '"
						+ project.getProjectDescription() + "', '" + project.getScrumMasterID() + "', '"
						+ project.getProductOwnerID() + "', '" + project.getCreatedAt() + "', "
						+ project.getUpdatedAt() + ");";
				
				statement.executeUpdate(sqlQueryProject);
				statement.close();
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Project> getAllProjects() {
		return (ArrayList<Project>) entityManager.createQuery("SELECT project FROM Project project").getResultList();
	}
}
