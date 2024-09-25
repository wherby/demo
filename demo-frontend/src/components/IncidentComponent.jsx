import React, { useEffect, useState } from 'react'
import { addIncident, getIncident, updateIncident } from '../services/IncidentService'
import { useNavigate, useParams} from 'react-router-dom'

const IncidentComponent = () => {
    const [name,setName]= useState('')
    const [type,setType]= useState('')
    const [description,setDescription]= useState('')
    const [priority,setPriority]= useState('')
    const [status,setStatus] = useState('')

    const [errors,setErrors]= useState({
        name:"",
        type:"",
        description:"",
        priority:"",
        status:""
    })
    const {id} =useParams()
    useEffect(()=>{
        if(id){
            getIncident(id).then((response)=>{
                const data = response.data
                setType(data.type)
                setDescription(data.description)
                setPriority(data.priority.toString())
                setStatus(data.status)
            }).catch((error)=>console.log(error))
        }
    },[id])

    
    const navigator = useNavigate()

    function handleName(e){
        setName(e.target.value)
    }
    function handleType(e){
        setType(e.target.value)
    }
    function handleDescription(e){
        setDescription(e.target.value)
    }
    function handlePrority(e){
        setPriority(e.target.value)
    }
    function saveOrUpdateIncident(e){
        e.preventDefault();
        if(validateForm()){
            
            if(id){
                const incident=    {
                    "type": type,
                    "description": description,
                    "priority": priority,
                    "updateBy": name,
                    "status":status
                }
                updateIncident(id,incident).then((response)=>{
                    console.log(response.data)
                    navigator('/')
                }).catch((error)=>{
                    alert(error)
                })
            }else{
                const incident=    {
                    "type": type,
                    "description": description,
                    "priority": priority,
                    "reportName": name
                }
                addIncident(incident).then((response)=>{
                    console.log(response.data)
                    navigator('/')
                }).catch((error)=>{
                    alert(error)
                })
            }

        }

    }

    const nums = new Set(['1','2','3','4','5','6','7','8','9','0']);
    
    function validateForm(){
        let valid = true;
        console.log(priority)
        const errorCopy={... errors}
        if(name.trim()){
            errorCopy.name=''
        }else{
            errorCopy.name = "Name is required"
            valid =false
        }
        if(type.trim()){
            errorCopy.type=''
        }else{
            errorCopy.type = "Type is required"
            valid =false
        }
        if(description.trim()){
            errorCopy.description=''
        }else{
            errorCopy.description = "Description is required"
            valid =false
        }
        if(nums.has(priority.toString())){
            errorCopy.priority=''
        }else{
            errorCopy.priority = "Priority is required and from 0 to 9"
            valid =false
        }
        if(id && status.trim()==""){
            errorCopy.status = "Status is required"
            valid =false
        }else{
            errorCopy.status=""
        }
        setErrors(errorCopy)
        return valid
    }
  return (
    <div className='container'>
        <br></br>
        <div className='row'>
            <div className='card col-md-6 offset-md-3'>
                <h2 className='text-center'>{id? "Update":"Add"} incident</h2>
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-lable'>{id ? "Update by":"Report by"}</label>
                            <input type='text'
                            placeholder='Enter name'
                            name='name'
                            value={name}
                            className={`form-control ${errors.name ? 'is-invalid':''}`}
                            onChange={handleName}
                            >
                            </input>
                            {errors.name && <div className='invalid-feedback'>{errors.name}</div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-lable'>Type</label>
                            <input type='text'
                            placeholder='Enter incident type'
                            name='type'
                            value={type}
                            className={`form-control ${errors.type ? 'is-invalid':''}`}
                            onChange={handleType}
                            >
                            </input>
                            {errors.type && <div className='invalid-feedback'>{errors.type}</div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-lable'>Description</label>
                            <input type='text'
                            placeholder='Enter description'
                            name='description'
                            value={description}
                            className={`form-control ${errors.description ? 'is-invalid':''}`}
                            onChange={handleDescription}
                            >
                            </input>
                            {errors.description && <div className='invalid-feedback'>{errors.description}</div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-lable'>Priority</label>
                            <input type='text'
                            placeholder='Enter priority'
                            name='priority'
                            value={priority}
                            className={`form-control ${errors.priority ? 'is-invalid':''}`}
                            onChange={handlePrority}
                            >
                            </input>
                            {errors.priority && <div className='invalid-feedback'>{errors.priority}</div>}
                        </div>
                        {id &&<div className='form-group mb-2'>
                            <label className='form-lable'>Status</label>
                            <input type='text'
                            placeholder='Enter status'
                            name='status'
                            value={status}
                            className={`form-control ${errors.status ? 'is-invalid':''}`}
                            onChange={(e)=>setStatus(e.target.value)}
                            >
                            </input>
                            {errors.status && <div className='invalid-feedback'>{errors.status}</div>}
                        </div>}
                        <button className='btn btn-success' onClick={saveOrUpdateIncident}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default IncidentComponent