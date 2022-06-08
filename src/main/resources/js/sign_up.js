// Scripts related to signup form

import {sendApiRequest,redirectTo} from "./requests";


const signupButton = document.getElementById("signup-button");

signupButton.addEventListener("click", function(event) {
    event.preventDefault(); // Don't submit the form automatically
    const signupData = {
        "username": document.getElementById("username").value,
        "password": document.getElementById("password").value,
        "address": document.getElementById("address").value,
        "zipcode": document.getElementById("zipcode").value,
    };
    sendApiRequest("POST", "/api/signup2", onSignupSuccess, signupData, onSignupError);
});

/**
 * This function is called when signup has been successful
 */
function onSignupSuccess() {
    redirectTo("/index.html");
}

/**
 * This function is called when signup failed
 * @param error Error message received from the backend API
 */
function onSignupError(error) {
    showFormError(error);
}



