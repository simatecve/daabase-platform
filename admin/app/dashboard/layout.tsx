'use client';

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
  return (
    <div style={{ minHeight: '100vh', display: 'flex' }}>
      <aside style={{ width: 240, borderRight: '1px solid #eee', padding: 16 }}>
        <h2 style={{ fontSize: 18, fontWeight: 'bold', marginBottom: 24 }}>daabase</h2>
        <nav style={{ display: 'flex', flexDirection: 'column', gap: 8 }}>
          <a href="/admin/dashboard" style={{ color: '#333', textDecoration: 'none' }}>Dashboard</a>
          <a href="/admin/tenants" style={{ color: '#333', textDecoration: 'none' }}>Tenants</a>
          <a href="/admin/billing" style={{ color: '#333', textDecoration: 'none' }}>Billing</a>
          <a href="/admin/waitlist" style={{ color: '#333', textDecoration: 'none' }}>Waitlist</a>
          <a href="/admin/settings" style={{ color: '#333', textDecoration: 'none' }}>Settings</a>
        </nav>
      </aside>
      <main style={{ flex: 1, padding: 24 }}>{children}</main>
    </div>
  );
}