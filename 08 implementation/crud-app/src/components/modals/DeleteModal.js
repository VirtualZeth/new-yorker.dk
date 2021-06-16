import React from "react";
import { connect } from "react-redux";
import { setDeleteModal } from "../../actions/modals";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import firebase from "../../firebase";
import { setAlert } from "../../actions/alerts";

const DeleteModal = ({ modals, setDeleteModal, setAlert }) => {
  const { deleteModal } = modals;
  const deleteProduct = (id) => {
    if (id !== undefined) {
      firebase
        .firestore()
        .collection("products")
        .doc(id)
        .delete()
        .then(() => setAlert("success", "Vare fjernet"))
        .catch((error) => setAlert("danger", error.code));
    }
    setDeleteModal(false, "");
  };
  return (
    <Modal
      size="sm"
      aria-labelledby="contained-modal-title-vcenter"
      centered
      show={deleteModal.isShow}
      onHide={() => setDeleteModal(false, "")}
    >
      <Modal.Body>
        <p>Er du sikker på at du vil slette varen?</p>
        <Button className="me-3" onClick={() => deleteProduct(deleteModal.id)}>
          Ja
        </Button>
        <Button className="me-3" onClick={() => setDeleteModal(false, "")}>
          Nej
        </Button>
      </Modal.Body>
    </Modal>
  );
};

export default connect(
  (state) => ({
    modals: state.modals,
  }),
  { setDeleteModal, setAlert }
)(DeleteModal);
