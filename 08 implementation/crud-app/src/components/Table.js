import React, { useState, useEffect } from "react";
import firebase from "../firebase";

const Table = () => {
  const [products, setProducts] = useState([]);
  useEffect(() => {
    (() => {
      firebase
        .firestore()
        .collection("products")
        .onSnapshot((querySnapshot) => {
          const items = [];
          querySnapshot.forEach((e) => items.push(e.data()));
          setProducts(items);
        });
    })();
  }, []);

  return (
    <div className="card container">
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Varenummer</th>
            <th scope="col">Navn</th>
            <th scope="col">Pris</th>
          </tr>
        </thead>
        <tbody>
          {products.map((e) => (
            <tr>
              <th scope="row">{e.productNumber}</th>
              <td>{e.name}</td>
              <td>{e.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Table;
