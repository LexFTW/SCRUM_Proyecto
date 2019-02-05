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
import main.models.User;
import main.models.UserGroupIntegrant;
import main.models.UserPermission;

public class ProjectSQLRemote implements IProject {

	private EntityManagerFactory factory;
	private EntityManager entityManager;
	private Connection connection;
	private Statement statement;
	private Project project;

	public ProjectSQLRemote() {
		factory = Persistence.createEntityManagerFactory("ScrumHibernate");
		entityManager = factory.createEntityManager();
	}

	@Override
	public void insertProject(Project project, boolean replic) {
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
		if (replic)
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
		if (this.connection != null) {
			try {
				statement = connection.createStatement();

				String sqlQueryProject = "INSERT INTO `projects` (ProjectTitle, ProjectDescription, ScrumMasterID, ProductOwnerID, CreatedAt, UpdatedAt)"
						+ "VALUES('" + project.getProjectName() + "', '" + project.getProjectDescription() + "', '"
						+ project.getScrumMasterID() + "', '" + project.getProductOwnerID() + "', '"
						+ project.getCreatedAt() + "', " + project.getUpdatedAt() + ");";

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

	@Override
	public ArrayList<Project> getAllProjects(int id) {
		return (ArrayList<Project>) entityManager
				.createQuery(
						"SELECT project FROM Project project WHERE ProductOwnerID = " + id + "OR ScrumMasterID = " + id)
				.getResultList();
	}

	@Override
	public Project getProject(String projectTitle) {
		this.project = (Project) entityManager
				.createQuery("SELECT project FROM Project project WHERE ProjectTitle = '" + projectTitle + "'")
				.getSingleResult();
		return this.project;
	}

	@Override
	public String getProductOwner(int id) {
		User user = (User) entityManager.createQuery("SELECT user FROM User user WHERE UserID = " + id)
				.getSingleResult();
		return user.getUserName();
	}

	@Override
	public String getScrumMaster(int id) {
		User user = (User) entityManager.createQuery("SELECT user FROM User user WHERE UserID = " + id)
				.getSingleResult();
		return user.getUserName();
	}

	@Override
	public Project getProjectSelected() {
		return this.project;
	}

	@Override
	public ArrayList<Project> getAllProjectsDevelopers(int userID) {
		ArrayList<UserGroupIntegrant> integrants = (ArrayList<UserGroupIntegrant>) entityManager
				.createQuery("SELECT integrant FROM UserGroupIntegrant integrant WHERE UserID = " + userID)
				.getResultList();
		ArrayList<Project> projectsDev = new ArrayList<>();
		for (UserGroupIntegrant integrant : integrants) {
			projectsDev.add((Project) entityManager
					.createQuery("SELECT p FROM Project p WHERE ProjectID = " + integrant.getProjectID())
					.getSingleResult());
		}

		return projectsDev;
	}
}
