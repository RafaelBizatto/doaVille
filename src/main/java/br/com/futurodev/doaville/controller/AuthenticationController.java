package br.com.futurodev.doaville.controller;

import br.com.futurodev.doaville.config.security.TokenService;
import br.com.futurodev.doaville.dto.LoginDTO;
import br.com.futurodev.doaville.dto.TokenDTO;
import br.com.futurodev.doaville.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login") // <-- ALTERAÇÃO AQUI
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager; // Gerenciador de autenticação do Spring

    @Autowired
    private TokenService tokenService; // Nosso serviço de token

    @PostMapping
    public ResponseEntity<TokenDTO> efetuarLogin(@RequestBody @Valid LoginDTO dados) {
        // Cria um token de autenticação com usuário e senha
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getNomeUsuario(), dados.getSenha());
        
        // O Spring Security usa o 'manager' para autenticar o usuário
        var authentication = manager.authenticate(authenticationToken);

        // Se a autenticação foi bem-sucedida, gera o token JWT
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // Retorna o token para o cliente
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}