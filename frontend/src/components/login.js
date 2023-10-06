import React from "react";
import imgTravel from "../travel2.png";
import { useState } from "react";
import axios from "axios";


function Login() {
  let id = localStorage.getItem("id");

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  //submit login information
  function SubmitLogin(e) {
    e.preventDefault();

  //backend call for login
    axios
      .post("https://localhost:8080/api/users/login", {
        username: username,
        password: password,
      })
      .then((response) => {
        if (response.status === 200) {
          let username = response.data.username;
          let email = response.data.email;
          let userType = response.data.userType;
          let id = response.data.id;

          //store logged users information
          localStorage.setItem("userType", userType);
          localStorage.setItem("username", username);
          localStorage.setItem("email", email);
          localStorage.setItem("id", id);

          if (userType === "Guide") {
            window.location.href = "/addactivity";
          } else if (userType === "Tourist") {
            window.location.href = "/activitytourist";
          } else {
            window.location.href = "/";
          }
        } else {
          alert("Ïnvalid Username or password");
        }
      })
      .catch((e) => {
        alert("Entered UserName or Password Invalid.");
      });
  }

  return (
    <div className="login" style={{ height: 700, backgroundColor: "#F8F8F8" }}>
      <nav className="navbars">
        <div className="subnav1">
          <span className="logoname">Travel</span>
          <span className="logonameappend">Guider</span>
        </div>
      </nav>

      <div class="container">
        <div class="row">
          <div class="col">
            <img
              src={imgTravel}
              alt="..."
              style={{ width: "86%", alignSelf: "center", marginTop: 60 }}
            />
          </div>
          <div class="col">
            <div
              class="card"
              style={{ width: "100%", alignSelf: "center", marginTop: 100 }}
            >
              <div class="card-body">
                <h2>Welcome to Travel</h2>
                <br />
                <br />
                <form onSubmit={SubmitLogin}>
                  <div class="cont">
                    <div class="form-group">
                      <label for="exampleInputusername1">UserName</label>
                      <input
                        type="username"
                        class="form-control"
                        id="exampleInputusername1"
                        aria-describedby="usernameHelp"
                        placeholder="Enter username"
                        required={true}
                        onChange={(e) => {
                          setUsername(e.target.value);
                        }}
                      />
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Password</label>
                      <input
                        type="password"
                        class="form-control"
                        id="exampleInputPassword1"
                        placeholder="Password"
                        required={true}
                        onChange={(e) => {
                          setPassword(e.target.value);
                        }}
                      />
                    </div>
                    <button type="submit" class="btn btn-primary">
                      Login
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
