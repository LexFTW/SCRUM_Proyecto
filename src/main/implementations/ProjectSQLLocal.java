package main.implementations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.interfaces.IProject;
import main.models.Project;


public class ProjectSQLLocal implements IProject {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public ProjectSQLLocal() {
		
	}

	private void getConnectionLocal() {
		try {
			Class.forName("org.sqlite.JDBC");
			try {
				this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
				System.out.println("Conexion embebida conectada.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertProject(Project project) {
		File fLog = new File("src/main/resources/log");
		FileWriter fw;
		if (fLog.exists()) {
			if (this.connection != null) {
				try {
					fw = new FileWriter(fLog, true);
					statement = connection.createStatement();
					
					String query = "insert into projects (ProjectTitle, ProjectDescription, ScrumMasterID, ProductOwnerID, CreatedAt, UpdatedAt) VALUES "
							+ "('" + project.getProjectName() + "', '" + project.getProjectDescription() + "', '"
							+ project.getScrumMasterID() + "', '" + project.getProductOwnerID() + "', '" + project.getCreatedAt() + "', '"
							+ project.getUpdatedAt() + "')";

					fw.write(query + "\n");
					fw.close();
					this.statement.executeUpdate(query);
					this.statement.close();

				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}finally {
					try {
						this.connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}

		} else {
			System.out.println("El archivo especificado no existe");
		}

	}

	@Override
	public ArrayList<Project> getAllProjects() {
		ArrayList<Project> projects = new ArrayList<>();
		getConnectionLocal();
		if(this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement.executeQuery("SELECT * FROM projects");
				while(!resultSet.next()) {
					Project project = new Project();
					project.setProjectID(this.resultSet.getInt("ProjectID"));
					project.setProjectName(this.resultSet.getString("ProjectTitle"));
					project.setProjectDescription(this.resultSet.getString("ProjectDescription"));
					project.setScrumMasterID(this.resultSet.getInt("ScrumMasterID"));
					project.setProductOwnerID(this.resultSet.getInt("ProductOwnerID"));
					project.setCreatedAt(this.resultSet.getDate("CreatedAt"));
					project.setUpdatedAt(this.resultSet.getDate("UpdatedAt"));
					projects.add(project);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return projects;
	}

}
