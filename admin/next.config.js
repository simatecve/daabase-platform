/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  transpilePackages: ['@instantdb/admin'],
  env: {
    NEXT_PUBLIC_SELF_HOSTED: process.env.NEXT_PUBLIC_SELF_HOSTED || 'true',
  },
};

module.exports = nextConfig;