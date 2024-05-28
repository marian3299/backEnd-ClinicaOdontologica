package BackEndHD.ClinicaOdontologica.controller;

import BackEndHD.ClinicaOdontologica.model.Paciente;
import BackEndHD.ClinicaOdontologica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService = new PacienteService();
    }

    @GetMapping
    @ResponseBody
    public List<Paciente> buscarTodos(){
        return pacienteService.buscarTodos();
    }

    @GetMapping("/buscarId/{id}")
    public Paciente buscarPorId(@PathVariable("id") Integer id){
        return pacienteService.buscarPorID(id);
    }

    @GetMapping("/buscarEmail/{email}")
    public Paciente buscarPacientePorCorreo(@PathVariable("email") String email){
        return pacienteService.buscarPorEmail(email); //En postman no nos va a devolver el html porque cambiamos a RestController para poder probar en postman
    }

    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteBuscado = pacienteService.buscarPorID(paciente.getId());

        if(pacienteBuscado != null){
            pacienteService.actualizarPaciente(paciente);
            return "Paciente actualizado con exito";
        }else {
            return "Paciente no encontrado";
        }
    }
}
