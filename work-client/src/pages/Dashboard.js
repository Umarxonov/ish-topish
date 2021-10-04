import React, {Component} from 'react';
import {connect} from "react-redux";
import {TOKEN_NAME} from "../utills/constants";
import AdminRequest from "../components/AdminRequest"
import {getRoleNameFromJWT} from "../utills/UsefullFunctions";
import NavBar from "../components/NavBar";
class Dashboard extends Component {
    componentDidMount() {
        console.log(localStorage.getItem(TOKEN_NAME), 'PROPS')
        AdminRequest.me().then(res => {
            if (res === null || getRoleNameFromJWT() !== 'ROLE_ADMIN') {
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
                        <NavBar active={1}/>
                    </div>
                    <div className="col-md-10">
<h1>Dashboard</h1>

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
export default connect(mapsStore, null)(Dashboard);