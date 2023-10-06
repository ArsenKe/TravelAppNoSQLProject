import React from "react";
import "./activities.css";
import { useState } from "react";
import Nav from "./common/nav";


function ActivitiesTourist() {

  //authorize
  let id = localStorage.getItem("id");
  let username = localStorage.getItem("username");
  const authorize= id ? true:false

  const [selectedtab, setSelectedtab]=useState(1);
  const [myActivityList, setMyActivityList] = useState([
    { id: 1, title: "Activity1", description: "activity desc disc descript" },
    { id: 2, title: "Activity2", description: "activity desc disc descript" },
    { id: 3, title: "Activity3", description: "activity desc disc descript" },

  ]);
  const [allActivityList, setAllActivityList] = useState([
    { id: 1, title: "hello Activity1", description: "activity desc disc descript" },
    { id: 2, title: "hello Activity2", description: "activity desc disc descript" },
    { id: 3, title: "hello Activity3", description: "activity desc disc descript" },
    { id: 4, title: "hello Activity4", description: "activity desc disc descript" },
    { id: 5, title: "hello Activity5", description: "activity desc disc descript" },
  ]);

  function booking(){
      //booing table fill
      //redirected to my activities


  }

  return (
<>{
  authorize?
  (<div className="Activities">
  <Nav authorize={authorize} username={username}/>
  <div className="ActivitiesInner">
    <div class="row">
      <h2 className="title">Activities</h2>
    </div>

    <div className="tableActivities">
    <nav>
      <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <button class={selectedtab===1?"nav-link active":"nav-link"} id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-MyActivities" type="button" role="tab" aria-controls="nav-MyActivities" aria-selected={selectedtab===1?"true":"false"} onClick={()=>{setSelectedtab(1)}}>My Activities</button>
        <button class={selectedtab===2?"nav-link active":"nav-link"} id="nav-AllActivities-tab" data-bs-toggle="tab" data-bs-target="#nav-AllActivities" type="button" role="tab" aria-controls="nav-AllActivities" aria-selected={selectedtab===2?"true":"false"} onClick={()=>{setSelectedtab(2)}}>All Activities</button>
      </div>
    </nav>

{selectedtab===1?(<div className="tab1">
      <div class="card" style={{ flex: 1, borderTopColor: "white" }}>
        <div class="card-body">
          <table class="table table-hover" style={{ flex: 1 }}>
            <thead>
              <tr style={{ backgroundColor: "#d9d9d9" }}>
                <th scope="col">Number</th>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Handle</th>
              </tr>
            </thead>
            <tbody>
              {myActivityList.map((item) => (
                <tr key={item.id}>
                  <th scope="row">{item.id}</th>
                  <td>{item.title}</td>
                  <td>{item.description}</td>
                  <td>@mdo</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      </div>):(<div></div>)}


      {selectedtab===2?(<div className="tab2">
      <div class="card" style={{ flex: 1, borderTopColor: "white" }}>
        <div class="card-body">
          <table class="table table-hover" style={{ flex: 1 }}>
            <thead>
              <tr style={{ backgroundColor: "#d9d9d9" }}>
                <th scope="col">Number</th>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Description</th>
                <th scope="col">Book Activities</th>
              </tr>
            </thead>
            <tbody>
              {allActivityList.map((item) => (
                <tr key={item.id}>
                  <th scope="row">{item.id}</th>
                  <td>{item.title}</td>
                  <td>{item.description}</td>
                  <td>{item.description}</td>
                  <td><button type="button" class="btn btn-primary"onClick={()=>booking()}>Book</button></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      </div>):(<div></div>)}

    </div>
  </div>
</div>)
:window.location.href="/"
}
</>

    
  );
}

export default ActivitiesTourist;
