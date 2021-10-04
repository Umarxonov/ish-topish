import {Link} from "react-router-dom";
import {getRoleNameFromJWT} from "../utills/UsefullFunctions";
import {TOKEN_NAME} from "../utills/constants";

const NavBar = (props) => {
    const exitFunc=()=>{
        localStorage.removeItem(TOKEN_NAME)
    }
    return <div>
        {getRoleNameFromJWT()=== 'ROLE_ADMIN' ?
            <ul className="list-group">
                <Link to={"/dashboard"} className={"text-dark"} style={{fontSize: "30px"}}>
                    <li className={props.active === 1 ? "list-group-item active" : "list-group-item"}>
                        Dashboard
                    </li>
                </Link>
                <Link to={"/reports"} className={"text-dark"} style={{fontSize: "30px"}}>
                    <li className={props.active === 2 ? "list-group-item active" : "list-group-item"}>
                        Hisobotlar
                    </li>
                </Link>
                <Link to={"/orders"} className={"text-dark"} style={{fontSize: "30px"}}>
                    <li className={props.active === 3 ? "list-group-item active" : "list-group-item"}>
                        Buyurtmalar
                    </li>
                </Link>
                <Link to={"/category"} className={"text-dark"} style={{fontSize: "30px"}}>
                    <li className={props.active === 4 ? "list-group-item active" : "list-group-item"}>
                        Kategoriyalar
                    </li>
                </Link>


                <Link to={"/products"} className={"text-dark"} style={{fontSize: "30px"}}>
                    <li className={props.active === 5 ? "list-group-item active" : "list-group-item"}>
                        Mahsulotlar
                    </li>
                </Link>
                {/*<Link to={"/kpi"} className={"text-dark"} style={{fontSize: "30px"}}>*/}
                {/*    <li className={props.active === 6 ? "list-group-item active" : "list-group-item"}>*/}
                {/*       Ma'lumotlar*/}
                {/*    </li>*/}
                {/*</Link>*/}
                <Link to={"/employee"} className={"text-dark"} style={{fontSize: "30px"}}>
                    <li className={props.active === 7 ? "list-group-item active" : "list-group-item"}>
                        Hodimlar
                    </li>
                </Link>
                <Link to={"/settings"} className={"text-dark"} style={{fontSize: "30px"}}>
                    <li className={props.active === 8 ? "list-group-item active" : "list-group-item"}>
                        Sozlamalar
                    </li>
                </Link>
                <Link to={"/"} className={"text-dark"} style={{fontSize: "30px"}}>
                    <li onClick={exitFunc} className={props.active === 9 ? "list-group-item active" : "list-group-item"}>
                        Exit
                    </li>
                </Link>
            </ul>
            :
            getRoleNameFromJWT() === 'ROLE_MANAGER' ?
                <ul className="list-group">
                    <Link to={"/reports"} className={"text-dark"} style={{fontSize: "30px"}}>
                        <li className={props.active === 2 ? "list-group-item active" : "list-group-item"}>
                            Hisobotlar
                        </li>
                    </Link>
                    <Link to={"/orders"} className={"text-dark"} style={{fontSize: "30px"}}>
                        <li className={props.active === 3 ? "list-group-item active" : "list-group-item"}>
                            Buyurtmalar
                        </li>
                    </Link>
                    <Link to={"/category"} className={"text-dark"} style={{fontSize: "30px"}}>
                        <li className={props.active === 4 ? "list-group-item active" : "list-group-item"}>
                            Kategoriyalar
                        </li>
                    </Link>


                    <Link to={"/products"} className={"text-dark"} style={{fontSize: "30px"}}>
                        <li className={props.active === 5 ? "list-group-item active" : "list-group-item"}>
                            Mahsulotlar
                        </li>
                    </Link>
                    <Link to={"/kpi"} className={"text-dark"} style={{fontSize: "30px"}}>
                        <li className={props.active === 6 ? "list-group-item active" : "list-group-item"}>
                            KPI
                        </li>
                    </Link>

                    <Link to={"/settings"} className={"text-dark"} style={{fontSize: "30px"}}>
                        <li className={props.active === 8 ? "list-group-item active" : "list-group-item"}>
                            Sozlamalar
                        </li>
                    </Link>
                    <Link to={"/"} className={"text-dark"} style={{fontSize: "30px"}}>
                        <li onClick={exitFunc} className={props.active === 9 ? "list-group-item active" : "list-group-item"}>
                            Exit
                        </li>
                    </Link>
                </ul>
                :
                getRoleNameFromJWT() === 'ROLE_CLIENT' ?
                    <ul className="list-group">

                        <Link to={"/client"} className={"text-dark"} style={{fontSize: "30px"}}>
                            <li className={props.active === 10 ? "list-group-item active" : "list-group-item"}>
                               Home
                            </li>
                        </Link>
                        <Link to={"/massagee"} className={"text-dark"} style={{fontSize: "30px"}}>
                            <li className={props.active === 11 ? "list-group-item active" : "list-group-item"}>
                                E'lonlar
                            </li>
                        </Link>


                        <Link to={"/settings"} className={"text-dark"} style={{fontSize: "30px"}}>
                            <li className={props.active === 8 ? "list-group-item active" : "list-group-item"}>
                                Sozlamalar
                            </li>
                        </Link>
                        <Link to={"/"} className={"text-dark"} style={{fontSize: "30px"}}>
                            <li onClick={exitFunc} className={props.active === 9 ? "list-group-item active" : "list-group-item"}>
                                Exit
                            </li>
                        </Link>
                    </ul>
                    :
                    <div>
                    </div>

        }
    </div>
}
export default NavBar