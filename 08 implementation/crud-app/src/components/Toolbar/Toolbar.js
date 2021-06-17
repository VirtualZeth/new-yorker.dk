import React from "react";
import firebase from "../../firebase";
import "firebase/firestore";
import { Link } from "react-router-dom";
import DashboardTool from "./DashboardTool";
import SettingsTool from "./SettingsTool";

const Toolbar = ({ view }) => {
  const btn2 = "btn btn-primary col col-2 mx-1";
  const btn = "btn btn-primary col col-1 mx-1";

  const signOut = () => firebase.auth().signOut();

  const selectTool = (() => {
    switch (view) {
      case "Dashboard":
        return <DashboardTool btn2={btn2} />;
      case "Settings":
        return <SettingsTool btn={btn} />;
      default:
        return null;
    }
  })();

  return (
    <div className="card container-fluid my-4">
      <div className="row p-2">
        {selectTool}
        {view !== "Settings" && (
          <Link to="/indstillinger" className={`btn btn-primary col col-2 mx-1 col-auto`}>
            Indstillinger
          </Link>
        )}
        <button onClick={() => signOut()} className="btn btn-danger col col-1 mx-1 col-auto">
          Log ud
        </button>
      </div>
    </div>
  );
};

export default Toolbar;
