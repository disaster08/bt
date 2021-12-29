import React, { useState, useEffect } from "react";
import AuthService from "../../services/auth.service";
import Table from "../issueTable/Table";
import issueServices from "../../services/issue.service";

const BoardUser = () => {
  
  const [reportersIssues, setReportersIssues] = useState([]);

  useEffect(() => {
    const currentUser = AuthService.getCurrentUser();
    issueServices.getReporterIssues(currentUser.id).then(
      (response) => {        
        setReportersIssues(response.data)
      },
      (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setReportersIssues(_content)
      }
    );

  }, []);
 
  return (
    <div>
    {/* pass an array of object to table component.  */}
    <Table rows={reportersIssues}/> 
    </div>
  );
};

export default BoardUser;