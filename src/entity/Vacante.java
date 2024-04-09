package entity;

public class Vacante {
    private int id_vacante;
    private Empresa empresa;
    private String titulo;
    private String descripcion;
    private String duracion;
    private String estado;
    private String tecnologia;


    public Vacante(){}

    public Vacante(int id_vacante, Empresa empresa, String titulo, String descripcion, String duracion, String estado, String tecnologia) {
        this.id_vacante = id_vacante;
        this.empresa = empresa;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.tecnologia = tecnologia;
    }

    public int getId_vacante() {
        return id_vacante;
    }

    public void setId_vacante(int id_vacante) {
        this.id_vacante = id_vacante;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    @Override
    public String toString() {
        return
                "id vacante: " + id_vacante + "\n" +
                "empresa: " + empresa.getNombre() + "\n" +
                "titulo: " + titulo + "\n" +
                "descripcion: " + descripcion + "\n" +
                "duracion: " + duracion + "\n" +
                "estado: " + estado + "\n"+
                        "tecnologia: " + tecnologia + "\n";

    }
}
