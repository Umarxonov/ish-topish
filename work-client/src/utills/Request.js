import {BASE_URL, TOKEN_NAME} from "./constants";
import axios from "axios";

export const request=(method,url,data)=>{
    const token=localStorage.getItem(TOKEN_NAME)
    const headers={
        'Authorization':token,
        'Access-Control-Allow-Origin': '*'
    }
    return axios({
        url:BASE_URL+url,method,data,headers})
}