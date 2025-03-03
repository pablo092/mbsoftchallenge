import { render } from "@testing-library/react";
import { Loader } from "../src/components/ui/Loader";
import React from "react";

test("renderiza el componente Loader", () => {
  const { container } = render(<Loader />);
  expect(container.firstChild).toHaveClass("flex justify-center items-center");
});
