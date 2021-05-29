import React, { useState, useEffect } from "react";
import firebase from "../../firebase";

const Dashboard = () => {
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
    <div>
      {products.map((e) => (
        <div key={products.id}>
          <h2>{e.name}</h2>
          <p>{e.price}</p>
        </div>
      ))}
    </div>
  );
};

export default Dashboard;
