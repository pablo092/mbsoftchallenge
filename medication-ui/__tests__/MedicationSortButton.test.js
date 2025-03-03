import { render, screen, fireEvent } from "@testing-library/react";
import MedicationSortButton from "../src/components/medications/MedicationSortButton";
import React from "react";

test("llama la función de ordenamiento cuando se hace clic", () => {
  const mockHandleSort = jest.fn();

  render(<MedicationSortButton handleSortProducts={mockHandleSort} />);

  fireEvent.click(screen.getByText("Ordenar productos"));
  expect(mockHandleSort).toHaveBeenCalledTimes(1);
});
