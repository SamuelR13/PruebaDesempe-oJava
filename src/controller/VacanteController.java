package controller;

import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;
import entity.Vacante;
import model.*;
import model.EmpresaModel;
import model.VacanteModel;

import javax.swing.*;
import java.util.Objects;

public class VacanteController {
    public static void getAll() {
        VacanteModel objModel = new VacanteModel();
        String listVacante = "LISTA DE VACANTES"+"\n";

        for (Object iterador : objModel.findAll()) {
            Vacante objVacante = (Vacante) iterador;
            listVacante += objVacante.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listVacante);
    }

    public static String getAllString() {
        VacanteModel objModel = new VacanteModel();
        String listVacante = "LISTA DE VACANTES"+"\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Vacante
            Vacante objVacante = (Vacante) iterador;
            listVacante += objVacante.toString() + "\n";
        }

        return listVacante;
    }
    public static String getAllActiveString() {
        VacanteModel objModel = new VacanteModel();
        String listVacante = "LISTA DE VACANTES ACTIVAS"+"\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Vacante
            Vacante objVacante = (Vacante) iterador;
            if (Objects.equals(objVacante.getEstado(), "ACTIVO")){
                listVacante += objVacante.toString() + "\n";
            }
        }
        return listVacante;
    }
    public static void create(){
        VacanteModel objModel = new VacanteModel();
        String titulo = JOptionPane.showInputDialog("Escribe el titulo del nuevo Vacante\n");
        String descripcion = JOptionPane.showInputDialog("Escribe la descripcion del nuevo Vacante\n");
        String duracion = JOptionPane.showInputDialog("Escribe la duracion del nuevo Vacante\n");
        String estado = "";
        do {
            estado = JOptionPane.showInputDialog("Escribe el estado del nuevo Vacante\n Solo puede ser ACTIVO o INACTIVO");
        }while (estado=="ACTIVO" || estado=="INACTIVO");

        String tecnologia = JOptionPane.showInputDialog("Escribe la tecnologia del nuevo Vacante\n");

        int id_empresa = Integer.parseInt(JOptionPane.showInputDialog(EmpresaController.getAllString()+"\n"+"Ingresa el ID de la Empresa del nuevo Vacante"));
        EmpresaModel objEspe = new EmpresaModel();
        Vacante objVacante = new Vacante();
        objVacante.setTitulo(titulo);
        objVacante.setDescripcion(descripcion);
        objVacante.setDuracion(duracion);
        objVacante.setEstado(estado);
        objVacante.setTecnologia(tecnologia);
        objVacante.setEmpresa(objEspe.findById(id_empresa));
        objModel.insert(objVacante);
        JOptionPane.showMessageDialog(null,objVacante.toString());
    }
    public static void delete() {
        VacanteModel objVacanteModel = new VacanteModel();
        String listVacante = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVacante + "\n Ingresa el ID del Vacante que deseas eliminar\n"));
        Vacante objVacante = objVacanteModel.findById(idDelete);

        if (objVacante== null){
            JOptionPane.showMessageDialog(null,"Vacante no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar este Vacante?\nTambién se eliminara todo lo relacionado con este Vacante \n"+ objVacante.toString());
            if (confirm == 0) objVacanteModel.delete(objVacante);
        }
    }
    public static void update(){
        VacanteModel objModel = new VacanteModel();
        String listVacante = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listVacante + "\nIngresa el ID del Vacante que deseas actualizar"));
        Vacante objVacante = objModel.findById(searchId);

        if(objVacante==null){
            JOptionPane.showMessageDialog(null,"Vacante no encontrado");
        }else{
            String titulo = JOptionPane.showInputDialog("Escribe el titulo del nuevo Vacante\n",objVacante.getTitulo());
            String descripcion = JOptionPane.showInputDialog("Escribe la descripcion del nuevo Vacante\n",objVacante.getDescripcion());
            String duracion = JOptionPane.showInputDialog("Escribe la duracion del nuevo Vacante\n",objVacante.getDuracion());
            String estado = JOptionPane.showInputDialog("Escribe el estado del nuevo Vacante\n",objVacante.getEstado());
            String tecnologia = JOptionPane.showInputDialog("Escribe la tecnologia del nuevo Vacante\n",objVacante.getTecnologia());
            int id_empresa = Integer.parseInt(JOptionPane.showInputDialog(EmpresaController.getAllString()+"\n"+"Ingresa el ID de la Empresa del nuevo Vacante",objVacante.getEmpresa().getId_empresa()));
            EmpresaModel objEspe = new EmpresaModel();
            objVacante.setTitulo(titulo);
            objVacante.setDescripcion(descripcion);
            objVacante.setDuracion(duracion);
            objVacante.setEstado(estado);
            objVacante.setTecnologia(tecnologia);
            objVacante.setEmpresa(objEspe.findById(id_empresa));
            objModel.update(objVacante);
        }

    }
    public static void getByTitulo(){
        VacanteModel objModel = new VacanteModel();
        String titulo = JOptionPane.showInputDialog("Ingresa el titulo");
        String Vacante = "";
        for (Vacante temporal :objModel.findByTitulo(titulo)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Vacante no encontrado");
            }else{
                Vacante += temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Vacante);
    }
    public static void getByDuracion(){
        VacanteModel objModel = new VacanteModel();
        String duracion = JOptionPane.showInputDialog("Ingresa la duracion");
        String Vacante = "";
        for (Vacante temporal :objModel.findByDuracion(duracion)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Duracion no encontrada");
            }else{
                Vacante+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Vacante);
    }


    public static void getByEstado(){
        VacanteModel objModel = new VacanteModel();
        String estado = JOptionPane.showInputDialog("Ingresa el Estado");
        String Vacante = "";
        for (Vacante temporal :objModel.findByEstado(estado)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Estado no encontrados");
            }else{
                Vacante+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Vacante);
    }
    public static void getByTecnologia(){
        VacanteModel objModel = new VacanteModel();
        String tecnologia = JOptionPane.showInputDialog("Ingresa la tecnologia");
        String Vacante = "";
        for (Vacante temporal :objModel.findByTecnologia(tecnologia)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"tecnologia no encontrada");
            }else{
                Vacante+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Vacante);
    }
    public static void getById(){
        VacanteModel objModel = new VacanteModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Vacante objEspe = new Vacante();
        objEspe = objModel.findById(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }

    public static void changeState(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        VacanteModel objVacanteModel = new VacanteModel();
        CoderModel objCoderModel = new CoderModel();
        EmpresaModel objEspeModel = new EmpresaModel();
        String listVacante = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listVacante + "\nIngresa el ID del Vacante que deseas actualizar"));
        Vacante objVacante = objVacanteModel.findById(searchId);

        if(objVacante==null){
            JOptionPane.showMessageDialog(null,"Contratacion no encontrada");
        }else{
            String selection =JOptionPane.showInputDialog(null,"El estado de esta contratacion es " + objVacante.getEstado()+ "\n" + "Ingresa el numero del estado al que deseas actualizar\n 1. ACTIVO\n2. INACTIVO");
            switch (selection){
                case "1":
                    boolean activo = objVacanteModel.changeState("ACTIVO",String.valueOf(searchId));
                    break;
                case "2":
                    boolean inactivo = objVacanteModel.changeState("INACTIVO",String.valueOf(searchId));
                    break;
            }
            JOptionPane.showMessageDialog(null,objVacante.toString());
        }
    }
}