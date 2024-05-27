package BackEndHD.ClinicaOdontologica.controller;

import BackEndHD.ClinicaOdontologica.model.Paciente;
import BackEndHD.ClinicaOdontologica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService = new PacienteService();
    }

    @GetMapping("/{email}")
    public String buscarPacientePorCorreo(Model model, @PathVariable("email") String email){
        Paciente paciente = pacienteService.buscarPorEmail(email);
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "index"; //En postman no nos va a devolver el html porque cambiamos a RestController para poder probar en postman
    }
}
