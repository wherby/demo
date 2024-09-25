import axios from "axios";

const REST_API_BASE_URL = "http://localhost:8080/api/v1/incident"

export const listIncident=()=>{
    return axios.get(REST_API_BASE_URL);
}

export const listPageIncident=(pageNo,pagesize)=>{
    let pageNoFrom = pageNo-1
    return axios.get(REST_API_BASE_URL +'/' + pageNoFrom + '/' + pagesize);
}


export const addIncident= (incident) =>{
    return axios.post(REST_API_BASE_URL,incident)
}

export const getIncident =(incidentId) => {
    return axios.get(REST_API_BASE_URL + '/' + incidentId)
}

export const updateIncident= (incidentId,incident)=>axios.put(REST_API_BASE_URL +'/'+incidentId,incident)

export const deleteIncident =(incidentId)=>axios.delete(REST_API_BASE_URL +'/' + incidentId)