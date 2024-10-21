import React, { useState } from "react";

function App() {
  // Define state variable
  const [count, setCount] = useState(0);

  // Function to update the state
  const handleClick = () => {
    setCount(count + 1);
  };

  return (
    <div>
      <h1>Count: {count}</h1>
      <button onClick={handleClick}>Increment</button>
    </div>
  );
}

export default App;
