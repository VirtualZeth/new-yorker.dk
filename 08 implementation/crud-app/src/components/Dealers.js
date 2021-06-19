import React, { useState } from "react";
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

  const onChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });

  const deleteDealer = () => {};

  const onSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <Form className="card p-3" onSubmit={(e) => onSubmit(e)}>
      <h3>Forhandlere</h3>
      <Form.Group>
        <select></select>
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
