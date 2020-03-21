import { GET_BACKLOG, GET_PROJECTS_TASKS, DELETE_PROJECT_TASKS } from "../actions/types"

const initialState = {
    project_tasks: [],
    project_task: {}
}

export default function (state = initialState,action) {
    switch(action.type){
        default:
            return state;
        case GET_BACKLOG:
            return{
                ...state,
                project_tasks: action.payload
            }
        case GET_PROJECTS_TASKS:
            return{
                ...state,
                project_task: action.payload
            }
        case DELETE_PROJECT_TASKS:
            return{
                ...state,
                project_tasks: state.project_tasks.filter(
                    project_task => project_task.projectIdentifier !== action.payload
                  )
            }
        

    }
}