import React, { useState } from "react";
import firebase from "../../firebase";
import "firebase/firestore";
import { connect } from "react-redux";
import { setCategoryModalShow } from "../../actions/modals";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

const CategoryModal = ({ modals, setCategoryModalShow, categories }) => {
  const { categoryModalShow } = modals;
  const selectDefault = "Vælg kategori";
  const [addCategoryName, setAddCategoryName] = useState("");
  const [selectedCategoryName, setSelectedCategoryName] = useState("");

  const onChange = (e) => setAddCategoryName(e.target.value);

  const addCategory = async () => {
    await firebase
      .firestore()
      .collection("categories")
      .add({ name: addCategoryName })
      .then((e) => {
        console.log(`Category with id ${e.id} added!`);
      })
      .catch((error) => console.log(error));
    setAddCategoryName("");
    setCategoryModalShow(false);
  };

  const deleteCategory = async () => {
    if (selectedCategoryName !== selectDefault && selectedCategoryName !== "") {
      const categoryId = categories.filter((e) => e.name === selectedCategoryName)[0].id;
      await firebase
        .firestore()
        .collection("categories")
        .doc(categoryId)
        .delete()
        .then(() => console.log("Category deleted successfully!"))
        .catch((error) => console.log(error));
      setSelectedCategoryName("");
      setCategoryModalShow(false);
    }
  };

  return (
    <Modal
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
      show={categoryModalShow}
      onHide={() => setCategoryModalShow(false)}
    >
      <Modal.Body>
        <Form>
          <Row className="mb-3">
            <Form.Group as={Col} className="col col-3">
              <Form.Label>Kategorinavn</Form.Label>
              <Form.Control
                name="name"
                value={addCategoryName}
                onChange={(e) => {
                  onChange(e);
                }}
                placeholder="Navn"
              />
            </Form.Group>
            <Form.Group as={Col} className="col col-3">
              <Form.Label>Vælg kategori</Form.Label>
              <select onChange={(e) => setSelectedCategoryName(e.target.value)} className="form-select">
                <option>{selectDefault}</option>
                {categories.map((e) => (
                  <option data-id={e.id} key={e.id} value={e.name}>
                    {e.name}
                  </option>
                ))}
              </select>
            </Form.Group>
          </Row>
          <Row>
            <Form.Group as={Col} className="col col-3">
              <Button onClick={addCategory}>Tilføj kategori</Button>
            </Form.Group>
            <Form.Group as={Col} className="col col-3">
              <Button onClick={deleteCategory} className="btn-danger">
                Slet kategori
              </Button>
            </Form.Group>
          </Row>
        </Form>
      </Modal.Body>
    </Modal>
  );
};

export default connect(
  (state) => ({
    modals: state.modals,
  }),
  { setCategoryModalShow }
)(CategoryModal);
