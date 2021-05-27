import React, { useState, useEffect } from "react";
import firebase from "./firebase";

const App = () => {
  const [items, setItems] = useState([]);

  useEffect(() => {
    getItems();
  }, []);

  const ref = firebase.firestore().collection("items");

  let getItems = () => {
    ref.onSnapshot((querySnapshot) => {
      const list = [];
      querySnapshot.forEach((e) => list.push(e.data()));
      setItems(list);
    });
  };

  return (
    <div>
      {items.map((e) => (
        <div key={items.id}>
          <h2>{e.name}</h2>
          <p>{e.price}</p>
        </div>
      ))}
    </div>
  );
};

export default App;
