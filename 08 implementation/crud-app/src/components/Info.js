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
    const close = firebase
      .firestore()
      .collection("info")
      .doc("info")
      .onSnapshot((doc) => {
        setFormData(doc.data());
      });
    return close;
  }, []);

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
    <Form onSubmit={(e) => onSubmit(e)}>
      <Form.Group>
        <Form.Label>Email</Form.Label>
        <Form.Control name="email" value={formData.email} onChange={(e) => onChange(e)} />
      </Form.Group>
      <Form.Group>
        <Form.Label>Hjemmesidekatalog</Form.Label>
        <Form.Control name="website" value={formData.website} onChange={(e) => onChange(e)} />
      </Form.Group>
      <Form.Group>
        <Form.Label>Telefonnummer</Form.Label>
        <Form.Control name="phone" value={formData.phone} onChange={(e) => onChange(e)} />
      </Form.Group>
      <Button type="submit">Ændre</Button>
    </Form>
  );
};

export default connect(null, { setAlert })(Info);
