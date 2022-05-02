import React, { Component } from "react";
import { Button, ButtonGroup, Container, Table } from "reactstrap";
import AppNavbar from "./AppNavBar";
import { Link } from "react-router-dom";

class EmployeeList extends Component {
  constructor(props) {
    super(props);
    this.state = { employees: [] };
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    fetch("/employees")
      .then((response) => response.json())
      .then((data) => this.setState({ employees: data }));
  }
  async remove(id) {
    await fetch(`/employees/${id}`, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    }).then(() => {
      let updatedEmployees = [...this.state.employees].filter(
        (i) => i.id !== id
      );
      this.setState({ employees: updatedEmployees });
    });
  }

  render() {
    const { employees, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }
    function refreshPage() {
      window.location.reload();
    }
    const employeeList = employees.map((employee) => {
      return (
        <tr key={employee.id}>
          <td style={{ whiteSpace: "nowrap" }}>{employee.firstName}</td>
          <td style={{ whiteSpace: "nowrap" }}>{employee.lastName}</td>
          <td>{employee.description}</td>
          <td>
            <ButtonGroup>
              <Link
                to={"/employees/" + employee.id}
                className="btn btn-primary"
              >
                <Button size="sm" color="danger" onClick={refreshPage}>
                  Edit
                </Button>
              </Link>
              <Button
                size="sm"
                color="danger"
                onClick={() => this.remove(employee.id)}
              >
                Delete
              </Button>
            </ButtonGroup>
          </td>
        </tr>
      );
    });

    return (
      <div>
        <AppNavbar />
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/employees/new">
              Add Employee
            </Button>
          </div>
          <h3>Employees</h3>
          <Table className="mt-4">
            <thead>
              <tr>
                <th width="30%">First Name</th>
                <th width="30%">Last Name</th>
                <th width="30%">Description</th>
                <th width="40%">Actions</th>
              </tr>
            </thead>
            <tbody>{employeeList}</tbody>
          </Table>
        </Container>
      </div>
    );
  }
}
export default EmployeeList;
