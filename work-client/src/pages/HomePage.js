import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {connect} from "react-redux";

class HomePage extends Component {
    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-1 offset-10">
                        <Link type={'button'} to={"/login"} className={'btn btn-success'}>login</Link>
                    </div>
                    <div className="col-md-1">
                        <Link type={'button'} to={"/register"}  className={'btn btn-success'}>register</Link>
                    </div>

                </div>
                <div className="row">


                </div>



                <div className="row">

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
export default connect(mapsStore,null)(HomePage);