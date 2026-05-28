package gamehub.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ReporteDAO extends ConexionBD {

    public double obtenerTotalGastado(String usuarioId, String fechaInicio, String fechaFin) throws SQLException {
        String sql = "{ ? = call FN_Total_Gastado_Usuario(?, ?, ?) }";
        double total = 0.0;
        try {
            this.conectar();
            try (CallableStatement cs = connection.prepareCall(sql)) {
                cs.registerOutParameter(1, Types.DECIMAL);
                cs.setString(2, usuarioId);
                cs.setString(3, fechaInicio);
                cs.setString(4, fechaFin);
                cs.execute();
                if (cs.getObject(1) != null) {
                    total = cs.getDouble(1);
                }
            }
        } finally {
            this.desconectar();
        }
        return total;
    }

    public int obtenerVentasVideojuego(int videojuegoId, int anio, int mes) throws SQLException {
        String sql = "{ ? = call FN_Ventas_Videojuego_Mes(?, ?, ?) }";
        int cantidad = 0;
        try {
            this.conectar();
            try (CallableStatement cs = connection.prepareCall(sql)) {
                cs.registerOutParameter(1, Types.INTEGER);
                cs.setInt(2, videojuegoId);
                cs.setInt(3, anio);
                cs.setInt(4, mes);
                cs.execute();
                if (cs.getObject(1) != null) {
                    cantidad = cs.getInt(1);
                }
            }
        } finally {
            this.desconectar();
        }
        return cantidad;
    }
}