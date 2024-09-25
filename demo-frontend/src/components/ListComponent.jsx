import React,{useEffect, useState} from 'react'
import { deleteIncident, listIncident, listPageIncident } from '../services/IncidentService'
import { useNavigate } from 'react-router-dom'


const ListComponent = () => {
    const pageRecord =sessionStorage.getItem('currentPage') || 1
    console.log(pageRecord)
    const [incidents, setIncidents]= useState([])
    const [currentPage, setCurrentPage] = useState(pageRecord)
    const [itemsPerPage] = useState(5)
    const [totalPages,setTotalPage] = useState(1)
    const navigator = useNavigate();

    const updatePage=(pageN)=>{
        setCurrentPage(pageN)
        sessionStorage.setItem('currentPage',pageN)
    }

    useEffect(()=>{
        getPageIncident()
    },[currentPage,totalPages])

    function getPageIncident(){
        listPageIncident(currentPage,itemsPerPage).then((response)=>{
            setIncidents(response.data.content)
            setTotalPage(response.data.totalPages)
            if(currentPage>response.data.totalPages){
                updatePage(response.data.totalPages)
            }
        }).catch(error => {
            alert(error)
        })
    }

    // function getAllIncident(){
    //     listIncident().then((response)=>{setIncidents(response.data)})
    //     .catch(error => {
    //         alert(error)
    //     })
    // }
    let headers=["id","type","description", "priority","reportName","updateBy","status","actions"]
    
    function addIncident(){
        navigator('/add-incident')
    }
    function updateIncident(id){
        navigator(`/edit-incident/${id}`)
    }
    function removeIncident(id){
        deleteIncident(id).then((response)=>{
            getPageIncident()
        }).catch(error=>{
            console.error(error)
        })
    }
  return (
    <div className='container'>
        <h2 className='text-center'>List of incident</h2>
        <button className='btn btn-primary mb-2' onClick={addIncident}>Add incident</button>
    <table className='table table-striped table-bordered'> 
        <thead>
            <tr key={"header"}>
                {headers.map(hd => <th key={hd}>{hd.charAt(0).toUpperCase() + hd.slice(1)}</th>)}
            </tr>
        </thead>
        <tbody>
            {
                incidents.map(item => 
                    <tr key={item.id}>
                        <td>{item.id}</td>
                        <td>{item.type}</td>
                        <td>{item.description}</td>
                        <td>{item.priority}</td>
                        <td>{item.reportName}</td>
                        <td>{item.updateBy}</td>
                        <td>{item.status}</td>
                        <td>
                            <button className='btn btn-info' onClick={()=>updateIncident(item.id)}>Update</button>
                            <button className='btn btn-danger' style={{marginLeft:'10px'}} onClick={()=>removeIncident(item.id)}>Delete</button>
                        </td>
                    </tr>)
            }
        </tbody>
    </table>
    <div className="flex items-center justify-between">
        <button
          onClick={() => updatePage( Math.max(currentPage - 1, 1))}
          disabled={currentPage === 1}
        >

          Previous
        </button>
        <span>
          Page {currentPage} of {totalPages}
        </span>
        <button
          onClick={() => {updatePage( Math.min(currentPage + 1, totalPages))}}
          disabled={currentPage === totalPages}
        >
          Next

        </button>
      </div>
    </div>
  )
}

export default ListComponent
