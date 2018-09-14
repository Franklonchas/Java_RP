
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

    public ResultSet rellenarChoiceJornada() {
        String sentenciaSQL = "SELECT jornada FROM jornadas";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet rellenarChoicePartido(String jornadaSeleccionada) {
        String sentenciaSQL = "SELECT codigoPartido FROM partidos WHERE jornada='" + jornadaSeleccionada + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet rellenarDatosPartido(String jornadaSeleccionada, String partidoSeleccionado) {
        String sentenciaSQL = "SELECT * FROM jornadas, partidos WHERE jornadas.jornada=partidos.jornada AND partidos.jornada='" + jornadaSeleccionada + "' AND partidos.codigoPartido='" + partidoSeleccionado + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet unaSeleccionadaBD(String jornadaSeleccionada) {
        String sentenciaSQL = "SELECT * FROM partidos WHERE partidos.jornada='" + jornadaSeleccionada + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet todasSeleccionadaBD() {
        String sentenciaSQL = "SELECT * FROM partidos";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet consultarUna(String jornadaSeleccionada) {
        String sentenciaSQL = "SELECT * FROM PUNTUACIONES  WHERE PUNTUACIONES.codigoJornada='" + jornadaSeleccionada + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet consultarTodas() {
        String sentenciaSQL = "SELECT * FROM partidos";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet consultarDatosPartido(String jornadaTabla, String codigoTabla) {
        String sentenciaSQL = "SELECT * FROM PUNTUACIONES  WHERE PUNTUACIONES.codigoJornada='" + jornadaTabla + "' AND PUNTUACIONES.codigoPartido='" + codigoTabla + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

}
