import {LOGIN} from "../types/AuthTypes";

export  const login=(roleName)=>{
    return{
        type:LOGIN,
        payload:roleName

    }





}