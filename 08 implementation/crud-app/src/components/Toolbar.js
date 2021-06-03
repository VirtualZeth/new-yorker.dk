import React, { useState, useEffect, Fragment } from "react";
import firebase from "../firebase";
import "firebase/firestore";
import { connect } from "react-redux";
import { setModalShow } from "../actions/modals";
import { setCurrentCategory } from "../actions/category";
import DropdownButton from "react-bootstrap/DropdownButton";
import Dropdown from "react-bootstrap/esm/Dropdown";
import AddProductModal from "./AddProductModal";

const Toolbar = ({ setModalShow, category, setCurrentCategory }) => {
  const [categoryData, setCategoryData] = useState([]);

  useEffect(() => {
    firebase
      .firestore()
      .collection("categories")
      .onSnapshot((querySnapshot) => {
        const items = [];
        querySnapshot.forEach((doc) => items.push({ ...doc.data(), id: doc.id }));
        setCategoryData(items);
      });
  }, [categoryData]);

  const signOut = async () => await firebase.auth().signOut();

  return (
    <Fragment>
      <AddProductModal categories={categoryData} />
      <div className="card container" style={toolbarStyle}>
        <div className="row">
          <DropdownButton className="col col-1" id="dropdown-basic-button" title={category.current}>
            <Dropdown.Item onClick={(e) => setCurrentCategory(e.target.attributes.class.ownerElement.innerText)}>
              Alle
            </Dropdown.Item>
            {categoryData.map((e) => (
              <Dropdown.Item
                onClick={(e) => setCurrentCategory(e.target.attributes.class.ownerElement.innerText)}
                key={e.id}
              >
                {e.name}
              </Dropdown.Item>
            ))}
          </DropdownButton>
          <button onClick={() => setModalShow(true)} className="btn btn-primary col col-1">
            Tilføj vare
          </button>
          <button onClick={() => signOut()} className="btn btn-danger col col-1 offset-md-9">
            Log ud
          </button>
        </div>
      </div>
    </Fragment>
  );
};

const toolbarStyle = {
  marginTop: "80px",
  marginBottom: "20px",
  padding: "5px 20px",
};

export default connect(
  (state) => ({
    category: state.category,
  }),
  { setModalShow, setCurrentCategory }
)(Toolbar);
