package BackEndHD.ClinicaOdontologica.dao;

import BackEndHD.ClinicaOdontologica.model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DomicilioDAOH2 implements IDao<Domicilio> {
    private static final Logger logger= Logger.getLogger(DomicilioDAOH2.class);
    private static final String SQL_SELECT_ONE="SELECT * FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_INSERT="INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=?";
    private static final String SQL_DELETE = "DELETE FROM DOMICILIOS WHERE ID=?";
    @Override
    public Domicilio guardar(Domicilio domicilio) {

        logger.info("Iniciando operacion de guardado");

        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2, domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());
            psInsert.execute();

            ResultSet clave = psInsert.getGeneratedKeys();

            while (clave.next()){
                domicilio.setId(clave.getInt(1));
            }

            logger.info("Domicilio guardado");

        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return domicilio;
    }

    @Override
    public List<Domicilio> listarTodos() {
        return null;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        logger.info("iniciando la busqueda de un domicilio con id: "+id);
        Connection connection= null;
        Domicilio domicilio= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1,id);
            ResultSet rs= psSelectOne.executeQuery();
            while (rs.next()){
                domicilio= new Domicilio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return domicilio;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Iniciando eliminacion de domicilio con id: " + id);
        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1, id);
            psDelete.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        logger.info("Iniciando actualizacion de domicilio con id: " + domicilio.getId());
        Connection connection = null;

        try {
            connection = BD.getConnection();

            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, domicilio.getCalle());
            psUpdate.setInt(2, domicilio.getNumero());
            psUpdate.setString(3, domicilio.getLocalidad());
            psUpdate.setString(4, domicilio.getProvincia());
            psUpdate.setInt(5, domicilio.getId());
            psUpdate.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public Domicilio buscarPorString(String valor) {
        return null;
    }
}
