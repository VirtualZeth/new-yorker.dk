import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import firebase from "../firebase";
import { TrashFill, PencilSquare } from "react-bootstrap-icons/";
import Button from "react-bootstrap/Button";
import { setDeleteModal } from "../actions/modals";
import DeleteModal from "./modals/DeleteModal";
import { setAlert } from "../actions/alerts";

const Table = ({ category, setDeleteModal }) => {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [productData, setProductData] = useState({
    id: "",
    productNumber: "",
    name: "",
    price: "",
    category: "",
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

  useEffect(() => {
    firebase
      .firestore()
      .collection("categories")
      .onSnapshot((querySnapshot) => {
        const items = [];
        querySnapshot.forEach((doc) => items.push(doc.data()));
        setCategories(items);
      });
  }, []);

  const onChange = (e) => setProductData({ ...productData, [e.target.name]: e.target.value });

  const editProduct = () => {
    firebase
      .firestore()
      .collection("products")
      .doc(productData.id)
      .set(productData)
      .then(() => {
        setAlert("success", "Varen blev ændret", true);
      })
      .catch((error) => {
        setAlert("danger", error.code);
      });
    setProductData({
      id: "",
      productNumber: "",
      name: "",
      price: "",
    });
  };

  const onCategoryChange = (name) => setProductData({ ...productData, category: name });

  const tableItem = (item) => {
    if (item.category !== category.current && category.current !== "Vis alle") return null;
    if (item.id === productData.id) {
      return (
        <tr key={item.id}>
          <td>
            <select onChange={(e) => onCategoryChange(e.target.value)} defaultValue={item.name} className="form-select">
              <option name={item.category} value={item.category}>
                {item.category}
              </option>
              {categories
                .filter((e) => item.category !== e.name)
                .map((e) => (
                  <option name={e.name} key={`${item.id}&&${e.name}`}>
                    {e.name}
                  </option>
                ))}
            </select>
          </td>
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
          <td>{item.category}</td>
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
            <div
              data-id={item.id}
              style={{ cursor: "pointer" }}
              onClick={(e) => setDeleteModal(true, e.target.dataset.id)}
            >
              <TrashFill color="red" size={20} style={{ pointerEvents: "none" }} />
            </div>
          </td>
        </tr>
      );
  };
  return (
    <div className="card p-3">
      <DeleteModal />
      <table className="table">
        <thead>
          <tr>
            <th scope="col">Kategori</th>
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

export default connect(
  (state) => ({
    category: state.category,
  }),
  { setDeleteModal }
)(Table);
