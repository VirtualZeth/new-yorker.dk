import { SET_IS_AUTH } from "../actions/types";

const initialState = {
  isAuth: false,
};

const auth = (state = initialState, action) => {
  switch (action.type) {
    case SET_IS_AUTH:
      return { ...state, isAuth: action.payload };
    default:
      return state;
  }
};

export default auth;
