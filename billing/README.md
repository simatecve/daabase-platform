# Billing

Capa de abstracción de proveedor de pagos. Implementa la interfaz `billing/provider.clj` para permitir cambiar de proveedor sin tocar el resto del sistema.

## Estructura

```
billing/
├── provider.clj           # interfaz (prepare, redirect, confirm, ledger)
├── stub.clj               # implementación de desarrollo/tests
├── payphone.clj           # implementación PayPhone sandbox/prod
└── stripe_passthrough.clj # adaptador que delega al stripe.clj del fork
```

## Toggle

Usa la variable `BILLING_PROVIDER`:
- `stub` → usa `billing/stub.clj` (desarrollo)
- `payphone` → usa `billing/payphone.clj` (sandbox/prod)
- `stripe` → usa `billing/stripe_passthrough.clj` (delegación al upstream)

## Interfaz

```clojure
(defprotocol BillingProvider
  (prepare [this user-id plan-id] "Inicia sesión de pago, devuelve redirect URL")
  (redirect [this session-id] "Redirige al usuario al provider")
  (confirm [this session-id] "Confirma estado del pago")
  (ledger [this user-id] "Devuelve historial de pagos del usuario"))
```

## Desarrollo

```bash
# Usar stub
BILLING_PROVIDER=stub docker compose -f infra/docker-compose.yml up

# Usar PayPhone sandbox
BILLING_PROVIDER=payphone PAYPHONE_TOKEN=xxx PAYPHONE_STORE_ID=yyy docker compose -f infra/docker-compose.yml up
```