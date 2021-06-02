import React, { useState, useEffect } from "react";
import firebase from "../firebase";
import { TrashFill, PencilSquare } from "react-bootstrap-icons/";

const Table = () => {
  const [products, setProducts] = useState([]);
  useEffect(() => {
    (() => {
      firebase
        .firestore()
        .collection("products")
        .onSnapshot((querySnapshot) => {
          const items = [];
          querySnapshot.forEach((doc) => items.push({ ...doc.data(), id: doc.id }));
          setProducts(items);
        });
    })();
  }, []);

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
  };

  return (
    <div className="card container">
      <table className="table">
        <thead>
          <tr>
            <th scope="col">Varenummer</th>
            <th scope="col">Navn</th>
            <th scope="col">Pris</th>
            <th scope="col">Rediger/Slet</th>
          </tr>
        </thead>
        <tbody>
          {products.map((e) => (
            <tr key={e.id}>
              <th scope="row">{e.productNumber}</th>
              <td>{e.name}</td>
              <td>{e.price}</td>
              <td style={{ display: "inline-flex" }}>
                <div>
                  <PencilSquare color="orange" size={20} style={{ marginRight: "10px", cursor: "pointer" }} />
                </div>
                <div data-id={e.id} style={{ cursor: "pointer" }} onClick={(e) => deleteProduct(e.target.dataset.id)}>
                  <TrashFill color="red" size={20} style={{ pointerEvents: "none" }} />
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Table;
