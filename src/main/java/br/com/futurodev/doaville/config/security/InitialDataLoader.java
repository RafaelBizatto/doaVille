package br.com.futurodev.doaville.config.security;

import br.com.futurodev.doaville.model.Usuario;
import br.com.futurodev.doaville.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Garante que o código execute apenas uma vez
        if (alreadySetup) {
            return;
        }

        // Verifica se o usuário 'admin' já existe para não criar duplicidade
        if (usuarioRepository.findByNomeUsuario("admin").isEmpty()) {
            System.out.println("Usuário 'admin' não encontrado. Criando usuário padrão...");

            Usuario adminUser = new Usuario();
            adminUser.setNome("Administrador Padrão");
            adminUser.setNomeUsuario("admin");
            adminUser.setSenha(passwordEncoder.encode("admin"));
            adminUser.setPerfil(Usuario.Perfil.ADMIN);
            usuarioRepository.save(adminUser);

            System.out.println("Usuário padrão 'admin' criado com sucesso.");
        }

        alreadySetup = true;
    }
}