import React from "react";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import About from "./pages/About";
import TopWork from "./pages/TopWork";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import Register from "./pages/Register";
import {combineReducers, createStore} from 'redux'
import auth from "./redux/reducers/authReducer";
import {Provider} from "react-redux";
import Category from "./pages/Category";
import Dashboard from "./pages/Dashboard";
// import {ToastContainer} from "react-toastify";
import Reports from "./pages/Reports";
import Orders from "./pages/Orders";
import Products from "./pages/Products";
import KPI from "./pages/KPI";
import Employee from "./pages/Employee";
import Settings from "./pages/Settings";
import ClientPage from "./pages/ClientPage";
import Massage from "./pages/Massage";

const allReducers = combineReducers(
    auth
)

const store = createStore(
    allReducers
)
const App = () => {

    return (
        <BrowserRouter>
            <Provider store={store}>
                {/*<ToastContainer/>*/}
                <Switch>
                    <Route exact path="/" component={HomePage}/>
                    <Route path="/about" component={About}/>
                    <Route path="/topWork" component={TopWork}/>
                    <Route path="/login" component={LoginPage}/>
                    <Route path="/register" component={Register}/>
                    <Route path="/reports" component={Reports}/>
                    <Route path="/category" component={Category}/>
                    <Route path="/dashboard" component={Dashboard}/>
                    <Route path="/orders" component={Orders}/>
                    <Route path="/products" component={Products}/>
                    <Route path="/kpi" component={KPI}/>
                    <Route path="/employee" component={Employee}/>
                    <Route path="/Settings" component={Settings}/>
                    <Route path="/client" component={ClientPage}/>
                    <Route path="/massage" component={Massage}/>


                </Switch>
            </Provider>

        </BrowserRouter>

    )

}
export default App