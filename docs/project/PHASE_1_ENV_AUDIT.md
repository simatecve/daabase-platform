# PHASE 1 ENVIRONMENT AUDIT

> Auditoría del entorno local para Fase 1 (Baseline local).
> Rama: `chore/phase-01-baseline-local` en `daabase-platform`.

## Requisitos del fork (InstantDB server)

- Docker (Docker Desktop o docker engine en Linux)
- Docker Compose
- Java 26 (Amazon Corretto 26 según `instant/server/Dockerfile`)
- Clojure 1.11+ (compatible con Java 26)
- golang-migrate v4.17.0
- Node 22.x
- pnpm 10.x
- PostgreSQL 16 con `pg_hint_plan` (vía imagen `ghcr.io/instantdb/postgresql:postgresql-16-pg-hint-plan`)

## Estado del entorno local (snapshot 2026-06-11)

| Requisito | Estado | Notas |
|---|---|---|
| Docker Desktop | ✓ Instalado (29.5.3) | Componente instalado pero **daemon no inicia** |
| Docker Compose | ✓ Disponible (5.1.4) | Depende del daemon |
| Docker Daemon | ✗ **No arranca** | Requiere WSL2 o Hyper-V, ambos no disponibles |
| WSL2 | ✗ No instalado | Requiere `wsl --install` + admin + reinicio |
| Hyper-V | ✗ No habilitado | Requiere admin + reinicio |
| Java | ✓ v26.0.1 (Corretto) | Instalado en `C:\Program Files\Amazon Corretto\jdk26.0.1_8` |
| Clojure | ✓ v1.11.4 | Wrapper en `C:\Users\joeld\bin\clojure.bat` |
| spec.alpha | ✓ v0.3.218 | Necesario para Clojure 1.11+ |
| golang-migrate | ✓ v4.17.0 | `C:\Users\joeld\bin\migrate.exe` |
| Node | ✓ v22.14.0 | Cumple con `engines.node: 22.x` |
| pnpm | ✓ 10.17.1 | Cumple con `packageManager: pnpm@10.2.0` |
| Submódulo instant | ✓ Poblado | `d278d843` desde `simatecve/daabase2` |

## Bloqueo

Docker Desktop instalado pero el daemon no puede iniciar porque WSL2 y Hyper-V no están disponibles. Ambos requieren elevación de administrador y reinicio del sistema, lo cual está fuera del alcance de esta sesión.

## Solución

Opción A (recomendada): Instalar WSL2 + Ubuntu desde PowerShell como administrador:
```powershell
wsl --install
# Reiniciar el sistema
wsl --set-default-version 2
wsl --install -d Ubuntu
```

Opción B: Habilitar Hyper-V desde "Activar o desactivar características de Windows":
- Panel de control → Programas → Activar características → Hyper-V → Reiniciar

## Pasos ya completados

1. ✓ `git submodule add -b main https://github.com/simatecve/daabase2.git instant`
2. ✓ Commit `ed43f2e`: pin instant submodule to d278d843
3. ✓ Java 26.0.1 (Corretto) instalado
4. ✓ Clojure 1.11.4 + spec.alpha 0.3.218 instalados
5. ✓ golang-migrate 4.17.0 instalado
6. ✓ Docker Desktop 29.5.3 instalado
7. ✗ Docker daemon no inicia (bloqueado por WSL2/Hyper-V)

## Pasos pendientes (requieren reinicio del sistema)

1. Instalar WSL2: `wsl --install` (PowerShell admin)
2. Reiniciar Windows
3. `wsl --set-default-version 2`
4. `docker info` para verificar daemon
5. `cd daabase-platform/instant/server && docker compose -f docker-compose-dev.yml up -d`
6. Verificar `:8888` (backend) y `:3000` (frontend) (requiere `make dev` para frontend)
7. Crear app, query, transact
8. Documentar evidencia

## Comando de fallback (sin Docker)

Si se quiere validar el código y las rutas sin levantar el backend:
```bash
cd daabase-platform/instant
git status   # debe mostrar clean (estamos en d278d843)
ls client/packages/ server/src/instant/reactive/ self-hosting/
```

Esto valida que el submódulo está correctamente poblado.

## Estado de dependencias externas

- `simatecve/daabase2` (fork): HEAD `d278d843` en rama `main` + `chore/phase-00-audit` (idéntico a main)
- Submódulo en `daabase-platform` apunta a `d278d843` (commit exacto)
- Apache 2.0 LICENSE preservado
- Todos los SDKs en `instant/client/packages/{core,react,react-native,admin,cli,platform,...}` presentes
- Server Clojure en `instant/server/src/instant/{reactive,runtime,db,auth,storage,...}` presente