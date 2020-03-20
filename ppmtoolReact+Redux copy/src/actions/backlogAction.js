import axios from "axios"
import {GET_ERROR, GET_BACKLOG, GET_PROJECTS_TASKS,DELETE_PROJECT_TASKS } from "./types";

export const addProjectTask = (backlog_id, project_task, history) => async dispatch => {
    try {
        const res = await axios.post
        (`/api/backlog/${backlog_id}`, project_task);
        history.push(`/projectBoard/${backlog_id}`);
        dispatch({
            type:GET_ERROR,
            payload: {}
        });
    } catch (error) {
        dispatch({
            type:GET_ERROR,
            payload:error.response.data
            }
        );
    }
};

export const getBacklog = backlog_id => async dispatch => {
    try {
      const res = await axios.get(`/api/backlog/${backlog_id}`);
      dispatch({
        type: GET_BACKLOG,
        payload: res.data
      });
    } catch (error) {
        dispatch({
            type:GET_ERROR,
            payload:error.response.data
            }
        );
    }
  };
  export const getProjectTask = (
    backlog_id,
    pt_id,
    history
  ) => async dispatch => {
    try {
      const res = await axios.get(`/api/backlog/${backlog_id}/${pt_id}`);
      dispatch({
        type: GET_PROJECTS_TASKS,
        payload: res.data
      });
    } catch (err) {
      history.push("/dashboard");
    }
  };
  
  export const updateProjectTask = (
    backlog_id,
    pt_id,
    project_task,
    history
  ) => async dispatch => {
    try {
      await axios.patch(`/api/backlog/${backlog_id}/${pt_id}`, project_task);
      history.push(`/projectBoard/${backlog_id}`);
      dispatch({
        type: GET_ERROR,
        payload: {}
      });
    } catch (err) {
      dispatch({
        type: GET_ERROR,
        payload: err.response.data
      });
    }
  };