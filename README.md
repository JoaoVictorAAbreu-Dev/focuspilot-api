# FocusPilot API

API backend para priorizacao diaria, acompanhamento de tarefas e reducao de sobrecarga operacional.

## Objetivo
O sistema organiza tarefas por urgencia, prazo e risco, permitindo que o usuario entenda o que deve ser feito agora e o que pode ser adiado.

## Problema atendido
- tarefas importantes se perdem na rotina
- prioridades mudam sem visibilidade
- prazos estouram sem alertas claros
- equipes precisam de um resumo rapido do foco do dia

## Escopo funcional
- autenticacao com JWT
- cadastro e listagem de tarefas
- atualizacao de status
- dashboard com foco do dia
- indicador de atraso e sobrecarga

## Stack
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- OpenAPI
- PostgreSQL em producao
- H2 em testes

## Regras de negocio
- tarefas novas comecam como `TODO`
- prioridade influencia a fila de trabalho
- tarefas vencidas reduzem o foco
- o dashboard resume estado operacional, atraso e carga

## Credenciais de demo
- email: `demo@focuspilot.dev`
- senha: `focus123`

## Variaveis de ambiente
Use `.env.example` como base para:
- `DATABASE_URL`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`
- `JWT_SECRET`
- `JWT_EXPIRATION_MINUTES`

## Como executar
1. suba o PostgreSQL com `docker compose up -d`
2. configure as variaveis de ambiente
3. execute `mvn spring-boot:run`

## Como testar
```bash
mvn test
```

## Documentacao da API
- Swagger UI: `GET /swagger-ui/index.html`
- OpenAPI JSON: `GET /v3/api-docs`

## Endpoints principais
- `GET /api/health`
- `POST /api/auth/login`
- `GET /api/tasks`
- `POST /api/tasks`
- `PATCH /api/tasks/{id}/status`
- `GET /api/dashboard`

## Estrutura do projeto
```text
src/main/java/com/jv/productivityguard
  auth/
  config/
  dashboard/
  exception/
  security/
  task/
  user/
```

## Decisoes tecnicas
- controllers finos
- regras concentradas em services
- DTOs para entrada e saida
- tratamento centralizado de erros
- persistencia relacional com UUID para identificadores publicos

## Evolucao prevista
- metas semanais
- historico de produtividade
- filtros por projeto e contexto
- notificacoes de atraso
