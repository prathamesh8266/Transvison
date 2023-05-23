import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
// import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import axios from 'axios';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Navigate , Link } from 'react-router-dom';

function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="#">
      Library Management System
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const defaultTheme = createTheme();

export default function SignIn() {

  const[user,setUser] = React.useState('')
    const [isAdminValid,setIsAdminValid] = React.useState(false)
    const [isGeneralUserValid,setIsGeneralUserValid] = React.useState(false)
    const [userId,setUserId] = React.useState('-1-1')
    const [userSelector,setUserSelector] = React.useState(false);

    const set_user_as_general = () =>{
      setUserSelector(true)
      setUser('general');
    }
  
    const set_user_as_admin = () => {
      setUserSelector(true)
      setUser('admin');
    }

    // handles login, password and user data
  const handleSubmit = async(event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('name'),
      password: data.get('password'),
      user
    });
    try {
      if (user === 'admin'){
        const response = await axios.post('http://localhost:8081/admin/getAdmin',{name:data.get('name'),password:data.get('password')});
        console.log(response.data)
        setUserSelector(true)
        if(response.data.is_valid){
          setUserId(response.data.response)
          setIsAdminValid(true)
        }else{
          setUserId('invalid')
          setIsAdminValid(false)
        }
      }else if (user === 'general'){
        const response = await axios.post('http://localhost:8081/general/getGeneralUser',{name:data.get('name'),password:data.get('password')});
        console.log(response.data)
        if(response.data.is_valid){
          setIsGeneralUserValid(true)
          setUserId(response.data.response)
        }else{
          setUserId('invalid')
          setIsGeneralUserValid(false)
        }
      }else{
        setUserSelector(false)
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <ThemeProvider theme={defaultTheme}>
      {isAdminValid && <Navigate to={('/admin/').concat(userId)}/>}
      {isGeneralUserValid && <Navigate to={('/general/').concat(userId)}/>}
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 4,
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Box sx={{width:'100%',margin:'20px'}}>
          <Typography component="h1" variant="h3" sx={{textAlign:'center',marginBottom:'60px'}}>
                          Library Management System
            </Typography>
            {(!isAdminValid || !isGeneralUserValid) && userId === 'invalid' ? <Typography component="p" variant="p" sx={{textAlign:'center',marginBottom:'15px',color:'red'}}>
                          Invalid Credentials
            </Typography> : null}
            {(!userSelector)  ? <Typography component="p" variant="p" sx={{textAlign:'center',marginBottom:'15px',color:'red'}}>
                          Select General or Admin user
            </Typography> : null}
            <Typography component="p" variant="h5" sx={{textAlign:'center',marginBottom:'15px'}}>
                Log in as
            </Typography>
            <Box
                sx={{display:'flex',justifyContent:'space-between'}}>
                <Button variant={user === 'general' ? 'contained' : "outlined"} size="large" sx={{width:'170px'}} onClick={set_user_as_general}>
                    GENERAL USER
                </Button>
                <Button variant={user === 'admin' ? 'contained' : "outlined"} size="large" sx={{widows:'170px'}} onClick={set_user_as_admin}>
                    ADMIN USER
                </Button>
            </Box>
          </Box>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="name"
              name="name"
              autoComplete="name"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item xs>
              </Grid>
              <Grid item>
                <Link to="/signup" variant="body2">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 8, mb: 4 }} />
      </Container>
    </ThemeProvider>
  );
}