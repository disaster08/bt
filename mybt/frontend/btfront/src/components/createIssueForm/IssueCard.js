import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import CardActions from '@material-ui/core/CardActions';
import Button from '@material-ui/core/Button';
import {Link} from 'react-router-dom';


const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
      flexWrap: 'wrap',
      justifyContent: 'center',
      alignItems: 'center',
      '& > *': {
        margin: theme.spacing(10),
        width: theme.spacing(50),
        height: theme.spacing(50),
      },

    },

    paperDataStyle:{
       paddingLeft:'50px',
       paddingTop: '10px'
    
    },
    actionButton:{
        marginBottom: 12,
        fontSize: 14,
    }

  }));
  
  export default function IssueCard(props) {
    const classes = useStyles();
  
    return (
      <div className={classes.root}>
          <Paper elevation={3} >
            <div className={classes.paperDataStyle}>
            <p>Summary: {props.formData[0]}</p>
            <p>IssueType: {props.formData[1]}</p>
            <p>description: {props.formData[2]}</p>
            </div>
      <CardActions className={classes.actionButton}> 
      <Link to='/' style={{ textDecoration: 'none' }}>
        <Button size="small">OK</Button>
        </Link>
      </CardActions>
         </Paper>
      </div>
    );
  }






