
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
        String url = ".\\alumnos.s3db";
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            System.out.println("Conexion realizada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerCursos() {
        String sentenciaSQL = "SELECT * FROM cursos";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet obtenerAsignaturas(String idCurso) {
        String sentenciaSQL = "SELECT * from asignaturas WHERE asignaturas.idCurso='" + idCurso + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet obtenerIdCursoBD(String cursoSeleccionado) {
        String sentenciaSQL = "SELECT idCurso FROM cursos WHERE cursos.curso='" + cursoSeleccionado + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet consultarUnaEvaluacion(String asignaturaBD, String evaluacionBD) {
        String sentenciaSQL = "Select nota FROM notas WHERE notas.idAsignatura='" + asignaturaBD + "' AND notas.evaluacion='" + evaluacionBD + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet obtenerIDAsignaturaBD(String asignaturaBD) {
        String sentenciaSQL = "Select idAsignatura FROM asignaturas where asignaturas.asignatura='" + asignaturaBD + "'";
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
