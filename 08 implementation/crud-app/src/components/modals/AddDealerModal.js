import React, { useState } from "react";
import { connect } from "react-redux";
import { setAddDealerModalShow } from "../../actions/modals";
import { setAlert } from "../../actions/alerts";
import firebase from "../../firebase";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const AddDealerModal = ({ modals, setAddDealerModalShow, setAlert }) => {
  const { addDealerModalShow } = modals;
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phone: "",
  });

  const onChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });
  const submit = () => {
    firebase
      .firestore()
      .collection("dealers")
      .add(formData)
      .then(() => {
        setAlert("success", "Forhandler tilføjet", true);
        setFormData({
          name: "",
          email: "",
          phone: "",
        });
        setAddDealerModalShow(false);
      })
      .catch((error) => setAlert("danger", error.code));
  };

  return (
    <Modal size="lg" centered show={addDealerModalShow} onHide={() => setAddDealerModalShow(false)}>
      <Modal.Body>
        <Form>
          <Form.Group className="my-2">
            <Form.Label>Navn</Form.Label>
            <Form.Control value={formData.name} name="name" onChange={(e) => onChange(e)} />
          </Form.Group>
          <Form.Group className="my-2">
            <Form.Label>Email</Form.Label>
            <Form.Control value={formData.email} name="email" onChange={(e) => onChange(e)} />
          </Form.Group>
          <Form.Group className="my-2">
            <Form.Label>Telefonnummer</Form.Label>
            <Form.Control value={formData.phone} name="phone" onChange={(e) => onChange(e)} />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={submit}>Tilføj</Button>
      </Modal.Footer>
    </Modal>
  );
};

export default connect(
  (state) => ({
    modals: state.modals,
  }),
  { setAddDealerModalShow, setAlert }
)(AddDealerModal);
