import {TOKEN_NAME} from "./constants";
import jwtDecode from "jwt-decode";

export const getRoleNameFromJWT=()=>{
    if (localStorage.getItem(TOKEN_NAME)){
        const parsedToken = jwtDecode(localStorage.getItem(TOKEN_NAME))
        return parsedToken.roles[0].roleName
    }else {
        return ''
    }
}