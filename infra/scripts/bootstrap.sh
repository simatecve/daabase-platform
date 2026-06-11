#!/bin/bash
set -e

echo "=== bootstrap checks ==="

echo "Checking Java..."
java -version 2>&1 | head -1

echo "Checking Docker..."
docker --version

echo "Checking pg_hint_plan..."
PG_VERSION=$(psql --version | awk '{print $3}')
echo "Postgres version: $PG_VERSION"

echo "Checking MinIO client..."
mc --version 2>/dev/null || echo "mc not installed (optional)"

echo "Checking golang-migrate..."
migrate --version 2>/dev/null || echo "migrate not installed"

echo "Bootstrap checks complete"