import React, { useState, useEffect, Fragment } from "react";
import { connect } from "react-redux";
import { setAddDealerModalShow } from "../actions/modals";
import { setAlert } from "../actions/alerts";
import firebase from "../firebase";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import AddDealerModal from "./modals/AddDealerModal";

const Dealers = ({ setAddDealerModalShow, setAlert }) => {
  const initialState = {
    name: "",
    email: "",
    phone: "",
  };
  const [dealers, setDealers] = useState([]);
  const [current, setCurrent] = useState(initialState);

  useEffect(() => {
    const close = firebase
      .firestore()
      .collection("dealers")
      .onSnapshot((querySnapshot) => {
        const items = [];
        querySnapshot.forEach((doc) => items.push({ ...doc.data(), id: doc.id }));
        if (items.length > 0) {
          setDealers(items);
          setCurrent({ ...items[0], selectName: items[0].name, id: items[0].id });
        }
      });
    return close;
  }, []);

  const onChange = (e) => setCurrent({ ...current, [e.target.name]: e.target.value });

  const onCurrentChange = (e) =>
    setCurrent({ ...dealers.filter((el) => e.target.value === el.name)[0], selectName: e.target.value });
  const deleteDealer = () =>
    firebase
      .firestore()
      .collection("dealers")
      .doc(current.id)
      .delete()
      .then(() => {
        const newDealers = dealers.filter((e) => e.id !== current.id);
        setDealers(newDealers);
        setAlert("success", `Forhandler ${current.name} slettet`, true);
        if (newDealers.length < 1) {
          setCurrent(initialState);
        }
      })
      .catch((error) => {
        setAlert("danger", error.code);
        console.log(error);
      });

  const onSubmit = (e) => {
    e.preventDefault();
    firebase
      .firestore()
      .collection("dealers")
      .doc(current.id)
      .set(current)
      .then(() => {
        setCurrent({ ...current, selectName: current.name });
        setAlert("success", "Forhandler ændret", true);
      })
      .catch((error) => setAlert("danger", error.code));
  };

  return (
    <Fragment>
      <AddDealerModal />
      <Form className="card p-3" onSubmit={(e) => onSubmit(e)}>
        <h3>Forhandlere</h3>
        <Form.Group className="my-2">
          <div className="row px-2">
            <select onChange={(e) => onCurrentChange(e)} className="form-select col mx-1">
              <option key={current.id}>{current.selectName}</option>
              {dealers
                .filter((e) => e.id !== current.id)
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
          <Form.Control name="name" value={current.name} onChange={(e) => onChange(e)} />
        </Form.Group>
        <Form.Group className="my-2">
          <Form.Label>Email</Form.Label>
          <Form.Control name="email" value={current.email} onChange={(e) => onChange(e)} />
        </Form.Group>
        <Form.Group className="my-2">
          <Form.Label>Telefonnummer</Form.Label>
          <Form.Control name="phone" value={current.phone} onChange={(e) => onChange(e)} />
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

export default connect(null, { setAddDealerModalShow, setAlert })(Dealers);
