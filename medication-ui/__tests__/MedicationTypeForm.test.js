import { render, screen, fireEvent } from "@testing-library/react";
import MedicationTypeForm from "../src/components/medications/MedicationTypeForm";
import React from "react";

test("deshabilita el botón cuando no hay nombre de tipo", () => {
  render(<MedicationTypeForm isOpen={true} onClose={() => {}} />);

  const createButton = screen.getByText("Crear Tipo");

  fireEvent.change(screen.getByPlaceholderText("Nombre del nuevo tipo"), {
    target: { value: "Nuevo Tipo" },
  });

  expect(createButton).not.toBeDisabled();
});
