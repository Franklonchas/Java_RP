
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
        String url = ".\\Facturas.s3db";
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            System.out.println("Conexion realizada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerNumeroFactura() {
        String sentenciaSQL = "SELECT nfactura FROM facturas WHERE nfactura = (SELECT MAX (nfactura) from facturas)";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet obtenerClientesOrdenados() {
        String sentenciaSQL = "SELECT * FROM clientes ORDER BY cliente ASC";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet obtenerDatosClientes(String nombreCliente) {
        String sentenciaSQL = "SELECT * FROM clientes WHERE clientes.cliente='" + nombreCliente + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet grabarDatosFacturas(int nuevoNumero, String diaBD, String codigoCliente, int importeTotal, int ivaTotal, int facturaTotal) {
        String sentenciaSQL = "INSERT INTO facturas (nfactura, fecha, codCliente, importe, iva, total) VALUES ('" + nuevoNumero + "', '" + diaBD + "', '" + codigoCliente + "', '" + importeTotal + "', '" + ivaTotal + "', '" + facturaTotal + "')";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet grabarDatosLineasFacturas(int nuevoNumero, String nLinea, String articulo, String unidades, String pvp, String importe) {
        String sentenciaSQL = "INSERT INTO lineasFacturas (nfactura, nLinea, articulo, unidades, pvp, importe) VALUES ('" + nuevoNumero + "', '" + nLinea + "', '" + articulo + "', '" + unidades + "', '" + pvp + "', '" + importe + "')";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet consultarFactura(int nFacturaIntro) {
        String sentenciaSQL = "SELECT * from facturas WHERE facturas.nFactura='" + nFacturaIntro + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet consultaFacturaClientes(String codClienteDatos) {
        String sentenciaSQL = "SELECT * FROM clientes WHERE clientes.codCliente='" + codClienteDatos + "'";
        System.out.println(sentenciaSQL);

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);

        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ResultSet consultaLineas(String nFacturaBD) {
        String sentenciaSQL = "SELECT * FROM lineasFacturas WHERE lineasFacturas.nFactura='" + nFacturaBD + "'";
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
