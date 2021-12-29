import './App.css';
import IssueForm from './components/createIssueForm/IssueForm';
import NavBar from './components/navbar/NavBar';
import HomePage from './components/remoteDataPages/HomePage';
import Register from './components/auth/Register';
import Login from './components/auth/Login';
import Profile from './components/auth/Profile';
import BoardAdmin from './components/remoteDataPages/BoardAdmin';
import BoardUser from './components/remoteDataPages/BoardUser';
import BoardModerator from './components/remoteDataPages/BoardModerator';


import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

function App() {
  return (
    <Router>
      <NavBar/>
    <Switch>            
      <Route path='/' exact component={HomePage} />
      <Route path='/createIssueForm' component={IssueForm} />
      <Route path='/signup' component={Register} />
      <Route exact path="/profile" component={Profile} />
      <Route path='/login' component={Login} />
      <Route path='/admin' component={BoardAdmin} />
      <Route path='/user' component={BoardUser} />
      <Route path='/moderator' component={BoardModerator} />
      </Switch>
    </Router>
  );
}

export default App;
