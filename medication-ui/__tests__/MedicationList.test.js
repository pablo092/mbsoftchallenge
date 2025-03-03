import { render, screen } from "@testing-library/react";
import MedicationList from "../src/components/medications/MedicationList";
import React from "react";

const mockMedications = [
  {
    id: 1,
    code: "IBU-200-2",
    commercialName: "Ibuprofeno",
    drugName: "Ibuprofeno",
    type: { typeName: "Antiinflamatorio" },
  },
  {
    id: 2,
    code: "PCT-100-1",
    commercialName: "Paracetamol",
    drugName: "Paracetamol",
    type: { typeName: "Analgésico" },
  },
];

test("muestra la lista de medicamentos correctamente", () => {
  render(
    <MedicationList
      medications={mockMedications}
      checkPriority={() => {}}
      checkCode={() => {}}
    />
  );

  expect(screen.getByText("Ibuprofeno")).toBeInTheDocument();
  expect(screen.getByText("Paracetamol")).toBeInTheDocument();
});
