package BackEndHD.ClinicaOdontologica;

import BackEndHD.ClinicaOdontologica.dao.BD;
import BackEndHD.ClinicaOdontologica.model.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import BackEndHD.ClinicaOdontologica.service.OdontologoService;

import java.util.List;

public class OdontologoTest {
    @Test
    public void listarOdontologos(){
        Odontologo odontologo = new Odontologo(11111, "Sofia", "Gonzalez");
        Odontologo odontologo1 = new Odontologo( 22222, "Andres", "Cardenas");
        BD.crearTablas();

        OdontologoService odontologoService = new OdontologoService();
        odontologoService.guardarOdontologo(odontologo);
        odontologoService.guardarOdontologo(odontologo1);

        List<Odontologo> ondontologos = null;
        ondontologos = odontologoService.listarTodos();

        Assertions.assertTrue(ondontologos!=null);

    }

    @Test
    public void buscarPorId(){
        OdontologoService odontologoService = new OdontologoService();
        Odontologo odontologo = odontologoService.buscarPorId(1);
        Assertions.assertTrue(odontologo!=null);
    }
}
