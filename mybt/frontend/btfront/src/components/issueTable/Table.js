import React from 'react';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const StyledTableCell = withStyles((theme) => ({
  head: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  body: {
    fontSize: 14,
  },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
  root: {
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
  },
}))(TableRow);

//https://run.mocky.io/v3/54b0360d-5218-4653-9039-bd457b763eca
// const rows = [
//   {summary:"something broken", description:"mouse is dead", priority:"high", reporter:"admin"},
//   {summary:"something broken2", description:"mouse is dead2", priority:"low", reporter:"bob"},
//   {summary:"something broken3", description:"mouse is dead3", priority:"Medium", reporter:"Steve"},
//   {summary:"something broken4", description:"mouse is dead4", priority:"low", reporter:"Martin"}
// ]

const useStyles = makeStyles({
  table: {
    minWidth: 700,
  },
});

export default function CustomizedTables(props) {
  const classes = useStyles();

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Issue Key</StyledTableCell>
            <StyledTableCell align="right">Summary</StyledTableCell>
            <StyledTableCell align="right">Description</StyledTableCell>
            <StyledTableCell align="right">Isuue type</StyledTableCell>
            <StyledTableCell align="right">Priority</StyledTableCell>
            <StyledTableCell align="right">Status</StyledTableCell>
            <StyledTableCell align="right">Reporter</StyledTableCell>       
            <StyledTableCell align="right">Assignee</StyledTableCell>     
          </TableRow>
        </TableHead>
        <TableBody>
          {/* props passed from BoardUser component as an array of objects */}
          {props.rows.map((row) => (
            <StyledTableRow key={row.issueId}>
              <StyledTableCell component="th" scope="row">
                {row.issueId}
              </StyledTableCell>
              <StyledTableCell align="right">{row.summary}</StyledTableCell>
              <StyledTableCell align="right">{row.description}</StyledTableCell>
              <StyledTableCell align="right">{row.issueType}</StyledTableCell>
              <StyledTableCell align="right">{row.priority}</StyledTableCell>
              <StyledTableCell align="right">{row.status}</StyledTableCell>
              <StyledTableCell align="right">{row.reporterName}</StyledTableCell>     
              <StyledTableCell align="right">{row.assigneeName}</StyledTableCell>         
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
