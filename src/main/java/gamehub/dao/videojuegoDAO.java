package gamehub.dao;

import gamehub.dto.OpcionDTO;
import gamehub.dto.videojuegoDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class videojuegoDAO extends ConexionBD {

    public boolean agregar(videojuegoDTO dto) throws SQLException {
        boolean resultado = false;
        try {
            this.conectar();
            this.connection.setAutoCommit(false);

            String sqlVid = "INSERT INTO videojuego (id_publisher, titulo, descripcion, fecha_lanzamiento_base, clasificacion_edades, precio_base, estado_disponibilidad_base) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement psVid = connection.prepareStatement(sqlVid, Statement.RETURN_GENERATED_KEYS)) {
                psVid.setInt(1, dto.getIdPublisher());
                psVid.setString(2, dto.getTitulo());
                psVid.setString(3, dto.getDescripcion());
                psVid.setDate(4, dto.getFechaLanzamiento());
                psVid.setString(5, dto.getClasificacion());
                psVid.setDouble(6, dto.getPrecioBase());
                psVid.setString(7, dto.getEstado());
                psVid.executeUpdate();

                ResultSet rs = psVid.getGeneratedKeys();
                int idVideojuego = 0;
                if (rs.next()) {
                    idVideojuego = rs.getInt(1);
                } else {
                    throw new SQLException("Fallo al obtener el ID del videojuego.");
                }

                String sqlGen = "INSERT INTO videojuego_genero (id_videojuego, id_genero) VALUES (?, ?)";
                try (PreparedStatement psGen = connection.prepareStatement(sqlGen)) {
                    psGen.setInt(1, idVideojuego);
                    psGen.setInt(2, dto.getIdGenero());
                    psGen.executeUpdate();
                }

                String sqlPlat = "INSERT INTO videojuego_plataforma (id_videojuego, id_plataforma, precio_plataforma, fecha_lanzamiento_plataforma, estado_disponibilidad_plataforma) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement psPlat = connection.prepareStatement(sqlPlat)) {
                    psPlat.setInt(1, idVideojuego);
                    psPlat.setInt(2, dto.getIdPlataforma());
                    psPlat.setDouble(3, dto.getPrecioPlataforma());
                    psPlat.setDate(4, dto.getFechaLanzamiento());
                    psPlat.setString(5, dto.getEstado());
                    psPlat.executeUpdate();
                }

                this.connection.commit();
                resultado = true;
            }
        } catch (SQLException ex) {
            if (this.connection != null) this.connection.rollback();
            throw ex;
        } finally {
            if (this.connection != null) this.connection.setAutoCommit(true);
            this.desconectar();
        }
        return resultado;
    }

    public boolean actualizar(videojuegoDTO dto) throws SQLException {
        boolean resultado = false;
        try {
            this.conectar();
            this.connection.setAutoCommit(false);

            String sqlVid = "UPDATE videojuego SET id_publisher=?, titulo=?, descripcion=?, fecha_lanzamiento_base=?, clasificacion_edades=?, precio_base=?, estado_disponibilidad_base=? WHERE id_videojuego=?";
            try (PreparedStatement psVid = connection.prepareStatement(sqlVid)) {
                psVid.setInt(1, dto.getIdPublisher());
                psVid.setString(2, dto.getTitulo());
                psVid.setString(3, dto.getDescripcion());
                psVid.setDate(4, dto.getFechaLanzamiento());
                psVid.setString(5, dto.getClasificacion());
                psVid.setDouble(6, dto.getPrecioBase());
                psVid.setString(7, dto.getEstado());
                psVid.setInt(8, dto.getIdVideojuego());
                psVid.executeUpdate();

                try (PreparedStatement delGen = connection.prepareStatement("DELETE FROM videojuego_genero WHERE id_videojuego=?")) {
                    delGen.setInt(1, dto.getIdVideojuego());
                    delGen.executeUpdate();
                }

                try (PreparedStatement delPlat = connection.prepareStatement("DELETE FROM videojuego_plataforma WHERE id_videojuego=?")) {
                    delPlat.setInt(1, dto.getIdVideojuego());
                    delPlat.executeUpdate();
                }

                String sqlGen = "INSERT INTO videojuego_genero (id_videojuego, id_genero) VALUES (?, ?)";
                try (PreparedStatement psGen = connection.prepareStatement(sqlGen)) {
                    psGen.setInt(1, dto.getIdVideojuego());
                    psGen.setInt(2, dto.getIdGenero());
                    psGen.executeUpdate();
                }

                String sqlPlat = "INSERT INTO videojuego_plataforma (id_videojuego, id_plataforma, precio_plataforma, fecha_lanzamiento_plataforma, estado_disponibilidad_plataforma) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement psPlat = connection.prepareStatement(sqlPlat)) {
                    psPlat.setInt(1, dto.getIdVideojuego());
                    psPlat.setInt(2, dto.getIdPlataforma());
                    psPlat.setDouble(3, dto.getPrecioPlataforma());
                    psPlat.setDate(4, dto.getFechaLanzamiento());
                    psPlat.setString(5, dto.getEstado());
                    psPlat.executeUpdate();
                }

                this.connection.commit();
                resultado = true;
            }
        } catch (SQLException ex) {
            if (this.connection != null) this.connection.rollback();
            throw ex;
        } finally {
            if (this.connection != null) this.connection.setAutoCommit(true);
            this.desconectar();
        }
        return resultado;
    }

    public boolean eliminar(videojuegoDTO dto) throws SQLException {
        boolean resultado = false;
        try {
            this.conectar();
            this.connection.setAutoCommit(false);

            try (PreparedStatement psGen = connection.prepareStatement("DELETE FROM videojuego_genero WHERE id_videojuego=?")) {
                psGen.setInt(1, dto.getIdVideojuego());
                psGen.executeUpdate();
            }

            try (PreparedStatement psPlat = connection.prepareStatement("DELETE FROM videojuego_plataforma WHERE id_videojuego=?")) {
                psPlat.setInt(1, dto.getIdVideojuego());
                psPlat.executeUpdate();
            }

            try (PreparedStatement psVid = connection.prepareStatement("DELETE FROM videojuego WHERE id_videojuego=?")) {
                psVid.setInt(1, dto.getIdVideojuego());
                psVid.executeUpdate();
            }

            this.connection.commit();
            resultado = true;
        } catch (SQLException ex) {
            if (this.connection != null) this.connection.rollback();
            throw ex;
        } finally {
            if (this.connection != null) this.connection.setAutoCommit(true);
            this.desconectar();
        }
        return resultado;
    }

    public List<videojuegoDTO> buscar(videojuegoDTO dtoParam) throws SQLException {
        List<videojuegoDTO> lista = new ArrayList<>();
        String sql = "SELECT v.id_videojuego, v.titulo, v.descripcion, v.fecha_lanzamiento_base, v.clasificacion_edades, v.precio_base, v.estado_disponibilidad_base, "
                   + "p.id_publisher, p.nombre_comercial, "
                   + "vg.id_genero, g.nombre AS genero_nombre, "
                   + "vp.id_plataforma, pl.nombre AS plataforma_nombre, vp.precio_plataforma "
                   + "FROM videojuego v "
                   + "INNER JOIN publisher p ON v.id_publisher = p.id_publisher "
                   + "LEFT JOIN videojuego_genero vg ON v.id_videojuego = vg.id_videojuego "
                   + "LEFT JOIN genero g ON vg.id_genero = g.id_genero "
                   + "LEFT JOIN videojuego_plataforma vp ON v.id_videojuego = vp.id_videojuego "
                   + "LEFT JOIN plataforma pl ON vp.id_plataforma = pl.id_plataforma "
                   + "WHERE v.titulo LIKE ? "
                   + "GROUP BY v.id_videojuego";
        try {
            this.conectar();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, "%" + dtoParam.getTitulo() + "%");
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        lista.add(mapearResultado(rs));
                    }
                }
            }
        } finally {
            this.desconectar();
        }
        return lista;
    }

    public List<videojuegoDTO> listarCompleto() throws SQLException {
        List<videojuegoDTO> lista = new ArrayList<>();
        String sql = "SELECT v.id_videojuego, v.titulo, v.descripcion, v.fecha_lanzamiento_base, v.clasificacion_edades, v.precio_base, v.estado_disponibilidad_base, "
                   + "p.id_publisher, p.nombre_comercial, "
                   + "vg.id_genero, g.nombre AS genero_nombre, "
                   + "vp.id_plataforma, pl.nombre AS plataforma_nombre, vp.precio_plataforma "
                   + "FROM videojuego v "
                   + "INNER JOIN publisher p ON v.id_publisher = p.id_publisher "
                   + "LEFT JOIN videojuego_genero vg ON v.id_videojuego = vg.id_videojuego "
                   + "LEFT JOIN genero g ON vg.id_genero = g.id_genero "
                   + "LEFT JOIN videojuego_plataforma vp ON v.id_videojuego = vp.id_videojuego "
                   + "LEFT JOIN plataforma pl ON vp.id_plataforma = pl.id_plataforma "
                   + "GROUP BY v.id_videojuego";
        try {
            this.conectar();
            try (PreparedStatement ps = connection.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearResultado(rs));
                }
            }
        } finally {
            this.desconectar();
        }
        return lista;
    }

    private videojuegoDTO mapearResultado(ResultSet rs) throws SQLException {
        videojuegoDTO dto = new videojuegoDTO();
        dto.setIdVideojuego(rs.getInt("id_videojuego"));
        dto.setTitulo(rs.getString("titulo"));
        dto.setDescripcion(rs.getString("descripcion"));
        dto.setFechaLanzamiento(rs.getDate("fecha_lanzamiento_base"));
        dto.setClasificacion(rs.getString("clasificacion_edades"));
        dto.setPrecioBase(rs.getDouble("precio_base"));
        dto.setEstado(rs.getString("estado_disponibilidad_base"));
        dto.setIdPublisher(rs.getInt("id_publisher"));
        dto.setPublisherNombre(rs.getString("nombre_comercial"));
        dto.setIdGenero(rs.getInt("id_genero"));
        dto.setGeneroNombre(rs.getString("genero_nombre"));
        dto.setIdPlataforma(rs.getInt("id_plataforma"));
        dto.setPlataformaNombre(rs.getString("plataforma_nombre"));
        dto.setPrecioPlataforma(rs.getDouble("precio_plataforma"));
        return dto;
    }

    public List<OpcionDTO> obtenerCatalogos(String tabla, String idColumna, String nombreColumna) {
        List<OpcionDTO> lista = new ArrayList<>();
        String sql = "SELECT " + idColumna + ", " + nombreColumna + " FROM " + tabla;
        try {
            this.conectar();
            try (PreparedStatement ps = connection.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new OpcionDTO(rs.getInt(1), rs.getString(2)));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar catalogo " + tabla + ": " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return lista;
    }
}
