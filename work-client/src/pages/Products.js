import React, {Component} from 'react';
import AdminRequest from "../components/AdminRequest";
import NavBar from "../components/NavBar";
import {BASE_URL, TOKEN_NAME} from "../utills/constants";
class Products extends Component {
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
                        <NavBar active={5}/>
                    </div>
                    <div className="col-md-10">
                        <h1>This is Products Page</h1>
                    </div>
                </div>
            </div>
        );
    }
}

export default Products;