
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

    public ResultSet nombreEquiposBD(String codigo) {
        String sentenciaSQL = "SELECT * FROM equipos WHERE equipos.codigoEquipo='" + codigo + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet rellenarTablaBD(String jornadaSeleccionada, String partidoSeleccionado, String codigoEquipo) {
        String sentenciaSQL = "SELECT * FROM PUNTUACIONES WHERE puntuaciones.codigoJornada='" + jornadaSeleccionada + "' AND puntuaciones.codigoPartido='" + partidoSeleccionado + "' AND puntuaciones.codigoEquipo='" + codigoEquipo + "'";
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
