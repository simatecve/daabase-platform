#!/bin/bash
set -e

BACKUP_DIR="/opt/daabase/backups"
DATE=$(date +%Y%m%d_%H%M%S)
DB_NAME="${POSTGRES_DB:-daabase}"
DB_USER="${POSTGRES_USER:-daabase}"

mkdir -p "$BACKUP_DIR"

echo "=== pg_dump $DB_NAME ==="
pg_dump -U "$DB_USER" -d "$DB_NAME" | zstd > "$BACKUP_DIR/daabase_${DATE}.sql.zst"

echo "=== rotate backups (keep 7) ==="
ls -1 "$BACKUP_DIR"/daabase_*.sql.zst | sort -r | tail -n +8 | xargs -r rm

echo "=== upload to S3 ==="
mc cp "$BACKUP_DIR/daabase_${DATE}.sql.zst" local/daabase-backups/

echo "Backup $DATE complete"