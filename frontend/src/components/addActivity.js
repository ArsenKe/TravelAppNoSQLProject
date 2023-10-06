import React from "react";
import { useState } from "react";
import "./addActivity.css";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Nav from "./common/nav";

const useStyles = makeStyles((theme) => ({
  container: {
    display: "flex",
    flexWrap: "wrap",
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: 200,
  },
}));

function AddActivity() {

  //authorize
  let id = localStorage.getItem("id");
  let username = localStorage.getItem("username");
  const authorize= id ? true:false

  var date = new Date();
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [activity_date, setActivityDate] = useState("");
  const [listing_date, setListingDate] = useState(date);
  const [location, setLocation] = useState("");

  const classes = useStyles();
  let navigate = useNavigate();

  //insert activity
  function SubmitActivity(e) {
        e.preventDefault();
        axios.post("https://localhost:8080/api/activities",
        {
          
            title:title,
            description:description,
            location:location,
            activity_date:activity_date,
            guides:id
          })
        .then((response)=>{

          if(response.status === 201){
            console.log("success fully created.")
            window.location.href="/activityguide";
          }
        }).catch((e)=>{
         alert("Activity creation failed. Please try again.");
        })  

        // redirect to activityGuide
        navigate("/activityguide")
  }


  return (
<>
    {
      authorize?
      //authoried
      (<div className="AddActivity">
      <Nav authorize={authorize} username={username}/>
          <div className="AddActivityInner">
            <h2>Add Your Activity</h2>
            <form
             onSubmit={SubmitActivity}
            >
              <div class="cont">
                <div class="form-group">
                  <label for="exampleInputtitle1">Title</label>
                  <input
                    type="title"
                    class="form-control"
                    id="Inputtitle1"
                    aria-describedby="titleHelp"
                    placeholder="Enter title"
                    required={true}
                    onChange={(e) => {
                      setTitle(e.target.value);
                    }}
                  />
                </div>
                <div class="form-group">
                  <label for="exampleInputdescription1">Description</label>
                  <textarea
                    class="form-control"
                    id="description1"
                    placeholder="description"
                    rows="3"
                    required={true}
                    onChange={(e) => {
                      setDescription(e.target.value);
                    }}
                  ></textarea>
                </div>

                <TextField
                  id="date"
                  label="Reserve Date"
                  type="date"
                  margin="dense"
                  defaultValue={activity_date}
                  className={classes.textField}
                  InputLabelProps={{
                    shrink: true,
                  }}
                  value={activity_date}
                  onChange={(e) => {
                    setActivityDate(e.target.value);
                  }}
                />

                <div class="form-group">
                  <label for="exampleInputlocation1">location</label>
                  <input
                    type="location"
                    class="form-control"
                    id="Inputlocation1"
                    aria-describedby="locationHelp"
                    placeholder="Enter location"
                    required={true}
                    onChange={(e) => {
                      setLocation(e.target.value);
                    }}
                  />
                </div>
                <br />
                <br />
                <br />

                <button type="submit" class="btn btn-primary">
                  Submit
                </button>
              </div>
            </form>
          </div>



    </div>
    )
      :
      //not authoried -> redirected to home
      window.location.href="/"
    }
</>
    
    
  );
}

export default AddActivity;
