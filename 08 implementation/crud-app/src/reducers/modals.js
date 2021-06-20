import {
  SET_ADD_DEALER_MODAL_SHOW,
  SET_ADD_PRODUCT_MODAL_SHOW,
  SET_CATEGORY_MODAL_SHOW,
  SET_DELETE_MODAL,
} from "../actions/types";

const initialState = {
  addProductModalShow: false,
  categoryModalShow: false,
  addDealerModalShow: false,
  deleteModal: {
    isShow: false,
    id: "",
  },
};

const modals = (state = initialState, action) => {
  const { type, payload } = action;
  switch (type) {
    case SET_ADD_PRODUCT_MODAL_SHOW:
      return { ...state, addProductModalShow: payload };
    case SET_CATEGORY_MODAL_SHOW:
      return { ...state, categoryModalShow: payload };
    case SET_ADD_DEALER_MODAL_SHOW:
      return { ...state, addDealerModalShow: payload };
    case SET_DELETE_MODAL:
      return { ...state, deleteModal: payload };
    default:
      return state;
  }
};

export default modals;
