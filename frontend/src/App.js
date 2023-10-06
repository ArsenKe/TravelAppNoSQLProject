import "./App.css";
import "./components/login";
import Login from "./components/login";
import Home from "./components/home";
import AddActivity from "./components/addActivity";
import ActivitiesGuide from "./components/activitiesGuide";
import ActivitiesTourist from "./components/activitiesTourist";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App">
      
      {/* routes for pages */}
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<Home />}/>
          <Route path="/login" element={<Login />}/>
          <Route path="/addactivity" element={<AddActivity />}/>
          <Route path="/activityguide" element={<ActivitiesGuide />}/>
          <Route path="/activitytourist" element={<ActivitiesTourist />}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
