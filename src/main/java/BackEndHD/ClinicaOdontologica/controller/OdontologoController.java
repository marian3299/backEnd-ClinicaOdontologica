package BackEndHD.ClinicaOdontologica.controller;

import BackEndHD.ClinicaOdontologica.model.Odontologo;
import BackEndHD.ClinicaOdontologica.service.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService = new OdontologoService();
    }

    @GetMapping
    @ResponseBody
    public List<Odontologo> buscarTodos(){
        return odontologoService.listarTodos();
    }

    @GetMapping("/{id}")
    public String buscarOdontPorId(Model  model, @PathVariable("id") Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "odontologos";
    }

    @PostMapping("/guardar")
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }
}
