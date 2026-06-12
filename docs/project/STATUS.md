# STATUS

## Fase activa
`Fase 1 — Baseline local`

## Estado
`Bloqueado por WSL2/Hyper-V ausentes. Docker Desktop instalado pero daemon no inicia. Java 26, Clojure, golang-migrate, submódulo OK.`

## Rama esperada
`chore/phase-01-baseline-local` en `daabase-platform`

## Próximo paso
1. Usuario: `wsl --install` desde PowerShell admin + reiniciar Windows
2. O habilitar Hyper-V desde características de Windows + reiniciar
3. Luego: `docker info` + levantar compose

## Repos en GitHub
- https://github.com/simatecve/daabase2 (fork InstantDB, branch main + chore/phase-00-audit)
- https://github.com/simatecve/daabase-landing (Next.js + Tailwind, branch master)
- https://github.com/simatecve/daabase-platform (infra + admin + billing + docs, branch master + chore/phase-01-baseline-local, submódulo del fork)