# TASK QUEUE

## Fase 0
- [x] Crear rama `chore/phase-00-audit`
- [x] Confirmar remotes (origin=daabase2, upstream=instantdb/instant)
- [x] Auditar estructura
- [x] Mapear comandos
- [x] Crear `UPSTREAM_AUDIT.md`
- [ ] Crear `simatecve/daabase-landing` y `simatecve/daabase-platform` en github.com/new
- [ ] Push daabase2 `chore/phase-00-audit` a origin
- [ ] Push daabase-platform y daabase-landing

## Fase 1 — Baseline local
- [ ] `git submodule update --remote instant`
- [ ] `docker compose -f infra/docker-compose.dev.yml up`
- [ ] Smoke test query + transact
- [ ] Validar OTP stdout, MinIO, migraciones