// gamepage.js
document.addEventListener("DOMContentLoaded", function() {
    // Add animation to dice result messages
    const messages = document.querySelectorAll("p");
    messages.forEach(message => {
        message.classList.add("dice-result");
    });

    // Focus on the input field for a better user experience
    const diceInput = document.getElementById("numeroDe");
    if (diceInput) {
        diceInput.focus();
    }
});
