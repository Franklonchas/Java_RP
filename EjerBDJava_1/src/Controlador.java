
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
        String url = ".\\baloncesto.s3db";
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            System.out.println("Conexion realizada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerFechaJornada(String jornadaSeleccionada) {
        String sentenciaSQL = "SELECT fecha FROM JORNADAS WHERE jornada ='" + jornadaSeleccionada + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet obtenerJornadas() {
        String sentenciaSQL = "SELECT * FROM JORNADAS";
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

    public ResultSet obtenerNombrePartidos(String jornadaSeleccionada) {
        String sentenciaSQL = "SELECT * FROM PARTIDOS WHERE PARTIDOS.jornada='" + jornadaSeleccionada + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            System.err.println("No se ha podido obtener los nombbres de los EQUIPOS");
            return null;
        }
        return resultado;
    }

    public ResultSet obtenerCanastas(String jornadaSeleccionada, String codEquipo) {
        String sentenciaSQL = "SELECT * FROM PUNTUACIONES WHERE PUNTUACIONES.codigoJornada='" + jornadaSeleccionada + "' AND codigoEquipo='"+ codEquipo +"'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            System.err.println("No se ha podido obtener los nombbres de los EQUIPOS");
            return null;
        }
        return resultado;
    }

}
