# Email

Capa de abstracción de envío de email. Implementa la interfaz `email/sender.clj` para poder cambiar de proveedor sin tocar el resto del sistema.

## Estructura

```
email/
├── sender.clj     # interfaz
├── stdout.clj     # implementación de desarrollo (imprime en consola)
└── resend.clj     # implementación de producción (Resend API)
```

## Toggle

Usa la variable `EMAIL_PROVIDER`:
- `stdout` → desarrollo, imprime HTML en consola
- `resend` → producción, envía via Resend API

## Interfaz

```clojure
(defprotocol EmailSender
  (send! [this opts] "Envía un email. opts = {:to :subject :html :from}")
  (send-magic-code! [this email code] "Envía magic code OTP")
  (send-waitlist-confirmation! [this email] "Confirma inscripción al waitlist"))
```

## Desarrollo

```bash
EMAIL_PROVIDER=stdout docker compose -f infra/docker-compose.yml up
# Los emails se imprimen en stdout del contenedor server
```