package gamehub.dto;

public class OpcionDTO {
    private Integer id;
    private String nombre;

    public OpcionDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() { return nombre; }
}
