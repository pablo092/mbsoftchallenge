import React, { useState, useEffect } from "react";
import { Input } from "../ui/Input";
import { Button } from "../ui/Button";
import { createMedicationType, getMedicationTypes, deleteMedicationType } from "../../services/medicationService";
import Modal from "../ui/Modal";

export default function MedicationTypeForm({ isOpen, onClose }) {
  const [newTypeName, setNewTypeName] = useState("");
  const [medicationTypes, setMedicationTypes] = useState([]);
  const [selectedTypeId, setSelectedTypeId] = useState("");

  useEffect(() => {
    fetchMedicationTypes();
  }, []);

  const fetchMedicationTypes = async () => {
    try {
      const types = await getMedicationTypes();
      setMedicationTypes(types);
    } catch (error) {
      alert("Error al obtener los tipos de medicamento.");
    }
  };

  const handleCreateType = async () => {
    try {
      await createMedicationType(newTypeName);
      alert("Tipo de medicamento creado.");
      setNewTypeName("");
      fetchMedicationTypes();
      onClose();
    } catch (error) {
      alert("Error al crear tipo de medicamento.");
    }
  };

  const handleDeleteType = async () => {
    if (!selectedTypeId) {
      alert("Seleccione un tipo para eliminar.");
      return;
    }

    try {
      await deleteMedicationType(selectedTypeId);
      setMedicationTypes((prevTypes) => prevTypes.filter((type) => type.id !== parseInt(selectedTypeId)));
      alert("Tipo de medicamento eliminado.");
      setSelectedTypeId("");
      onClose();
    } catch (error) {
      alert("Error al eliminar tipo de medicamento.");
    }
  };

  return (
    <Modal title="Tipo de Medicamento" isOpen={isOpen} onClose={onClose}>
      <Input
        type="text"
        placeholder="Nombre del nuevo tipo"
        value={newTypeName}
        onChange={(e) => setNewTypeName(e.target.value)}
        className="w-full p-2 border rounded-md shadow"
      />
      <Button
        onClick={handleCreateType}
        disabled={newTypeName.trim() === ""}
        className={`mt-2 px-4 py-2 rounded-md shadow transition ${
          newTypeName.trim() !== "" ? "bg-blue-500 text-white hover:bg-blue-600" : "bg-gray-300 text-gray-500 cursor-not-allowed"
        }`}
      >
        Crear Tipo
      </Button>

      <h3 className="text-lg font-semibold mt-4">Eliminar Tipo</h3>
      <select
        className="w-full p-2 border rounded-md shadow mt-2"
        value={selectedTypeId}
        onChange={(e) => setSelectedTypeId(e.target.value)}
      >
        <option value="">Seleccione un tipo</option>
        {medicationTypes.map((type) => (
          <option key={type.id} value={type.id}>
            {type.typeName}
          </option>
        ))}
      </select>
      <Button
        onClick={handleDeleteType}
        className="mt-2 bg-red-500 text-white px-4 py-2 rounded-md shadow hover:bg-red-600 transition"
      >
        Eliminar Tipo
      </Button>
    </Modal>
  );
}
