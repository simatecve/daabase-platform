# daabase-platform

Repositorio de control del miniSaaS `daabase`. Contiene infraestructura, panel admin, capa de billing, email y archivos de control del proyecto.

## Estructura

```
daabase-platform/
├── instant/                 # submódulo -> simatecve/daabase2
├── admin/                   # panel admin Next.js
├── billing/                 # abstracción de proveedor + PayPhone
├── email/                   # interfaz de email + adapters
├── infra/                   # Docker Compose, Caddy, scripts
├── sql/saas/                # migraciones del schema SaaS
├── docs/project/            # archivos de control
└── .github/workflows/       # CI/CD
```

## Primeros pasos

```bash
# Clonar con submódulo
git clone --recurse-submodules https://github.com/simatecve/daabase-platform.git
cd daabase-platform

# Actualizar submódulo
git submodule update --remote instant

# Desarrollo local
docker compose -f infra/docker-compose.dev.yml up

# Ver documentación
# docs/project/UPSTREAM_AUDIT.md
# docs/project/STATUS.md
```

## Dominio

- Landing: https://daabase.click (Netlify)
- Dashboard: https://dash.daabase.click
- Admin: https://admin.daabase.click
- API: https://api.daabase.click
- Files: https://files.daabase.click
- PostHog: https://posthog.daabase.click