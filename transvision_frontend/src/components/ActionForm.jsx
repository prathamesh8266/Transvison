import React, { useEffect, useState } from 'react'
import OutlinedInput from '@mui/material/OutlinedInput';
import Box from '@mui/material/Box';
import { Button, Typography } from '@mui/material';
import Modal from '@mui/material/Modal';
import CloseIcon from '@mui/icons-material/Close';
import axios from 'axios';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  borderRadius: '10px', 
  boxShadow: 24,
  pt: 2,
  px: 4,
  pb: 3,
};

const ActionForm = ({modelStatus,heading,get_data,data,admin_id}) => {

  const [name,setName] = useState(data === null ? '': data.name);
  const [edition,setEdition] = useState(data === null ? '' : data.edition);
  const [pages,setPages] = useState(data === null ? '' : data.pages);
  const [open, setOpen] = React.useState(false);

  useEffect(()=>{
    // console.log(data)
    handleOpen()
  },[])

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    modelStatus(false)
  };

  const submit_book = async (book) => {
    try {
      console.log(('http://localhost:8081/admin/addBook/').concat(admin_id))
      const response = (await axios.post(('http://localhost:8081/admin/addBook/').concat(admin_id),[book]));
      // console.log(response , 48)
      get_data();
      handleClose();
    } catch (error) {
      console.log(error);
    }
  }

  const submit_handeler = (e) => {
    e.preventDefault();
    // console.log('submit');
    let book = {};
    if (data !== null){
      book = {name,edition,pages,id:data?.id,code:data?.code}
    }else{
      book = {name,edition,pages}
    }
    submit_book(book)
    // console.log(book);
  } 
    
  return (
    <div>
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="parent-modal-title"
          aria-describedby="parent-modal-description"
          >
          <Box sx={{ ...style, width: '60%' }}>
            <Box sx={{marginLeft:'99%',cursor:'pointer'}} onClick={handleClose}>
          <CloseIcon sx={{marginLeft:"auto"}} />
            </Box>
          <Typography component="h2" variant="h3" sx={{textAlign:'center',margin:'15px'}}>
            {heading}
          </Typography>
          <form sx={{ width: '25ch' }} onSubmit={submit_handeler} style={{display:'flex',flexDirection:"column",justifyContent:'center',alignItems:'center'}}>
            <OutlinedInput placeholder="Please enter text" required onInput={e=>setName(e.target.value)} sx={{width:'80%',minWidth:300,margin:2}} value={name}/>
            <OutlinedInput placeholder="edition" type='number' required onInput={e=>setEdition(e.target.value)} sx={{width:'80%',minWidth:300,margin:2}} value={edition}/>
            <OutlinedInput placeholder="pages" type='number' required onInput={e=>setPages(e.target.value)} sx={{width:'80%',minWidth:300,margin:2}} value={pages}/>
            <Button type="submit" size='large' variant="contained" sx={{margin:4}}>Submit</Button>
          </form>
          </Box>
        </Modal>
  </div>
  )
}

export default ActionForm