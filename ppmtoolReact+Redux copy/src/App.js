import React from 'react';

import './App.css';
import Dashboard from './Components/Dashboard';
import Header from './Components/Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css"
import {BrowserRouter as Router, Route } from "react-router-dom"
import AddProject from './Components/Project/AddProject';
import UpdateProject from './Components/Project/UpdateProject';
import {Provider} from "react-redux"
import store from './store'



function App() {
  return (
    <Provider store={store}>
    <Router>
      <div className="App">
        <Header />
        <Route exact path="/dashboard" component={Dashboard} />
        <Route exact path="/addProject" component={AddProject} />
        <Route exact path="/updateProject/:id" component={UpdateProject} />
      </div>
    </Router>
    </Provider>
  );
}

export default App;
