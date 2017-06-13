package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InteraccionBase {
	private InteraccionBase() {

	}

	private static InteraccionBase ib;

	public static InteraccionBase getInstance() {
		if (ib == null) {
			ib = new InteraccionBase();
		}
		return ib;
	}

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	private void initializeDatabase() {
		String connectionUrl = "jdbc:mysql://localhost:3306/segip";
		String connectionUser = "Apita49";
		String connectionPassword = "Aparicio49mysql";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser,
					connectionPassword);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean comprobarLogin(String user, int id) {
		try {
			boolean ans = false;
			initializeDatabase();
			rs = stmt
					.executeQuery("select * from empleados where id_empleado = "
							+ id + " and nombre = '" + user + "';");
			ans = rs.next();
			closeDatabase();
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void registrarCliente(String nombre, String profesion, String fecha,
			String estado, String direccion, String foto) {
		try {
			initializeDatabase();
			String q = "insert into cliente(nombre, fecha_nacimiento, profesion, estado_civil, direccion, foto) values('"
					+ nombre
					+ "', '"
					+ fecha
					+ "', '"
					+ profesion
					+ "', '"
					+ estado + "', '" + direccion + "', '" + foto + "');";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeDatabase() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean comprobarAdmin(int id) {
		try {
			boolean ans = false;
			initializeDatabase();
			rs = stmt
					.executeQuery("select * from empleados where id_empleado = "
							+ id + " and admin = true;");
			ans = rs.next();
			closeDatabase();
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void actualizarCliente(String id, String user, String fecha,
			String profesion, String estadoCivil, String direccion, String foto) {
		try {
			initializeDatabase();
			String q = "update cliente set nombre = '" + user
					+ "', fecha_nacimiento ='" + fecha + "', profesion = '"
					+ profesion + "',estado_civil ='" + estadoCivil
					+ "', direccion ='" + direccion + "', foto = '" + foto
					+ "' where codigo = " + id + ";";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deshabilitarCliente(String id) {
		try {
			initializeDatabase();
			String q = "update cliente set habilitado = false where codigo = "
					+ id + ";";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String buscarCliente(String id) {
		try {
			initializeDatabase();
			String q = "select * from cliente where codigo = " + id + ";";

			rs = stmt.executeQuery(q);
			rs.next();
			String codigo = rs.getString("codigo");
			String nombre = rs.getString("nombre");
			String fecha = rs.getString("fecha_nacimiento");
			String profesion = rs.getString("profesion");
			String estado = rs.getString("estado_civil");
			String direccion = rs.getString("direccion");
			String habilitado = rs.getString("habilitado");
			String foto = rs.getString("foto");
			closeDatabase();
			return "<html>CI: " + codigo + "<br>Nombre: " + nombre
					+ "<br>Profesion: " + profesion
					+ "<br>Fecha de nacimiento: " + fecha
					+ "<br>Estado civil: " + estado + "<br>Direccion: "
					+ direccion + "<br>Habilitado: " + habilitado
					+ "<br><img src = \"photos\\" + foto
					+ "\" width=\"400px\" height=\"400px\" /></html>";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public String buscarCI(String id) {
		try {
			initializeDatabase();
			String q = "select * from ci where numero = " + id + ";";

			rs = stmt.executeQuery(q);
			rs.next();
			String codigo = rs.getString("numero");
			String ciudad = rs.getString("ciudad");
			String seccion = rs.getString("seccion");
			String serie = rs.getString("serie");
			String vencimiento = rs.getString("vencimiento");
			String habilitado = rs.getString("habilitado");
			closeDatabase();
			return "Numero: " + codigo + "\nCiudad: " + ciudad + "\nSeccion: "
					+ seccion + "\nSerie: " + serie + "\nVencimiento: "
					+ vencimiento + "\nHabilitado: " + habilitado;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String buscarEmpleado(String id) {
		try {
			initializeDatabase();
			String q = "select * from empleados where id_empleado = " + id
					+ ";";

			rs = stmt.executeQuery(q);
			rs.next();
			String codigo = rs.getString("id_empleado");
			String nombre = rs.getString("nombre");
			String admin = rs.getString("admin");
			closeDatabase();
			return "Numero: " + codigo + "\nNombre: " + nombre
					+ "\nAdministrador: " + admin;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void deshabilitarCI(String id) {
		try {
			initializeDatabase();
			String q = "update ci set habilitado = false where numero = " + id
					+ ";";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deshabilitarEmpleado(String id) {
		try {
			initializeDatabase();
			String q = "delete from empleados where id_empleado = " + id + ";";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void registrarCI(String id, String ciudad, String fechaVenc,
			String serie, String seccion) {
		try {
			initializeDatabase();
			String q = "insert into ci(numero, ciudad, seccion, serie, vencimiento) values('"
					+ id
					+ "', '"
					+ ciudad
					+ "', '"
					+ seccion
					+ "', '"
					+ serie
					+ "', '" + fechaVenc + "');";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void registrarEmpleados(String nombre, String admin) {
		try {
			initializeDatabase();
			admin = admin.toLowerCase();
			if (admin.equals("si")) {
				admin = "true";
			} else {
				admin = "false";
			}
			String q = "insert into empleados(nombre, admin) values('" + nombre
					+ "', " + admin + ");";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void actualizarCI(String id, String ciudad, String fechaVenc,
			String serie, String seccion) {
		try {
			initializeDatabase();
			String q = "update ci set ciudad = '" + ciudad + "', seccion ='"
					+ seccion + "', serie = '" + serie + "',vencimiento ='"
					+ fechaVenc + "' where numero = " + id + ";";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actualizarEmpleados(String id, String nombre) {
		try {
			initializeDatabase();
			String q = "update empleados set nombre = '" + nombre
					+ "' where id_empleado = " + id + ";";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void actualizarLicencia(String id, String nacionalidad,
			String fechaVenc, String sexo, String fechaEmision,
			String grupoSanguineo, String audifonos, String lentes) {
		try {
			initializeDatabase();
			String q = "update licencia set nacionalidad = '" + nacionalidad
					+ "', genero ='" + sexo + "', grupo_sanguineo = '"
					+ grupoSanguineo + "',vencimiento ='" + fechaVenc
					+ "', audifonos =" + (audifonos.equals("Si"))
					+ ", lentes = " + (lentes.equals("Si")) + ",emision ='"
					+ fechaEmision + "' where numero = " + id + ";";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String buscarLicencia(String id) {
		try {
			initializeDatabase();
			String q = "select * from licencia where numero = " + id + ";";

			rs = stmt.executeQuery(q);
			rs.next();
			String codigo = rs.getString("numero");
			String nacionalidad = rs.getString("nacionalidad");
			String genero = rs.getString("genero");
			String grupoS = rs.getString("grupo_sanguineo");
			String audifonos = rs.getString("audifonos");
			String lentes = rs.getString("lentes");
			String emision = rs.getString("emision");
			String vencimiento = rs.getString("vencimiento");
			String habilitado = rs.getString("habilitado");
			closeDatabase();
			return "Numero: " + codigo + "\nNacionalidad: " + nacionalidad
					+ "\nGenero: " + genero + "\nGrupo Sanguineo: " + grupoS
					+ "\nAudifonos: " + audifonos + "\nLentes: " + lentes
					+ "\nFecha de Emision: " + emision
					+ "\nFecha de Vencimiento: " + vencimiento
					+ "\nHabilitado: " + habilitado;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void deshabilitarLicencia(String id) {
		try {
			initializeDatabase();
			String q = "update licencia set habilitado = false where numero = "
					+ id + ";";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void registrarLicencia(String id, String nacionalidad,
			String fechaVenc, String sexo, String fechaEmision,
			String grupoSanguineo, String audifonos, String lentes) {
		try {
			initializeDatabase();
			String q = "insert into licencia(numero, nacionalidad, genero, grupo_sanguineo, audifonos, lentes, emision, vencimiento) values('"
					+ id
					+ "', '"
					+ nacionalidad
					+ "', '"
					+ sexo
					+ "', '"
					+ grupoSanguineo
					+ "', "
					+ (audifonos.equals("Si"))
					+ ", "
					+ (lentes.equals("Si"))
					+ ", '"
					+ fechaEmision
					+ "', '"
					+ fechaVenc + "');";

			stmt.executeUpdate(q);
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
