import { render, screen } from "@testing-library/react";
import NoResultsMessage from "../src/components/NoResultsMessage";
import React from "react";

test("muestra un mensaje cuando no hay resultados", () => {
  render(<NoResultsMessage />);
  expect(screen.getByText("No se encontraron resultados.")).toBeInTheDocument();
});
