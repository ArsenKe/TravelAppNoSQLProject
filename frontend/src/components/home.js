import React from "react";
import "./home.css";
import {Link} from "react-router-dom"
import { makeStyles } from "@material-ui/core/styles";
import Nav from "./common/nav";
import axios from "axios";
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';


function Alert(props){
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
    '& > * + *': {
      marginTop: theme.spacing(2),
    },
  },
}));

function Home() {
  
  //authorize
  let id = localStorage.getItem("id");
  let username = localStorage.getItem("username");
  const authorize= id ? true:false

  const classes = useStyles();
  const [fillopen, setFillOpen] = React.useState(false);
  const [migraopen, setMigraOpen] = React.useState(false);
  
 
  const fillData=()=>{
  //backend call for fill data
    axios.get("https://localhost:8080/api/utils/generate")
    .then((response)=>{
      if(response.status === 200){
        console.log("success data filling")
        setFillOpen(true);
      }
    }).catch((e)=>{
     alert("Data filling failed!");
    })  
  }

  const Migrate=()=>{
    //backend call for databse migration
    axios.get("https://localhost:8080/api/utils/migrate")
    .then((response)=>{
      if(response.status === 200){
        console.log("success Data Migration")
        setMigraOpen(true);
      }
    }).catch((e)=>{
     alert("DataBase Migration failed!");
    }) 
  }

  //close button of success notifier
  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setFillOpen(false)
    setMigraOpen(false)
  };

  return (
    <div className="home">
      <div className="homeInner">
      <Nav authorize={authorize} username={username}/>

        <div className="bodyofhome">
          <div className="innerbodyofhome">
                <h1 className="title">WELCOME TO TRAVEL GUIDER</h1>
                <p>This is a platform where local guides can offer their
                services as a guided activity for travelers, who visit a certain location at a certain time. Add new activities now!</p>
                <p>Add activities as a guide</p>
                {
                  id ?(
                    <Link to="/addactivity">
                <button type="button" class="btn btn-primary">ADD ACTIVITIY</button>
                </Link>
                  ):(
                    <Link to="/login">
                <button type="button" class="btn btn-primary">ADD ACTIVITIY</button>
                </Link>
                  )
                }
                
                
          </div>
        </div>
        <div className="assignement">
          <div className="innerAssignment">
                <h1 className="title">DATABASE ACTIVITIY</h1>
                <br/>
                <br/>
                <div class="row">

                  {/* filldata */}
                  <div class="col">
                  <div class="card">
                    <div class="card-body">
                      <h3 class="card-title">Data Filling</h3>
                      <p class="card-text">Database should be filled with data to system functions looks better. Click on data filling to run the Data Filling scripts.</p>
                      <button type="button" class="btn btn-primary" onClick={()=>(fillData())}>Fill Data</button>
                    </div>
                  </div>
                  </div>
                  
                  {/* migrate */}
                  <div class="col">
                  <div class="card">
                    <div class="card-body">
                      <h3 class="card-title">Data Migration</h3>
                      <p class="card-text">Data in the postgreSQL will be migrated to MongoDB database. Click here to start Migration</p>
                <button type="button" class="btn btn-primary" onClick={()=>(Migrate())}>Migrate</button>
                    </div>
                  </div>
                  </div>
                  
                </div>
          </div>
        </div>


      {/* success notifiers */}
      <Snackbar open={fillopen} autoHideDuration={6000} onClose={handleClose}>
        <Alert onClose={handleClose} severity="success">
          Data Filling Success!
        </Alert>
      </Snackbar>
      <Snackbar open={migraopen} autoHideDuration={6000} onClose={handleClose}>
        <Alert onClose={handleClose} severity="success">
          Database Migration Success!
        </Alert>
      </Snackbar>

        <div className="footer">
          
        </div>
      </div>
    </div>
  );
}

export default Home;
