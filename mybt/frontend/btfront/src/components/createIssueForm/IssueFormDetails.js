import React from 'react';
import TextField from '@material-ui/core/TextField';
import { makeStyles } from '@material-ui/core/styles';
import {Link} from 'react-router-dom';
import Button from '@material-ui/core/Button';
import AuthService from "../../services/auth.service";
import issueService from "../../services/issue.service";


const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    padding: theme.spacing(10),
'& .MuiTextField-root': {
      margin: theme.spacing(1),
      width: '300px',
    },
    '& .MuiButtonBase-root': {
      margin: theme.spacing(2),
    },


  },

}));

const issueTypes = [
  {
    value: 'BUG',
    label: 'bug',
  },
  {
    value: 'HELP DESK',
    label: 'help desk',
  },
  {
    value: 'REQUEST',
    label: 'request',
  },

]; 

const priorityOption = [
  {
    value: 'HIGH',
    label: 'high',
  },
  {
    value: 'MEDIUM',
    label: 'medium',
  },
  {
    value: 'LOW',
    label: 'low',
  },

]; 



export default function IssueFormDetails(props) {

      
  const classes = useStyles();
  

  // create state variables for each input
  const [summary, setSummary] = React.useState('');
  const [issueType, setIssueType] = React.useState('BUG');
  const [priority, setPriority] = React.useState('LOW');
  const [description, setDescription] = React.useState('');


  const handleSubmit = e => {
    props.switchToCard()
    props.sendFormData(arrOfData)
    e.preventDefault();
    const currentUser = AuthService.getCurrentUser();
   
    const issueData = {summary: summary, issueType: issueType, priority:priority, description:description, reporter:currentUser.id};
    
    issueService.createIssue(issueData.summary, issueData.issueType, issueData.priority, issueData.description, issueData.reporter).then(
      //We don't need to route to profile page after user has signed in. Instead create separate profile page.
      () => { 
        //props.history.push("/");
        //window.location.reload();
      },
      (error) => {
        console.log(error)      
      }
    );

  };   

  var arrOfData = [summary, issueType, description]

  return (
   
    <form className={classes.root} onSubmit={handleSubmit}>
         
        <TextField
          id="outlined-helperText"
          label="Summary"
          variant="outlined"
          value={summary}
          onChange={e => setSummary(e.target.value)}
        />
          <TextField
          id="outlined-select-currency-native"
          select
          label="Issue type"
          value={issueType}
          onChange={e => setIssueType(e.target.value)}
          SelectProps={{
            native: true,
          }}
          helperText="Please select your type"
          variant="outlined"
        >
          {issueTypes.map((option) => (
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
          ))}
        </TextField>

        <TextField
          id="outlined-select-currency-native"
          select
          label="Priority"
          value={priority}
          onChange={e => setPriority(e.target.value)}
          SelectProps={{
            native: true,
          }}
          helperText="Please select priority"
          variant="outlined"
        >
          {priorityOption.map((option) => (
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
          ))}
        </TextField>

         <TextField
          id="outlined-multiline-static"
          label="Description"
          multiline
          rows={5}          
          variant="outlined"
          helperText="Enter detailed description"
          value={description}
          onChange={e => setDescription(e.target.value)}
        />
        
        <div>
        <Link to='/' style={{ textDecoration: 'none' }}>
        <Button variant="contained">       
          Cancel         
        </Button>
        </Link>
        
        <Button type="submit" variant="contained" color="primary">
          Create
        </Button>
        
      </div>
    </form>
   
  );
}
