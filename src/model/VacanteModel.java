package model;

import database.CRUD;
import database.ConfigDB;
import entity.*;
import entity.Vacante;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;

        try {
            String sql="INSERT INTO Vacante (id_empresa,titulo,descripcion,duracion,estado,tecnologia) VALUES (?,?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objVacante.getEmpresa().getId_empresa());
            objPrepare.setString(2,objVacante.getTitulo());
            objPrepare.setString(3,objVacante.getDescripcion());
            objPrepare.setString(4,objVacante.getDuracion());
            objPrepare.setString(5,objVacante.getEstado());
            objPrepare.setString(6, objVacante.getTecnologia());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objVacante.setId_vacante(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        };
        ConfigDB.closeConnection();
        return objVacante;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listVacante = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM Vacante INNER JOIN Empresa ON Empresa.id_empresa=Vacante.id_empresa;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();
                objVacante.setId_vacante(objResult.getInt("Vacante.id_vacante"));
                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("Vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("Vacante.duracion"));
                objVacante.setEstado(objResult.getString("Vacante.estado"));
                objVacante.setTecnologia(objResult.getString("Vacante.tecnologia"));
                objEmpresa.setId_empresa(objResult.getInt("Empresa.id_empresa"));
                objEmpresa.setNombre(objResult.getString("Empresa.nombre"));
                objEmpresa.setSector(objResult.getString("Empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("Empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("Empresa.contacto"));
                objVacante.setEmpresa(objEmpresa);
                listVacante.add(objVacante);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }


    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Vacante SET id_empresa=?,titulo=?,descripcion=?,duracion=?,estado=?,tecnologia=? WHERE Vacante.id_vacante = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1,objVacante.getEmpresa().getId_empresa());
            objPrepared.setString(2,objVacante.getTitulo());
            objPrepared.setString(3,objVacante.getDescripcion());
            objPrepared.setString(4,objVacante.getDuracion());
            objPrepared.setString(5,objVacante.getEstado());
            objPrepared.setString(6, objVacante.getTecnologia());
            objPrepared.setInt(7, objVacante.getId_vacante());

            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Update was succesfull");
                JOptionPane.showMessageDialog(null, objVacante.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Vacante objVacante = (Vacante) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Vacante WHERE id_vacante=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objVacante.getId_vacante());
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows>0){
                isDeleted= true;
                JOptionPane.showMessageDialog(null,"Delete was succesfull");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
    public Vacante findById(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Vacante objVacante = null;

        try {
            String sql = "SELECT * FROM Vacante INNER JOIN Empresa ON Empresa.id_empresa=Vacante.id_empresa WHERE Vacante.id_vacante=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();
                objVacante.setId_vacante(objResult.getInt("Vacante.id_vacante"));
                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("Vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("Vacante.duracion"));
                objVacante.setEstado(objResult.getString("Vacante.estado"));
                objVacante.setTecnologia(objResult.getString("Vacante.tecnologia"));
                objEmpresa.setId_empresa(objResult.getInt("Empresa.id_empresa"));
                objEmpresa.setNombre(objResult.getString("Empresa.nombre"));
                objEmpresa.setSector(objResult.getString("Empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("Empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("Empresa.contacto"));
                objVacante.setEmpresa(objEmpresa);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objVacante;
    }

    public ArrayList<Vacante> findByTitulo(String titulo) {
        ArrayList<Vacante> listVacante = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = null;
        try {
            String sql = "SELECT * FROM Vacante INNER JOIN Empresa ON Vacante.id_empresa=Empresa.id_empresa WHERE Vacante.titulo LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + titulo + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();
                objVacante.setId_vacante(objResult.getInt("Vacante.id_vacante"));
                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("Vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("Vacante.duracion"));
                objVacante.setEstado(objResult.getString("Vacante.estado"));
                objVacante.setTecnologia(objResult.getString("Vacante.tecnologia"));
                objEmpresa.setId_empresa(objResult.getInt("Empresa.id_empresa"));
                objEmpresa.setNombre(objResult.getString("Empresa.nombre"));
                objEmpresa.setSector(objResult.getString("Empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("Empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("Empresa.contacto"));
                objVacante.setEmpresa(objEmpresa);
                listVacante.add(objVacante);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }
    public ArrayList<Vacante> findByDescripcion(String descripcion) {
        ArrayList<Vacante> listVacante = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = null;
        try {
            String sql = "SELECT * FROM Vacante INNER JOIN Empresa ON Empresa.id_empresa=Vacante.id_empresa WHERE Vacante.descripcion LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + descripcion + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();
                objVacante.setId_vacante(objResult.getInt("Vacante.id_vacante"));
                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("Vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("Vacante.duracion"));
                objVacante.setEstado(objResult.getString("Vacante.estado"));
                objVacante.setTecnologia(objResult.getString("Vacante.tecnologia"));
                objEmpresa.setId_empresa(objResult.getInt("Empresa.id_empresa"));
                objEmpresa.setNombre(objResult.getString("Empresa.nombre"));
                objEmpresa.setSector(objResult.getString("Empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("Empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("Empresa.contacto"));
                objVacante.setEmpresa(objEmpresa);
                listVacante.add(objVacante);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }

    public ArrayList<Vacante> findByDuracion(String duracion) {
        ArrayList<Vacante> listVacante = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = null;
        try {
            String sql = "SELECT * FROM Vacante INNER JOIN Empresa ON Empresa.id_empresa=Vacante.id_empresa WHERE Vacante.duracion LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + duracion + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();
                objVacante.setId_vacante(objResult.getInt("Vacante.id_vacante"));
                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("Vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("Vacante.duracion"));
                objVacante.setEstado(objResult.getString("Vacante.estado"));
                objVacante.setTecnologia(objResult.getString("Vacante.tecnologia"));
                objEmpresa.setId_empresa(objResult.getInt("Empresa.id_empresa"));
                objEmpresa.setNombre(objResult.getString("Empresa.nombre"));
                objEmpresa.setSector(objResult.getString("Empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("Empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("Empresa.contacto"));
                objVacante.setEmpresa(objEmpresa);
                listVacante.add(objVacante);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }

    public ArrayList<Vacante> findByEstado(String estado) {
        ArrayList<Vacante> listVacante = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = null;
        try {
            String sql = "SELECT * FROM Vacante INNER JOIN Empresa ON Empresa.id_empresa=Vacante.id_empresa WHERE Vacante.estado = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + estado + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();
                objVacante.setId_vacante(objResult.getInt("Vacante.id_vacante"));
                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("Vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("Vacante.duracion"));
                objVacante.setEstado(objResult.getString("Vacante.estado"));
                objVacante.setTecnologia(objResult.getString("Vacante.tecnologia"));
                objEmpresa.setId_empresa(objResult.getInt("Empresa.id_empresa"));
                objEmpresa.setNombre(objResult.getString("Empresa.nombre"));
                objEmpresa.setSector(objResult.getString("Empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("Empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("Empresa.contacto"));
                objVacante.setEmpresa(objEmpresa);
                listVacante.add(objVacante);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }
    public ArrayList<Vacante> findByTecnologia(String tecnologia) {
        ArrayList<Vacante> listVacante = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = null;
        try {
            String sql = "SELECT * FROM Vacante INNER JOIN Empresa ON Empresa.id_empresa=Vacante.id_empresa WHERE Vacante.tecnologia LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + tecnologia + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();
                objVacante.setId_vacante(objResult.getInt("Vacante.id_vacante"));
                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("Vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("Vacante.duracion"));
                objVacante.setEstado(objResult.getString("Vacante.estado"));
                objVacante.setTecnologia(objResult.getString("Vacante.tecnologia"));
                objEmpresa.setId_empresa(objResult.getInt("Empresa.id_empresa"));
                objEmpresa.setNombre(objResult.getString("Empresa.nombre"));
                objEmpresa.setSector(objResult.getString("Empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("Empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("Empresa.contacto"));
                objVacante.setEmpresa(objEmpresa);
                listVacante.add(objVacante);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }
    public boolean changeState(String obj, String id) {
        Connection objConnection = ConfigDB.openConnection();
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Vacante SET estado=? WHERE Vacante.id_vacante = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1,obj);
            objPrepared.setString(2,id);

            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Se ha cambiado el estado a "+obj);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

}