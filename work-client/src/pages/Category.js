import React, {Component} from 'react';
import AdminRequest from "../components/AdminRequest";
import NavBar from "../components/NavBar";
import {BASE_URL, TOKEN_NAME} from "../utills/constants";
import {Modal, ModalBody, ModalFooter, ModalHeader, Table} from "reactstrap";
import {AvForm, AvField} from 'availity-reactstrap-validation'
import Switch from "react-switch";
import Pagination from "react-js-pagination";
import {toast} from "react-toastify";
import {connect} from "react-redux";

class Category extends Component {
    constructor(props) {
        super(props);
        this.state={
            item:'',
            showSaveOrEditModal:false,
            showDeleteModal:false,
            allCategories:[],
            allCategoriesByPageable:[],
            parentCategory:'',
            page:0,
            size:10,
            totalElements:0
        }
    }
    componentDidMount() {
        AdminRequest.me().then(res=>{
            if (res===null){
                localStorage.removeItem(TOKEN_NAME)
                this.props.history.push("/")
            }else {
                AdminRequest.getAllCategoryByPageable({page:this.state.page,size:this.state.size,search:'all'}).then(res=>{
                    this.setState({
                        allCategoriesByPageable:res.object,
                        totalElements:res.totalElements
                    })
                })
                AdminRequest.getAllCategoryByPageable({page:this.state.page,size:0,search:'all'}).then(res=>{
                    this.setState({
                        allCategories:res.object,
                        totalElements:res.totalElements
                    })
                })
            }
        })
    }

