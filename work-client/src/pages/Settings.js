import React, {Component} from 'react';
import {connect} from "react-redux";
import {TOKEN_NAME} from "../utills/constants";
import AdminRequest from "../components/AdminRequest";
import NavBar from "../components/NavBar";

class Settings extends Component {
    componentDidMount() {
        AdminRequest.me().then(res=>{
            if (res===null){
                localStorage.removeItem(TOKEN_NAME)
                this.props.history.push("/")
            }
        })
    }

    render() {
        return (
            <div className="row">
                <div className="col-md-2">
                    <NavBar active={8}/>
                </div>
                <div className="col-md-10">
                    <h1>This is Settings Page</h1>
                </div>
            </div>
        );
    }
}
const mapsStore = store => {
    return {
        store: store
    }
}
export default connect(mapsStore,null)(Settings);