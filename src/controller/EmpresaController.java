package controller;

import entity.Empresa;
import model.EmpresaModel;

import javax.swing.*;
import java.nio.file.WatchService;

public class EmpresaController {

    public static void getAll() {
        EmpresaModel objModel = new EmpresaModel();
        String listEmpresa = "LISTA DE EMPRESAS"+"\n";

        for (Object iterador : objModel.findAll()) {
            Empresa objEmpresa = (Empresa) iterador;
            listEmpresa += objEmpresa.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listEmpresa);
    }

    public static String getAllString() {
        EmpresaModel objModel = new EmpresaModel();
        String listEmpresa = "LISTA DE EMPRESAS\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Empresa
            Empresa objEmpresa = (Empresa) iterador;
            listEmpresa += objEmpresa.toString() + "\n";
        }

        return listEmpresa;
    }

    public static void getByName(){
        EmpresaModel objModel = new EmpresaModel();
        String name = JOptionPane.showInputDialog("Ingresa el nombre");
        String Empresa = "";
        for (Empresa temporal :objModel.findByName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Empresa no encontrada");
            }else{
                Empresa+=temporal.toString();
            }
        }
        JOptionPane.showMessageDialog(null,Empresa);
    }

    public static void getById(){
        EmpresaModel objModel = new EmpresaModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Empresa objEspe = new Empresa();
        objEspe = objModel.findById(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }

    public static void getBySector(){
        EmpresaModel objModel = new EmpresaModel();
        String sector = JOptionPane.showInputDialog("Ingresa el Sector");
        String Empresa = "";
        for (Empresa temporal :objModel.findBySector(sector)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Sector no encontrado");
            }else{
                Empresa+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Empresa);
    }

    public static void getByUbicacion(){
        EmpresaModel objModel = new EmpresaModel();
        String ubicacion = JOptionPane.showInputDialog("Ingresa la Ubicacion");
        String Empresa = "";
        for (Empresa temporal :objModel.findByUbicacion(ubicacion)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Ubicacion no encontrada");
            }else{
                Empresa+=temporal.toString();
            }
        }
        JOptionPane.showMessageDialog(null,Empresa);
    }
}