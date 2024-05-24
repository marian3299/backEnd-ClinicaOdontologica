package BackEndHD.ClinicaOdontologica.service;

import BackEndHD.ClinicaOdontologica.dao.IDao;
import BackEndHD.ClinicaOdontologica.dao.PacienteDAOH2;
import BackEndHD.ClinicaOdontologica.model.Paciente;

import java.util.List;

public class PacienteService {
    private IDao<Paciente> pacienteiDao;

    public PacienteService() {
        pacienteiDao= new PacienteDAOH2();
    }


    //metodos manuales
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorID(Integer id){
        return pacienteiDao.buscarPorId(id);
    }
    public Paciente buscarPorEmail(String email){
        return pacienteiDao.buscarPorString(email);
    }

    public List<Paciente> buscarTodos(){
        return pacienteiDao.listarTodos();
    }
}
