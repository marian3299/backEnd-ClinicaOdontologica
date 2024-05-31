package BackEndHD.ClinicaOdontologica.dao;

import BackEndHD.ClinicaOdontologica.model.Odontologo;
import BackEndHD.ClinicaOdontologica.model.Paciente;
import BackEndHD.ClinicaOdontologica.model.Turno;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class TurnoDAOH2 implements IDao<Turno>{
    private static final Logger logger= Logger.getLogger(PacienteDAOH2.class);
    private static final String SQL_SELECT_ONE="SELECT * FROM TURNOS WHERE ID=?";
    private static final String SQL_INSERT = "INSERT INTO TURNOS(PACIENTE_ID, ODONTOLOGO_ID, FECHA_Y_HORA) VALUES(?,?,?)";
    private static final String SQL_SELECT_ALL="SELECT * FROM ";
    private static final String SQL_UPDATE = "UPDATE  SET WHERE ID=?";
    private static final String SQL_DELETE = "DELETE FROM  WHERE ID=?";
    @Override
    public Turno guardar(Turno turno) {
        logger.info("Iniciando guardado de turno");
        Connection connection = null;
        PacienteDAOH2 pacienteDAOH2 = new PacienteDAOH2();
        OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
        Paciente paciente = pacienteDAOH2.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologoDAOH2.buscarPorId(turno.getOdontologo().getId());

        try{
            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, paciente.getId());
            psInsert.setInt(2, odontologo.getId());
            psInsert.setTimestamp(3, Timestamp.valueOf(turno.getFechaYHora()));
            psInsert.execute();

            ResultSet clave = psInsert.getGeneratedKeys();
            while (clave.next()){
                turno.setId(clave.getInt(1));
            }
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return turno;
    }

    @Override
    public List<Turno> listarTodos() {
        Connection connection = null;

        return null;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Turno turno) {

    }

    @Override
    public Turno buscarPorString(String valor) {
        return null;
    }
}
