import React, { Fragment } from "react";
import { Redirect } from "react-router-dom";
import { connect } from "react-redux";
import firebase from "../../firebase";
import "firebase/auth";
import { setIsAuth } from "../../actions/auth";
import PropTypes from "prop-types";
import Table from "../Table";
import Toolbar from "../Toolbar";

const Dashboard = ({ auth, setIsAuth }) => {
  firebase.auth().onAuthStateChanged((user) => {
    if (user && auth.isAuth === false) {
      setIsAuth(true);
    } else if (!user && auth.isAuth === true) {
      setIsAuth(false);
    }
  });

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
