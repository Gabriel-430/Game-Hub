package gamehub.dto;

import java.sql.Date;

public class videojuegoDTO {
    private Integer idVideojuego;
    private String titulo;
    private String descripcion;
    private Date fechaLanzamiento;
    private String clasificacion;
    private Double precioBase;
    private String estado;

    private Integer idGenero;
    private String generoNombre;

    private Integer idPublisher;
    private String publisherNombre;

    private Integer idPlataforma;
    private String plataformaNombre;
    private Double precioPlataforma;

    public videojuegoDTO() {
    }

    public Integer getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(Integer idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getGeneroNombre() {
        return generoNombre;
    }

    public void setGeneroNombre(String generoNombre) {
        this.generoNombre = generoNombre;
    }

    public Integer getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(Integer idPublisher) {
        this.idPublisher = idPublisher;
    }

    public String getPublisherNombre() {
        return publisherNombre;
    }

    public void setPublisherNombre(String publisherNombre) {
        this.publisherNombre = publisherNombre;
    }

    public Integer getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(Integer idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public String getPlataformaNombre() {
        return plataformaNombre;
    }

    public void setPlataformaNombre(String plataformaNombre) {
        this.plataformaNombre = plataformaNombre;
    }

    public Double getPrecioPlataforma() {
        return precioPlataforma;
    }

    public void setPrecioPlataforma(Double precioPlataforma) {
        this.precioPlataforma = precioPlataforma;
    }
}
