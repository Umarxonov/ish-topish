import {request} from "../utills/Request";
import {api} from "../apiPaths/api";


class AdminRequest {


    // Auth
    static async me() {
        let user = ''
        await request('get', api.me).then(res => {
            user = res.data
        }).catch(res => {
            user = null
        })
        return user
    }

    // Category

    static async getAllCategory() {
        let categories = []
        await request('get', api.allCategories).then(res => {
            categories = res.data
        }).catch(res => {
            categories = []
        })
        return categories
    }
    static async getAllCategoryByPageable(params){
        let categories=[]
        await request('get',api.allCategoriesByPageable+'?page='+params.page+'&size='+params.size+'&search='+params.search).then(res=>{
            categories= res.data
        }).catch(res=>{
            categories=[]
        })
        return categories
    }
    static async changeActiveOfCategory(id){
        let categories=[]
        await request('get',api.changeActiveOfCategory+id).then(res=>{
            categories= res.data
        }).catch(res=>{
            categories=[]
        })
        return categories
    }
    static async saveOrEditCategory(item){
        let response=[]
        await request('post',api.saveOrEditCategory,item).then(res=>{
            response=res
        }).catch(res=>{
            response=[]
        })
        return response
    }
    static async remove(id){
        let categories=[]
        await request('get',api.remove+id).then(res=>{
            categories= res.data
        }).catch(res=>{
            categories=[]
        })
        return categories
    }


    // Employee ********************************************

    static async saveOrEditEmployee(item){
        let response=[]
        await request('post',api.saveOrEditEmployee,item).then(res=>{
            response=res
        }).catch(res=>{
            response=[]
        })
        return response
    }


    static async getAllEmployee() {
        let categories = []
        await request('get', api.allEmployee).then(res => {
            categories = res.data
        }).catch(res => {
            categories = []
        })
        return categories
    }
    static async getAllEmployeeByPageable(params){
        let employees=[]
        await request('get',api.allEmployeeByPageable+'?page='+params.page+'&size='+params.size+'&search='+params.search).then(res=>{
            employees= res.data
        }).catch(res=>{
            employees=[]
        })
        return employees
    }
}

export default AdminRequest