package BackEndHD.ClinicaOdontologica.service;

import BackEndHD.ClinicaOdontologica.dao.IDao;
import BackEndHD.ClinicaOdontologica.dao.TurnoDAOH2;
import BackEndHD.ClinicaOdontologica.model.Turno;

public class TurnoService {
    private IDao<Turno> turnoIDao;

    public TurnoService() {
        turnoIDao = new TurnoDAOH2();
    }

    public Turno guardarTurno(Turno turno){
        return turnoIDao.guardar(turno);
    }
}
