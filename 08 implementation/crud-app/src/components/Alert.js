import React from "react";
import { connect } from "react-redux";

const Alert = ({ alerts }) =>
  alerts.length > 0 &&
  alerts.map((e) => (
    <div style={{ zIndex: "99" }} key={e.id} className={`alert alert-${e.displayType}`}>
      {e.message}
    </div>
  ));
export default connect((state) => ({
  alerts: state.alerts,
}))(Alert);
