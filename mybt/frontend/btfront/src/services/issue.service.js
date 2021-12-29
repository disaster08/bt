import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8081/api/issues/";

const createIssue = (summary, issueType, priority, description, reporter) => {
    return axios
        .post(API_URL,
            {
                summary,
                issueType,
                priority,
                description,
                reporter
            },
            { headers: authHeader() }
        )
        .then((response) => {
            return response.data;
        });
};

const getReporterIssues = (reporterId) => {
    return axios.get(API_URL + "reporterIssues/" + reporterId, { headers: authHeader() });
  };

const issueServices = {
    createIssue,
    getReporterIssues
};

export default issueServices