import { SET_IS_AUTH } from "../actions/types";

const initialState = {
  isAuth: false,
};

// eslint-disable-next-line
export default (state = initialState, action) => {
  switch (action.type) {
    case SET_IS_AUTH:
      return action.payload;
    default:
      return state;
  }
};
