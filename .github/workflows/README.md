# GitHub Actions workflows para daabase-platform

## Workflows

### server.yml
Builds the Clojure server from `instant/server/` and publishes `ghcr.io/simatecve/daabase2-server`.

### dashboard.yml
Builds the Next.js dashboard from `instant/client/www/` and publishes `ghcr.io/simatecve/daabase2-dashboard`.

### admin.yml
Builds the admin panel from `admin/` and publishes `ghcr.io/simatecve/daabase-platform-admin`.

### submodule-sync.yml
Opens a PR in `daabase-platform` whenever `daabase2` main branch advances (sync de submódulo).

### ci.yml
Runs lint and typecheck on every push.