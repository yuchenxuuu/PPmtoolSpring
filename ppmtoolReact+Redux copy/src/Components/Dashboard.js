import React, { Component } from 'react'
import Item from './Project/Item';
import CreateProjectBouttom from './Project/CreateProjectButtom';
import {connect} from "react-redux"
import { getProjects } from "../actions/projectAction"

export default class Dashboard extends Component {
    render() {
        return (
            <div>
                <h1>This is a Component.</h1>
                <CreateProjectBouttom />
              <br />
              <hr />
                <Item />
                <Item />
            </div>
        )
    }
}
