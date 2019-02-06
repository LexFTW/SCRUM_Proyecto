package main.implementations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.interfaces.IProject;
import main.models.Project;
import main.models.Specification;

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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertProject(Project project, boolean replic) {
		this.getConnectionLocal();
		if (this.connection != null) {
			try {
				statement = connection.createStatement();

				String query = "insert into projects (ProjectTitle, ProjectDescription, ScrumMasterID, ProductOwnerID, CreatedAt, UpdatedAt) VALUES "
						+ "('" + project.getProjectName() + "', '" + project.getProjectDescription() + "', '"
						+ project.getScrumMasterID() + "', '" + project.getProductOwnerID() + "', '"
						+ project.getCreatedAt() + "', '" + project.getUpdatedAt() + "')";

				this.statement.executeUpdate(query);
				this.statement.close();
				this.connection.close();
				this.serializeProject(project);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error al conectarse a la Base de Datos, vuelva a intentarlo",
					"No se ha podido conectar a la Base de Datos", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public ArrayList<Project> getAllProjects() {
		ArrayList<Project> projects = new ArrayList<>();
		getConnectionLocal();
		if (this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement.executeQuery("SELECT * FROM projects");
				while (resultSet.next()) {
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
			} finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return projects;
	}

	@Override
	public Project getProject(String projectTitle) {
		this.getConnectionLocal();
		if (this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement
						.executeQuery("SELECT * FROM projects WHERE ProjectTitle ='" + projectTitle + "'");
				while (this.resultSet.next()) {
					project.setProjectID(this.resultSet.getInt("ProjectID"));
					project.setProjectName(this.resultSet.getString("ProjectTitle"));
					project.setProjectDescription(this.resultSet.getString("ProjectDescription"));
					project.setProductOwnerID(this.resultSet.getInt("ProductOwnerID"));
					project.setScrumMasterID(this.resultSet.getInt("ScrumMasterID"));
					project.setCreatedAt(this.resultSet.getDate("CreatedAt"));
					project.setUpdatedAt(this.resultSet.getDate("UpdatedAt"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return project;
	}

	@Override
	public String getProductOwner(int id) {
		String userName = "";
		this.getConnectionLocal();
		if (this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement.executeQuery("SELECT UserName FROM users WHERE UserID =" + id + "");
				userName = this.resultSet.getString("UserName");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return userName;
	}

	@Override
	public String getScrumMaster(int id) {
		String userName = "";
		this.getConnectionLocal();
		if (this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement.executeQuery("SELECT UserName FROM users WHERE UserID =" + id + "");
				userName = this.resultSet.getString("UserName");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return userName;
	}

	@Override
	public ArrayList<Project> getAllProjects(int id) {
		ArrayList<Project> projects = new ArrayList<>();
		this.getConnectionLocal();
		if (this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement.executeQuery("SELECT * FROM projects WHERE ProductOwnerID =" + id);
				while (resultSet.next()) {
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
			} finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return projects;
	}

	@Override
	public Project getProjectSelected() {
		return this.project;
	}

	public void serializeProject(Project project) {
		File f = new File("src/main/resources/log");
		if (f.length() > 0) {
			try {
				MyObjectOutputStream oos = new MyObjectOutputStream(new FileOutputStream(f, true));
				project.setInserted(true);
				oos.writeUnshared(project);
				oos.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,
						"No se ha encontrado el archivo log para registrar la información, pongase en contacto con el Administrador",
						"No se ha encontrado el archivo", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Error de E/S al archivo log, pongase en contacon con el Administrador", "Error de E/S",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				ObjectOutputStream userWriter = new ObjectOutputStream(new FileOutputStream(f, true));
				project.setInserted(true);
				userWriter.writeObject(project);
				userWriter.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,
						"No se ha encontrado el archivo log para registrar la información, pongase en contacto con el Administrador",
						"No se ha encontrado el archivo", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Error de E/S al archivo log, pongase en contacon con el Administrador", "Error de E/S",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public ArrayList<Project> getAllProjectsDevelopers(int userID) {
		ArrayList<Project> developers = new ArrayList<>();
		this.getConnectionLocal();
		if(this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement.executeQuery("SELECT * FROM projects WHERE ProjectID = (SELECT ProjectID FROM users_group_integrants WHERE UserID = " + userID + ")");
				
				// # Arreglar consulta
				
				while(this.resultSet.next()) {
					Project p = new Project();
					p.setProjectID(this.resultSet.getInt("ProjectID"));
					p.setProjectName(this.resultSet.getString("ProjectTitle"));
					p.setProjectDescription(this.resultSet.getString("ProjectDescription"));
					p.setScrumMasterID(this.resultSet.getInt("ScrumMasterID"));
					p.setProductOwnerID(this.resultSet.getInt("ProductOwnerID"));
					p.setCreatedAt(this.resultSet.getDate("CreatedAt"));
					p.setUpdatedAt(this.resultSet.getDate("UpdatedAt"));
					developers.add(p);
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
		return developers;
	}

	@Override
	public ArrayList<Specification> getAllSpecifications(int projectID) {
		ArrayList<Specification> specifications = new ArrayList<>();
		this.getConnectionLocal();
		if (this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement
						.executeQuery("SELECT * FROM specifications WHERE ProjectID =" + projectID);
				while (resultSet.next()) {
					Specification spec = new Specification();
					spec.setSpecificationID(resultSet.getInt("SpecificationID"));
					spec.setSpecificationTitle(resultSet.getString("SpecificationTitle"));
					spec.setSpecificationDescription(resultSet.getString("SpecificationDescription"));
					spec.setSpecificationStatus(resultSet.getInt("SpecificationStatus"));
					spec.setSpecificationTime(resultSet.getInt("SpecificationTime"));
					spec.setSprintID(resultSet.getInt("SprintID"));
					spec.setProjectID(resultSet.getInt("ProjectID"));
					specifications.add(spec);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return specifications;
	}

	@Override
	public void insertSpecification(Specification specification, boolean replic) {
		this.getConnectionLocal();
		if (this.connection != null) {
			try {
				statement = connection.createStatement();

				String query = "INSERT INTO specifications (SpecificationDescription, SpecificationTime, ProjectID) VALUES ('"
				+ specification.getSpecificationDescription() + "', " + specification.getSpecificationTime() + ", " + specification.getProjectID() + ");";
				
				this.statement.executeUpdate(query);
				this.statement.close();
				this.connection.close();
				this.serializeSpecification(specification);
				JOptionPane.showMessageDialog(null, "¡Se ha insertado la especificación en la Base de Datos correctamente!", "Especificación creada", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error al conectarse a la Base de Datos, vuelva a intentarlo",
					"No se ha podido conectar a la Base de Datos", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void serializeSpecification(Specification specification) {
		File f = new File("src/main/resources/log");
		if (f.length() > 0) {
			try {
				MyObjectOutputStream oos = new MyObjectOutputStream(new FileOutputStream(f, true));
				oos.writeUnshared(specification);
				oos.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,
						"No se ha encontrado el archivo log para registrar la información, pongase en contacto con el Administrador",
						"No se ha encontrado el archivo", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Error de E/S al archivo log, pongase en contacon con el Administrador", "Error de E/S",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f, true));
				oos.writeObject(specification);
				oos.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,
						"No se ha encontrado el archivo log para registrar la información, pongase en contacto con el Administrador",
						"No se ha encontrado el archivo", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Error de E/S al archivo log, pongase en contacon con el Administrador", "Error de E/S",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
