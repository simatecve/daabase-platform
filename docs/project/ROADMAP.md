# ROADMAP

## Fase 0 — Auditoría
[x] Auditoría upstream y creación de UPSTREAM_AUDIT.md.

## Fase 1 — Baseline local
- [ ] Docker Compose dev levantando Postgres + server + dashboard
- [ ] Smoke test: crear app, query, transact, realtime
- [ ] OTP por stdout, MinIO local, migraciones aplicadas

## Fase 2 — VPS staging
- [ ] Elegir proveedor y tamaño
- [ ] DNS: cinco registros A (dash, api, files, admin, posthog)
- [ ] Caddy + TLS, server + dashboard + admin + Postgres + MinIO
- [ ] Aplicar sql/saas/*.sql (schema saas, columna is_admin)
- [ ] Backups automatizados, restore drill
- [ ] Smoke test completo en staging

## Fase 3 — Branding
- [ ] Logos, favicons, OG, copy, dominio en daabase2 (submódulo)
- [ ] Assets en daabase-landing/public/img/
- [ ] Atribución Apache 2.0 visible

## Fase 4 — Landing Netlify
- [ ] Hero, demo, beneficios, snippet, waitlist, /gracias, FAQ, atribucion, privacidad
- [ ] netlify.toml con dominio custom daabase.click
- [ ] Waitlist → POST /api/waitlist → saas.waitlist → Resend

## Fase 5 — Tracking
- [ ] PostHog self-hosted en posthog.daabase.click
- [ ] Proxy /a/* en dashboard y landing
- [ ] Instrumentar embudo landing_viewed → first_transaction_run

## Fase 6 — Onboarding
- [ ] email/sender.clj + stdout.clj (dev) + resend.clj (prod)
- [ ] OTP real con Resend
- [ ] Checklist, snippets por framework, demo realtime, quickstart

## Fase 7 — PayPhone
- [ ] billing/provider.clj interfaz
- [ ] billing/stub.clj (dev) + billing/payphone.clj (sandbox)
- [ ] Ledger, idempotencia, planes, sandbox

## Fase 8 — Beta cerrada
- [ ] 10-20 testers, capturar bloqueos, fix loop

## Fase 9 — Beta pública y Product Hunt
- [ ] Release gates, landing específica, video, UTMs, soporte