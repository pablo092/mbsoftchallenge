import React from "react";
import { motion } from "framer-motion";

export function Loader() {
  return (
    <motion.div 
      className="flex justify-center items-center my-4"
      animate={{ rotate: 360 }}
      transition={{ repeat: Infinity, duration: 1.5 }}
    >
      <div className="w-8 h-8 border-t-4 border-blue-500 border-solid rounded-full"></div>
    </motion.div>
  );
}
