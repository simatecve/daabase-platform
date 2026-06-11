# AGENTS.md — daabase platform

## Misión
Este repositorio es el centro de control del miniSaaS `daabase`. Contiene la infraestructura, el panel admin, la capa de billing, y los archivos de control del proyecto. Consume `simatecve/daabase2` como submódulo.

El MVP debe permitir que una persona cree una cuenta, cree un proyecto, copie su `appId`, conecte una app React, ejecute una consulta, ejecute una transacción y observe sincronización realtime.

## Fase activa
Trabaja únicamente en la fase indicada en `docs/project/STATUS.md`. No avances automáticamente a otra fase aunque termines antes.

## Núcleo heredado que debes proteger
Durante el MVP no reescribas ni renombres masivamente:
- motor de sincronización del fork en `instant/server/src/instant/reactive/`;
- capa `reactive`;
- query engine;
- invalidación basada en WAL;
- triple store;
- permisos;
- migraciones;
- variables internas `INSTANT_*`.

Antes de modificar código heredado, inspecciona la implementación real, explica el riesgo y propone el cambio mínimo.

## Alcance MVP
Incluido:
- baseline local reproducible;
- VPS staging;
- branding visible en dashboard y landing;
- atribución Apache 2.0;
- landing Netlify;
- formulario early access;
- tracking con PostHog self-hosted;
- OTP real con Resend;
- onboarding guiado;
- demo realtime;
- integración PayPhone beta;
- beta cerrada;
- beta pública limitada;
- Product Hunt con producto utilizable.

Excluido por ahora:
- reescritura del sync engine;
- multi-región;
- alta disponibilidad empresarial;
- billing recurrente complejo;
- renombrado completo de paquetes npm;
- marketplace;
- promesas de SLA.

## Seguridad
- Nunca leas, muestres, copies ni commitees secretos.
- No abras `.env` con valores reales.
- Usa solo plantillas `.env.example`.
- No uses producción como sandbox.
- No despliegues automáticamente a producción.
- No ejecutes `git push`, `git reset --hard`, `git clean`, `rm -rf`, `sudo`, `ssh`, `scp`, `rsync`, eliminación de volúmenes o cambios DNS sin autorización humana explícita.
- PayPhone `TOKEN` y `STOREID` solo pueden existir en servidor.
- Resend API key solo en servidor y GitHub Secrets.

## Git
- Rama por fase.
- Commits pequeños.
- No mezcles branding, infraestructura, tracking y pagos en el mismo commit.
- Mantén remote `upstream` en `instant/` hacia `https://github.com/instantdb/instant.git`.
- Antes de cada build: `git submodule update --remote instant`.
- Revisa `git status` antes de editar.
- Ejecuta pruebas y registra evidencia después de editar.

## Legal
- Conserva `LICENSE.md` del fork en `instant/`.
- Mantén avisos aplicables.
- Actualiza `MODIFICATIONS.md`.
- Publica atribución visible en `daabase.click/atribucion` y footer del dashboard.
- No uses nombre, logo ni diseño de InstantDB como identidad propia.

## Métrica principal
La métrica central de activación es `first_transaction_run`.

## Archivos de control
Actualiza al cerrar cada sesión:
- `docs/project/STATUS.md`
- `docs/project/WORKLOG.md`
- `docs/project/DECISIONS.md`
- `docs/project/RISKS.md`
- `docs/project/TASK_QUEUE.md`
- `MODIFICATIONS.md`

## Cierre de sesión
Entrega:
1. Rama actual.
2. Fase trabajada.
3. Archivos modificados.
4. Comandos ejecutados.
5. Pruebas y resultados.
6. Riesgos o bloqueos.
7. Siguiente paso exacto.
8. Confirmación de que no avanzaste fuera de la fase.