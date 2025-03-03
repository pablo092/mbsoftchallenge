import React from "react";
import { motion } from "framer-motion";
import { Card } from "../ui/Card";
import { Button } from "../ui/Button";

export default function MedicationList({ medications, checkPriority, checkCode }) {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4 mt-4">
      {medications.map((med) => (
        <motion.div key={med.id} whileHover={{ scale: 1.05 }}>
          <Card title={med.commercialName}>
            <p className="text-sm text-gray-600">Código: {med.code}</p>
            <p className="text-sm text-gray-600">Droga: {med.drugName}</p>
            <p className="text-sm text-gray-600">Tipo: {med.type.typeName}</p>
            <div className="flex flex-wrap gap-2 mt-2">
              <Button
                onClick={() => checkPriority(med.code)}
                className="bg-green-500 text-white px-3 py-1 rounded-md shadow hover:bg-green-600 transition"
              >
                Ver Prioridad
              </Button>
              <Button
                onClick={() => checkCode(med.code)}
                className="bg-yellow-500 text-white px-3 py-1 rounded-md shadow hover:bg-yellow-600 transition"
              >
                Verificar Código
              </Button>
            </div>
          </Card>
        </motion.div>
      ))}
    </div>
  );
}
