import React, { useState } from 'react';
import './App.css';

function HabitForm({ onAdd }) {
  const [habitName, setHabitName] = useState('');
  const [habitDescription, setHabitDescription] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!habitName) return;
    onAdd({ habitName, habitDescription, habitStatus: 'pending' });
    setHabitName('');
    setHabitDescription('');
  };

  return (
    <form className="habit-form" onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Habit name"
        value={habitName}
        onChange={e => setHabitName(e.target.value)}
        required
      />
      <input
        type="text"
        placeholder="Description (optional)"
        value={habitDescription}
        onChange={e => setHabitDescription(e.target.value)}
      />
      <button type="submit">Add Habit</button>
    </form>
  );
}

export default HabitForm;

