import React, { Component } from 'react'
import Item from './Project/Item';
import CreateProjectBouttom from './Project/CreateProjectButtom';
import {connect} from "react-redux"
import { getProjects } from "../actions/projectAction"
import PropTypes from "prop-types";
class Dashboard extends Component {

    componentDidMount () {
        this.props.getProjects();
    }

    render() {
        const { projects } = this.props.project;
        return (
            <div>
                <h1>This is a Component.</h1>
                <CreateProjectBouttom />
              <br />
              <hr />
              {projects.map(project => (
                <Item key={project.id} project={project} />
              ))}
            </div>
        )
    }
}
Dashboard.propTypes = {
    project: PropTypes.object.isRequired,
    getProjects: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
    project: state.project

});

export default connect(mapStateToProps, {getProjects})(Dashboard);
