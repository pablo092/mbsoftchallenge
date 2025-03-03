import { render, screen, fireEvent } from "@testing-library/react";
import MedicationForm from "../src/components/medications/MedicationForm";
import React from "react";

test("deshabilita el botón cuando los campos están vacíos", () => {
  render(
    <MedicationForm isOpen={true} onClose={() => {}} />
  );

  const submitButton = screen.getByText("Crear");

  fireEvent.change(screen.getByPlaceholderText("Código del medicamento"), { target: { value: "ABC-123-1" } });
  fireEvent.change(screen.getByPlaceholderText("Nombre comercial"), { target: { value: "TestMed" } });
  fireEvent.change(screen.getByPlaceholderText("Nombre de la droga"), { target: { value: "TestDrug" } });

  expect(submitButton).not.toBeDisabled();
});
