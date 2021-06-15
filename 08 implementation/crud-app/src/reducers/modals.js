import { SET_ADD_PRODUCT_MODAL_SHOW, SET_CATEGORY_MODAL_SHOW } from "../actions/types";

const initialState = {
  addProductModalShow: false,
  categoryModalShow: false,
};

const modals = (state = initialState, action) => {
  switch (action.type) {
    case SET_ADD_PRODUCT_MODAL_SHOW:
      return { ...state, addProductModalShow: action.payload };
    case SET_CATEGORY_MODAL_SHOW:
      return { ...state, categoryModalShow: action.payload };
    default:
      return state;
  }
};

export default modals;
