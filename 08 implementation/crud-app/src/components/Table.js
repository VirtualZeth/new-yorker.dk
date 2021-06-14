import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import firebase from "../firebase";
import { TrashFill, PencilSquare } from "react-bootstrap-icons/";
import Button from "react-bootstrap/Button";

const Table = ({ category }) => {
  const [products, setProducts] = useState([]);
  const [productData, setProductData] = useState({
    id: "",
    productNumber: "",
    name: "",
    price: "",
  });

  useEffect(() => {
    const close = firebase
      .firestore()
      .collection("products")
      .onSnapshot((querySnapshot) => {
        const items = [];
        querySnapshot.forEach((doc) => items.push({ ...doc.data(), id: doc.id }));
        setProducts(items);
      });
    return close;
  }, []);

  const deleteProduct = async (id) => {
    if (id !== undefined) {
      await firebase
        .firestore()
        .collection("products")
        .doc(id)
        .delete()
        .then(() => console.log("Product deleted successfully!"))
        .catch((error) => console.log(error));
    }
  };

  const onChange = (e) => setProductData({ ...productData, [e.target.name]: e.target.value });

  const editProduct = async () => {
    await firebase
      .firestore()
      .collection("products")
      .doc(productData.id)
      .set(productData)
      .then(() => {
        console.log("Document successfully edited!");
      })
      .catch((error) => {
        console.error("Error writing document: ", error);
      });
    setProductData({
      id: "",
      productNumber: "",
      name: "",
      price: "",
    });
  };

  const tableItem = (item) => {
    if (item.category !== category.current && category.current !== "Vis alle") return null;
    if (item.id === productData.id) {
      return (
        <tr key={item.id}>
          <td>
            <input
              name="productNumber"
              onChange={(e) => onChange(e)}
              type="text"
              className="form-control"
              placeholder={item.productNumber}
              defaultValue={item.productNumber}
            />
          </td>
          <td>
            <input
              name="name"
              onChange={(e) => onChange(e)}
              type="text"
              className="form-control"
              placeholder={item.name}
              defaultValue={item.name}
            />
          </td>
          <td>
            <input
              name="price"
              onChange={(e) => onChange(e)}
              type="text"
              className="form-control"
              placeholder={item.price}
              defaultValue={item.price}
            />
          </td>
          <td>
            <Button onClick={editProduct}>Færdig</Button>
          </td>
        </tr>
      );
    } else
      return (
        <tr key={item.id}>
          <td>{item.productNumber}</td>
          <td>{item.name}</td>
          <td>{item.price}</td>
          <td style={{ display: "inline-flex" }}>
            <div
              data-id={item.id}
              style={{ marginRight: "10px", cursor: "pointer" }}
              onClick={(e) => setProductData(products.filter((product) => product.id === e.target.dataset.id)[0])}
            >
              <PencilSquare color="orange" size={20} style={{ pointerEvents: "none" }} />
            </div>
            <div data-id={item.id} style={{ cursor: "pointer" }} onClick={(e) => deleteProduct(e.target.dataset.id)}>
              <TrashFill color="red" size={20} style={{ pointerEvents: "none" }} />
            </div>
          </td>
        </tr>
      );
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
        <tbody>{products.map((e) => tableItem(e))}</tbody>
      </table>
    </div>
  );
};

export default connect((state) => ({
  category: state.category,
}))(Table);
