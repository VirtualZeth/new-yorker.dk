import { SET_CURRENT_CATEGORY } from "../actions/types";

const initialState = {
  current: "Alle",
};

// eslint-disable-next-line
export default (state = initialState, action) => {
  switch (action.type) {
    case SET_CURRENT_CATEGORY:
      return { ...state, current: action.payload };
    default:
      return state;
  }
};
