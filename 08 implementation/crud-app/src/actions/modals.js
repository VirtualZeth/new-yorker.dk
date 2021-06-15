import { SET_ADD_PRODUCT_MODAL_SHOW, SET_CATEGORY_MODAL_SHOW, SET_DELETE_MODAL } from "./types";

export const setAddProductModalShow = (value) => (dispatch) => {
  dispatch({
    type: SET_ADD_PRODUCT_MODAL_SHOW,
    payload: value,
  });
};

export const setCategoryModalShow = (value) => (dispatch) => {
  dispatch({
    type: SET_CATEGORY_MODAL_SHOW,
    payload: value,
  });
};

export const setDeleteModal = (value, id) => (dispatch) => {
  dispatch({
    type: SET_DELETE_MODAL,
    payload: { isShow: value, id },
  });
};
