import React from "react";

export function Card({ title, children }) {
  return (
    <div className="border rounded-lg p-4 shadow-md">
      <h2 className="text-lg font-semibold">{title}</h2>
      <div>{children}</div>
    </div>
  );
}
