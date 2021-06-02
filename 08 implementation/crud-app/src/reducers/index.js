import { combineReducers } from "redux";
import auth from "./auth";
import modals from "./modals";

export default combineReducers({ auth, modals });
