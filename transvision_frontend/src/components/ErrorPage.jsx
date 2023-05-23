import { Typography } from "@mui/material";
import { useRouteError } from "react-router-dom";

export default function ErrorPage() {
  const error = useRouteError();
  console.error(error);

  return (
    <div id="error-page" style={{display:'flex',justifyContent:'center',alignItems:'center',flexDirection:'column'}}>
      <Typography component="h2" variant="h3" sx={{marginTop:15}}>Oops!</Typography>
      <Typography component="p" variant="h4" sx={{marginTop:7}}>Sorry, an unexpected error has occurred.</Typography>
      <Typography component="p" variant="p" sx={{marginTop:5}}>
        <i>{error.statusText || error.message}</i>
      </Typography>
    </div>
  );
}