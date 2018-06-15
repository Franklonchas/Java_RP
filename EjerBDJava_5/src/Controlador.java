
import java.sql.*;

/**
 *
 * @author Fran De La Torre
 */
public class Controlador {

    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultado;

    public Controlador() {
        String url = ".\\camiones.s3db";
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            System.out.println("Conexion realizada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerNombres() {
        String sentenciaSQL = "SELECT * FROM conductores";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            System.err.println("Fallo al obtener nombres");
            return null;
        }
        return resultado;
    }

    public ResultSet obtenerDatosConductor(String nombreSeleccionado) {
        String sentenciaSQL = "SELECT * FROM conductores WHERE conductores.nombre='" + nombreSeleccionado + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            System.err.println("No se ha podido obtener las JORNADAS");
            return null;
        }
        return resultado;
    }
}
