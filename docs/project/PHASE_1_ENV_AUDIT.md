# PHASE 1 ENVIRONMENT AUDIT

> Auditoría del entorno local necesaria para Fase 1 (Baseline local).
> Rama: `chore/phase-01-baseline-local` en `daabase-platform`.

## Requisitos del fork (InstantDB server)

- Docker (Docker Desktop o docker engine en Linux)
- Docker Compose
- Java 26 (Amazon Corretto 26 según `instant/server/Dockerfile`)
- Clojure 1.11+ (instalable via linux-install.sh o brew)
- golang-migrate v4.17.0 (para `make dev-up` / `make dev-down`)
- Node 22.x
- pnpm 10.x
- PostgreSQL 16 con `pg_hint_plan` (vía imagen `ghcr.io/instantdb/postgresql:postgresql-16-pg-hint-plan`)

## Estado del entorno local (snapshot 2026-06-11)

| Requisito | Estado | Notas |
|---|---|---|
| Docker | ✗ No instalado | No se puede usar `infra/docker-compose.dev.yml` |
| Docker Compose | ✗ No instalado | Idem |
| Java | ✗ v17.0.16 instalado | Fork requiere Java 26 (Amazon Corretto 26) |
| Clojure | ✗ No instalado | Necesario para `make dev` sin Docker |
| golang-migrate | ✗ No instalado | Necesario para `make dev-up` / `make dev-down` |
| Node | ✓ v22.14.0 | Cumple con `engines.node: 22.x` |
| pnpm | ✓ 10.17.1 | Cumple con `packageManager: pnpm@10.2.0` |

## Bloqueo

Fase 1 no se puede ejecutar en este entorno sin instalar Docker Desktop y Java 26. Decisión necesaria: continuar con instalación local o saltar a Fase 2 (VPS staging, donde el entorno Docker está garantizado).

## Pasos pendientes para Fase 1

1. Instalar Docker Desktop desde https://www.docker.com/products/docker-desktop/
2. Reiniciar y verificar `docker --version`
3. `cd daabase-platform && git submodule update --init --remote instant`
4. `docker compose -f infra/docker-compose.dev.yml up`
5. Esperar a que `server` (puerto 8888) y `www` (puerto 3000) estén healthy
6. Abrir http://localhost:3000
7. `localStorage.setItem('devBackend', true)` en DevTools
8. Click "Sign up" con email cualquiera
9. Backend imprime código de 6 dígitos en stdout del contenedor
10. Copiar código en la UI
11. Crear app, copiar `appId`
12. Crear sandbox React en `instant/client/sandbox/react-nextjs/`
13. Configurar `.env` con `VITE_PUBLIC_APP_ID=<appId>`
14. Levantar sandbox, probar query y transact
15. Documentar evidencia en `WORKLOG.md`

## Comandos de fallback (sin Docker)

Si solo se quiere validar el código y las rutas sin levantar el backend:

```bash
cd daabase-platform/instant
git status
git log --oneline -1
ls client/packages/ server/src/instant/reactive/ self-hosting/
```

Esto valida que el submódulo se pobló correctamente desde `simatecve/daabase2`.