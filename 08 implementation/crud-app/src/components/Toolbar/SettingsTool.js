import React from "react";
import { Link } from "react-router-dom";

const SettingsTool = ({ btn }) => {
  return (
    <Link className={`${btn} me-auto`} to="/skrivebord">
      Skrivebord
    </Link>
  );
};

export default SettingsTool;
