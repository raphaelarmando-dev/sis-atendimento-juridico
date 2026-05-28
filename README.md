# SisJur - Sistema de Agendamento Jurídico

Componente backend corporativo para gerenciamento e controle de consultas e agendamentos jurídicos, estruturado sobre o ecossistema Java EE (namespace clássico javax).

## Preview

<img width="1849" height="966" alt="image" src="https://github.com/user-attachments/assets/cdc9ac86-4fac-4f2c-b360-368f2c9c4e60" />

----
<img width="1849" height="966" alt="image" src="https://github.com/user-attachments/assets/cb984032-f7c9-41f4-a72f-808728dfb9da" />

----

<img width="1849" height="966" alt="image" src="https://github.com/user-attachments/assets/fac605e5-6b23-497f-a1b2-fe633f6b2caa" />



## Matriz de Compatibilidade Arquitetural

* **Ambiente de Execução (Runtime):** Java 21 (OpenJDK)
* **Alvo de Compilação (Source/Target):** Java 1.8 (Java 8)
* **Gerenciador de Build:** Maven 3.x
* **Servidor de Aplicação:** Apache Tomcat 9.x
* **Banco de Dados:** PostgreSQL 15 (Docker) / 18.3 (local)
* **Mapeamento Objeto-Relacional (ORM):** JPA 2.2 / Hibernate Core 5.6.15.Final
* **Framework MVC / Visão:** JSF 2.2.20 (Mojarra)
* **Componentes de UI:** PrimeFaces 13.0

## Inicialização do Ambiente Local

##### Opção 1 — Docker (recomendado)

Suba o PostgreSQL com um único comando na raiz do projeto:

    docker-compose up -d

O banco `sisjur_db` será criado automaticamente com usuário `postgres` e senha `root` na porta `5432`.

##### Opção 2 — PostgreSQL local

##### 1 - Camada de Persistência (Banco de Dados)

A aplicação estabelece conexão local via porta padrão 5432. Para provisionar a base de dados isolada no PostgreSQL, utilize o DBeaver ou terminal:

    CREATE DATABASE sisjur_db;

##### 2 - Ajuste de Credenciais (Java/JPA)

1. Abra o arquivo de configuração localizado em:<br />
`src/main/resources/META-INF/persistence.xml`

2. Altere as seguintes propriedades de acordo com a sua instalação local do Postgres:<br />
• **Usuário (Padrão):** `postgres` *(na linha 14)*<br />
• **Senha:** Altere o `value="root"` *(na linha 15)* para a senha definida no seu servidor local.

## Compilando e Executando

1. Clone o repositório:<br />
`git clone https://github.com/raphaelarmando-dev/sis-atendimento-juridico.git`

2. Compile o projeto com Maven:<br />
`mvn clean package`

3. Copie o `.war` gerado para a pasta `webapps` do Tomcat:<br />
`cp target/sis-atendimento-juridico.war $CATALINA_HOME/webapps/`

4. Inicie o Tomcat e acesse:<br />
`http://localhost:8080/sis-atendimento-juridico/pages/agendamento.xhtml`

## Estrutura do Projeto

    src/
    ├── main/
    │   ├── java/br/com/sisjur/
    │   │   ├── bean/        # ManagedBeans (AgendamentoBean, ClienteBean, AdvogadoBean)
    │   │   ├── dao/         # Camada de persistência (AgendamentoDAO, ClienteDAO, AdvogadoDAO)
    │   │   ├── model/       # Entidades JPA (Agendamento, Cliente, Advogado)
    │   │   └── util/        # JPAUtil - fábrica do EntityManager
    │   ├── resources/
    │   │   └── META-INF/
    │   │       └── persistence.xml
    │   └── webapp/
    │       ├── pages/       # Páginas XHTML
    │       │   ├── agendamento.xhtml
    │       │   ├── cliente.xhtml
    │       │   └── advogado.xhtml
    │       ├── WEB-INF/
    │       │   └── templates/
    │       │       └── template.xhtml
    │       └── resources/
    │           └── css/
    │               └── style.css

## Status do Roadmap de Desenvolvimento

- [x] Provisionamento da infraestrutura local e alinhamento de banco de dados.
- [x] Configuração de autenticação remota segura via GitHub Personal Access Token (GHP).
- [x] Criação do primeiro commit estável e push da branch main.
- [x] Homologação e sincronização da matriz de dependências no pom.xml.
- [x] Criação da arquitetura de pacotes Java corporativos (model, dao, bean, util).
- [x] Configuração do arquivo central de persistência da JPA (persistence.xml).
- [x] Criação e mapeamento da primeira entidade do sistema (Advogado no pacote model).
- [x] Implementação de classe temporária de testes (Main) e validação com sucesso da conexão com o PostgreSQL e inserção de dados.
- [x] Refatoração: Criação da classe abstrata Pessoa e validação da herança em Advogado via testes na Main.
- [x] Criação e mapeamento da entidade Cliente (Herdando de Pessoa) + Teste de inserção na Main.
- [x] Criação e mapeamento do Enum TipoAgendamento e da entidade Agendamento + Teste na Main.
- [x] Organizar estrutura de pastas (mover Main para archive).
- [x] Implementação da camada de persistência: AdvogadoDAO e testes de integração.
- [x] Implementação da camada de persistência: ClienteDAO e testes de integração.
- [x] Implementação da camada de persistência: AgendamentoDAO e testes de integração.
- [x] Implementação dos Managed Beans (camada de controle do JSF) para gerenciamento das regras de tela.
    - [x] AdvogadoBean implementado.
    - [x] ClienteBean implementado.
    - [x] AgendamentoBean implementado.
- [x] Refatoração: Processo de fixes baseados na interface gráfica.
- [x] Construção das interfaces gráficas em XHTML utilizando componentes PrimeFaces.
    - [x] agendamento.xhtml implementado.
    - [x] cliente.xhtml implementado.
    - [x] advogado.xhtml implementado.
- [x] Adição de navegação lateral (sidebar) para acesso às telas de Agendamentos, Clientes e Advogados.
- [x] Containerização do banco de dados via Docker Compose.

## Desenvolvido por

Raphael Armando - 2026
