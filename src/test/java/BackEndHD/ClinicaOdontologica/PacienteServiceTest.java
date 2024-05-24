package BackEndHD.ClinicaOdontologica;

import BackEndHD.ClinicaOdontologica.dao.BD;
import BackEndHD.ClinicaOdontologica.model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import BackEndHD.ClinicaOdontologica.service.PacienteService;

import java.util.List;

public class PacienteServiceTest {
    @Test
    public void buscarPacientePorID(){
        BD.crearTablas();
        PacienteService pacienteService= new PacienteService();
        Integer id=2;
        Paciente paciente= pacienteService.buscarPorID(id);
        Assertions.assertTrue(paciente!=null);
    }

    @Test
    public void buscarPacientePorEmail(){
        BD.crearTablas();
        PacienteService pacienteService = new PacienteService();
        Paciente paciente = pacienteService.buscarPorEmail("jorgito@jorgito.com");
        Assertions.assertTrue(paciente!=null);
    }

    @Test
    public void listarTodos(){
        BD.crearTablas();
        PacienteService pacienteService = new PacienteService();
        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assertions.assertTrue(pacientes!=null);
    }
}
