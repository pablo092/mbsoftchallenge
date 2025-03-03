import { medicationApi } from "./api";

// Crear un nuevo medicamento
export const createMedication = async (
  code,
  commercialName,
  drugName,
  typeId
) => {
  try {
    const response = await medicationApi.post("/medications/", null, {
      params: { code, commercialName, drugName, typeId },
    });
    return response.data;
  } catch (error) {
    throw new Error("Error al crear el medicamento.");
  }
};

// Crear un nuevo tipo de medicamento
export const createMedicationType = async (typeName) => {
  try {
    const response = await medicationApi.post("/medications/type", null, {
      params: { typeName },
    });
    return response.data;
  } catch (error) {
    throw new Error("Error al crear el tipo de medicamento.");
  }
};

// Lista los tipos de medicamento
export const getMedicationTypes = async () => {
  try {
    const response = await medicationApi.get("/medications/types");
    return response.data;
  } catch (error) {
    throw new Error("Error al obtener los tipos de medicamento.");
  }
};

// Eliminar un tipo de medicamento por ID
export const deleteMedicationType = async (id) => {
  try {
    await medicationApi.delete(`/medications/type/${id}`);
  } catch (error) {
    throw new Error("Error al eliminar el tipo demedicamento.");
  }
};

// Obtener medicamentos por tipo
export const getMedicationsByType = async (typeName) => {
  try {
    const response = await medicationApi.get("/medications/byType", {
      params: { typeName },
    });
    return response.data;
  } catch (error) {
    throw new Error("Error al obtener medicamentos por tipo.");
  }
};

// Obtener medicamentos cuyo nombre comercial empieza con un prefijo
export const getMedicationsByNamePrefix = async (prefix) => {
  try {
    const response = await medicationApi.get("/medications/byName", {
      params: { prefix },
    });
    return response.data;
  } catch (error) {
    throw new Error("Error al obtener medicamentos por prefijo.");
  }
};
