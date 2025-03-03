import React from "react";
import { Button } from "../ui/Button";

export default function MedicationActions({ setShowTypeModal, setShowMedicationModal }) {
  return (
    <div className="flex space-x-4 mt-4">
      <Button
        onClick={() => setShowTypeModal(true)}
        className="bg-green-500 text-white px-4 py-2 rounded-md shadow hover:bg-green-600 transition"
      >
        Crear / Eliminar Tipo
      </Button>
      <Button
        onClick={() => setShowMedicationModal(true)}
        className="bg-purple-500 text-white px-4 py-2 rounded-md shadow hover:bg-purple-600 transition"
      >
        Crear Medicamento
      </Button>
    </div>
  );
}
