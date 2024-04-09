import controller.ContratacionController;
import controller.EmpresaController;
import controller.VacanteController;
import controller.CoderController;

import javax.swing.*;
import java.text.BreakIterator;

public class Main {
    public static void main(String[] args) {

        String option = "";
        String optionEmpresa = "";
        String optionCoder = "";
        String optionVacante = "";
        String optionContra = "";


        do {
            option = JOptionPane.showInputDialog("""
                    1. Menú Empresas
                    2. Menú Coders 
                    3. Menú Vacantes
                    4. Menú Contrataciones
                    
                    5. Salir
                    
                    Ingresa un opción:
                    
                    """);
            switch (option){
                case "1":
                    do {
                        optionEmpresa = JOptionPane.showInputDialog("""
                                1. Listar todas las Empresas
                                2. Buscar Empresa por Nombre
                                3. Buscar Empresa por ID
                                4. Buscar Empresa por Sector
                                5. Buscar Empresa por Ubicacion
                                
                                
                                6. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionEmpresa){
                            case "1":
                                EmpresaController.getAll();
                                break;
                            case "2":
                                EmpresaController.getByName();
                                break;
                            case "3":
                                EmpresaController.getById();
                                break;
                            case "4":
                                EmpresaController.getBySector();
                                break;
                            case "5":
                                EmpresaController.getByUbicacion();
                                break;
                        }
                    }while (!optionEmpresa.equals("6"));
                    break;
                case "2":
                    do {
                        optionCoder = JOptionPane.showInputDialog("""
                                1. Listar todos los Coders
                                2. Agregar Coder
                                3. Actualizar Coder
                                4. Eliminar Coder
                                5. Buscar Coder por Nombre
                                6. Buscar Coder por Apellidos        
                                7. Buscar Coder por Documento
                                8. Buscar Coder por Clan
                                9. Buscar Coder por Cohorte
                                10. Buscar Coder por Tecnologia
                                
                        
                                11. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionCoder){
                            case "1":
                                CoderController.getAll();
                                break;
                            case "2":
                                CoderController.create();
                                break;
                            case "3":
                                CoderController.update();
                                break;
                            case "4":
                                CoderController.delete();
                                break;
                            case "5":
                                CoderController.getByName();
                                break;
                            case "6":
                                CoderController.getByLastname();
                                break;
                            case "7":
                                CoderController.getByDocument();
                                break;
                            case "8":
                                CoderController.getByClan();
                                break;
                            case "9":
                                CoderController.getByCohorte();
                                break;
                            case "10":
                                CoderController.getByTecnologia();
                                break;
                        }
                    }while (!optionCoder.equals("11"));
                    break;
                case "3":
                    do {
                        optionVacante = JOptionPane.showInputDialog("""
                                1. Listar todos los Vacantes
                                2. Agregar Vacante
                                3. Actualizar Vacante
                                4. Eliminar Vacante
                                5. Buscar Vacante por Titutlo
                                6. Buscar Vacante por Duracion        
                                7. Buscar Vacante por Estado
                                8. Buscar Vacante por ID
                                9. Buscar Vacante por Tecnologia
                                10. CAMBIAR DE ESTADO UNA VACANTE
                                
                                11. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionVacante){
                            case "1":
                                VacanteController.getAll();
                                break;
                            case "2":
                                VacanteController.create();
                                break;
                            case "3":
                                VacanteController.update();
                                break;
                            case "4":
                                VacanteController.delete();
                                break;
                            case "5":
                                VacanteController.getByTitulo();
                                break;
                            case "6":
                                VacanteController.getByDuracion();
                                break;
                            case "7":
                                VacanteController.getByEstado();
                                break;
                            case "8":
                                VacanteController.getById();
                                break;
                            case "9":
                                VacanteController.getByTecnologia();
                                break;
                            case "10":
                                VacanteController.changeState();
                                break;
                        }
                    }while (!optionVacante.equals("11"));
                    break;
                case "4":
                    do {
                        optionContra = JOptionPane.showInputDialog("""
                                1. Listar todas las Contrataciones
                                2. Agregar Contratacion
                                3. Actualizar Contratacion
                                4. Eliminar Contratacion
                                5. Buscar Contratacion por Nombre del Coder 
                                6. Buscar Contratacion por Apellidos del Coder        
                                7. Buscar Contratacion por Documento del Coder
                                8. Buscar Contratacion por Titulo del Vacante      
                                9. Buscar Contratacion por ID del Vacante
                                10. Buscar Contratacion por Estado del Vacante
                                11. Buscar Contratacion por ID
                                
                                12. CAMBIAR DE ESTADO A UNA CONTRATACION
                                
                                13. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionContra){
                            case "1":
                                ContratacionController.getAll();
                                break;
                            case "2":
                                ContratacionController.create();
                                break;
                            case "3":
                                ContratacionController.update();
                                break;
                            case "4":
                                ContratacionController.delete();
                                break;
                            case "5":
                                ContratacionController.getByCoderName();
                                break;
                            case "6":
                                ContratacionController.getByCoderLastname();
                                break;
                            case "7":
                                ContratacionController.getByCoderDocument();
                                break;
                            case "8":
                                ContratacionController.getByVacanteTitulo();
                                break;
                            case "9":
                                ContratacionController.getByVacanteId();
                                break;
                            case "10":
                                ContratacionController.getByVacanteEstado();
                                break;
                            case "11":
                                ContratacionController.getById();
                                break;
                            case "12":
                                ContratacionController.changeState();
                                break;
                        }
                    }while (!optionContra.equals("13"));
                    break;
            }
        }while (!option.equals("5"));

    }
}