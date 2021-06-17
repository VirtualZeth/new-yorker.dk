import React, { Fragment, useEffect, useState } from "react";
import { connect } from "react-redux";
import firebase from "../../firebase";
import { setAddProductModalShow, setCategoryModalShow } from "../../actions/modals";
import { setCurrentCategory } from "../../actions/category";
import DropdownButton from "react-bootstrap/DropdownButton";
import Dropdown from "react-bootstrap/esm/Dropdown";
import AddProductModal from "../modals/AddProductModal";
import CategoryModal from "../modals/CategoryModal";

const DashboardTool = ({ btn2, setAddProductModalShow, setCategoryModalShow, category, setCurrentCategory }) => {
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

  return (
    <Fragment>
      <CategoryModal categories={categoryData} />
      <AddProductModal categories={categoryData} />
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
      <button onClick={() => setCategoryModalShow(true)} className={btn2}>
        Rediger kategorier
      </button>
      <button onClick={() => setAddProductModalShow(true)} className={`${btn2} me-auto`}>
        Tilføj vare
      </button>
    </Fragment>
  );
};

export default connect(
  (state) => ({
    category: state.category,
  }),
  { setAddProductModalShow, setCurrentCategory, setCategoryModalShow }
)(DashboardTool);
