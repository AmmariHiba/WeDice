// homepage.js
document.addEventListener("DOMContentLoaded", function() {
    // Add dynamic dice to the background
    for (let i = 0; i < 5; i++) {
        createDice();
    }
});

function createDice() {
    const dice = document.createElement('div');
    dice.className = 'dice';
    dice.textContent = getRandomDiceFace();
    document.body.appendChild(dice);

    // Randomize animation delay
    dice.style.animationDelay = `${Math.random() * 2}s`;

    // Randomize dice position
    dice.style.left = `${Math.random() * (window.innerWidth - 100)}px`;
}

function getRandomDiceFace() {
    return Math.floor(Math.random() * 6) + 1;
}
// homepage.js

document.addEventListener('DOMContentLoaded', (event) => {
    var modal = document.getElementById("gameInfoModal");
    var btn = document.getElementById("gameInfoButton");
    var span = document.getElementsByClassName("close")[0];

    btn.onclick = function() {
        modal.style.display = "block";
    }

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});
