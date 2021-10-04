import React, {Component} from 'react';
import axios from "axios";
import {BASE_URL, TOKEN_NAME, TOKEN_TYPE} from "../utills/constants";
import {api} from "../apiPaths/api";
import {toast} from "react-toastify";
import jwtDecode from "jwt-decode";
import {AvField, AvForm} from "availity-reactstrap-validation";

class Register extends Component {


    render() {
        const login = (e, v) => {
            console.log(v, 'VALUES')


            axios.post(BASE_URL + api.login, v).then(res => {
                if (res.status === 200) {
                    toast.success("Success")
                    localStorage.setItem(TOKEN_NAME, TOKEN_TYPE + res.data)
                    let parsedToken = jwtDecode(res.data)
                    console.log("bu user==ni role " + parsedToken.aud)
                    console.log("yangi token" + parsedToken.roles[0].roleName, 'PARSED TOKEN')

                    // this.props.signIn(parsedToken.aud[0].roleName)
                    this.props.signIn(parsedToken.roles[0].roleName)
                    if (parsedToken.roles[0].roleName === 'ROLE_ADMIN') {
                        this.props.history.push("/dashboard")
                    } else if (parsedToken.roles[0].roleName === 'ROLE_MANAGER') {
                        this.props.history.push("/reports")
                    } else if (parsedToken.roles[0].roleName === 'ROLE_CLIENT') {
                        this.props.history.push("/client")
                    } else {
                        // this.props.history.push("/")
                    }
                }

            }).catch(res => {

                toast.error("Error" + res)
            })
        }


        const register = (e, v) => {
            console.log(v, 'VALUES')
            axios.post(BASE_URL + api.register, v).then(res => {
                console.log(res.data.success)
                if (res.data.success) {
                    localStorage.setItem(TOKEN_NAME, TOKEN_TYPE + res.data.object)
                    let parsedToken = jwtDecode(res.data.object);
                    console.log(22, parsedToken);
                    console.log(parsedToken.roles)
                    console.log(parsedToken.roles[0])
                    // this.props.signIn(parsedToken.aud[0].roleName)
                    // this.props.signIn(parsedToken.roles[0].roleName)
                    if (parsedToken.roles[0].roleName === 'ROLE_ADMIN') {
                        this.props.history.push("/dashboard")
                    } else if (parsedToken.roles[0].roleName === 'ROLE_MANAGER') {
                        this.props.history.push("/reports")
                    } else if (parsedToken.roles[0].roleName === "ROLE_CLIENT") {
                        console.log("hammsi yaxhsi joyda")
                        this.props.history.push("/client");
                    } else {
                        this.props.history.push("/23/asdf23/fasfdf");
                    }
                }
            }).catch(res => {
                toast.error("Error" + res)
            })
        }
        return (
            <div>
                <div className="row">
                    <div className="col-md-4 ">

                        <div className="row mt-5">
                            <div className="col-md-12">
                                <AvForm onValidSubmit={register}>
                                    <AvField type={'text'} label={'Enter user name : '} name={'firstName'}/>
                                    <AvField type={'text'} label={'Enter last name : '} name={'lastName'}/>
                                    <AvField type={'text'} label={'Enter phone number : '} name={'phoneNumber'}/>
                                    <AvField type={'password'} label={'Enter password : '} name={'password'}/>
                                    <button type={'submit'} className={'btn btn-success mt-2'}>Register</button>
                                </AvForm>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

export default Register;