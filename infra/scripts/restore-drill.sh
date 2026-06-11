#!/bin/bash
set -e

BACKUP_DIR="/opt/daabase/backups"
DATE=$(date +%Y%m%d_%H%M%S)
TEMP_DB="daabase_restore_test"

echo "=== restore drill $DATE ==="
echo "Creating throwaway db $TEMP_DB..."
psql -U "${POSTGRES_USER:-daabase}" -d postgres -c "DROP DATABASE IF EXISTS $TEMP_DB"
psql -U "${POSTGRES_USER:-daabase}" -d postgres -c "CREATE DATABASE $TEMP_DB"

LATEST=$(ls -1 "$BACKUP_DIR"/daabase_*.sql.zst | sort -r | head -1)
if [ -z "$LATEST" ]; then
  echo "No backup found, skipping drill"
  exit 0
fi

echo "Restoring $LATEST to $TEMP_DB..."
zstd -d < "$LATEST" | psql -U "${POSTGRES_USER:-daabase}" -d "$TEMP_DB"

ROWS=$(psql -U "${POSTGRES_USER:-daabase}" -d "$TEMP_DB" -t -c "SELECT COUNT(*) FROM app_user")
echo "app_user rows: $ROWS"

psql -U "${POSTGRES_USER:-daabase}" -d postgres -c "DROP DATABASE $TEMP_DB"
echo "Restore drill complete"