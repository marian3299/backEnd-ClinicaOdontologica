package BackEndHD.ClinicaOdontologica.controller;

import BackEndHD.ClinicaOdontologica.model.Odontologo;
import BackEndHD.ClinicaOdontologica.model.Paciente;
import BackEndHD.ClinicaOdontologica.model.Turno;
import BackEndHD.ClinicaOdontologica.service.OdontologoService;
import BackEndHD.ClinicaOdontologica.service.PacienteService;
import BackEndHD.ClinicaOdontologica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoController() {
        turnoService = new TurnoService();
        pacienteService= new PacienteService();
        odontologoService= new OdontologoService();
    }

    @PostMapping
    public ResponseEntity<Turno> gudardarTurno(@RequestBody Turno turno){
        Paciente paciente = pacienteService.buscarPorID(turno.getPaciente().getId());
        Odontologo odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if(paciente!=null && odontologo!=null){
            Turno savedTurno = turnoService.guardarTurno(turno);
            return new ResponseEntity<>(savedTurno, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


    }


}
