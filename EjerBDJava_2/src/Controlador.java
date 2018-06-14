
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
        String url = ".\\infracciones.s3db";
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            System.out.println("Conexion realizada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerRegistro(String fechaInicial, String fechaFinal) {
        String sentenciaSQL = "SELECT infracciones.infraccion, multas.fecha, Coches.matricula, Coches.nombre, infracciones.penalizacion FROM infracciones,multas,Coches WHERE Coches.codigo=multas.codigoCoche AND multas.codigoInfraccion=infracciones.codigoInfraccion AND multas.fecha BETWEEN '" + fechaInicial + "' AND '" + fechaFinal + "'";

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

}
