import React, { useState } from 'react'
import Paper from '@mui/material/Paper';
import InputBase from '@mui/material/InputBase';
import IconButton from '@mui/material/IconButton';
import SearchIcon from '@mui/icons-material/Search';
import Box from '@mui/material/Box';
import { Button, Typography } from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import axios from 'axios';
import {Navigate, useParams } from 'react-router-dom'

export default function GeneralUserPage() {

  let {userId} = useParams();

  const [searchItem,setSearchItem] = useState('');
  const [logout,setLogout] = useState(false);
  const [actualRows,setActualRows] = useState([]);
  const [rows,setRows] = useState([]);

  const get_data = () => {
    axios.get('http://localhost:8081/admin/getAll').then((response) => {
        const get_rows = response.data.response;
        // console.log(get_rows)
        let get_books = []
        for(let i = 0; i < get_rows.length; i++) {
          for(let j=0;j< get_rows[i].books.length;j++){
            get_books.push({...get_rows[i]?.books[j],author:get_rows[i].name,author_id:get_rows[i].id})
          }
        }
        // console.log(get_books)
        setRows(get_books)
        setActualRows(get_books)
      });
  }

  useState(()=>{
    get_data()
  },[])

  const request_book = async (book) => {
    try {
      // console.log(book)
      const response = await axios.post('http://localhost:8081/requests/add',book);
      console.log(response)
    } catch (error) {
      console.log(error);
    }
  }

  const columns = [
    { field: 'name', headerName: 'Name', width: 250 },
    { field: 'code', headerName: 'Code', width: 450 },
    { field: 'edition', headerName: 'Edition', width: 200 },
    { field: 'pages', headerName: 'Pages', width: 150 },
    { field: 'author', headerName: 'Author', width: 200 },
    { field:'delete', headerName: 'Delete',width:120,sortable: false,
    filterable: false,
    hideable: false,
      renderCell: (cell_val) => {
        return (
                <Button size="small" variant="contained" color="primary"
                  onClick={(event)=>{
                    // console.log(cell_val.row)
                    request_book({bookId: cell_val.row.id,code:cell_val.row.code,authorId:cell_val.row.author_id,userId:parseInt(userId)})
                  }}>
                    Request
                </Button>
              )
      }},
  ];


  const searchItemHandler = (event) => {
    console.log(event.target.value);
    setSearchItem(event.target.value);
    let searched_books = [];
    for(let i=0;i<actualRows.length;i++) {
        if(actualRows[i].name.includes(event.target.value)){
          searched_books.push(actualRows[i]);
        }
    }
    setRows(searched_books)
  }

  return (
    <Box sx={{maxWidth:'1400px',margin:'auto'}}>
      {logout ? <Navigate to={'/'} /> : null}
      <Button variant="outlined" size="large" color='error' sx={{width:'170px', position:'absolute',right:10}} onClick={()=>setLogout(true)}>Log out</Button>
      <Typography component="h2" variant="h3" sx={{textAlign:'center',margin:'15px'}}>
            General User 
      </Typography>
      <Box sx={{display: 'flex',justifyContent:'center', alignItems: 'center', marginTop:6}}>
          <Paper
          component="form"
          sx={{ p: '2px 4px', display: 'flex',justifyContent:'center', alignItems: 'center', width: 800 }}
        >
            <InputBase
              sx={{ ml: 1, flex: 1 }}
              placeholder="Search Book Name"
              onChange={searchItemHandler}
            />
            <IconButton type="button" sx={{ p: '10px' }} aria-label="search">
              <SearchIcon />
            </IconButton>
        </Paper>
      </Box>
      <Box sx={{marginTop:10}}>
        {rows.length == 0 ? 
        <Box sx={{textAlign:'center'}}>
          <h2>No Results Found</h2>
          <p>Search for items you want to see</p>
        </Box> :
        <Box>
          <DataGrid rows={rows} columns={columns} sx={{width:'100%',margin:'auto',marginTop:'25px'}} disableRowSelectionOnClick/>
        </Box>}
      </Box>
    </Box>
  )
}
