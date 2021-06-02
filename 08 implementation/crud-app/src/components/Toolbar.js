import React, { useState, Fragment } from "react";
import firebase from "../firebase";
import "firebase/firestore";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

const Toolbar = () => {
  const [productData, setProductData] = useState({
    productNumber: "",
    name: "",
    category: "",
    price: "",
  });
  const [modalShow, setModalShow] = useState(false);

  const onChange = (e) =>
    setProductData({ ...productData, [e.target.name]: e.target.value });

  const signOut = async () => await firebase.auth().signOut();
  const addProduct = async () => {
    firebase
      .firestore()
      .collection("products")
      .add(productData)
      .then((e) => {
        console.log(e.id);
      })
      .catch((error) => {
        console.log(error);
      });
    setProductData({
      productNumber: "",
      name: "",
      category: "",
      price: "",
    });
    setModalShow(false);
  };
  return (
    <Fragment>
      <Modal
        size="lg"
        aria-labelledby="contained-modal-title-vcenter"
        centered
        show={modalShow}
        onHide={() => setModalShow(false)}
      >
        <Modal.Body>
          <Form>
            <Row>
              <Col>
                <Form.Label>Kategori</Form.Label>
                <Form.Control
                  name="category"
                  value={productData.category}
                  onChange={(e) => {
                    onChange(e);
                  }}
                  placeholder="f.eks. Paneler"
                />
              </Col>
              <Col>
                <Form.Label>Varenummer</Form.Label>
                <Form.Control
                  name="productNumber"
                  value={productData.productNumber}
                  onChange={(e) => {
                    onChange(e);
                  }}
                  placeholder="f.eks. 20008"
                />
              </Col>
              <Col>
                <Form.Label>Navn</Form.Label>
                <Form.Control
                  name="name"
                  value={productData.name}
                  onChange={(e) => {
                    onChange(e);
                  }}
                  placeholder="f.eks. Akustikpanel"
                />
              </Col>
              <Col>
                <Form.Label>Pris</Form.Label>
                <Form.Control
                  name="price"
                  value={productData.price}
                  onChange={(e) => {
                    onChange(e);
                  }}
                  placeholder="f.eks. 318"
                />
              </Col>
            </Row>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={addProduct}>Tilføj vare</Button>
        </Modal.Footer>
      </Modal>
      <div className="card container" style={toolbarStyle}>
        <div className="row">
          <button
            onClick={() => setModalShow(true)}
            className="btn btn-primary col col-1"
          >
            Tilføj vare
          </button>
          <button
            onClick={() => signOut()}
            className="btn btn-primary col col-1 offset-md-10"
          >
            Log ud
          </button>
        </div>
      </div>
    </Fragment>
  );
};

const toolbarStyle = {
  marginTop: "120px",
  marginBottom: "20px",
  padding: "5px 20px",
};

export default Toolbar;
