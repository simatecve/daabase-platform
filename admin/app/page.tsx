'use client';

import { useState } from 'react';

export default function AdminPage() {
  const [email, setEmail] = useState('');
  const [code, setCode] = useState('');

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL || 'https://api.daabase.click'}/auth/admin/magic-code`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email }),
    });
    const data = await res.json();
    if (data.ok) setCode('code-sent');
  };

  const handleVerify = async (e: React.FormEvent) => {
    e.preventDefault();
    const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL || 'https://api.daabase.click'}/auth/admin/verify`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, code }),
    });
    if (res.ok) window.location.href = '/admin/dashboard';
  };

  if (code === 'code-sent') {
    return (
      <div style={{ minHeight: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
        <form onSubmit={handleVerify} style={{ display: 'flex', flexDirection: 'column', gap: 16, maxWidth: 320 }}>
          <h1 style={{ fontSize: 24, fontWeight: 'bold' }}>daabase admin</h1>
          <p>Revisá tu email. Te enviamos un código.</p>
          <input
            type="text"
            placeholder="Código de 6 dígitos"
            value={code}
            onChange={(e) => setCode(e.target.value)}
            style={{ padding: 8, border: '1px solid #ccc', borderRadius: 4 }}
          />
          <button type="submit" style={{ padding: 8, background: '#0ea5e9', color: '#fff', borderRadius: 4, border: 'none', cursor: 'pointer' }}>
            Verificar
          </button>
        </form>
      </div>
    );
  }

  return (
    <div style={{ minHeight: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
      <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', gap: 16, maxWidth: 320 }}>
        <h1 style={{ fontSize: 24, fontWeight: 'bold' }}>daabase admin</h1>
        <input
          type="email"
          placeholder="Email de admin"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          style={{ padding: 8, border: '1px solid #ccc', borderRadius: 4 }}
        />
        <button type="submit" style={{ padding: 8, background: '#0ea5e9', color: '#fff', borderRadius: 4, border: 'none', cursor: 'pointer' }}>
          Enviar código
        </button>
      </form>
    </div>
  );
}