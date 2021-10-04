import {LOGIN} from "../types/AuthTypes";

const initialState={

    userRole:''
}
const auth=(state=initialState,action)=>{
    switch (action.type){
        case LOGIN : return {...state,userRole: action.payload}
        default:return state
    }
}
export default auth