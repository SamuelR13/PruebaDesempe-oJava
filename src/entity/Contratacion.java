package entity;

public class Contratacion {
    private int id_contratacion;
    private Vacante vacante;
    private Coder coder;
    private String fecha_aplicacion;
    private String estado;
    private Double salario;

    public Contratacion(){}

    public Contratacion(int id_contratacion, Vacante vacante, Coder coder, String fecha_aplicacion, String estado, Double salario) {
        this.id_contratacion = id_contratacion;
        this.vacante = vacante;
        this.coder = coder;
        this.fecha_aplicacion = fecha_aplicacion;
        this.estado = estado;
        this.salario = salario;
    }

    public int getId_contratacion() {
        return id_contratacion;
    }

    public void setId_contratacion(int id_contratacion) {
        this.id_contratacion = id_contratacion;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public Coder getCoder() {
        return coder;
    }

    public void setCoder(Coder coder) {
        this.coder = coder;
    }

    public String getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(String fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return
                "------------------CODER----------------" + "\n" +
                        "Nombre: " + coder.getNombre() + "\n" +
                        "Apellidos: " + coder.getApellidos() + "\n" +
                        "Documento: " + coder.getDocumento() + "\n" +
                        "------------------VACANTE----------------" + "\n" +
                        "Titulo: " + vacante.getTitulo() + "\n" +
                        "Descripcion: " + vacante.getDescripcion() + "\n" +
                        "------------------EMPRESA----------------" + "\n" +
                        "Nombre: " + vacante.getEmpresa().getNombre() + "\n" +
                        "Ubicacion: " + vacante.getEmpresa().getUbicacion() + "\n" +
                        "------------------INFO----------------" + "\n" +
                        "id contratacion: " + id_contratacion + "\n" +


                        "fecha_aplicacion: " + fecha_aplicacion + "\n" +
                        "estado: " + estado + "\n" +
                        "salario: " + salario +"\n";

    }
    public String toStringNew() {
        return
                        "Nombre: " + coder.getNombre() + "\n" +
                        "Apellidos: " + coder.getApellidos() + "\n" +
                        "Titulo: " + vacante.getTitulo() + "\n" +
                        "Nombre: " + vacante.getEmpresa().getNombre() + "\n" +
                        "id contratacion: " + id_contratacion + "\n" +
                        "fecha_aplicacion: " + fecha_aplicacion + "\n" +
                        "estado: " + estado + "\n" +
                        "salario: " + salario +"\n";
    }
}
