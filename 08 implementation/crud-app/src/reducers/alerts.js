import { SET_ALERT } from "../actions/types";

const initialState = [];

const toast = (state = initialState, action) => {
  const { type, payload } = action;
  switch (type) {
    case SET_ALERT:
      return [...state, payload];
    default:
      return state;
  }
};

export default toast;
