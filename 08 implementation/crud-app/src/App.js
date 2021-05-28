import React from "react";
import Login from "./components/Login";
import "./styling/App.css";

const App = () => {
  return (
    <div
      id="login_container"
      class="container-sm d-flex justify-content-center"
    >
      <Login />
    </div>
  );
};

export default App;
