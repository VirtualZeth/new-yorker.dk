import { combineReducers } from "redux";
import auth from "./auth";
import modals from "./modals";
import category from "./category";
import alerts from "./alerts";

export default combineReducers({ auth, modals, category, alerts });
