import React, { useState, useEffect, Fragment } from "react";
import firebase from "../firebase";
import "firebase/firestore";
import { connect } from "react-redux";
import { setAddProductModalShow, setCategoryModalShow } from "../actions/modals";
import { setCurrentCategory } from "../actions/category";
import DropdownButton from "react-bootstrap/DropdownButton";
import Dropdown from "react-bootstrap/esm/Dropdown";
import AddProductModal from "./modals/AddProductModal";
import CategoryModal from "./modals/CategoryModal";

const Toolbar = ({ setAddProductModalShow, setCategoryModalShow, category, setCurrentCategory }) => {
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
  }, []);

  const signOut = () => firebase.auth().signOut();

  return (
    <Fragment>
      <CategoryModal categories={categoryData} />
      <AddProductModal categories={categoryData} />
      <div className="card container container-fluid" style={toolbarStyle}>
        <div className="row">
          <DropdownButton className="col col-2" id="dropdown-basic-button" title={category.current}>
            <Dropdown.Item onClick={(e) => setCurrentCategory(e.target.attributes.class.ownerElement.innerText)}>
              Vis alle
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
          <button onClick={() => setCategoryModalShow(true)} className="btn btn-primary col col-1-auto mb-1">
            Rediger kategorier
          </button>
          <button
            style={itemStyle}
            onClick={() => setAddProductModalShow(true)}
            className="btn btn-primary col col-1-auto mb-1"
          >
            Tilføj vare
          </button>
          <button onClick={() => signOut()} className="btn btn-danger col col-1 mb-1 offset-md-6">
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
  paddingTop: "7.5px",
  paddingBottom: "5px",
  paddingRight: "22.5px",
};

const itemStyle = {
  marginLeft: "5px",
};

export default connect(
  (state) => ({
    category: state.category,
  }),
  { setAddProductModalShow, setCurrentCategory, setCategoryModalShow }
)(Toolbar);
