import React, { Component } from "react";
import "./App.css";
import AppNavbar from "./AppNavBar";
import { Link } from "react-router-dom";
import { Button, Container } from "reactstrap";

class Home extends Component {
  render() {
    function refreshPage() {
      window.location.reload();
    }
    return (
      <div>
        <AppNavbar />
        <Container fluid>
          <Button color="link" onClick={refreshPage}>
            <Link to="/employees">Employees</Link>
          </Button>
        </Container>
      </div>
    );
  }
}
export default Home;
