package BackEndHD.ClinicaOdontologica.controller;

import BackEndHD.ClinicaOdontologica.model.Odontologo;
import BackEndHD.ClinicaOdontologica.model.Paciente;
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
    public Odontologo buscarOdontPorId(@PathVariable("id") Integer id){
        return odontologoService.buscarPorId(id);
    }

    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @DeleteMapping("/{id}")
    public String eliminarOdontologo(@PathVariable("id") Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);

        if(odontologo != null){
            odontologoService.eliminarOdontologo(id);
            return "Odontologo eliminado con exito";
        }else{
            return "Odontologo no encontrado";
        }

    }

    @PutMapping
    public String actualizarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());

        if(odontologoBuscado != null){
            odontologoService.actualizarOdontologo(odontologo);
            return "Odontologo actualizado con exito";
        }else{
            return "Odontologo no encontrado";
        }
    }
}
