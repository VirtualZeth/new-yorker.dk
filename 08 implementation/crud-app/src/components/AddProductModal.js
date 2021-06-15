import React, { useState } from "react";
import firebase from "../firebase";
import "firebase/firestore";
import { connect } from "react-redux";
import { setAddProductModalShow } from "../actions/modals";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { setAlert } from "../actions/alerts";

const AddProductModal = ({ modals, setAddProductModalShow, categories, setAlert }) => {
  const { addProductModalShow } = modals;
  const selectDefault = "Vælg kategori";
  const [productData, setProductData] = useState({
    category: "",
    productNumber: "",
    name: "",
    price: "",
  });

  const onChange = (e) => setProductData({ ...productData, [e.target.name]: e.target.value });

  const addProduct = async () => {
    if (productData.category !== selectDefault)
      await firebase
        .firestore()
        .collection("products")
        .add(productData)
        .then((e) => {
          setAlert("success", `${productData.name} tilføjet`, true);
        })
        .catch((error) => {
          setAlert("danger", error.code);
        });
    setProductData({
      category: selectDefault,
      productNumber: "",
      name: "",
      price: "",
    });
    setAddProductModalShow(false);
  };

  return (
    <Modal
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
      show={addProductModalShow}
      onHide={() => setAddProductModalShow(false)}
    >
      <Modal.Body>
        <Form>
          <Row className="mb-3">
            <Form.Group as={Col} controlId="category">
              <Form.Label>Kategori</Form.Label>
              <select
                onChange={(e) => setProductData({ ...productData, category: e.target.value })}
                className="form-select"
                aria-label="Default select example"
              >
                <option>{selectDefault}</option>
                {categories.map((e) => (
                  <option key={e.id} value={e.name}>
                    {e.name}
                  </option>
                ))}
              </select>
            </Form.Group>
            <Form.Group as={Col} controlId="productNumber">
              <Form.Label>Varenummer</Form.Label>
              <Form.Control
                name="productNumber"
                value={productData.productNumber}
                onChange={(e) => {
                  onChange(e);
                }}
                placeholder="f.eks. 20008"
              />
            </Form.Group>
            <Form.Group as={Col} controlId="name">
              <Form.Label>Navn</Form.Label>
              <Form.Control
                name="name"
                value={productData.name}
                onChange={(e) => {
                  onChange(e);
                }}
                placeholder="f.eks. Akustikpanel"
              />
            </Form.Group>
            <Form.Group as={Col} controlId="price">
              <Form.Label>Pris</Form.Label>
              <Form.Control
                name="price"
                value={productData.price}
                onChange={(e) => {
                  onChange(e);
                }}
                placeholder="f.eks. 318"
              />
            </Form.Group>
          </Row>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={addProduct}>Tilføj vare</Button>
      </Modal.Footer>
    </Modal>
  );
};

export default connect(
  (state) => ({
    modals: state.modals,
  }),
  { setAddProductModalShow, setAlert }
)(AddProductModal);
