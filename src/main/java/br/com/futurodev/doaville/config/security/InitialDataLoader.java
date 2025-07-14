package br.com.futurodev.doaville.config.security;

import br.com.futurodev.doaville.model.Usuario;
import br.com.futurodev.doaville.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialDataLoader {

    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Verifica se o usuário 'admin' já existe para não criar duplicidade
            if (usuarioRepository.findByNomeUsuario("admin").isEmpty()) {
                Usuario adminUser = new Usuario();
                adminUser.setNome("Administrador Padrão");
                adminUser.setNomeUsuario("admin");
                adminUser.setSenha(passwordEncoder.encode("admin")); // Criptografa a senha "admin"
                adminUser.setPerfil(Usuario.Perfil.ADMIN);
                usuarioRepository.save(adminUser);
                System.out.println("Usuário padrão 'admin' criado na inicialização.");
            }
        };
    }
}
