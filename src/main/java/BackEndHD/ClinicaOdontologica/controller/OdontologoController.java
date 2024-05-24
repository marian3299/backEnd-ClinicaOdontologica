package BackEndHD.ClinicaOdontologica.controller;

import BackEndHD.ClinicaOdontologica.model.Odontologo;
import BackEndHD.ClinicaOdontologica.service.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {
    OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService = new OdontologoService();
    }

    @GetMapping
    public String buscarOdontPorId(Model model, @RequestParam("id") Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "odontologos";
    }
}
