#!/bin/bash
set -e

API_URL="${INSTANT_BACKEND_URL:-https://api.daabase.click}"
DASH_URL="${INSTANT_DASHBOARD_URL:-https://dash.daabase.click}"

echo "=== smoke test ==="

echo "1. Health check..."
STATUS=$(curl -s -o /dev/null -w "%{http_code}" "$API_URL/health")
if [ "$STATUS" != "200" ]; then
  echo "FAIL: server health returned $STATUS"
  exit 1
fi
echo "OK: server health $STATUS"

echo "2. Dashboard reachable..."
STATUS=$(curl -s -o /dev/null -w "%{http_code}" "$DASH_URL")
if [ "$STATUS" != "200" ]; then
  echo "FAIL: dashboard returned $STATUS"
  exit 1
fi
echo "OK: dashboard $STATUS"

echo "=== smoke test passed ==="