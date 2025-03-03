import React from "react";
import { Button } from "../ui/Button";

export default function MedicationSortButton({ handleSortProducts }) {
  return (
    <Button
      onClick={handleSortProducts}
      className="bg-blue-500 text-white px-4 py-2 mt-4 rounded-md shadow hover:bg-blue-600 transition"
    >
      Ordenar productos
    </Button>
  );
}
