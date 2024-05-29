package BackEndHD.ClinicaOdontologica.dao;

import BackEndHD.ClinicaOdontologica.model.Domicilio;
import BackEndHD.ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements IDao<Paciente> {
    private static final Logger logger= Logger.getLogger(PacienteDAOH2.class);
    private static final String SQL_SELECT_ONE="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_INSERT = "INSERT INTO PACIENTES(NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_BY_EMAIL="SELECT * FROM PACIENTES WHERE EMAIL=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM PACIENTES";
    private static final String SQL_UPDATE = "UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, CEDULA=?, FECHA_INGRESO=?, DOMICILIO_ID=?, EMAIL=? WHERE ID=?";
    private static final String SQL_DELETE = "DELETE FROM PACIENTES WHERE ID=?";

    @Override
    public Paciente guardar(Paciente paciente) {
        logger.info("Iniciando las operaciones de guardado: " + paciente.getNombre());
        Connection connection = null;
        DomicilioDAOH2 daoAux = new DomicilioDAOH2();
        Domicilio domicilio = daoAux.guardar(paciente.getDomicilio());

        try {
            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5, domicilio.getId());
            psInsert.setString(6, paciente.getEmail());
            psInsert.execute();

            ResultSet clave = psInsert.getGeneratedKeys();

            while (clave.next()){
                paciente.setId(clave.getInt(1)); //Setear el id del paciente obtenido de la linea 25
            }

            logger.info("Paciente guardado");

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return paciente;
    }

    @Override
    public List<Paciente> listarTodos() {
        logger.info("Iniciado la busqueda de todos los pacientes");
        Connection connection = null;
        List<Paciente> pacientes = new ArrayList<>();
        Domicilio domicilio = null;
        DomicilioDAOH2 daoAux = new DomicilioDAOH2();

        try{
            connection = BD.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);

            while (rs.next()){
                domicilio = daoAux.buscarPorId(rs.getInt(6));
                Paciente paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio, rs.getString(7));
                pacientes.add(paciente);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return pacientes;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        logger.info("iniciando la operacion de buscado de un paciente con id : "+id);
        Connection connection= null;
        Paciente paciente= null;
        Domicilio domicilio= null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            PreparedStatement psSElectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSElectOne.setInt(1,id);
            ResultSet rs= psSElectOne.executeQuery();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while(rs.next()){
                domicilio= daoAux.buscarPorId(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio, rs.getString(7));
            }


        }catch (Exception e){
            logger.error(e.getMessage());
        }


        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Iniciando eliminacion de paciente con id: " + id);
        Connection connection = null;
        DomicilioDAOH2 daoAux = new DomicilioDAOH2();

        try{
            connection = BD .getConnection();
            daoAux.eliminar(id);

            PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1,id);
            psDelete.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void actualizar(Paciente paciente) {
        logger.warn("Iniciando la actualizacion de paciente con id: " + paciente.getId());
        Connection connection = null;
        DomicilioDAOH2 doaAux = new DomicilioDAOH2();

        try{
            connection = BD.getConnection();
            doaAux.actualizar(paciente.getDomicilio());

            PreparedStatement psUpadte = connection.prepareStatement(SQL_UPDATE);
            psUpadte.setString(1, paciente.getNombre());
            psUpadte.setString(2, paciente.getApellido());
            psUpadte.setString(3, paciente.getCedula());
            psUpadte.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psUpadte.setInt(5, paciente.getDomicilio().getId());
            psUpadte.setString(6, paciente.getEmail());
            psUpadte.setInt(7, paciente.getId());
            psUpadte.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public Paciente buscarPorString(String valor) {
        logger.info("Iniciando busqueda por email: " + valor);
        Connection connection = null;
        Paciente paciente = null;
        Domicilio domicilio = null;
        DomicilioDAOH2 daoAux = new DomicilioDAOH2();

        try{
            connection = BD.getConnection();
            PreparedStatement psSelectEmail = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelectEmail.setString(1, valor);

            ResultSet rs = psSelectEmail.executeQuery();

            while (rs.next()){
                domicilio = daoAux.buscarPorId(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio, rs.getString(7));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return paciente;
    }
}
