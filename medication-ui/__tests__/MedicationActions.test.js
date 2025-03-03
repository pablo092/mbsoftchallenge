import { render, screen, fireEvent } from "@testing-library/react";
import MedicationActions from "../src/components/medications/MedicationActions";
import React from "react";

test("ejecuta las funciones al hacer clic en los botones", () => {
  const mockSetShowTypeModal = jest.fn();
  const mockSetShowMedicationModal = jest.fn();

  render(
    <MedicationActions
      setShowTypeModal={mockSetShowTypeModal}
      setShowMedicationModal={mockSetShowMedicationModal}
    />
  );

  fireEvent.click(screen.getByText("Crear / Eliminar Tipo"));
  expect(mockSetShowTypeModal).toHaveBeenCalledTimes(1);

  fireEvent.click(screen.getByText("Crear Medicamento"));
  expect(mockSetShowMedicationModal).toHaveBeenCalledTimes(1);
});
