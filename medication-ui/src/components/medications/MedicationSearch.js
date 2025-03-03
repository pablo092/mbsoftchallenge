import React from "react";
import { Input } from "../ui/Input";
import { Button } from "../ui/Button";

export default function MedicationSearch({ type, setType, searchType, handleSearchTypeChange, fetchMedications }) {
  const handleKeyDown = (event) => {
    if (event.key === "Enter" && type.trim() !== "") {
      fetchMedications();
    }
  };

  return (
    <div>
      <div className="flex mt-4 space-x-2">
        <Input
          type="text"
          placeholder="Ingrese su búsqueda"
          value={type}
          onChange={(e) => setType(e.target.value)}
          onKeyDown={(e) => handleKeyDown(e) && fetchMedications()}
          className="w-full p-2 border rounded-md shadow"
        />
        <Button
          onClick={fetchMedications}
          disabled={type.trim() === ""}
          className={`px-4 py-2 rounded-md shadow transition ${
            type.trim() !== "" ? "bg-blue-500 text-white hover:bg-blue-600" : "bg-gray-300 text-gray-500 cursor-not-allowed"
          }`}
        >
          Buscar
        </Button>
      </div>

      <div className="mt-4 flex space-x-4">
        <label>
          <input
            type="radio"
            value="byType"
            checked={searchType === "byType"}
            onChange={() => handleSearchTypeChange("byType")}
          />
          Por Tipo
        </label>
        <label>
          <input
            type="radio"
            value="byName"
            checked={searchType === "byName"}
            onChange={() => handleSearchTypeChange("byName")}
          />
          Por Nombre
        </label>
      </div>
    </div>
  );
}