    render() {
        const openSaveOrEditModal=(item)=>{
            this.setState({
                item,
                showSaveOrEditModal:!this.state.showSaveOrEditModal
            })
        }
        const openDeleteModal=(item)=>{
            this.setState({
                item,
                showDeleteModal:!this.state.showDeleteModal
            })
        }
        const saveOrEdit=(e,v)=>{
            if (this.state.item){
                v={...v,id:this.state.item.id}
            }
            if (v.parentId){
                v={...v,parentDto:{id:v.parentId}}
            }
            AdminRequest.saveOrEditCategory(v).then(res=>{
                console.log(res,'res SaveOrEdit')
                AdminRequest.getAllCategoryByPageable({page:this.state.page,size:this.state.size,search:'all'}).then(res=>{
                    console.log(res.object,'RES ALL Cat By Pageable')
                    this.setState({
                        allCategoriesByPageable:res.object,
                        totalElements:res.totalElements
                    })
                })
                AdminRequest.getAllCategoryByPageable({page:this.state.page,size:0,search:'all'}).then(res=>{
                    console.log(res.object,'RES ALL Cat By Pageable')
                    this.setState({
                        allCategories:res.object,
                        totalElements:res.totalElements
                    })
                })
                openSaveOrEditModal('')
            })
        }
        const changeActive = (item) => {
            AdminRequest.changeActiveOfCategory(item.id).then(res=>{
                console.log(res,'RES Change Active')
                if (res.message==='Activated'){
                    toast.success(res.message)
                }
                else {
                    toast.warning(res.message)
                }
                AdminRequest.getAllCategoryByPageable({page:this.state.page,size:this.state.size,search:'all'}).then(res=>{
                    console.log(res.object,'RES ALL Cat By Pageable')
                    this.setState({
                        allCategoriesByPageable:res.object,
                        totalElements:res.totalElements
                    })
                })
            })

        }
        const handlePageChange=(pageNumber)=>{


            console.log(pageNumber,'PAGENumber')
            AdminRequest.getAllCategoryByPageable({page:pageNumber-1,size:this.state.size,search:'all'}).then(res=>{
                this.setState({
                    allCategoriesByPageable:res.object,
                    totalElements:res.totalElements,
                    page:pageNumber-1
                })
            })
        }
        const search=(e)=>{
            let s= e.target.value;
            console.log(s.length,'SSS')
            if (s.length>=2){
                AdminRequest.getAllCategoryByPageable({page:this.state.page,size:this.state.size,search:s}).then(res=>{
                    this.setState({
                        allCategoriesByPageable:res.object,
                        totalElements:res.totalElements,
                        page:0
                    })
                })
            }else {
                AdminRequest.getAllCategoryByPageable({page:this.state.page,size:this.state.size,search:'all'}).then(res=>{
                    this.setState({
                        allCategoriesByPageable:res.object,
                        totalElements:res.totalElements,
                        page:0
                    })
                })
            }
        }
        const remove=()=>{
            AdminRequest.remove(this.state.item.id).then(res=>{
                AdminRequest.getAllCategoryByPageable({page:this.state.page,size:this.state.size,search:'all'}).then(res=>{
                    this.setState({
                        allCategoriesByPageable:res.object,
                        totalElements:res.totalElements,
                        page:0,
                        showDeleteModal:!this.state.showDeleteModal,
                        item:''
                    })
                })
            })
        }
        return (
            <div className="row">
                <div className="col-md-2 bg-light">
                    <NavBar active={5}/>
                </div>
                <div className="col-md-10 bg-light">
                    <div className="row mt-3">
                        <div className="col-md-4">
                            <button className="btn btn-success" onClick={()=>openSaveOrEditModal('')}>+Add</button>
                        </div>
                        <div className="col-md-4">
                            <input type={'text'} className="searchTagDescResult" onChange={search}/>
                        </div>
                        <div className="col-md-4">
                            <Pagination
                                activePage={this.state.page + 1}
                                itemsCountPerPage={this.state.size}
                                totalItemsCount={this.state.totalElements}
                                pageRangeDisplayed={5}
                                onChange={handlePageChange}
                                itemClass="page-item"
                                linkClass="page-link"
                            />
                        </div>
                    </div>
                    <div className="row mt-3">
                        <div className="col-md-12">
                            <Table>
                                <thead>
                                <tr>
                                    <th>T/R</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Parent Category Name</th>
                                    <th>Active</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    this.state.allCategoriesByPageable.map((item,index)=>
                                        <tr>
                                            <td>{index+1+(this.state.page*this.state.size)}</td>
                                            <td>{item.name}</td>
                                            <td>{item.description}</td>
                                            <td>{item.parentDto?item.parentDto.name:''}</td>
                                            <td>
                                                <Switch checked={item.active} onChange={()=>changeActive(item)}/>
                                            </td>
                                            <td>
                                                <button className={'btn btn-warning'} onClick={()=>openSaveOrEditModal(item)}>Edit</button>
                                                <button className={'btn btn-danger ml-3'} onClick={()=>openDeleteModal(item)}>Delete</button>
                                            </td>
                                        </tr>
                                    )
                                }
                                </tbody>
                            </Table>
                        </div>
                    </div>
                    <Modal isOpen={this.state.showSaveOrEditModal} toggle={()=>openSaveOrEditModal('')}>
                        <ModalHeader><h4>{this.state.item?"Tahrirlash":"Yangi Category qoshish"}</h4></ModalHeader>
                        <AvForm onValidSubmit={saveOrEdit}>
                            <ModalBody>
                                <AvField type={'text'} defaultValue={this.state.item?this.state.item.name:''} name={'name'} requared label={'Enter name'}/>
                                <AvField type={'text'} defaultValue={this.state.item?this.state.item.description:''} name={'description'} label={'Enter description'}/>
                                <AvField type={'select'} defaultValue={this.state.item?this.state.item.parentDto?this.state.item.parentDto.id:'0':'0'} name={'parentId'} label={'Choose parent category'}>
                                    <option value="0">Choose parent category</option>
                                    {
                                        this.state.allCategories.length>0?
                                            this.state.allCategories.map(item=>
                                                <option value={item.id}>{item.name}</option>
                                            ):''}
                                </AvField>
                            </ModalBody>
                            <ModalFooter>
                                <button className={'btn btn-danger'} onClick={()=>openSaveOrEditModal('')} type={'button'}>Bekor qilish</button>
                                <button className={'btn btn-success'} type={'submit'}>Saqlash</button>
                            </ModalFooter>
                        </AvForm>
                    </Modal>
                    <Modal isOpen={this.state.showDeleteModal} toggle={()=>openDeleteModal('')}>
                        <ModalHeader>O'chirishni istaysizmi?</ModalHeader>
                        <ModalFooter>
                            <button className={'btn btn-danger'} onClick={()=>openDeleteModal('')} type={'button'}>Bekor qilish</button>
                            <button className={'btn btn-success'} onClick={remove}>O'chirish</button>
                        </ModalFooter>
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
export default connect(mapsStore, null)(Category);