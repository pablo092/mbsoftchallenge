import { render, screen, fireEvent } from "@testing-library/react";
import MedicationSearch from "../src/components/medications/MedicationSearch";
import React from "react";

test("deshabilita el botón de búsqueda cuando el input está vacío", () => {
  const { getByPlaceholderText, getByText } = render(
    <MedicationSearch
      type="byName"
      setType={() => {}}
      searchType="byType"
      handleSearchTypeChange={() => {}}
      fetchMedications={() => {}}
    />
  );

  fireEvent.change(getByPlaceholderText("Ingrese su búsqueda"), {
    target: { value: "Ibuprofeno" },
  });

  expect(getByText("Buscar")).not.toBeDisabled();
});
