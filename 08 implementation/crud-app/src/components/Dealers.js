import React, { useState, useEffect, Fragment } from "react";
import { connect } from "react-redux";
import { setAddDealerModalShow } from "../actions/modals";
import firebase from "../firebase";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import AddDealerModal from "./modals/AddDealerModal";

const Dealers = ({ setAddDealerModalShow }) => {
  const [dealers, setDealers] = useState({
    list: [],
    current: {},
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
        querySnapshot.forEach((doc) => items.push({ ...doc.data(), id: doc.id }));
        if (items.length > 0) {
          setDealers({ list: items, current: items[0] });
          setFormData(items[0]);
        }
      });
    return close;
  }, []);

  const onChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });

  const deleteDealer = () => firebase.firestore().collection("dealers").doc(dealers.current.id).delete();

  const onSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <Fragment>
      <AddDealerModal />
      <Form className="card p-3" onSubmit={(e) => onSubmit(e)}>
        <h3>Forhandlere</h3>
        <Form.Group className="my-2">
          <div className="row px-2">
            <select className="form-select col mx-1">
              <option key={dealers.current.id}>{dealers.current.name}</option>
              {dealers.list
                .filter((e) => e.id !== dealers.current.id)
                .map((e) => (
                  <option key={e.id}>{e.name}</option>
                ))}
            </select>
            <Button onClick={() => setAddDealerModalShow(true)} className="col-1 mx-1">
              +
            </Button>
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
    </Fragment>
  );
};

export default connect(null, { setAddDealerModalShow })(Dealers);
