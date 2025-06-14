import React, { useEffect, useState } from 'react';
import HabitList from './HabitList';
import HabitForm from './HabitForm';
import './App.css';

function App() {
  const [habits, setHabits] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchHabits = async () => {
    setLoading(true);
    const res = await fetch('/api/habits-tracker/getAllHabits');
    const data = await res.json();
    setHabits(data);
    setLoading(false);
  };

  useEffect(() => {
    fetchHabits();
  }, []);

  const addHabit = async (habit) => {
    await fetch('/api/habits-tracker/save', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(habit),
    });
    fetchHabits();
  };

  const updateHabitStatus = async (id, status) => {
    await fetch('/api/habits-tracker/updateHabitStatus', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id, habitStatus: status }),
    });
    fetchHabits();
  };

  const deleteHabit = async (id) => {
    await fetch('/api/habits-tracker/deleteHabit', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id }),
    });
    fetchHabits();
  };

  return (
    <div className="app-container">
      <h1>Habit Tracker</h1>
      <HabitForm onAdd={addHabit} />
      {loading ? <p>Loading...</p> : (
        <HabitList habits={habits} onUpdateStatus={updateHabitStatus} onDelete={deleteHabit} />
      )}
    </div>
  );
}

export default App;

