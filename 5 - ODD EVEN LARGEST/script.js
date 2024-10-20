function findLargest(num1, num2, num3) {
    return Math.max(num1, num2, num3);
}

function isOddOrEven(num) {
    return num % 2 === 0 ? "Even" : "Odd";
}

// Example usage
const largest = findLargest(10, 25, 5);
const numberType = isOddOrEven(25);
console.log(`The largest number is ${largest} and it is ${numberType}.`);