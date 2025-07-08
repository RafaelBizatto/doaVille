package br.com.futurodev.doaville.config.security;

import br.com.futurodev.doaville.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Injeta o valor da propriedade 'jwt.secret' do seu application.properties
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Gera um token JWT para o usuário fornecido.
     * @param usuario O usuário para o qual o token será gerado.
     * @return Uma string contendo o token JWT.
     */
    public String gerarToken(Usuario usuario) {
        try {
            // Define o algoritmo de assinatura usando a chave secreta
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("DoaVille API") // Emissor do token
                .withSubject(usuario.getNomeUsuario()) // Assunto do token (geralmente o login do usuário)
                .withExpiresAt(dataExpiracao()) // Define a data de expiração
                .sign(algoritmo); // Assina o token
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    /**
     * Valida um token JWT e retorna o "assunto" (subject) se o token for válido.
     * @param tokenJWT O token a ser validado.
     * @return O nome de usuário (subject) contido no token.
     */
    public String getSubject(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                .withIssuer("DoaVille API") // Verifica se o emissor é o mesmo
                .build()
                .verify(tokenJWT) // Valida o token (lança exceção se for inválido)
                .getSubject(); // Retorna o subject
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    /**
     * Calcula a data de expiração do token (2 horas a partir de agora).
     * @return Um Instant representando o momento da expiração.
     */
    private Instant dataExpiracao() {
        // Define o fuso horário de Brasília
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}