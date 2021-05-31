import { SET_IS_AUTH } from "./types";

export const setIsAuth = (isAuth) => (dispatch) => {
  dispatch({
    type: SET_IS_AUTH,
    payload: { isAuth },
  });
};
