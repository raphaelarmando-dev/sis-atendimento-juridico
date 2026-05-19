# SisJur - Sistema de Agendamento Jurídico

Componente backend corporativo para gerenciamento e controle de consultas e agendamentos jurídicos, estruturado sobre o ecossistema Java EE (namespace clássico javax).

## Matriz de Compatibilidade Arquitetural

* **Ambiente de Execução (Runtime):** Java 21 (OpenJDK)
* **Alvo de Compilação (Source/Target):** Java 1.8 (Java 8)
* **Gerenciador de Build:** Maven 3.x
* **Servidor de Aplicação:** Apache Tomcat 9.x
* **Banco de Dados:** PostgreSQL 18.3
* **Mapeamento Objeto-Relacional (ORM):** JPA 2.2 / Hibernate Core 5.6.15.Final
* **Framework MVC / Visão:** JSF 2.2.20 (Mojarra)
* **Componentes de UI:** PrimeFaces 13.0

## Inicialização do Ambiente Local

##### 1 - Camada de Persistência (Banco de Dados)

A aplicação estabelece conexão local via porta padrão 5432. Para provisionar a base de dados isolada no PostgreSQL, utilize o DBeaver ou terminal:

CREATE DATABASE sisjur_db;

##### 2 - Ajuste de Credenciais (Java/JPA)

1. Abra o arquivo de configuração localizado em:<br />
`src/main/resources/META-INF/persistence.xml`

2. Altere as seguintes propriedades de acordo com a sua instalação local do Postgres:<br />
• **Usuário (Padrão):** `postgres` *(na linha 14)*<br />
• **Senha:** Altere o `value="root"` *(na linha 15)* para a senha definida no seu servidor local.

## Status do Roadmap de Desenvolvimento
- [x] Provisionamento da infraestrutura local e alinhamento de banco de dados.
- [x] Configuração de autenticação remota segura via GitHub Personal Access Token (GHP).
- [x] Criação do primeiro commit estável e push da branch main.
- [x] Homologação e sincronização da matriz de dependências no pom.xml.
- [x] Criação da arquitetura de pacotes Java corporativos (model, dao, bean, util).
- [x] Configuração do arquivo central de persistência da JPA (persistence.xml).
- [x] Implementação de classe temporária de testes (Main) e validação com sucesso da conexão com o PostgreSQL.
- [ ] Desenvolvimento das classes do modelo e mapeamento de entidades (pacote model).
- [ ] Desenvolvimento da camada de persistência de dados (AdvogadoDAO, ClienteDAO, AgendamentoDAO).
- [ ] Implementação dos Managed Beans (camada de controle do JSF) para gerenciamento das regras de tela.
- [ ] Construção das interfaces gráficas em XHTML utilizando componentes PrimeFaces.
