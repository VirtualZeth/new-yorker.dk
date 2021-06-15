import React from "react";
import { connect } from "react-redux";
import { setDeleteModal } from "../../actions/modals";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import firebase from "../../firebase";

const DeleteModal = ({ modals, setDeleteModal }) => {
  const { deleteModal } = modals;
  const deleteProduct = (id) => {
    if (id !== undefined) {
      firebase
        .firestore()
        .collection("products")
        .doc(id)
        .delete()
        .then(() => console.log("Product deleted successfully!"))
        .catch((error) => console.log(error));
    }
    setDeleteModal(false, "");
  };
  return (
    <Modal
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
      show={deleteModal.isShow}
      onHide={() => setDeleteModal(false, "")}
    >
      <Modal.Body>
        <Button onClick={() => deleteProduct(deleteModal.id)}>Ja</Button>
        <Button onClick={() => setDeleteModal(false, "")}>Nej</Button>
      </Modal.Body>
    </Modal>
  );
};

export default connect(
  (state) => ({
    modals: state.modals,
  }),
  { setDeleteModal }
)(DeleteModal);
