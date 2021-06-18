import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import { setAlert } from "../actions/alerts";
import firebase from "../firebase";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const Info = ({ setAlert }) => {
  const [formData, setFormData] = useState({
    email: "",
    website: "",
    phone: "",
  });

  useEffect(() => {
    firebase
      .firestore()
      .collection("info")
      .doc("info")
      .get()
      .then((doc) => setFormData(doc.data()))
      .catch((error) => {
        setAlert("danger", error.code);
      });
  }, [setAlert]);

  const onChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });
  const onSubmit = (e) => {
    e.preventDefault();
    firebase
      .firestore()
      .collection("info")
      .doc("info")
      .set(formData)
      .then(setAlert("success", "Ændringer gemt", true))
      .catch((error) => {
        setAlert("danger", error.code);
      });
  };
  return (
    <Form className="card p-3" onSubmit={(e) => onSubmit(e)}>
      <h3>Information</h3>
      <Form.Group className="my-2">
        <Form.Label>Email</Form.Label>
        <Form.Control name="email" value={formData.email} onChange={(e) => onChange(e)} />
      </Form.Group>
      <Form.Group className="my-2">
        <Form.Label>Hjemmesidekatalog</Form.Label>
        <Form.Control name="website" value={formData.website} onChange={(e) => onChange(e)} />
      </Form.Group>
      <Form.Group className="my-2">
        <Form.Label>Telefonnummer</Form.Label>
        <Form.Control name="phone" value={formData.phone} onChange={(e) => onChange(e)} />
      </Form.Group>
      <Button className="mt-2" type="submit">
        Ændre
      </Button>
    </Form>
  );
};

export default connect(null, { setAlert })(Info);
