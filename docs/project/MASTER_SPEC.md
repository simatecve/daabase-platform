# MASTER SPEC

## Propuesta
Backend reactivo y self-hostable para crear aplicaciones con IA.Marca `daabase`, dominio `daabase.click`.

## Arquitectura MVP
```
Netlify
  └── daabase.click (landing, waitlist)

VPS backend (Caddy + Docker)
  ├── dash.daabase.click  → dashboard Next.js
  ├── admin.daabase.click → panel admin Next.js
  ├── api.daabase.click   → server Clojure JVM :8888
  ├── files.daabase.click → MinIO :9000
  └── Postgres + MinIO

VPS PostHog
  └── posthog.daabase.click → PostHog self-hosted
```

## Embudo
`landing_viewed → waitlist_joined → signup_completed → app_created → first_query_run → first_transaction_run → payment_started → payment_confirmed`

## Tres repositorios
- `simatecve/daabase2` — fork upstream, sin infra
- `simatecve/daabase-landing` — landing Netlify
- `simatecve/daabase-platform` — control + infra + admin + billing

## Billing
Abstracción `billing/provider.clj` con implementaciones `stub`, `payphone`, `stripe_passthrough`. Toggle por `BILLING_PROVIDER`.

## Email
Abstracción `email/sender.clj` con implementaciones `stdout` (dev) y `resend` (prod). Cuatro remitentes: hola@, verify@, teams@, billing@.