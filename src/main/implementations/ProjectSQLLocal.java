package main.implementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.interfaces.IProject;
import main.models.Project;
import main.models.UserPermission;

public class ProjectSQLLocal implements IProject {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public ProjectSQLLocal() {
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
		FileReader fr;
		BufferedReader br;
		FileWriter fw;
		if (fLog.exists()) {
			if (this.connection != null) {
				try {
					fw = new FileWriter(fLog, true);
					this.connection = DriverManager
							.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
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
				}

			}

		} else {
			System.out.println("El archivo especificado no existe");
		}

	}

}
