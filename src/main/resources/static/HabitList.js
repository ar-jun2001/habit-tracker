import React from 'react';
import './App.css';

function HabitList({ habits, onUpdateStatus, onDelete }) {
  if (!habits.length) return <p>No habits found.</p>;
  return (
    <div className="habit-list">
      {habits.map(habit => (
        <div className="habit-card" key={habit.id}>
          <div>
            <h3>{habit.habitName}</h3>
            <p>{habit.habitDescription}</p>
            <span className={`status ${habit.habitStatus}`}>{habit.habitStatus}</span>
          </div>
          <div className="habit-actions">
            <button onClick={() => onUpdateStatus(habit.id, habit.habitStatus === 'completed' ? 'pending' : 'completed')}>
              Mark as {habit.habitStatus === 'completed' ? 'Pending' : 'Completed'}
            </button>
            <button className="delete-btn" onClick={() => onDelete(habit.id)}>Delete</button>
          </div>
        </div>
      ))}
    </div>
  );
}

export default HabitList;

