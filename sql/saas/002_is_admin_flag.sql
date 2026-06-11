-- Adds is_admin flag to the app_user table from the fork
-- This allows magic code auth to work for both regular users and admins

ALTER TABLE app_user ADD COLUMN IF NOT EXISTS is_admin BOOLEAN NOT NULL DEFAULT FALSE;

COMMENT ON COLUMN app_user.is_admin IS 'True if this user can access admin.daabase.click';
CREATE INDEX IF NOT EXISTS idx_app_user_is_admin ON app_user(is_admin) WHERE is_admin = TRUE;