import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
// import IconButton from '@material-ui/core/IconButton';
// import MenuIcon from '@material-ui/icons/Menu';
import { Link } from 'react-router-dom';
import AuthService from "../../services/auth.service";
import { Box } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(1),
  },
  title: {
    flexGrow: 1,
  },
  customColor: {
    // or hex code, this is normal CSS background-color
    backgroundColor: 'primary'
  },
  customHeight: {
    minHeight: 20
  },
  fontFamily:{
    fontFamily:'Calibri'
  },
  loginItemRight:{
    marginLeft: 'auto',
  },
  offset: theme.mixins.toolbar
}));

export default function NavBar() {
  const classes = useStyles();

  const [showCreateButton, setshowCreateButton] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);



  const logOut = () => {
    AuthService.logout();
    setshowCreateButton(false)
    setCurrentUser(undefined)
  };

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
      setshowCreateButton(true);
    }
  }, []);

  return (
    //<div className={classes.root}>
      <AppBar position="static"   className={`${classes.customColor} ${classes.customHeight}`}>
        <Toolbar>
          {/* <IconButton edge="start"  color="inherit" aria-label="menu"  className={`${classes.menuButton}`}>
            <MenuIcon />
          </IconButton> */}

          <Link to='/' style={{ textDecoration: 'none', color: 'white' }}>
            <Button color="inherit" className={`${classes.fontFamily}`}>Home</Button>
          </Link>

          {/* if showCreateButton = true, show component */}
          {showCreateButton && (
            <Link to='/createIssueForm' style={{ textDecoration: 'none', color: 'white' }}>
              <Button color="inherit" className={`${classes.fontFamily}`}>Create issue</Button>
            </Link>
          )}
{/* className={classes.fontFamily} */}
          <Box className={classes.loginItemRight}>
          {currentUser ? (
            
            <div>
              <Link to='/profile' style={{ textDecoration: 'none', color: 'white' }}>
                <Button color="inherit" className={`${classes.fontFamily}`}>{currentUser.username}</Button>
              </Link>
              <Link to='/login' style={{ textDecoration: 'none', color: 'white' }}>
                <Button color="inherit" onClick={logOut} className={`${classes.fontFamily}`}>LogOut</Button>
              </Link>
            </div>
            
          ) : (
            
            <div>
              <Link to='/login' style={{ textDecoration: 'none', color: 'white' }}>
                <Button color="inherit" className={`${classes.fontFamily}`}>Login</Button>
              </Link>
              <Link to='/signup' style={{ textDecoration: 'none', color: 'white' }}>
                <Button color="inherit" className={`${classes.fontFamily}`}>Sign Up</Button>
              </Link>
            </div>
            
          )}
          </Box>
        </Toolbar>
      </AppBar>
    //</div>
  );
}
