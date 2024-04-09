package controller;

import entity.Coder;
import entity.Vacante;
import model.CoderModel;
import model.VacanteModel;

import javax.swing.*;
import java.util.Objects;

public class CoderController {
    public static void getAll() {
        CoderModel objModel = new CoderModel();
        String listCoder = "LISTA DE CODERS"+"\n";

        for (Object iterador : objModel.findAll()) {
            Coder objCoder = (Coder) iterador;
            listCoder += objCoder.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listCoder);
    }

    public static String getAllString() {
        CoderModel objModel = new CoderModel();
        String listCoder = "LISTA DE CODERS\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Coder
            Coder objCoder = (Coder) iterador;
            listCoder += objCoder.toString() + "\n";
        }

        return listCoder;
    }
    public static String getAllActiveString(String tecnologia) {
        CoderModel objModel = new CoderModel();
        String listModel = "LISTA DE CODERS DISPONIBLES PARA ESA VACANTE"+"\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Vacante
            Coder objCoder = (Coder) iterador;
            System.out.println(objCoder.getCv());
            for (String i : objCoder.getCv().split(":")){
                if (i.contains(tecnologia)){
                    listModel += objCoder.toString() + "\n";
                }
            }
        }
        return listModel;
    }
    public static void create(){
        CoderModel objModel = new CoderModel();
        String nombre = JOptionPane.showInputDialog("Escribe el nombre del nuevo Coder\n");
        String apellidos = JOptionPane.showInputDialog("Escribe los apellidos del nuevo Coder\n");
        String documento = JOptionPane.showInputDialog("Escribe el documento del nuevo Coder\n"+"Sin puntos ni comas ej:1000089781");
        String cohorte = JOptionPane.showInputDialog("Ingresa la Cohorte del nuevo Coder\n");
        String cv = JOptionPane.showInputDialog("Ingresa el CV del nuevo Coder\n");
        String clan = JOptionPane.showInputDialog("Ingresa el Clan del nuevo Coder\n");
        Coder objCoder = new Coder();
        objCoder.setNombre(nombre);
        objCoder.setApellidos(apellidos);
        objCoder.setDocumento(documento);
        objCoder.setCohorte(cohorte);
        objCoder.setCv(cv);
        objCoder.setClan(clan);

        objModel.insert(objCoder);
        JOptionPane.showMessageDialog(null,objCoder.toString());
    }
    public static void delete() {
        CoderModel objCoderModel = new CoderModel();
        String listCoder = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCoder + "\n Ingresa el ID del Coder que deseas eliminar\n"));
        Coder objCoder = objCoderModel.findById(idDelete);

        if (objCoder== null){
            JOptionPane.showMessageDialog(null,"Coder no encontrado");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar este Coder?\nTambién se eliminara todo lo relacionado con este Coder \n"+ "\n"+objCoder.toString());
            if (confirm == 0) objCoderModel.delete(objCoder);
        }
    }
    public static void update(){
        CoderModel objModel = new CoderModel();
        String listCoder = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listCoder + "\nIngresa el ID del Coder que deseas actualizar"));
        Coder objCoder = objModel.findById(searchId);

        if(objCoder==null){
            JOptionPane.showMessageDialog(null,"Coder no encontrado");
        }else{
            String nombre = JOptionPane.showInputDialog("Escribe el nombre del nuevo Coder\n",objCoder.getNombre());
            String apellidos = JOptionPane.showInputDialog("Escribe los apellidos del nuevo Coder\n",objCoder.getApellidos());
            String documento = JOptionPane.showInputDialog("Escribe el documento del nuevo Coder\n"+"Sin puntos ni comas ej:1000089781",objCoder.getDocumento());
            String cohorte = JOptionPane.showInputDialog("Ingresa la Cohorte del nuevo Coder\n",objCoder.getCohorte());
            String cv = JOptionPane.showInputDialog("Ingresa el CV del nuevo Coder\n",objCoder.getCv());
            String clan = JOptionPane.showInputDialog("Ingresa el Clan del nuevo Coder\n",objCoder.getClan());
            objCoder.setNombre(nombre);
            objCoder.setApellidos(apellidos);
            objCoder.setDocumento(documento);
            objCoder.setCohorte(cohorte);
            objCoder.setCv(cv);
            objCoder.setClan(clan);
            objModel.update(objCoder);
        }

    }
    public static void getByName(){
        CoderModel objModel = new CoderModel();
        String name = JOptionPane.showInputDialog("Ingresa el nombre");
        String Coder = "";
        for (Coder temporal :objModel.findByName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Nombre no encontrado");
            }else{
                Coder+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Coder);
    }
    public static void getByLastname(){
        CoderModel objModel = new CoderModel();
        String name = JOptionPane.showInputDialog("Ingresa los apellidos");
        String Coder = "";
        for (Coder temporal :objModel.findByLastname(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Apellidos no encontrados");
            }else{
                Coder+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Coder);
    }


    public static void getByDocument(){
        CoderModel objModel = new CoderModel();
        String document = JOptionPane.showInputDialog("Ingresa el Documento"+"\n"+"Sin puntos ni comas ej:1000089781\n");
        Coder objCoder = new Coder();
        objCoder = objModel.findByDocument(document);
        if (objCoder==null){
            JOptionPane.showMessageDialog(null,"Documento no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objCoder.toString());
        }
    }
    public static void getByCohorte(){
        CoderModel objModel = new CoderModel();
        String cohorte = JOptionPane.showInputDialog("Ingresa la Cohorte");
        String Coder = "";
        for (Coder temporal :objModel.findByCohorte(cohorte)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Cohorte no econtrada");
            }else{
                Coder+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Coder);
    }
    public static void getByClan(){
        CoderModel objModel = new CoderModel();
        String clan = JOptionPane.showInputDialog("Ingresa el Clan");
        String Coder = "";
        for (Coder temporal :objModel.findByClan(clan)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Clan no encontrado");
            }else{
                Coder+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Coder);
    }
    public static void getByTecnologia(){
        CoderModel objModel = new CoderModel();
        String clan = JOptionPane.showInputDialog("Ingresa la tecnologia");
        String Coder = "";
        for (Coder temporal :objModel.findByTecnologia(clan)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Clan no encontrado");
            }else{
                Coder+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Coder);
    }

}