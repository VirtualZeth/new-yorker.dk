import { SET_ADD_PRODUCT_MODAL_SHOW } from "./types";

export const setModalShow = (value) => (dispatch) => {
  dispatch({
    type: SET_ADD_PRODUCT_MODAL_SHOW,
    payload: value,
  });
};
