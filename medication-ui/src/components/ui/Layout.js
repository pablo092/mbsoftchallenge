import React from "react";

export function Layout({ children }) {
  return (
    <div className="min-h-screen bg-gray-100">
      <header className="bg-blue-600 text-white p-4 shadow">
        <h1 className="text-xl font-bold">Gestión de Medicamentos</h1>
      </header>
      <main className="p-6">{children}</main>
    </div>
  );
}
