-- daabase SaaS schema
-- Corre en el mismo Postgres que las apps de los usuarios, en el schema "saas"

CREATE SCHEMA IF NOT EXISTS saas;

-- Plans
CREATE TABLE IF NOT EXISTS saas.plan (
  id          TEXT PRIMARY KEY,
  name        TEXT NOT NULL,
  price_cents INTEGER NOT NULL DEFAULT 0,
  created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

INSERT INTO saas.plan (id, name, price_cents) VALUES
  ('free',    'Free',     0),
  ('starter', 'Starter',  500),
  ('pro',     'Pro',     1500)
ON CONFLICT (id) DO NOTHING;

-- Admin users
CREATE TABLE IF NOT EXISTS saas.admin_user (
  id           TEXT PRIMARY KEY DEFAULT gen_random_id(),
  email        TEXT UNIQUE NOT NULL,
  created_at   TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  last_login   TIMESTAMPTZ,
  is_active    BOOLEAN NOT NULL DEFAULT TRUE
);

-- Subscriptions
CREATE TABLE IF NOT EXISTS saas.subscription (
  id              TEXT PRIMARY KEY DEFAULT gen_random_id(),
  user_id         TEXT NOT NULL REFERENCES saas.admin_user(id),
  plan_id         TEXT NOT NULL REFERENCES saas.plan(id),
  status          TEXT NOT NULL DEFAULT 'active',
  current_period_start TIMESTAMPTZ,
  current_period_end   TIMESTAMPTZ,
  created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Invoices
CREATE TABLE IF NOT EXISTS saas.invoice (
  id              TEXT PRIMARY KEY DEFAULT gen_random_id(),
  subscription_id TEXT REFERENCES saas.subscription(id),
  user_id         TEXT NOT NULL,
  amount_cents    INTEGER NOT NULL,
  status          TEXT NOT NULL DEFAULT 'pending',
  payphone_tx_id  TEXT,
  created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Waitlist
CREATE TABLE IF NOT EXISTS saas.waitlist (
  id         TEXT PRIMARY KEY DEFAULT gen_random_id(),
  email      TEXT UNIQUE NOT NULL,
  referrer   TEXT,
  utm_source TEXT,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

COMMENT ON SCHEMA saas IS 'daabase SaaS metadata: plans, admin users, subscriptions, invoices, waitlist';
COMMENT ON TABLE saas.admin_user IS 'daabase platform admin accounts';
COMMENT ON TABLE saas.waitlist IS 'Early access waitlist leads';