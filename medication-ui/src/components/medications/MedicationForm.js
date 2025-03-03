import React, { useState, useEffect } from "react";
import { Input } from "../ui/Input";
import { Button } from "../ui/Button";
import { getMedicationTypes, createMedication } from "../../services/medicationService";
import Modal from "../ui/Modal";

export default function MedicationForm({ isOpen, onClose }) {
  const [medicationTypes, setMedicationTypes] = useState([]);
  const [medicationData, setMedicationData] = useState({
    code: "",
    commercialName: "",
    drugName: "",
    typeId: "",
  });

  useEffect(() => {
    const fetchTypes = async () => {
      try {
        const types = await getMedicationTypes();
        setMedicationTypes(types);
      } catch (error) {
        alert("Error al obtener los tipos de medicamento.");
      }
    };
    fetchTypes();
  }, []);

  const handleCreateMedication = async () => {
    try {
      await createMedication(
        medicationData.code,
        medicationData.commercialName,
        medicationData.drugName,
        medicationData.typeId
      );
      alert("Medicamento creado.");
      onClose();
      setMedicationData({ code: "", commercialName: "", drugName: "", typeId: "" });
    } catch (error) {
      alert("Error al crear el medicamento.");
    }
  };

  return (
    <Modal title="Nuevo Medicamento" isOpen={isOpen} onClose={onClose}>
      <Input
        type="text"
        placeholder="Código del medicamento"
        value={medicationData.code}
        onChange={(e) => setMedicationData({ ...medicationData, code: e.target.value })}
        className="w-full p-2 border rounded-md shadow"
      />
      <Input
        type="text"
        placeholder="Nombre comercial"
        value={medicationData.commercialName}
        onChange={(e) => setMedicationData({ ...medicationData, commercialName: e.target.value })}
        className="w-full p-2 border rounded-md shadow mt-2"
      />
      <Input
        type="text"
        placeholder="Nombre de la droga"
        value={medicationData.drugName}
        onChange={(e) => setMedicationData({ ...medicationData, drugName: e.target.value })}
        className="w-full p-2 border rounded-md shadow mt-2"
      />
      <select
        className="w-full p-2 border rounded-md shadow mt-2"
        value={medicationData.typeId}
        onChange={(e) => setMedicationData({ ...medicationData, typeId: e.target.value })}
      >
        <option value="">Seleccione un tipo</option>
        {medicationTypes.map((type) => (
          <option key={type.id} value={type.id}>
            {type.typeName}
          </option>
        ))}
      </select>
      <div className="flex justify-end mt-4">
        <Button
          onClick={handleCreateMedication}
          disabled={!medicationData.code || !medicationData.commercialName || !medicationData.drugName || !medicationData.typeId}
          className={`px-4 py-2 rounded-md shadow transition ${
            medicationData.code && medicationData.commercialName && medicationData.drugName && medicationData.typeId
              ? "bg-blue-500 text-white hover:bg-blue-600"
              : "bg-gray-300 text-gray-500 cursor-not-allowed"
          }`}
        >
          Crear Medicamento
        </Button>
      </div>
    </Modal>
  );
}
