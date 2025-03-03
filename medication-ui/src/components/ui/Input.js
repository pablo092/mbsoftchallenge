import React from "react";

export function Input({ type, placeholder, value, onChange, onKeyDown }) {
  return (
    <input
      type={type}
      placeholder={placeholder}
      value={value}
      onChange={onChange}
      onKeyDown={onKeyDown}
      className="border p-2 rounded w-full"
    />
  );
}
