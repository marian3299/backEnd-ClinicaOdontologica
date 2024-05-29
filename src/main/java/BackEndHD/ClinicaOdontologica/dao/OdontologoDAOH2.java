package BackEndHD.ClinicaOdontologica.dao;

import BackEndHD.ClinicaOdontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo>{
    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT = "INSERT INTO ODONTOLOGOS(MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SEARCH_ALL = "SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SEARCH_ONE = "SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_DELETE = "DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE = "UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID=?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;

        try{
            connection = BD.getConnection();

            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute();

            ResultSet clave = psInsert.getGeneratedKeys();

            while (clave.next()){
                odontologo.setId(clave.getInt(1));
            }
            logger.info("Odonologo guardado correctamente");

        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            connection = BD.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SEARCH_ALL);

            while (rs.next()){
                Odontologo odontologo = new Odontologo(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                odontologos.add(odontologo);
            }

            logger.info("Odontologos encontrados");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        logger.info("Iniciando busqueda de Odontologo");
        Connection connection = null;
        Odontologo odontologo = null;

        try{
            connection = BD.getConnection();
            PreparedStatement psSearchOne = connection.prepareStatement(SQL_SEARCH_ONE);
            psSearchOne.setInt(1, id);
            ResultSet rs = psSearchOne.executeQuery();

            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                System.out.println(odontologo.toString());
            }
            logger.info("Odontologo encontrado");
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return odontologo;

    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Iniciando eliminacion de odontologo con id: " + id);
        Connection connection = null;

        try{
            connection = BD.getConnection();

            PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1, id);
            psDelete.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }


    }

    @Override
    public void actualizar(Odontologo odontologo) {
        logger.info("Iniciando actualizacion de odontologo con id " + odontologo.getId());
        Connection connection = null;

        try{
            connection = BD.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setInt(1, odontologo.getMatricula());
            psUpdate.setString(2, odontologo.getNombre());
            psUpdate.setString(3, odontologo.getApellido());
            psUpdate.setInt(4, odontologo.getId());
            psUpdate.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    @Override
    public Odontologo buscarPorString(String valor) {
        return null;
    }
}
