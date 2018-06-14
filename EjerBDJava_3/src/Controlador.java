
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

    public ResultSet obtenerDatos(String nombreSeleccionado) {
        String sentenciaSQL = "SELECT nif,direccion FROM conductores WHERE conductores.nombre = '" + nombreSeleccionado + "'";

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            System.out.println("No se puede realizar la consulta");
            System.out.println(sentenciaSQL);
            return null;
        }
        return resultado;
    }

    public ResultSet rellenaChoiceNombres() {
        String sentenciaSQL = "SELECT * FROM conductores";
        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            System.out.print("No se pudo realizar consulta");
            e.printStackTrace();
            return null;
        }
        return resultado;
    }

    public ResultSet rellenarTabla(String fechaInicial, String fechaFinal, String nombreConductor) {
        String sentenciaSQL = "SELECT * FROM camiones, conductores, portes WHERE camiones.matricula=portes.matricula AND conductores.nif=portes.nif AND conductores.nombre='" + nombreConductor + "' AND portes.fecha BETWEEN '" + fechaInicial + "' AND '" + fechaFinal + "'";
        System.out.println(sentenciaSQL);
        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            System.out.print("No se pudo realizar consulta");
            e.printStackTrace();
            return null;
        }
        return resultado;
    }

}
