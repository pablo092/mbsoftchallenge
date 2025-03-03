import React from "react";

export default function Modal({ title, isOpen, onClose, children }) {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
      <div className="bg-white rounded-lg shadow-lg p-6 w-96">
        <h2 className="text-xl font-semibold text-center mb-4">{title}</h2>
        <div className="mb-4">{children}</div>
        <div className="flex justify-end space-x-2">
          <button
            onClick={onClose}
            className="bg-gray-400 text-white px-4 py-2 rounded-md hover:bg-gray-500 transition"
          >
            Cerrar
          </button>
        </div>
      </div>
    </div>
  );
}
