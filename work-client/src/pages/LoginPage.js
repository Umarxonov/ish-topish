import React, {Component} from 'react';
import {connect} from "react-redux";
import {login} from "../redux/actions/AuthAction";
import axios from "axios";
import {BASE_URL, TOKEN_NAME, TOKEN_TYPE} from "../utills/constants";
import {toast} from "react-toastify";
import {AvForm,AvField} from 'availity-reactstrap-validation'
import {api} from '../apiPaths/api'
import jwtDecode from "jwt-decode";


class LoginPage extends Component{

   render() {
       const login=(e,v)=>{
           console.log(v,'VALUES')



           axios.post(BASE_URL+api.login,v).then(res=>{
               if (res.status===200){
                   toast.success("Success")
                   localStorage.setItem(TOKEN_NAME,TOKEN_TYPE+res.data)
                   let parsedToken=jwtDecode(res.data)
                   console.log("bu user==ni role "+parsedToken.aud)
                   console.log("yangi token"+parsedToken.roles[0].roleName,'PARSED TOKEN')

                   // this.props.signIn(parsedToken.aud[0].roleName)
                   this.props.signIn(parsedToken.roles[0].roleName)
                   if (parsedToken.roles[0].roleName==='ROLE_ADMIN'){
                       this.props.history.push("/dashboard")
                   }
                   else if (parsedToken.roles[0].roleName==='ROLE_MANAGER'){
                       this.props.history.push("/reports")
                   }
                   else if (parsedToken.roles[0].roleName==='ROLE_CLIENT'){
                       this.props.history.push("/client")
                   }else {
                       this.props.history.push("/")
                   }
               }

           }).catch(res=>{

               toast.error("Error"+res)
           })
       }

    return (
        <div>
            <div className="row">
                <div className="col-md-4 ">

                    <div className="row mt-5">
                        <div className="col-md-12">
                            <AvForm onValidSubmit={login}>
                                <AvField type={'text'} label={'Enter phone number : '} name={'phoneNumber'}/>
                                <AvField type={'password'} label={'Enter password : '} name={'password'}/>
                                <button type={'submit'} className={'btn btn-success mt-2'}>Login</button>
                            </AvForm>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
   }






}


// export default LoginPage;

const mapDispatch = {
    signIn: login
}
export default connect(null, mapDispatch)(LoginPage);