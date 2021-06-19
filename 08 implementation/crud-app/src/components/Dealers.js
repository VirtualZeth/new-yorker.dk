import React, { useState, useEffect } from "react";
import firebase from "../firebase";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const Dealers = () => {
  const [dealers, setDealers] = useState({
    list: [],
    current: "",
  });
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phone: "",
  });

  useEffect(() => {
    const close = firebase
      .firestore()
      .collection("dealers")
      .onSnapshot((querySnapshot) => {
        const items = [];
        querySnapshot.forEach((doc) => items.push(doc.data()));
        setDealers({ ...dealers, list: items });
      });
    return close;
  }, [dealers]);

  const onChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });

  const deleteDealer = (id) => {};

  const onSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <Form className="card p-3" onSubmit={(e) => onSubmit(e)}>
      <h3>Forhandlere</h3>
      <Form.Group className="my-2">
        <div className="row px-2">
          <select className="form-select col mx-1">
            {dealers.list.map((e) => (
              <option>{e.name}</option>
            ))}
          </select>
          <Button className="col-1 mx-1">+</Button>
        </div>
      </Form.Group>
      <Form.Group className="my-2">
        <Form.Label>Navn</Form.Label>
        <Form.Control name="name" value={formData.name} onChange={(e) => onChange(e)} />
      </Form.Group>
      <Form.Group className="my-2">
        <Form.Label>Email</Form.Label>
        <Form.Control name="email" value={formData.email} onChange={(e) => onChange(e)} />
      </Form.Group>
      <Form.Group className="my-2">
        <Form.Label>Telefonnummer</Form.Label>
        <Form.Control name="phone" value={formData.phone} onChange={(e) => onChange(e)} />
      </Form.Group>
      <div className="row px-1">
        <Button className="mt-2 mx-2 col" type="submit">
          Ændre
        </Button>
        <Button className="mt-2 mx-2 col btn-danger" onClick={deleteDealer}>
          Slet
        </Button>
      </div>
    </Form>
  );
};

export default Dealers;
