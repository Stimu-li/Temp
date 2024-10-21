// Function to display squares of the first 10 numbers
function displaySquares() {
  for (let i = 1; i <= 10; i++) {
    console.log(`Square of ${i}: ${i * i}`);
  }
}

// Function to reverse a number
function reverseNumber(num) {
  const reversed = num.toString().split("").reverse().join("");
  return Number(reversed);
}

// Call the functions
displaySquares();
const numberToReverse = 12345;
console.log(
  `Reversed number of ${numberToReverse}: ${reverseNumber(numberToReverse)}`
);
