# FocusPilot API

API backend para resolver a dor de priorizacao diaria, foco e acompanhamento de tarefas. O objetivo e ajudar pessoas e equipes a decidir o que fazer agora com base em urgencia, prazo, carga de trabalho e risco.

## Proposta
- Prioridade diaria automatizada
- Alertas de atraso e sobrecarga
- Visao consolidada de tarefas por impacto
- Base para autenticacao, auditoria e historico
- Cadastro e consulta de tarefas
- Atualizacao de status
- Dashboard com resumo de foco

## Stack
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- OpenAPI
- PostgreSQL
- H2 para testes

## Estrutura
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

## Endpoints
- `GET /api/health`
- `POST /api/auth/login`
- `GET /api/tasks`
- `POST /api/tasks`
- `PATCH /api/tasks/{id}/status`
- `GET /api/dashboard`
- `GET /swagger-ui/index.html`

## Regras
- tarefas novas começam como `TODO`
- prioridade influencia a fila de trabalho
- tarefas vencidas e bloqueadas reduzem o foco
- o dashboard consolida o estado operacional do dia
- o login autentica o usuario `demo@focuspilot.dev` com a senha `focus123`
- em producao o backend usa PostgreSQL; em testes usa H2

## Como evoluir
1. adicionar dominio de tarefas e metas
2. adicionar service layer com regras de prioridade
3. adicionar JWT e controle por usuario
4. adicionar testes de integracao e documentacao OpenAPI

## LinkedIn
Projeto ideal para demonstrar engenharia de software aplicada a produtividade real, com foco em decisao, prioridade e acompanhamento.
