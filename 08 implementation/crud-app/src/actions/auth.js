import { SET_IS_AUTH } from "./types";

export const setIsAuth = (value) => (dispatch) => {
  dispatch({
    type: SET_IS_AUTH,
    payload: value,
  });
};
