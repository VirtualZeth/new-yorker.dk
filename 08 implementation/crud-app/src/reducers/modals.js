import { SET_ADD_PRODUCT_MODAL_SHOW } from "../actions/types";

const initialState = {
  modalShow: false,
};

// eslint-disable-next-line
export default (state = initialState, action) => {
  switch (action.type) {
    case SET_ADD_PRODUCT_MODAL_SHOW:
      return { ...state, modalShow: action.payload };
    default:
      return state;
  }
};
