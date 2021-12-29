import React, { useState } from 'react'
import IssueFormDetails from '../createIssueForm/IssueFormDetails';
import IssueCard from '../createIssueForm/IssueCard';



export default function IssueForm() {

        const [step, setStep] = useState(1);
        const [summary, setSummary]=useState("")
        const [issueType, setIssueType]=useState("")
        const [description, setDescription]=useState("")

        
        
        function callBackFromIssueFormDetails(IssueFormDetailsData){
            setSummary(IssueFormDetailsData[0])
            setIssueType(IssueFormDetailsData[1])
            setDescription(IssueFormDetailsData[2])
            //console.log("got data from Form Component: " + childData)
        }
        var arrFormData = [summary, issueType, description]

        function setStepToSwitchComponents(){
            setStep(step+1)
        }

    switch (step) {
        case 1:
            return (
                <IssueFormDetails
                switchToCard={setStepToSwitchComponents}//when issueFromDetails component submit the create button, it start handleSubmit function and trigger switchToCard function. 
                sendFormData={callBackFromIssueFormDetails} //same here. Additionally issueFromDetails component sends an array "arrOfData"
                />
            )
        case 2:
            return (
                <IssueCard
                formData={arrFormData} //IssueCard component resives issue details and display it on card
                />
            )
        default:
            return (
                <h1>Something is wrong</h1>
            )
    }
}




