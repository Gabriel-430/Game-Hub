package gamehub.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class CompraDAO extends ConexionBD {

    public String registrarCompra(String usuarioId, int videojuegoId, int plataformaId, double precio, int cantidad, String metodoPago, String referencia) throws SQLException {
        String sql = "{call registrar_nueva_compra(?, ?, ?, ?, ?, ?, ?, ?)}";
        String mensajeResultado = "";
        try {
            this.conectar();
            try (CallableStatement cs = connection.prepareCall(sql)) {
                cs.setString(1, usuarioId);
                cs.setInt(2, videojuegoId);
                cs.setInt(3, plataformaId);
                cs.setDouble(4, precio);
                cs.setInt(5, cantidad);
                cs.setString(6, metodoPago);
                cs.setString(7, referencia);
                cs.registerOutParameter(8, Types.VARCHAR);
                cs.execute();
                mensajeResultado = cs.getString(8);
            }
        } finally {
            this.desconectar();
        }
        return mensajeResultado;
    }
}