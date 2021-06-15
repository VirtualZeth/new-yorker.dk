import React from "react";
import { connect } from "react-redux";

const Alert = ({ alerts }) =>
  alerts.length > 0 && alerts.map((e) => <div className={`alert alert-${e.displayType}`}>{e.msg}</div>);
export default connect((state) => ({
  alerts: state.alerts,
}))(Alert);
