import { render, screen } from "@testing-library/react";
import Home from "../src/pages/index";
import React from "react";

test("renderiza el título principal de la aplicación", () => {
  render(<Home />);
  expect(screen.getByText("Búsqueda de Medicamentos")).toBeInTheDocument();
});

test("renderiza el botón de búsqueda", () => {
  render(<Home />);
  expect(screen.getByRole("button", { name: /Buscar/i })).toBeInTheDocument();
});
