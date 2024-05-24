package BackEndHD.ClinicaOdontologica.service;

import BackEndHD.ClinicaOdontologica.dao.IDao;
import BackEndHD.ClinicaOdontologica.dao.OdontologoDAOH2;
import BackEndHD.ClinicaOdontologica.model.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService() {
        odontologoIDao = new OdontologoDAOH2();
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> listarTodos(){
        return odontologoIDao.listarTodos();
    }

    public Odontologo buscarPorId(Integer id){
        return odontologoIDao.buscarPorId(id);
    }


}
