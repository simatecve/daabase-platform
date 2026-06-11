export default function DashboardPage() {
  return (
    <div>
      <h1 style={{ fontSize: 24, fontWeight: 'bold', marginBottom: 24 }}>Dashboard</h1>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: 16 }}>
        <div style={{ padding: 16, border: '1px solid #eee', borderRadius: 8 }}>
          <p style={{ color: '#666', fontSize: 14 }}>Tenants activos</p>
          <p style={{ fontSize: 32, fontWeight: 'bold' }}>—</p>
        </div>
        <div style={{ padding: 16, border: '1px solid #eee', borderRadius: 8 }}>
          <p style={{ color: '#666', fontSize: 14 }}>Ingresos del mes</p>
          <p style={{ fontSize: 32, fontWeight: 'bold' }}>—</p>
        </div>
        <div style={{ padding: 16, border: '1px solid #eee', borderRadius: 8 }}>
          <p style={{ color: '#666', fontSize: 14 }}>Waitlist</p>
          <p style={{ fontSize: 32, fontWeight: 'bold' }}>—</p>
        </div>
      </div>
    </div>
  );
}