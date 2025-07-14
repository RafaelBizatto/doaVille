
# 🌱 DoaVille - Plataforma de Doações Sustentáveis

> 🔄 Transformando doações em solidariedade e sustentabilidade.

---

## 🧭 Visão Geral

🎯 **DoaVille** é uma API REST construída com Java + Spring Boot, com o objetivo de conectar doadores e recebedores de itens como roupas, alimentos e utensílios. Seu foco é reduzir o desperdício e gerar impacto social positivo.

> Esta é a versão **MVP (Minimum Viable Product)** da aplicação, pronta para evoluir em funcionalidades e escalabilidade.

---

## ❓ Por que o DoaVille existe?

> 🗑️ **Problema**: Milhares de toneladas de itens reutilizáveis são descartadas anualmente, mesmo podendo ajudar outras pessoas.

> 💡 **Solução**: DoaVille oferece uma forma simples e segura de doar ou solicitar itens essenciais — tudo digitalmente.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia               | Finalidade                                   |
|--------------------------|----------------------------------------------|
| ☕ Java 21               | Linguagem principal                          |
| 🌱 Spring Boot 3        | Framework para desenvolvimento ágil          |
| 🔐 Spring Security + JWT| Autenticação e controle de acesso            |
| 🐘 PostgreSQL           | Banco de dados relacional                    |
| 📦 Gradle               | Gerenciador de dependências e build          |
| 🧰 Lombok               | Redução de boilerplate (getters/setters)     |
| ✅ Bean Validation       | Validações com anotações (@NotBlank, etc)   |
| 🔍 JUnit + Spring Test   | Testes automatizados                         |

---

## 📁 Estrutura do Projeto

```
📦 doaville
 ┣ 📂 src
 ┣ 📄 build.gradle
 ┣ 📄 settings.gradle
 ┣ 📄 gradlew / gradlew.bat
 ┗ 📄 README.md
```

✅ Projeto baseado em **Gradle** com suporte ao Spring Boot.

---

## ⚙️ Como Executar Localmente

### 📋 Pré-requisitos

- Java 21+
- PostgreSQL
- Git
- IDE (IntelliJ, Eclipse ou VSCode)

### 📦 Clonar o projeto

```bash
git clone https://github.com/seu-usuario/doaville.git
cd doaville
```

### 🧑‍💻 Configurar `application.properties`

No caminho: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/doaville
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
jwt.secret=suaChaveSuperSecreta
```

### ▶️ Rodar o projeto

```bash
./gradlew bootRun
```

Acesse a API em: `http://localhost:8080`

---

## 🔐 Endpoints Principais

| Método | Rota                  | Descrição                        | Proteção |
|--------|-----------------------|----------------------------------|----------|
| POST   | `/api/auth/login`     | Autenticar e gerar token JWT     | ❌       |
| POST   | `/api/usuarios`       | Cadastro de novo usuário         | ❌       |
| GET    | `/api/doacoes`        | Listar doações disponíveis       | ✅       |
| POST   | `/api/doacoes`        | Criar uma nova doação            | ✅       |
| POST   | `/api/solicitacoes`   | Solicitar item doado             | ✅       |
| GET    | `/api/solicitacoes`   | Ver solicitações feitas          | ✅       |

🔒 Rotas protegidas exigem **token JWT** no header:  
`Authorization: Bearer SEU_TOKEN`

---

## 🧠 Possíveis Melhorias Futuras

- [ ] 📸 Upload de imagem dos itens doados  
- [ ] 📨 Envio de e-mails automáticos  
- [ ] 📍 Geolocalização de doadores próximos  
- [ ] 🔄 Sistema de avaliação entre usuários  
- [ ] 📊 Dashboard administrativo

---

## 🤝 Contribuindo

1. Faça um fork do repositório  
2. Crie sua branch: `git checkout -b feature/nova-funcionalidade`  
3. Commit suas mudanças: `git commit -m "feat: nova funcionalidade"`  
4. Push: `git push origin feature/nova-funcionalidade`  
5. Abra um Pull Request 🙌

---

## 📄 Licença

Este projeto está sob a [licença MIT](LICENSE).

---

## ✍️ Autor

Desenvolvido com 💚 por **Rafael Sonni Bizatto**  
GitHub: [@RafaelBizatto](https://github.com/RafaelBizatto)
