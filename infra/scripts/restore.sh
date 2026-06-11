#!/bin/bash
set -e

BACKUP_FILE=$1
if [ -z "$BACKUP_FILE" ]; then
  echo "Usage: $0 <backup_file>"
  exit 1
fi

DB_NAME="${POSTGRES_DB:-daabase}"
DB_USER="${POSTGRES_USER:-daabase}"

echo "=== restore $BACKUP_FILE to $DB_NAME ==="
zstd -d < "$BACKUP_FILE" | psql -U "$DB_USER" -d "$DB_NAME"
echo "Restore complete"