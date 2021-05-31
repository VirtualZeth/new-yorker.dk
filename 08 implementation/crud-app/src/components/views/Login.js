import React, { useState } from "react";
import firebase from "../../firebase";
import "firebase/auth";

const Login = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const onChange = (e) => {
    console.log(e.name);
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    firebase
      .auth()
      .signInWithEmailAndPassword(formData.email, formData.password)
      .then((e) => {
        let user = e.user;
        console.log(user);
      })
      .catch((error) => {
        let errorCode = error.code;
        let errorMessage = error.message;
        console.log(errorCode);
        console.log(errorMessage);
      });
  };

  return (
    <div
      id="login_container"
      class="container-sm d-flex justify-content-center"
    >
      <h1 id="log">log</h1>
      <form class="row g-3" onSubmit={(e) => onSubmit(e)}>
        <div class="col-12">
          <label for="inputEmail4" class="form-label">
            Email
          </label>
          <input
            value={formData.email}
            onChange={(e) => onChange(e)}
            name="email"
            type="email"
            class="form-control"
            id="inputEmail4"
            required
          />
        </div>
        <div class="col-12">
          <label for="inputPassword4" class="form-label">
            Password
          </label>
          <input
            value={formData.password}
            onChange={(e) => onChange(e)}
            name="password"
            type="password"
            class="form-control"
            id="inputPassword4"
            required
          />
        </div>
        <div class="col-12">
          <button type="submit" class="btn btn-primary">
            Sign in
          </button>
        </div>
      </form>
    </div>
  );
};

export default Login;
