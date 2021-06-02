import React, { useState } from "react";
import { Redirect } from "react-router-dom";
import { connect } from "react-redux";
import firebase from "../../firebase";
import "firebase/auth";
import { setIsAuth } from "../../actions/auth";
import PropTypes from "prop-types";

const Login = ({ auth, setIsAuth }) => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const onChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

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

  firebase.auth().onAuthStateChanged((user) => {
    if (user && auth.isAuth === false) {
      setIsAuth(true);
    } else if (!user && auth.isAuth === true) {
      setIsAuth(false);
    }
  });

  return auth.isAuth ? (
    <Redirect to="/dashboard" />
  ) : (
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
            Log ind
          </button>
        </div>
      </form>
    </div>
  );
};

Login.propTypes = {
  auth: PropTypes.object.isRequired,
};

export default connect(
  (state) => ({
    auth: state.auth,
  }),
  { setIsAuth }
)(Login);
