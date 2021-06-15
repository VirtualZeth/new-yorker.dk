import { SET_CURRENT_CATEGORY } from "../actions/types";

const initialState = {
  current: "Vis alle",
};

const category = (state = initialState, action) => {
  switch (action.type) {
    case SET_CURRENT_CATEGORY:
      return { ...state, current: action.payload };
    default:
      return state;
  }
};

export default category;
