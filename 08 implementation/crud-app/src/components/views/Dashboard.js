import React, { useState, useEffect, Fragment } from "react";
import { Redirect } from "react-router-dom";
import { connect } from "react-redux";
import firebase from "../../firebase";
import "firebase/auth";
import { setIsAuth } from "../../actions/auth";
import PropTypes from "prop-types";

const Dashboard = ({ auth, setIsAuth }) => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const ref = firebase.firestore().collection("products");

    (() => {
      ref.onSnapshot((querySnapshot) => {
        const items = [];
        querySnapshot.forEach((e) => items.push(e.data()));
        setProducts(items);
      });
    })();
  }, []);

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
      <button onClick={() => signOut()} className="btn btn-primary">
        Sign out
      </button>
      <div>
        {products.map((e) => (
          <div key={products.id}>
            <h2>{e.name}</h2>
            <p>{e.price}</p>
          </div>
        ))}
      </div>
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
