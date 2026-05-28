package gamehub.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

public class SuscripcionDAO extends ConexionBD {

    public String registrarSuscripcion(String usuarioId, int planId, Timestamp fechaInicio, double montoPago, String metodoPago, String referencia) throws SQLException {
        String sql = "{call registrar_nueva_suscripcion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        String mensajeResultado = "";
        try {
            this.conectar();
            try (CallableStatement cs = connection.prepareCall(sql)) {
                cs.setString(1, usuarioId);
                cs.setInt(2, planId);
                cs.setTimestamp(3, fechaInicio);
                cs.setDouble(4, montoPago);
                cs.setString(5, metodoPago);
                cs.setString(6, referencia);
                cs.registerOutParameter(7, Types.INTEGER);
                cs.registerOutParameter(8, Types.TIMESTAMP);
                cs.registerOutParameter(9, Types.TIMESTAMP);
                cs.registerOutParameter(10, Types.VARCHAR);
                cs.execute();
                mensajeResultado = cs.getString(10);
            }
        } finally {
            this.desconectar();
        }
        return mensajeResultado;
    }

    public String cancelarSuscripcion(String usuarioId, int suscripcionId, String motivo) throws SQLException {
        String sql = "{call cancelar_suscripcion(?, ?, ?, ?)}";
        String mensajeResultado = "";
        try {
            this.conectar();
            try (CallableStatement cs = connection.prepareCall(sql)) {
                cs.setString(1, usuarioId);
                cs.setInt(2, suscripcionId);
                cs.setString(3, motivo);
                cs.registerOutParameter(4, Types.VARCHAR);
                cs.execute();
                mensajeResultado = cs.getString(4);
            }
        } finally {
            this.desconectar();
        }
        return mensajeResultado;
    }
}