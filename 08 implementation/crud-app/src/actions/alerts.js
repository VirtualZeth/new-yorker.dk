import { v4 } from "uuid";
import { SET_ALERT } from "./types";

export const setAlert = (displayType, code) => (dispatch) => {
  const id = v4();
  const msg = (() => {
    switch (code) {
      case "auth/user-not-found":
        return "Det indtastede brugernavn blev ikke fundet";
      case "auth/wrong-password":
        return "Det indtastede password er forkert";
      default:
        return "Vi støtte på en fejl";
    }
  })();

  dispatch({
    type: SET_ALERT,
    payload: { id, displayType, msg },
  });
};
