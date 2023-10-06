import React, { useEffect } from "react";
import "./activities.css";
import { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import Nav from "./common/nav";


function ActivitiesGuide() {

    //authorize
    let id = localStorage.getItem("id");
    let username = localStorage.getItem("username");
    const authorize= id ? true:false

  const [myActivityList, setMyActivityList] = useState([]);


  useEffect(() => {
    
  //backend call to get MyActivity data
    axios
      .get("https://localhost:8080/api/activities/" + id)
      .then((response) => {
        if (response.status === 200) {
          setMyActivityList(response.data);
        }
      })
      .catch((e) => {
        alert("Activity creation failed. Please try again.");
      });
  }, []);


  return (
    <>
    {
      authorize?
      //authoried
      (<div className="Activities">
      <Nav authorize={authorize} username={username}/>
      <div className="ActivitiesInner">
        <div class="row">
          <h2 className="title">Activities</h2>
          <Link to="/addactivity">
            <button id="addbutton" type="button" class="btn btn-success">
              Add +
            </button>
          </Link>
        </div>

        <div className="tableActivities">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page">
                My Activities
              </a>
            </li>
          </ul>

          <div class="card" style={{ flex: 1, borderTopColor: "white" }}>
            <div class="card-body">

            {/* my activities table */}
              <table class="table table-hover" style={{ flex: 1 }}>
                <thead>
                  <tr style={{ backgroundColor: "#d9d9d9" }}>
                    <th scope="col">Number</th>
                    <th scope="col">Title</th>
                    <th scope="col">Description</th>
                    <th scope="col">Location</th>
                    <th scope="col">Activity Date</th>
                  </tr>
                </thead>
                <tbody>
                  {myActivityList.map((item) => (
                    <tr key={item.id}>
                      <th scope="row">{item.id}</th>
                      <td>{item.title}</td>
                      <td>{item.description}</td>
                      <td>{item.location}</td>
                      <td>{item.activity_date}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
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

export default ActivitiesGuide;
