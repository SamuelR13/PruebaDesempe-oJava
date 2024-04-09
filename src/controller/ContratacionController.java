package controller;

import entity.Coder;
import entity.Contratacion;
import entity.Vacante;
import model.*;
import model.ContratacionModel;

import javax.swing.*;

public class ContratacionController {
    public static void getAll() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listContratacion = "LISTA DE CONTRATACIONES"+"\n";

        for (Object iterador : objContratacionModel.findAll()) {
            Contratacion objContratacion = (Contratacion) iterador;
            listContratacion += objContratacion.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listContratacion);
    }

    public static String getAllString() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listContratacion = "LISTA DE CONTRATACIONES"+"\n";

        for (Object iterador : objContratacionModel.findAll()) {
            //Convertimos del Object a Contratacion
            Contratacion objContratacion = (Contratacion) iterador;
            listContratacion += objContratacion.toStringNew() + "\n";
        }

        return listContratacion;
    }
    public static void create(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        VacanteModel objVacanteModel = new VacanteModel();
        CoderModel objCoderModel = new CoderModel();
        EmpresaModel objEspeModel = new EmpresaModel();

        int vacante = Integer.parseInt(JOptionPane.showInputDialog(VacanteController.getAllActiveString()+
                "\nIngresa el ID de la Vacante para la Contratacion"));
        Vacante objNewContrata = new Vacante();
        objNewContrata = objVacanteModel.findById(vacante);

        int coder = Integer.parseInt(JOptionPane.showInputDialog(CoderController.getAllActiveString(objNewContrata.getTecnologia())+
                "\nIngresa el ID del Coder para la Contratacion"));


        Double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el salario\n"));

        Contratacion objContratacion = new Contratacion();
        objContratacion.setCoder(objCoderModel.findById(coder));
        objContratacion.setVacante(objVacanteModel.findById(vacante));
        objContratacion.setEstado("Pendiente");
        objContratacion.setSalario(salario);

        objContratacionModel.insert(objContratacion);
        JOptionPane.showMessageDialog(null,objContratacion.toString());
    }
    public static void delete() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listContratacion = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listContratacion + "\n Ingresa el ID del Contratacion que deseas eliminar\n"));
        Contratacion objContratacion = objContratacionModel.findById(idDelete);

        if (objContratacion== null){
            JOptionPane.showMessageDialog(null,"Contratacion no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar esta Contratacion?\nTambién se eliminara todos lo relacionado con esta Contratacion \n"+ objContratacion.toString());
            if (confirm == 0) objContratacionModel.delete(objContratacion);
        }
    }
    public static void update(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        VacanteModel objVacanteModel = new VacanteModel();
        CoderModel objCoderModel = new CoderModel();
        EmpresaModel objEspeModel = new EmpresaModel();
        String listContratacion = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listContratacion + "\nIngresa el ID del Contratacion que deseas actualizar"));
        Contratacion objContratacion = objContratacionModel.findById(searchId);

        if(objContratacion==null){
            JOptionPane.showMessageDialog(null,"Contratacion no encontrado");
        }else{
            int vacante = Integer.parseInt(JOptionPane.showInputDialog(VacanteController.getAllActiveString()+
                    "\nIngresa el ID de la Vacante para la Contratacion"),objContratacion.getVacante().getId_vacante());
            Vacante objNewContrata = new Vacante();
            objNewContrata = objVacanteModel.findById(vacante);

            int coder = Integer.parseInt(JOptionPane.showInputDialog(CoderController.getAllActiveString(objNewContrata.getTecnologia())+
                    "\nIngresa el ID del Coder para la Contratacion"),objContratacion.getCoder().getId_coder());


            Double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el salario\n",String.valueOf(objContratacion.getSalario())));

            objContratacion.setCoder(objCoderModel.findById(coder));
            objContratacion.setVacante(objVacanteModel.findById(vacante));
            objContratacion.setEstado("Pendiente");
            objContratacion.setSalario(salario);


            objContratacionModel.update(objContratacion);
            JOptionPane.showMessageDialog(null,objContratacion.toString());
        }

    }
    public static void changeState(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        VacanteModel objVacanteModel = new VacanteModel();
        CoderModel objCoderModel = new CoderModel();
        EmpresaModel objEspeModel = new EmpresaModel();
        String listContratacion = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listContratacion + "\nIngresa el ID del Contratacion que deseas actualizar"));
        Contratacion objContratacion = objContratacionModel.findById(searchId);

        if(objContratacion==null){
            JOptionPane.showMessageDialog(null,"Contratacion no encontrada");
        }else{
            String selection =JOptionPane.showInputDialog(null,"El estado de esta contratacion es " + objContratacion.getEstado()+"\n"+"Ingresa el numero del estado al que deseas actualizar\n 1. ACTIVO\n2. INACTIVO");
            switch (selection){
                case "1":
                    boolean activo = objContratacionModel.changeState("ACTIVO",String.valueOf(searchId));
                    break;
                case "2":
                    boolean inactivo = objContratacionModel.changeState("INACTIVO",String.valueOf(searchId));
                    break;
            }
            JOptionPane.showMessageDialog(null,objContratacion.toString());
        }
    }
    public static void getByCoderName(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        String name = JOptionPane.showInputDialog("Ingresa el nombre");
        String Contratacion = "";
        for (Contratacion temporal :objContratacionModel.findByCoderName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Contratacion no encontrado");
            }else{
                Contratacion+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Contratacion);
    }
    public static void getByCoderLastname(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        String name = JOptionPane.showInputDialog("Ingresa los apellidos");
        String Contratacion = "";
        for (Contratacion temporal :objContratacionModel.findByCoderLastname(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Apellidos no encontrados");
            }else{
                Contratacion+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Contratacion);
    }
    public static void getByVacanteTitulo(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        String name = JOptionPane.showInputDialog("Ingresa el titulo");
        String Contratacion = "";
        for (Contratacion temporal :objContratacionModel.findByTitulo(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Vacante no encontrada");
            }else{
                Contratacion+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Contratacion);
    }
    public static void getByVacanteEstado(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        String estado = JOptionPane.showInputDialog("Ingresa los apellidos");
        String Contratacion = "";
        for (Contratacion temporal :objContratacionModel.findByVacanteEstado(estado)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Apellidos no encontrados");
            }else{
                Contratacion+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Contratacion);
    }
    public static void getByCoderDocument(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        String id = JOptionPane.showInputDialog("Ingresa el Documento del coder sin puntos ni comas ej: 1000089781");
        Contratacion objEspe = new Contratacion();
        objEspe = objContratacionModel.findByCoderDocument(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }

    public static void getById(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Contratacion objEspe = new Contratacion();
        objEspe = objContratacionModel.findById(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }
    public static void getByVacanteId(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Contratacion objEspe = new Contratacion();
        objEspe = objContratacionModel.findByVacanteId(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }
}
