# SisJur - Sistema de Agendamento Jurídico

Componente backend corporativo para gerenciamento e controle de consultas e agendamentos jurídicos, estruturado sobre o ecossistema Java EE (namespace clássico javax).

## Matriz de Compatibilidade Arquitetural

* **Ambiente de Execução (Runtime):** Java 21 (OpenJDK)
* **Alvo de Compilação (Source/Target):** Java 1.8 (Java 8)
* **Gerenciador de Build:** Maven 3.x
* **Servidor de Aplicação:** Apache Tomcat 9.x
* **Banco de Dados:** PostgreSQL 18.3
* **Mapeamento Objeto-Relacional (ORM):** Hibernate Core 5.6.15.Final
* **Framework MVC / Visão:** JSF 2.2.20 (Mojarra)
* **Componentes de UI:** PrimeFaces 13.0

## Inicialização do Ambiente Local

##### Camada de Persistência (Banco de Dados)
A aplicação estabelece conexão local via porta padrão 5432. Para provisionar a base de dados isolada no PostgreSQL, utilize o DBeaver ou terminal:

CREATE DATABASE sisjur_db;

## Status do Roadmap de Desenvolvimento
- [x] Provisionamento da infraestrutura local e alinhamento de banco de dados.
- [x] Configuração de autenticação remota segura via GitHub Personal Access Token (GHP).
- [x] Criação do primeiro commit estável e push da branch main.
- [x] Homologação e sincronização da matriz de dependências no pom.xml.
- [ ] Criação da arquitetura de pacotes Java corporativos (model, dao, controller, util).
- [ ] Configuração do arquivo central de persistência (hibernate.cfg.xml).
