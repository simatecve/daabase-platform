# RISKS
| Riesgo | Impacto | Mitigación |
|---|---|---|
| Romper upstream | Alto | Cambios mínimos, solo superficie visible |
| Exponer secretos | Crítico | .env.example, GitHub Secrets, permisos 600 |
| Producción como sandbox | Crítico | Staging separado, VPS propio |
| Billing inconsistente | Alto | Ledger, idempotencia, stub en dev |
| Sin restore probado | Crítico | Restore drill semanal |
| Submódulo desincronizado | Medio | Workflow submodule-sync.yml |
| Token GH sin alcance crear repos | Alto | Crear repos manualmente |
| Resend cae | Medio | stdout.clj como fallback |
| PostHog self-hosted | Medio | VPS aparte, 2-4 GB RAM extra |
| Apache 2.0 sin atribución | Crítico | Sección atribucion visible |