# WORKLOG

## 2026-06-11 — Fase 1 (continuación)

**Objetivo**
Continuar instalación de dependencias y levantar baseline local.

**Rama**
`chore/phase-01-baseline-local` en `daabase-platform`

**Cambios**
- Instalado Docker Desktop 29.5.3 (componente, daemon no inicia)
- Instalado Java Corretto 26.0.1
- Instalado Clojure 1.11.4 (Maven Central, JAR directo)
- Instalado spec.alpha 0.3.218 (dep de Clojure 1.11+)
- Instalado golang-migrate 4.17.0
- Creados wrappers `clojure.bat` y `clj.bat` en `C:\Users\joeld\bin\`
- `git submodule add -b main https://github.com/simatecve/daabase2.git instant`
- Commit `ed43f2e`: pin instant submodule to d278d843
- Actualizado `PHASE_1_ENV_AUDIT.md`, `STATUS.md`

**Comandos ejecutados**
- `docker --version` (29.5.3 instalado)
- `winget install` para Corretto (no encontrado en source)
- `msiexec /i corretto-26.msi /quiet` (instalación silenciosa OK)
- `java -version` (openjdk 26.0.1)
- `curl ... clojure-1.11.4.jar` desde Maven Central
- `java -cp clojure-1.11.4.jar clojure.main` (probado: OK con Java 26)
- `git submodule add` para el fork
- `git submodule status` (d278d843 pinned)
- `git commit -m "chore(submodule): pin instant..."` 
- `docker info` → ERROR Docker Desktop is unable to start
- `wsl --status` → WSL no instalado

**Pruebas**
- `java -cp clojure-1.11.4.jar clojure.main test.clj` → imprime "hello from 1.11.4" ✓
- `migrate.exe --version` → 4.17.0 ✓
- `docker compose -f docker-compose-dev.yml up -d` → error: daemon no inicia
- `git submodule status` → d278d843 ✓

**Resultado**
Todas las herramientas instaladas y operativas, excepto Docker daemon. Bloqueo por WSL2/Hyper-V.

**Riesgos**
- WSL2 requiere elevación y reinicio
- Hyper-V requiere elevación y reinicio
- Docker Toolbox es legacy y no recomendado

**Siguiente paso**
1. Usuario ejecuta `wsl --install` desde PowerShell admin
2. Reinicia Windows
3. `wsl --set-default-version 2`
4. `docker info` debería funcionar
5. Levantar compose

## 2026-06-11 — Fase 1 (inicio)

**Objetivo**
Iniciar Fase 1 — Baseline local

**Rama**
`chore/phase-01-baseline-local` en `daabase-platform`

**Cambios**
- Creada rama
- `docs/project/PHASE_1_ENV_AUDIT.md` creado
- Bloqueo inicial por entorno local

**Comandos ejecutados**
- `git checkout -b chore/phase-01-baseline-local`
- `git commit && git push -u origin chore/phase-01-baseline-local`