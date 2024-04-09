package entity;

public class Coder {
    private int id_coder;
    private String nombre;
    private String apellidos;
    private String documento;
    private String cohorte;
    private String cv;
    private String clan;

    public Coder(){}

    public Coder(int id_coder, String nombre, String apellidos, String documento, String cohorte, String cv, String clan) {
        this.id_coder = id_coder;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.documento = documento;
        this.cohorte = cohorte;
        this.cv = cv;
        this.clan = clan;
    }

    public int getId_coder() {
        return id_coder;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCohorte() {
        return cohorte;
    }

    public void setCohorte(String cohorte) {
        this.cohorte = cohorte;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    @Override
    public String toString() {
        return
                "id coder: " + id_coder + "\n" +
                "nombre: " + nombre + "\n" +
                "apellidos: " + apellidos + "\n" +
                "documento: " + documento + "\n" +
                "cohorte: " + cohorte + "\n" +
                "cv: " + cv + "\n"+
                        "clan: " + clan + "\n";
    }

    }

