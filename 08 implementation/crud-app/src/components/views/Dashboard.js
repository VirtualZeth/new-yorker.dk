import React, { Fragment } from "react";
import { Redirect } from "react-router-dom";
import { connect } from "react-redux";
import firebase from "../../firebase";
import "firebase/auth";
import { setIsAuth } from "../../actions/auth";
import PropTypes from "prop-types";
import Table from "../Table";

const Dashboard = ({ auth, setIsAuth }) => {
  const signOut = async () => firebase.auth().signOut();

  firebase.auth().onAuthStateChanged((user) => {
    if (user && auth.isAuth === false) {
      setIsAuth(true);
    } else if (!user && auth.isAuth === true) {
      setIsAuth(false);
    }
  });

  return auth.isAuth ? (
    <Fragment>
      <button
        style={{ position: "absolute", right: "0", top: "0" }}
        onClick={() => signOut()}
        className="btn btn-primary"
      >
        Sign out
      </button>
      <div
        style={TableContainerStyle}
        className="container-sm d-flex justify-content-center"
      >
        <Table />
      </div>
    </Fragment>
  ) : (
    <Redirect to="/" />
  );
};

const TableContainerStyle = {
  marginTop: "80px",
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
