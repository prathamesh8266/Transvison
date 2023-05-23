import React, { useEffect, useState } from 'react'
import { Button, Typography } from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import Box from '@mui/material/Box';
import axios from 'axios';
import ActionForm from './ActionForm';
import {Navigate, useParams } from 'react-router-dom'

function AdminUserPage({params}) {

  let {adminId} = useParams();

  const [rows,setRows] = useState([]);
  const [logout,setLogout] = useState(false);
  const [openModelHandlerForAdding,setOpenModelHandlerForAdding] = useState(false);
  const [openModelHandlerForEditing,setOpenModelHandlerForEditing] = useState(false);
  const [editData,setEditData] = useState({});

  const closeModel = (bool) => {
    setOpenModelHandlerForAdding(bool)
    setOpenModelHandlerForEditing(bool)
  }

  const get_data = () => {
    try{
      axios.get(('http://localhost:8081/admin/getAll/').concat(adminId)).then((response) => {
          const get_rows = response.data.response;
          // console.log(get_rows);
          setRows([...get_rows])
        });
    }catch(err){
      console.log(err);
    }
  }

  const delete_data = async (id) => {
    try {
      const response = (await axios.post(('http://localhost:8081/admin/deleteBooks/').concat(adminId),[id]));
      get_data();
    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    setOpenModelHandlerForAdding(false)
    get_data()
  },[])

  
  const columns = [
    { field: 'name', headerName: 'Name', width: 250 },
    { field: 'code', headerName: 'Code', width: 450 },
    { field: 'edition', headerName: 'Edition', width: 200 },
    { field: 'pages', headerName: 'Pages', width: 200 },
    { field:'delete', headerName: 'Delete',width:140,sortable: false,
    filterable: false,
    hideable: false,
      renderCell: (cell_val) => {
        return (
                <Button size="small" variant="contained" color="error"
                  onClick={(event)=>{
                    // console.log(cell_val.row)
                    delete_data(cell_val.row.id);
                  }}>
                    Delete
                </Button>
              )
      }},
      {field:'edit', headerName: 'Edit',width:130,sortable: false,
      filterable: false,
      hideable: false,
      renderCell: (cell_val) => {
        return (
                <Button size="small" variant="contained" color="primary"
                  onClick={(event)=>{
                    setEditData(cell_val.row)
                    setOpenModelHandlerForEditing(true);
                  }}>
                    Edit
                </Button>
              )
      }}
  ];

  return (
    <Box sx={{maxWidth:'1400px',margin:'auto'}}>
      {logout ? <Navigate to={'/'} /> : null}
      <Button variant="outlined" size="large" color='error' sx={{width:'170px', position:'absolute',right:10}} onClick={()=>setLogout(true)}>Log out</Button>
      <Typography component="h2" variant="h3" sx={{textAlign:'center',margin:'15px'}}>
            Admin User 
      </Typography>
      <Button variant="outlined" size="large" sx={{width:'170px', marginTop:2}} onMouseDown={()=>setOpenModelHandlerForAdding(true)}>
          Add Book
      </Button>
      <DataGrid rows={rows} columns={columns} sx={{width:'100%',margin:'auto',marginTop:'25px',minHeight:'100px'}} disableRowSelectionOnClick/>
      {openModelHandlerForEditing &&  <ActionForm modelStatus={closeModel} get_data={get_data} heading={'Edit Book'} admin_id={adminId} data={editData}/>}
      {openModelHandlerForAdding &&  <ActionForm modelStatus={closeModel} get_data={get_data} heading={'Add Book'} admin_id={adminId} data={null}/>}
    </Box>
  )
}

export default AdminUserPage