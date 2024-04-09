package entity;

public class Empresa {
    private int id_empresa;
    private String nombre;
    private String sector;
    private String ubicacion;
    private String contacto;

    public Empresa(){}

    public Empresa(int id_empresa, String nombre, String sector, String ubicacion, String contacto) {
        this.id_empresa = id_empresa;
        this.nombre = nombre;
        this.sector = sector;
        this.ubicacion = ubicacion;
        this.contacto = contacto;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return
                "id empresa: " + id_empresa + "\n" +
                "nombre: " + nombre + "\n" +
                "sector: " + sector + "\n" +
                "ubicacion: " + ubicacion + "\n" +
                "contacto: " + contacto + "\n" ;

    }
}
