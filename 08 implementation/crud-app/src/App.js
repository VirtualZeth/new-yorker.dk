import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./store";

import Settings from "./components/views/Settings";
import Dashboard from "./components/views/Dashboard";
import Login from "./components/views/Login";
import Alert from "./components/Alert";

import "./styling/App.css";

const App = () => {
  return (
    <Provider store={store}>
      <Router>
        <div style={{ position: "absolute", top: "2.5vh", right: "2.5vh" }}>
          <Alert />
        </div>
        <Switch>
          <Route exact path="/" component={Login} />
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/settings" component={Settings} />
        </Switch>
      </Router>
    </Provider>
  );
};

export default App;
