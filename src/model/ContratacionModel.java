package model;

import database.CRUD;
import database.ConfigDB;
import entity.*;
import entity.Contratacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;

        try {
            String sql="INSERT INTO Contratacion (id_coder,id_vacante,estado,salario) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objContratacion.getCoder().getId_coder());
            objPrepare.setInt(2,objContratacion.getVacante().getId_vacante());
            objPrepare.setString(3,objContratacion.getEstado());
            objPrepare.setDouble(4,objContratacion.getSalario());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objContratacion.setId_contratacion(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        };

        VacanteModel objVacanteModel = new VacanteModel();
        objVacanteModel.changeState("INACTIVO",String.valueOf(objContratacion.getVacante().getId_vacante()));
        ConfigDB.closeConnection();
        return objContratacion;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listContratacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM Contratacion INNER JOIN Coder ON Coder.id_coder=Contratacion.id_coder INNER JOIN Vacante ON Vacante.id_vacante=Contratacion.id_vacante INNER JOIN Empresa ON Empresa.id_empresa = Vacante.id_empresa;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Contratacion objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("Contratacion.id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("Contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("Contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("Contratacion.salario"));

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

                objCoder.setId_coder(objResult.getInt("Coder.id_coder"));
                objCoder.setNombre(objResult.getString("Coder.nombre"));
                objCoder.setApellidos(objResult.getString("Coder.apellidos"));
                objCoder.setDocumento(objResult.getString("Coder.documento"));
                objCoder.setCohorte(objResult.getString("Coder.cohorte"));
                objCoder.setCv(objResult.getString("Coder.cv"));
                objCoder.setClan(objResult.getString("Coder.clan"));


                objContratacion.setCoder(objCoder);
                objContratacion.setVacante(objVacante);
                listContratacion.add(objContratacion);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listContratacion;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Contratacion SET id_coder=?,id_vacante=?,estado=?,salario=? WHERE Contratacion.id_contratacion = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1,objContratacion.getCoder().getId_coder());
            objPrepared.setInt(2,objContratacion.getVacante().getId_vacante());
            objPrepared.setString(3,objContratacion.getEstado());
            objPrepared.setDouble(4,objContratacion.getSalario());

            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Update was succesfull");
                JOptionPane.showMessageDialog(null, objContratacion.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Contratacion objContratacion = (Contratacion) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Contratacion WHERE id_contratacion=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objContratacion.getId_contratacion());
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
    public Contratacion findById(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Contratacion objContratacion = null;

        try {
            String sql = "SELECT * FROM Contratacion INNER JOIN Coder ON Coder.id_coder=Contratacion.id_coder " +
                    " INNER JOIN Vacante ON Vacante.id_vacante=Contratacion.id_vacante " +
                    " INNER JOIN Empresa ON Empresa.id_empresa = Vacante.id_empresa " +
                    " WHERE Contratacion.id_contratacion=?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("Contratacion.id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("Contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("Contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("Contratacion.salario"));

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

                objCoder.setId_coder(objResult.getInt("Coder.id_coder"));
                objCoder.setNombre(objResult.getString("Coder.nombre"));
                objCoder.setApellidos(objResult.getString("Coder.apellidos"));
                objCoder.setDocumento(objResult.getString("Coder.documento"));
                objCoder.setCohorte(objResult.getString("Coder.cohorte"));
                objCoder.setCv(objResult.getString("Coder.cv"));
                objCoder.setClan(objResult.getString("Coder.clan"));


                objContratacion.setCoder(objCoder);
                objContratacion.setVacante(objVacante);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objContratacion;
    }
    public Contratacion findByCoderDocument(String document) {
        Connection objConnection = ConfigDB.openConnection();

        Contratacion objContratacion = null;

        try {
            String sql = "SELECT * FROM Contratacion INNER JOIN Coder ON Coder.id_coder=Contratacion.id_coder " +
                    " INNER JOIN Vacante ON Vacante.id_vacante=Contratacion.id_vacante " +
                    " INNER JOIN Empresa ON Empresa.id_empresa = Vacante.id_empresa " +
                    " WHERE Coder.documento=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, document);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("Contratacion.id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("Contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("Contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("Contratacion.salario"));

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

                objCoder.setId_coder(objResult.getInt("Coder.id_coder"));
                objCoder.setNombre(objResult.getString("Coder.nombre"));
                objCoder.setApellidos(objResult.getString("Coder.apellidos"));
                objCoder.setDocumento(objResult.getString("Coder.documento"));
                objCoder.setCohorte(objResult.getString("Coder.cohorte"));
                objCoder.setCv(objResult.getString("Coder.cv"));
                objCoder.setClan(objResult.getString("Coder.clan"));


                objContratacion.setCoder(objCoder);
                objContratacion.setVacante(objVacante);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objContratacion;
    }

    public ArrayList<Contratacion> findByCoderName(String name) {
        ArrayList<Contratacion> listContratacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = null;
        try {
            String sql = "SELECT * FROM Contratacion INNER JOIN Coder ON Coder.id_coder=Contratacion.id_coder " +
                    " INNER JOIN Vacante ON Vacante.id_vacante=Contratacion.id_vacante " +
                    " INNER JOIN Empresa ON Empresa.id_empresa = Vacante.id_empresa " +
                    " WHERE Coder.nombre LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("Contratacion.id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("Contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("Contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("Contratacion.salario"));

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

                objCoder.setId_coder(objResult.getInt("Coder.id_coder"));
                objCoder.setNombre(objResult.getString("Coder.nombre"));
                objCoder.setApellidos(objResult.getString("Coder.apellidos"));
                objCoder.setDocumento(objResult.getString("Coder.documento"));
                objCoder.setCohorte(objResult.getString("Coder.cohorte"));
                objCoder.setCv(objResult.getString("Coder.cv"));
                objCoder.setClan(objResult.getString("Coder.clan"));


                objContratacion.setCoder(objCoder);
                objContratacion.setVacante(objVacante);
                listContratacion.add(objContratacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listContratacion;
    }
    public ArrayList<Contratacion> findByCoderLastname(String name) {
        ArrayList<Contratacion> listContratacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = null;
        try {
            String sql = "SELECT * FROM Contratacion INNER JOIN Coder ON Coder.id_coder=Contratacion.id_coder " +
                    " INNER JOIN Vacante ON Vacante.id_vacante=Contratacion.id_vacante " +
                    " INNER JOIN Empresa ON Empresa.id_empresa = Vacante.id_empresa " +
                    " WHERE Coder.apellidos LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("Contratacion.id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("Contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("Contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("Contratacion.salario"));

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

                objCoder.setId_coder(objResult.getInt("Coder.id_coder"));
                objCoder.setNombre(objResult.getString("Coder.nombre"));
                objCoder.setApellidos(objResult.getString("Coder.apellidos"));
                objCoder.setDocumento(objResult.getString("Coder.documento"));
                objCoder.setCohorte(objResult.getString("Coder.cohorte"));
                objCoder.setCv(objResult.getString("Coder.cv"));
                objCoder.setClan(objResult.getString("Coder.clan"));


                objContratacion.setCoder(objCoder);
                objContratacion.setVacante(objVacante);
                listContratacion.add(objContratacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listContratacion;
    }
    public ArrayList<Contratacion> findByCoderCohorte(String name) {
        ArrayList<Contratacion> listContratacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = null;
        try {
            String sql = "SELECT * FROM Contratacion INNER JOIN Coder ON Coder.id_coder=Contratacion.id_coder " +
                    " INNER JOIN Vacante ON Vacante.id_vacante=Contratacion.id_vacante " +
                    " INNER JOIN Empresa ON Empresa.id_empresa = Vacante.id_empresa " +
                    " WHERE Coder.cohorte LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("Contratacion.id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("Contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("Contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("Contratacion.salario"));

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

                objCoder.setId_coder(objResult.getInt("Coder.id_coder"));
                objCoder.setNombre(objResult.getString("Coder.nombre"));
                objCoder.setApellidos(objResult.getString("Coder.apellidos"));
                objCoder.setDocumento(objResult.getString("Coder.documento"));
                objCoder.setCohorte(objResult.getString("Coder.cohorte"));
                objCoder.setCv(objResult.getString("Coder.cv"));
                objCoder.setClan(objResult.getString("Coder.clan"));


                objContratacion.setCoder(objCoder);
                objContratacion.setVacante(objVacante);
                listContratacion.add(objContratacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listContratacion;
    }
    public ArrayList<Contratacion> findByTitulo(String titulo) {
        ArrayList<Contratacion> listContratacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = null;
        try {
            String sql = "SELECT * FROM Contratacion INNER JOIN Coder ON Coder.id_coder=Contratacion.id_coder " +
                    " INNER JOIN Vacante ON Vacante.id_vacante=Contratacion.id_vacante " +
                    " INNER JOIN Empresa ON Empresa.id_empresa = Vacante.id_empresa " +
                    " WHERE Vacante.titulo LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + titulo + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("Contratacion.id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("Contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("Contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("Contratacion.salario"));

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

                objCoder.setId_coder(objResult.getInt("Coder.id_coder"));
                objCoder.setNombre(objResult.getString("Coder.nombre"));
                objCoder.setApellidos(objResult.getString("Coder.apellidos"));
                objCoder.setDocumento(objResult.getString("Coder.documento"));
                objCoder.setCohorte(objResult.getString("Coder.cohorte"));
                objCoder.setCv(objResult.getString("Coder.cv"));
                objCoder.setClan(objResult.getString("Coder.clan"));


                objContratacion.setCoder(objCoder);
                objContratacion.setVacante(objVacante);
                listContratacion.add(objContratacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listContratacion;
    }
    public ArrayList<Contratacion> findByVacanteDuracion(String duracion) {
        ArrayList<Contratacion> listContratacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = null;
        try {
            String sql = "SELECT * FROM Contratacion INNER JOIN Coder ON Coder.id_coder=Contratacion.id_coder " +
                    " INNER JOIN Vacante ON Vacante.id_vacante=Contratacion.id_vacante " +
                    " INNER JOIN Empresa ON Empresa.id_empresa = Vacante.id_empresa " +
                    " WHERE Vacante.duracion LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + duracion + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("Contratacion.id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("Contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("Contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("Contratacion.salario"));

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

                objCoder.setId_coder(objResult.getInt("Coder.id_coder"));
                objCoder.setNombre(objResult.getString("Coder.nombre"));
                objCoder.setApellidos(objResult.getString("Coder.apellidos"));
                objCoder.setDocumento(objResult.getString("Coder.documento"));
                objCoder.setCohorte(objResult.getString("Coder.cohorte"));
                objCoder.setCv(objResult.getString("Coder.cv"));
                objCoder.setClan(objResult.getString("Coder.clan"));


                objContratacion.setCoder(objCoder);
                objContratacion.setVacante(objVacante);
                listContratacion.add(objContratacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listContratacion;
    }
    public ArrayList<Contratacion> findByVacanteEstado(String estado) {
        ArrayList<Contratacion> listContratacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = null;
        try {
            String sql = "SELECT * FROM Contratacion INNER JOIN Coder ON Coder.id_coder=Contratacion.id_coder " +
                    " INNER JOIN Vacante ON Vacante.id_vacante=Contratacion.id_vacante " +
                    " INNER JOIN Empresa ON Empresa.id_empresa = Vacante.id_empresa " +
                    " WHERE Vacante.estado LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + estado + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("Contratacion.id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("Contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("Contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("Contratacion.salario"));

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

                objCoder.setId_coder(objResult.getInt("Coder.id_coder"));
                objCoder.setNombre(objResult.getString("Coder.nombre"));
                objCoder.setApellidos(objResult.getString("Coder.apellidos"));
                objCoder.setDocumento(objResult.getString("Coder.documento"));
                objCoder.setCohorte(objResult.getString("Coder.cohorte"));
                objCoder.setCv(objResult.getString("Coder.cv"));
                objCoder.setClan(objResult.getString("Coder.clan"));


                objContratacion.setCoder(objCoder);
                objContratacion.setVacante(objVacante);
                listContratacion.add(objContratacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listContratacion;
    }
    public Contratacion findByVacanteId(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Contratacion objContratacion = null;

        try {
            String sql = "SELECT * FROM Contratacion INNER JOIN Coder ON Coder.id_coder=Contratacion.id_coder " +
                    " INNER JOIN Vacante ON Vacante.id_vacante=Contratacion.id_vacante " +
                    " INNER JOIN Empresa ON Empresa.id_empresa = Vacante.id_empresa " +
                    " WHERE Vacante.id_vacante=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("Contratacion.id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("Contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("Contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("Contratacion.salario"));

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

                objCoder.setId_coder(objResult.getInt("Coder.id_coder"));
                objCoder.setNombre(objResult.getString("Coder.nombre"));
                objCoder.setApellidos(objResult.getString("Coder.apellidos"));
                objCoder.setDocumento(objResult.getString("Coder.documento"));
                objCoder.setCohorte(objResult.getString("Coder.cohorte"));
                objCoder.setCv(objResult.getString("Coder.cv"));
                objCoder.setClan(objResult.getString("Coder.clan"));


                objContratacion.setCoder(objCoder);
                objContratacion.setVacante(objVacante);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objContratacion;
    }

    public boolean changeState(String obj, String id) {
        Connection objConnection = ConfigDB.openConnection();
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Contratacion SET estado=? WHERE Contratacion.id_contratacion = ?;";
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