import React, { useState, useEffect } from "react";
import firebase from "../firebase";

const Table = () => {
  const [products, setProducts] = useState([]);
  useEffect(() => {
    const ref = firebase.firestore().collection("products");

    (() => {
      ref.onSnapshot((querySnapshot) => {
        const items = [];
        querySnapshot.forEach((e) => items.push(e.data()));
        setProducts(items);
      });
    })();
  }, []);

  return (
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
  );
};

export default Table;
