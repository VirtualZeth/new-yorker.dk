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
      .then(({ user }) => {
        console.log(user);
      })
      .catch((error) => {
        console.log(error.code);
        console.log(error.message);
      });
  };

  return (
    <div
      id="login_container"
      className="container-sm d-flex justify-content-center"
    >
      <form className="row g-3" onSubmit={(e) => onSubmit(e)}>
        <div className="col-12">
          <label htmlFor="inputEmail4" className="form-label">
            Email
          </label>
          <input
            value={formData.email}
            onChange={(e) => onChange(e)}
            name="email"
            type="email"
            className="form-control"
            id="inputEmail4"
            required
          />
        </div>
        <div className="col-12">
          <label htmlFor="inputPassword4" className="form-label">
            Password
          </label>
          <input
            value={formData.password}
            onChange={(e) => onChange(e)}
            name="password"
            type="password"
            className="form-control"
            id="inputPassword4"
            required
          />
        </div>
        <div className="col-12">
          <button type="submit" className="btn btn-primary">
            Sign in
          </button>
        </div>
      </form>
    </div>
  );
};

export default Login;
