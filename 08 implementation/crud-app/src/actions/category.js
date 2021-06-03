import { SET_CURRENT_CATEGORY } from "./types";

export const setCurrentCategory = (value) => (dispatch) => {
  dispatch({
    type: SET_CURRENT_CATEGORY,
    payload: value,
  });
};
