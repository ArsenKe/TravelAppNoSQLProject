import React from "react";
import {Link} from "react-router-dom"
import AccountCircleIcon from "@material-ui/icons/AccountCircle";


function Nav(props) {
    const {authorize, username}= props

    //LogOut function
    const logout=()=>{
        localStorage.setItem("userType", "");
        localStorage.setItem("username", "");
        localStorage.setItem("email", "");
        localStorage.setItem("id","");
        window.location.href="/";
    }

  return (
    <>
    <nav className="navbars">
          <div className="subnav1">
            <span className="logoname">Travel</span>
            <span className="logonameappend">Guider</span>
          </div>
          <div className="subnav2">
            {/* <button type="button" id="btns" class="btn btn-light">SignUp</button> */}

            {
                authorize? 
                (
                    <div class="dropdown">
                    <div class='row' type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <AccountCircleIcon style={{ fontSize: 40 }} />
                        <span style={{fontSize:22, marginLeft:10}}>{username}</span>
                    </div> 
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="#" onClick={()=>logout()}>Log Out</a></li>
                    </ul>
                    </div>
                )
                :
                ( 
                <Link to="/login">
                <button type="button" id="btns" class="btn btn-light">
                  Login
                </button>
                </Link>
                )
            }
          </div>
    </nav>
    </>
  );
}

export default Nav;
