package BackEndHD.ClinicaOdontologica.controller;

import BackEndHD.ClinicaOdontologica.model.Paciente;
import BackEndHD.ClinicaOdontologica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService = new PacienteService();
    }

    @GetMapping
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email){
        Paciente paciente = pacienteService.buscarPorEmail(email);
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "index";
    }
}
