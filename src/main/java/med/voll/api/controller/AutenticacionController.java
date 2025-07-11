package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DatosAutenticacion;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        try {
            var token = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
            var autenticacion = manager.authenticate(token);
            return ResponseEntity.ok(tokenService.generarToken((Usuario) autenticacion.getPrincipal()));
        } catch (Exception e) {
            System.out.println("❌ Error en autenticación: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
