import React, {Component} from 'react';
import AdminRequest from "../components/AdminRequest";
import {BASE_URL, TOKEN_NAME} from "../utills/constants";
import NavBar from "../components/NavBar";
import {connect} from "react-redux";
class Reports extends Component {
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
            <div>
                <div className="row">
                    <div className="col-md-2">
                        <NavBar active={2}/>
                    </div>
                    <div className="col-md-10">
                        <h1>This is Products Page</h1>
                    </div>
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
export default connect(mapsStore,null)(Reports);