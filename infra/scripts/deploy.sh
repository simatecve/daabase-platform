#!/bin/bash
set -e

echo "=== daabase deploy ==="
git submodule update --remote instant
docker compose -f infra/docker-compose.yml pull
docker compose -f infra/docker-compose.yml up -d
sleep 10
./infra/scripts/smoke.sh
echo "=== deploy complete ==="