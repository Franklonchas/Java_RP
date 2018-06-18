
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

    public ResultSet obtenerDatos(String nombreConductor, String fechaInicial, String fechaFinal) {
        String sentenciaSQL = "SELECT * FROM camiones, conductores, portes WHERE camiones.matricula=portes.matricula AND conductores.nif=portes.nif AND conductores.nombre='" + nombreConductor + "' AND portes.fecha BETWEEN '" + fechaInicial + "' AND '" + fechaFinal + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            System.err.println("No se ha podido obtener matricula, marca y modelo");
            return null;
        }
        return resultado;
    }
}
