import React, { Fragment, useEffect } from "react";
import { Redirect } from "react-router-dom";
import { connect } from "react-redux";
import firebase from "../../firebase";
import "firebase/auth";
import { setIsAuth } from "../../actions/auth";
import PropTypes from "prop-types";
import Table from "../Table";
import Toolbar from "../Toolbar";

const Dashboard = ({ auth, setIsAuth }) => {
  const { isAuth } = auth;
  useEffect(() => {
    const close = firebase.auth().onAuthStateChanged((user) => {
      if (user && isAuth === false) {
        setIsAuth(true);
      } else if (!user && isAuth === true) {
        setIsAuth(false);
      }
    });
    return close;
  }, [isAuth, setIsAuth]);

  return auth.isAuth ? (
    <Fragment>
      <Toolbar />
      <Table />
    </Fragment>
  ) : (
    <Redirect to="/" />
  );
};

Dashboard.propTypes = {
  auth: PropTypes.object.isRequired,
};

export default connect(
  (state) => ({
    auth: state.auth,
  }),
  { setIsAuth }
)(Dashboard);
