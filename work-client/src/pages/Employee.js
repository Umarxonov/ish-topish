import React, {Component} from 'react';
import {connect} from "react-redux";
import {TOKEN_NAME} from "../utills/constants";
import AdminRequest from "../components/AdminRequest";
import NavBar from "../components/NavBar";
import {Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";
import {AvForm, AvField} from 'availity-reactstrap-validation'

class Employee extends Component {
    constructor(props) {
        super(props);
        this.state = {
            item: '',
            showSaveOrEditModal: false,
            allEmployees: [],
            allEmployeesByPageable: [],
            page: 0,
            size: 10,
            totalElements:0

        }
    }

    componentDidMount() {
        AdminRequest.me().then(res => {
            if (res === null) {
                localStorage.removeItem(TOKEN_NAME)
                this.props.history.push("/")
            } else {
                AdminRequest.getAllEmployeeByPageable({page:this.state.page,size:this.state.size,search:'all'}).then(res=>{
                    this.setState({
                        allEmployeesByPageable:res.object,
                        totalElements:res.totalElements
                    })
                })
                AdminRequest.getAllEmployeeByPageable({page:this.state.page,size:0,search:'all'}).then(res=>{
                    this.setState({
                        allEmployees:res.object,
                        totalElements:res.totalElements
                    })
                })
            }
        })
    }

    render() {
        const openSaveOrEditModal = (item) => {
            this.setState(
                {
                    item,
                    showSaveOrEditModal: !this.state.showSaveOrEditModal
                }
            )
        }
        const saveOrEdit = (e, v) => {
            if (this.state.item) {
                v = {...v, id: this.state.item}
            }
            console.log(v, 'VALUES ')
        }
        return (
            <div className="row">
                <div className="col-md-2">
                    <NavBar active={7}/>
                </div>
                <div className="col-md-10 bg-light">
                    <div className="row">

                        <div className="col-md-4">
                            <button className="btn btn-success" onClick={() => openSaveOrEditModal('')}>+ADD</button>
                        </div>


                        <div className="col-md-4">

                        </div>
                        <div className="col-md-4">

                        </div>
                    </div>


                    <Modal isOpen={this.state.showSaveOrEditModal} toggle={() => openSaveOrEditModal('')}>
                        <ModalHeader><h4>{this.state.item ? "Tahrirlash" : "Yangi hodim qo'shish"}</h4></ModalHeader>

                        <AvForm onValidSubmit={saveOrEdit}>
                            <ModalBody>
                                <AvField type={'text'} name={'firstName'} label={'First name'}> </AvField>
                                <AvField type={'text'} name={'lastName'} label={'Last name'}> </AvField>
                                <AvField type={'number'} name={'phoneNumber'} label={'Phone Number '}> </AvField>
                                <AvField type={'password'} name={'password'} label={'Password '}> </AvField>
                                <AvField type={'select'} name={'role'} label={'Enter position '}>
                                    <option value="44">Admin</option>
                                    <option value="45">Manager</option>
                                    <option value="46">Client</option>

                                </AvField>
                            </ModalBody>
                            <ModalFooter>
                                <button className={'btn btn-danger'} onClick={() => openSaveOrEditModal('')}
                                        type={'button'}>Bekor qilish
                                </button>
                                <button className={'btn btn-success'} type={'submit'}>Saqlash</button>
                            </ModalFooter>
                        </AvForm>
                    </Modal>

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
export default connect(mapsStore, null)(Employee);