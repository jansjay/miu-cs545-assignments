import { BrowserRouter } from "react-router-dom";
import Navigator from "./components/Navigator";
import Dashboard from "./pages/Dashboard";

function App() {
  return (
    <>
      <BrowserRouter>
        <Navigator />
        <div className="App">
          <header className="w3-container w3-center w3-padding-32">
            <h1>
              <b>CS-545 - Assignments : Single Page Applications with React</b>
            </h1>
          </header>
          <Dashboard />
        </div>
      </BrowserRouter>
    </>
  );
}

export default App;
